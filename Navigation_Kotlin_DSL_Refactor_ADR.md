# Title

Navigation Kotlin DSL Refactor ADR

## Status

In Progress

## Problem

As previously mentioned in
the [Navigation_Simplification_and_Delete_Router_ADR](https://github.com/Hinge/android/blob/main/docs/Architecture_Decision_Records/Navigation_Simplification_and_Delete_Router_ADR.md)
,
Android team discussed about deprecating Router and use Navigation Component directly in UI features.

Other than
the [issues](https://github.com/Hinge/android/blob/main/docs/Architecture_Decision_Records/Navigation_Simplification_and_Delete_Router_ADR.md#problem)
mentioned for using `Router` as the current navigation approach, It has limitations on scalability as Client app gets
bigger, the centralized `graph.xml` currently contains all the fragment destinations for the app will get bigger and
bigger and it would be hard to maintain.

## Solution

To use Navigation Component directly in UI features in a multi-module Android project, Navigation Component
supports [building the graph programmatically from Kotlin code](https://developer.android.com/guide/navigation/navigation-kotlin-dsl)
. When building the navGraph from Kotlin code, `graph.xml` file is not needed, Instead, One can define the String Route
for destination fragment in each module by below.
See [DiscoverNavBuilder](https://github.com/Hinge/android/blob/5493c87c4868b1a388058fa0d010c47691f3e995/features/home/discover/src/main/java/co/hinge/discover/nav/DiscoverNavBuilder.kt)
for example.

```
        navGraphBuilder.apply {
            fragment<FragmentA>(route = "fragmentARoute?fragmentAArg=$fragmentAArg") {
                label = "fragment A"
                argument("fragmentAArg") {
                    type = NavType.StringType
                    nullable = true
                }

                deepLink {
                    uriPattern =
                        "${fragmentADeepLink}"
                }
            }
        }
```

then to perform the navigation, simply calling below in UI feature modules.

```
        findNavController().navigate(route = "fragmentARoute?fragmentAArg=$fragmentAArg")
```

in each UI module, Navigation can be achieved using Nav Component directly without having dependency of
other feature modules since each destination fragment has an unique String route which can be accessible
for all feature modules during compile time.

### Details:

A Migration plan is needed because Navigation Component does not support having `graph.xml` and building the graph from
Kotlin
Code at the same time. all the destinations in `graph.xml` need to be migrated to building the graph from kotlin code
before we can roll it out.

To ensure existing navigation does not break and new fragments, and actions added in `graph.xml` can be supported before
we fully move to build NavGraph from Kotlin. the Migration plan has below steps:

* Convert all `<fragment>`, `<navigation>`, `<dialog>` in `graph.xml` to build NavGraph using Kotlin code
* in each place where `navigateTo(route = router.fragmentAToFragmentB())` is used, provide navigation as below to
  add `navRouteBundle`

```
        navigateTo(
            route = router.videoPromptRecording(
                routeFrom = Extras.FROM_VIDEO_PROMPT_CONTEXTUAL_NUDGE,
                questionId = videoPromptQuestionId
            ),
            navRouteBundle = NavRouteBundle(
                baseRoute = NavRoute.videoPromptRecordingFragment,
                argMap = mapOf(
                    NavArguments.routeFrom to Extras.FROM_VIDEO_PROMPT_CONTEXTUAL_NUDGE,
                    NavArguments.questionId to videoPromptQuestionId
                )
            )
        )
```

* in each fragment, add below code so that when feature flag is turned on, the navigation will be performed in each
  fragment
  directly.

```
        viewModel.getNavDSLDirectionFlow(isInNavDSLExperiment = isInNavDSLExperiment())
            .observeFlow { routeBundle ->
                findNavController().navigate(
                    route = routeBundle.toRouteString(),
                    navOptions = routeBundle.buildNavOptions()
                )
            }

```

* Turn on feature flag for Internal testing and staged rollout
  Once all <fragment>, <navigation>, <dialog> in graph.xml are provided with Route, and all places of fragment
  navigation are provided with navRouteBundle, feature flag can be enabled in dev testing and stage rollout accordingly
* Clean up and deprecate Router
  Once 100% enable of the feature flag in prod and no issues are found, We can start deprecating the Router for Fragment
  navigation. changes will be similar to below

### In progress:
* iron out navigation DSL support for custom type [arguments](https://developer.android.com/guide/navigation/navigation-kotlin-dsl)
* Team buy in for the refactor plan

## Resources
[Android Navigation Notion](https://www.notion.so/hinge/Android-Navigation-100-c7eb17ec2c18457ca1c2abfe47f17036)
[Android Navigation Refactor shortcut epic](https://app.shortcut.com/hingegroups/epic/94680/android-navigation-refactor?cf_workflow=500012091&ct_workflow=all&group_by=none)
[Navigation via DSL Migration Strategy](https://github.com/Hinge/android/pull/7714)

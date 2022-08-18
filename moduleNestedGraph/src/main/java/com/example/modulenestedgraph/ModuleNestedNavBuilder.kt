package com.example.modulenestedgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.example.navigation.NavDeepLink
import com.example.navigation.NavRoute

object ModuleNestedNavBuilder {

    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = NavRoute.fragmentNestedParent,
                route = NavRoute.nestedGraphParent
            ) {
                fragment<FragmentNestedParent>(NavRoute.fragmentNestedParent) {
                    label = "fragment nested parent"
                }

                navigation(
                    startDestination = NavRoute.fragmentNestedChildA,
                    route = NavRoute.nestedGraphChild
                ) {

                    fragment<FragmentNestedChildA>(NavRoute.fragmentNestedChildA) {
                        label = "fragment nested child A"
                        deepLink {
                            uriPattern = NavDeepLink.fragmentNestedChildADeepLink
                        }
                    }

                    fragment<FragmentNestedChildB>(NavRoute.fragmentNestedChildB) {
                        label = "fragment nested child B"
                        deepLink {
                            uriPattern = NavDeepLink.fragmentNestedChildBDeepLink
                        }
                    }
                }
            }
        }
    }

}
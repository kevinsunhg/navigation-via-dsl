package com.example.firstmodule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.fragment.fragment
import com.example.navigation.NavArguments
import com.example.navigation.NavDeepLink
import com.example.navigation.NavRoute

object ModuleBNavBuilder {

    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            fragment<FragmentB>("${NavRoute.fragmentB}?${NavArguments.fragmentBArg}={${NavArguments.fragmentBArg}}") {
                label = "fragment B"
                argument(NavArguments.fragmentBArg) {
                    type = NavType.StringType
                    nullable = true
                }

                deepLink {
                    uriPattern =
                        "${NavDeepLink.fragmentBDeepLinkScheme}?${NavArguments.fragmentBArg}={${NavArguments.fragmentBArg}}"
                }
            }
        }
    }
}
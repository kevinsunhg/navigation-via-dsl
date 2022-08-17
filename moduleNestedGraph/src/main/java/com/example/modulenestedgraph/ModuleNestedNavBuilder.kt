package com.example.modulenestedgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.example.navigation.NavRoute

object ModuleNestedNavBuilder {

    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = NavRoute.fragmentNestedParent,
                route = NavRoute.nestedGraph
            ) {
                fragment<FragmentNestedParent>(NavRoute.fragmentNestedParent){
                    label = "fragment nested parent"
                }

                fragment<FragmentNestedChild>(NavRoute.fragmentNestedChild){
                    label = "fragment nested child"
                    deepLink {
                        uriPattern = "testnavigation://fragmentNestedChild"
                    }
                }
            }
        }
    }

}
package com.example.modulea

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.example.navigation.NavArguments
import com.example.navigation.NavRoute

object ModuleANavBuilder {

    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            fragment<FragmentA>(NavRoute.fragmentA) {
                label = "fragment A"
            }
        }
    }
}
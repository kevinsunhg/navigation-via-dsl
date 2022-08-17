package com.example.secondmodule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.example.navigation.NavRoute

object ModuleCNavBuilder {

    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            fragment<FragmentC>(NavRoute.fragmentC) {
                label = "fragment C"
            }
        }
    }
}
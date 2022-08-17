package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import com.example.firstmodule.ModuleBNavBuilder
import com.example.modulea.ModuleANavBuilder
import com.example.navigation.NavRoute
import com.example.secondmodule.ModuleCNavBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        navController.graph = navController.createGraph(
            startDestination = NavRoute.fragmentA
        ) {
            ModuleANavBuilder.buildNavGraph(this)
            ModuleBNavBuilder.buildNavGraph(this)
            ModuleCNavBuilder.buildNavGraph(this)
        }

    }
}
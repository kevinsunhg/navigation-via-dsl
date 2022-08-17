package com.example.firstmodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.navigation.NavArguments
import com.example.navigation.NavRoute

class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments?.getString(NavArguments.fragmentBArg)
        view.findViewById<TextView>(R.id.arg).text = arg
        view.findViewById<Button>(R.id.click).setOnClickListener {
            findNavController().navigate(NavRoute.fragmentC){
                anim {
                    enter = com.example.navigation.R.anim.translate_from_right
                    exit = com.example.navigation.R.anim.translate_to_left
                    popEnter = com.example.navigation.R.anim.translate_from_left
                    popExit = com.example.navigation.R.anim.translate_to_right
                }
            }
        }
    }
}
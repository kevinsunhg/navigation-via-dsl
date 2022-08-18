package com.example.modulea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.navigation.NavArguments
import com.example.navigation.NavRoute
import com.example.navigation.defaultSlidingNavOption

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.click).setOnClickListener {
            val text = view.findViewById<EditText>(R.id.textInput).text.toString()
            findNavController().navigate(
                route = "${NavRoute.fragmentB}?${NavArguments.fragmentBArg}=${text}",
                navOptions = defaultSlidingNavOption()
            )
        }
    }
}
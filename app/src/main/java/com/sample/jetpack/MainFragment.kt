package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.life_cycle).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_lifecycleFragment)
        }

        view.findViewById<Button>(R.id.navigation).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_navigationFragment)
        }

        view.findViewById<Button>(R.id.view_model).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_viewModelFragment)
        }

        view.findViewById<Button>(R.id.live_data).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_liveDataFragment)
        }

        return view
    }
}

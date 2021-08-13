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
        val view = inflater.inflate(R.layout.fragment_main, container, false)

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

        view.findViewById<Button>(R.id.data_binding).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_dataBindingFragment)
        }

        view.findViewById<Button>(R.id.room).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_roomFragment)
        }

        view.findViewById<Button>(R.id.work_manager).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_workManagerFragment)
        }

        view.findViewById<Button>(R.id.paging).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_pagingFragment)
        }

        view.findViewById<Button>(R.id.mvvm).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_mvvmFragment)
        }

        view.findViewById<Button>(R.id.hilt).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_hiltFragment)
        }

        view.findViewById<Button>(R.id.camerax).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_cameraXFragment)
        }

        return view
    }
}

package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class KTXFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ktx, container, false)

        // https://developer.android.com/kotlin/ktx

        // Extension functions

        // Extension Properties

        // Lambdas

        // Named parameters

        // Parameter default values

        // Coroutines
        view.findViewById<Button>(R.id.coroutines).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_KTXFragment_to_coroutinesFragment)
        }

        return view
    }
}
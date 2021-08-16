package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class KTXFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // https://developer.android.com/kotlin/ktx

        // Extension functions

        // Extension Properties

        // Lambdas

        // Named parameters

        // Parameter default values

        // Coroutines

        return inflater.inflate(R.layout.fragment_ktx, container, false)
    }
}
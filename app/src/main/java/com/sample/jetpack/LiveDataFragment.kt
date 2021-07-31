package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class LiveDataFragment : Fragment() {

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_view_model, container, false)

        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)
        var tick = viewModel.getTick()
        tick.observe(viewLifecycleOwner, {
            view.post {
                var tickTextView = view.findViewById<TextView>(R.id.timer)
                tickTextView.text = it.toString()
            }
        })
        viewModel.startTimer()

        return view
    }
}
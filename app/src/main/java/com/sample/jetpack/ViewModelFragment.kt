package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class ViewModelFragment : Fragment() {

    private lateinit var viewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_view_model, container, false)

        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        viewModel.startTimer(object : MyServiceListener.Callback{
            override fun onTick(tick: Int) {
                view.post {
                    var tickTextView = view.findViewById<TextView>(R.id.timer)
                    tickTextView.text = tick.toString()
                }
            }
        })

        return view
    }
}
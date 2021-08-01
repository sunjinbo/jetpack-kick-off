package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sample.jetpack.databinding.FragmentDataBindingBinding

class DataBindingFragment : Fragment() {

    var time:MyTime = MyTime()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_data_binding, container, false)

        var binding = DataBindingUtil.setContentView<FragmentDataBindingBinding>(requireActivity(), R.layout.fragment_data_binding)
        binding.setVariable(BR.time, time)
        binding.eventHandler = EventHandlerListener(time, binding)

        return view
    }
}
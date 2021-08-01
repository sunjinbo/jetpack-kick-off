package com.sample.jetpack

import android.view.View
import com.sample.jetpack.databinding.FragmentDataBindingBinding

class EventHandlerListener(val time: MyTime, val binding: FragmentDataBindingBinding) {

    fun onClicked(view: View) {
        time.time = System.currentTimeMillis()
        binding.setVariable(BR.time, time)
    }
}

package com.sample.jetpack

import android.view.View
import com.sample.jetpack.databinding.FragmentDataBindingBinding

class EventHandlerListener(private val time: MyTime, private val binding: FragmentDataBindingBinding) {

    fun onClicked(view: View) {
        view.post {
            time.time = System.currentTimeMillis()
            binding.setVariable(BR.time, time)
        }
    }
}

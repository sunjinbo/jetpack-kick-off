package com.sample.jetpack

import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private var timer: MyServiceListener? = null

    fun startTimer(callback: MyServiceListener.Callback) {
        timer = MyServiceListener(callback)
        timer?.startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.stopTimer()
    }
}
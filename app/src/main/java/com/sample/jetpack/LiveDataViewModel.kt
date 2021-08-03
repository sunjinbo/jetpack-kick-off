package com.sample.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel(), MyServiceListener.Callback {
    private var tick: MutableLiveData<Int>? = null
    private var timer: MyServiceListener? = null

    fun getTick() : LiveData<Int> {
        if (tick == null) {
            tick = MutableLiveData()
        }
        return tick!!
    }

    fun startTimer() {
        timer = MyServiceListener(this)
        timer?.startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.stopTimer()
    }

    override fun onTick(tk: Int) {
        tick?.postValue(tk)
    }
}

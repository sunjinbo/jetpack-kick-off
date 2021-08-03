package com.sample.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class MyServiceListener(private val callback: Callback) : TimerTask(), LifecycleObserver {
    private var timer:Timer? = null
    private var running:Boolean = false
    private var tick:Int = 0

    interface Callback {
        fun onTick(tk: Int)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startTimer() {
        if (!running) {
            running = true
            Log.d("jetpack", "start Timer")
            tick = 0
            timer = Timer()
            timer?.schedule(this, 1000L, 1000L)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopTimer() {
        if (running) {
            running = false
            Log.d("jetpack", "stop Timer")
            timer?.cancel()
            timer = null
        }
    }

    override fun run() {
        Log.d("jetpack", "do Timer tick")
        tick += 1
        callback.onTick(tick)
    }
}

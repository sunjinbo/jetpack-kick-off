package com.sample.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class MyServiceListener : TimerTask(), LifecycleObserver {
    private var timer:Timer = Timer()
    private var running:Boolean = false;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startTimer() {
        if (!running) {
            running = true
            Log.d("jetpack", "start Timer")
            timer.schedule(this, 1000L, 1000L)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopTimer() {
        if (running) {
            running = false
            Log.d("jetpack", "stop Timer")
            timer.cancel()
        }
    }

    override fun run() {
        Log.d("jetpack", "do Timer tick")
    }
}

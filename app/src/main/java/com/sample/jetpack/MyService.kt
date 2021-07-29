package com.sample.jetpack

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService

class MyService : LifecycleService(), MyServiceListener.Callback {
    private var myServiceListener: MyServiceListener = MyServiceListener(this)
    private var callback: Callback? = null

    init {
        lifecycle.addObserver(myServiceListener)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return MyBinder()
    }

    override fun onTick(tick: Int) {
        callback?.onTick(tick)
    }

    interface Callback {
        fun onTick(tick: Int)
    }

    inner class MyBinder : Binder() {
        fun setCallback(cb: Callback?) {
            callback = cb
        }
    }
}

package com.sample.jetpack

import androidx.lifecycle.LifecycleService

class MyService : LifecycleService() {
    private var myServiceListener: MyServiceListener = MyServiceListener()

    init {
        lifecycle.addObserver(myServiceListener)
    }
}
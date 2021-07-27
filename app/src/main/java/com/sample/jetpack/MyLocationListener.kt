package com.sample.jetpack

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationListener(private val context: Context, private val listener: OnLocationChangedListener) : LifecycleObserver {

    lateinit var lm: LocationManager
    lateinit var lp: String
    var running: Boolean = false

    var ll: LocationListener = LocationListener {
        listener.onChanged(it.latitude, it.longitude)
    }

    init {
        initLocationManager()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startGetLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listener.onFailed("没有权限")
            return
        }

        if (!running) {
            running = true
            Log.d("jetpack", "requestLocationUpdates()")

            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            criteria.isCostAllowed = true
            criteria.powerRequirement = Criteria.POWER_LOW
            lp = lm.getBestProvider(criteria, true).toString()
            lm.requestLocationUpdates(lp, 0L, 0F, ll)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopGetLocation() {
        if (running) {
            running = false
            Log.d("jetpack", "removeUpdates()")
            lm.removeUpdates(ll)
        }
    }

    private fun initLocationManager() {
        lm = context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    interface OnLocationChangedListener {
        fun onChanged(latitude: Double, longitude: Double)
        fun onFailed(error: String)
    }
}

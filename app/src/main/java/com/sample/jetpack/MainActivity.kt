package com.sample.jetpack

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listener: MyLocationListener

    companion object {
        const val REQUEST_PERMISSION_LOCATION: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var transaction = supportFragmentManager.beginTransaction()
//        var blankFragment = BlankFragment.newInstance("", "")
//        transaction.add(R.id.container, blankFragment)
//        transaction.commit()

        listener = MyLocationListener(this, object : MyLocationListener.OnLocationChangedListener{
            override fun onChanged(latitude: Double, longitude: Double) {
                Log.d("jetpack", "latitude = $latitude, longitude = $longitude")
            }

            override fun onFailed(error: String) {
                Log.d("jetpack", "error = $error")
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_PERMISSION_LOCATION)
            }
        })
        lifecycle.addObserver(listener)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (REQUEST_PERMISSION_LOCATION == requestCode) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                listener.startGetLocation()
            }
        }
    }
}

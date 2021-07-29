package com.sample.jetpack

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class LiefCycleFragment : Fragment(), ServiceConnection, MyService.Callback {

    private lateinit var tickTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_lifecycle, container, false)

        view.findViewById<Button>(R.id.start_timer).setOnClickListener {
            var intent = Intent(context, MyService::class.java)
            context?.bindService(intent, this, BIND_AUTO_CREATE)
        }

        view.findViewById<Button>(R.id.stop_timer).setOnClickListener {
            context?.unbindService(this)
        }

        tickTextView = view.findViewById<TextView>(R.id.timer)

        return view
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        var binder = service as MyService.MyBinder
        binder.setCallback(this)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        // ignore
    }

    override fun onTick(tick: Int) {
        tickTextView.post {
            tickTextView.text = tick.toString()
        }
    }
}

package com.sample.jetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.coroutines.*

class CoroutinesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coroutines, container, false)
        view.findViewById<Button>(R.id.coroutines).setOnClickListener {
            testCoroutines()
        }
        return view
    }

    private fun testCoroutines() {
        Log.d("coroutines", "before launch() invocation ${Thread.currentThread().id}")
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("coroutines", "before delayTest() invocation ${Thread.currentThread().id}")
            delayTest()
            Log.d("coroutines", "after delayTest() invocation ${Thread.currentThread().id}")
        }
        Log.d("coroutines", "after launch() invocation ${Thread.currentThread().id}")
    }

    private suspend fun delayTest() {
        Log.d("coroutines", "before withContext() invocation ${Thread.currentThread().id}")
        withContext(Dispatchers.IO) {
            Log.d("coroutines", "before delay() invocation ${Thread.currentThread().id}")
            delay(5000L)
            Log.d("coroutines", "after delay() invocation ${Thread.currentThread().id}")
        }
        Log.d("coroutines", "after withContext() invocation ${Thread.currentThread().id}")
    }
}
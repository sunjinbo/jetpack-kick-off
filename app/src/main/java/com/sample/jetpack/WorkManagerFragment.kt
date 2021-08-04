package com.sample.jetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkManagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_work_manager, container, false)

        view.findViewById<Button>(R.id.start_work).setOnClickListener {
            var constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()
            var inputData = Data.Builder()
                .putInt("start_timer", 1)
                .putInt("end_timer", 66)
                .putLong("interval", 300L)
                .build()
            var request = OneTimeWorkRequest.Builder(TimerWorker::class.java)
                .setConstraints(constraints)
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .setInputData(inputData)
                .addTag("timer")
                .build()

            WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(request.id)
                .observe(viewLifecycleOwner, {
                    it?.let {
                        var progressData = it.progress
                        progressData.getString("progress")?.let { p -> Log.d("jetpack", p) }
                    }
            })

            WorkManager.getInstance(requireContext()).enqueue(request)
        }

        view.findViewById<Button>(R.id.cancel_work).setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWork()
        }

        return view
    }
}

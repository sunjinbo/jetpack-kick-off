package com.sample.jetpack

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class TimerWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        var startTimer = inputData.getInt("start_timer", 1)
        var endTimer = inputData.getInt("end_timer", 10)
        var interval = inputData.getLong("interval", 100)

        for (x in startTimer..endTimer) {
            SystemClock.sleep(interval)
            var total = endTimer - startTimer
            var progress = x.toFloat() / total.toFloat() * 100
            var outputData = Data.Builder().putString("progress", "$progress%").build()
            setProgressAsync(outputData)

            Log.d("jetpack", "TimerWorker() - x = $x, progress = $progress%")
        }

        return Result.success()
    }
}

package com.sample.jetpack

import android.icu.text.SimpleDateFormat
import java.util.*

class TimeUtil {
    companion object {
        @JvmStatic
        fun getTimeString(time: Long): String? {
            var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return df.format(Date(time))
        }
    }
}

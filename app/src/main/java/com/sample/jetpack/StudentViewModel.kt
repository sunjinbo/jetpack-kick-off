package com.sample.jetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private var database: MyDatabase? = null
    private var liveDataStudent: LiveData<Student>? = null

    init {
        database = application?.let { MyDatabase.getInstance(it) }
        liveDataStudent = database?.studentDao()?.getOldestStudent()
    }

    fun getOldestStudent() : LiveData<Student>? {
        return liveDataStudent
    }

    fun refreshOldestStudent() {
        Thread {
            liveDataStudent?.let {
                var student = liveDataStudent!!.value
                student?.age = student?.age?.plus(1)!!
                database?.studentDao()?.updateStudent(student)
            }
        }.start()
    }
}

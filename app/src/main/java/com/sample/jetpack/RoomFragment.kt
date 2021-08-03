package com.sample.jetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class RoomFragment : Fragment() {

    private var database: MyDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_room, container, false)

        database = context?.let { MyDatabase.getInstance(it) }

        view.findViewById<Button>(R.id.insert_student).setOnClickListener {
            Thread {
                var rand = Random(System.currentTimeMillis())
                var student = Student(getRandomName(), rand.nextInt(1, 20))
                database?.studentDao()?.insertStudent(student)
                showToast(it, "We insert ${student.name} to database.")
            }.start()
        }

        view.findViewById<Button>(R.id.update_student).setOnClickListener {
            Thread {
                var allStudents = ArrayList<Student>()
                database?.studentDao()?.getStudentList()?.let { s -> allStudents.addAll(s) }
                var youngestStudent: Student? = null
                for (s in allStudents) {
                    if (youngestStudent == null) {
                        youngestStudent = s
                    } else {
                        if (youngestStudent.age > s.age) {
                            youngestStudent = s
                        }
                    }
                }
                youngestStudent?.let {
                    youngestStudent.age += 1
                    database?.studentDao()?.updateStudent(youngestStudent)
                }

                showToast(it, "We update ${youngestStudent?.name} to database.")
            }.start()
        }

        view.findViewById<Button>(R.id.delete_student).setOnClickListener {
            Thread {
                var allStudents = ArrayList<Student>()
                database?.studentDao()?.getStudentList()?.let { s -> allStudents.addAll(s) }
                var oldestStudent: Student? = null
                for (s in allStudents) {
                    if (oldestStudent == null) {
                        oldestStudent = s
                    } else {
                        if (oldestStudent.age < s.age) {
                            oldestStudent = s
                        }
                    }
                }
                oldestStudent?.let {
                    database?.studentDao()?.deleteStudent(oldestStudent)
                }

                showToast(it, "We delete ${oldestStudent?.name} from database.")
            }.start()
        }

        view.findViewById<Button>(R.id.query_student).setOnClickListener {
            Thread {
                var allStudents = ArrayList<Student>()
                database?.studentDao()?.getStudentList()?.let { s -> allStudents.addAll(s) }
                showToast(it, "We find ${allStudents.size} students from database.")
            }.start()
        }

        view.findViewById<Button>(R.id.check_student).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_roomFragment_to_studentFragment)
        }

        return view
    }

    private fun getRandomName() : String {
        var sb = StringBuilder()
        for (i in 0..5) {
            var rand = Random(System.currentTimeMillis())
            sb.append('a' + rand.nextInt(i, 25))
        }
        return sb.toString()
    }

    private fun showToast(v:View, toast: String) {
        v.post {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
        }
    }
}

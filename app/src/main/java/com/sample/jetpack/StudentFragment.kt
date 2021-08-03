package com.sample.jetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class StudentFragment : Fragment() {

    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_student, container, false)

        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        viewModel.getOldestStudent()?.observe(viewLifecycleOwner, {
            var id = view.findViewById<TextView>(R.id.id)
            id.text = it?.id.toString()

            var name = view.findViewById<TextView>(R.id.name)
            name.text = it?.name.toString()

            var age = view.findViewById<TextView>(R.id.age)
            age.text = it?.age.toString()
        })

        view.findViewById<Button>(R.id.refresh_student).setOnClickListener {
            viewModel.refreshOldestStudent()
        }

        return view
    }
}

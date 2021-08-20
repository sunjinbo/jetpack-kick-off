package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sample.jetpack.databinding.FragmentMvvmBinding

class MvvmFragment : Fragment() {

    private val mvvmViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMvvmBinding>(inflater, R.layout.fragment_mvvm, container, false)
        binding.viewModel = mvvmViewModel
        binding.lifecycleOwner = this
        binding.refreshUser.setOnClickListener { mvvmViewModel.getUser("Joe") }

        return binding.root
    }
}
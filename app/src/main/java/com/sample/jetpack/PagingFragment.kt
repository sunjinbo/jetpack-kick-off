package com.sample.jetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_paging, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        val moviePagedListAdapter = MoviePagedListAdapter(requireContext())
        val movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.moviePageList.observe(viewLifecycleOwner,
            { t -> moviePagedListAdapter.submitList(t) })

        recyclerView.adapter = moviePagedListAdapter

        return view
    }
}
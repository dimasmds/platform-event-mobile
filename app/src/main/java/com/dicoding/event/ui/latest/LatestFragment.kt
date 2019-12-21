package com.dicoding.event.ui.latest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dicoding.event.R
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.ext.invisible
import com.dicoding.event.ext.visible
import com.dicoding.event.ui.home.HomeEventRecyclerAdapter
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import com.dicoding.event.utils.ViewModelState
import org.koin.androidx.viewmodel.ext.android.viewModel


class LatestFragment : Fragment() {

    private val latestViewModel: LatestViewModel by viewModel()

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private var events: List<EventsResponse.Event> = listOf()
    private val adapter = HomeEventRecyclerAdapter().apply {
        events = this@LatestFragment.events
    }

    companion object {
        fun newInstance() : LatestFragment {
            return LatestFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_latest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.fragmentLatestProgressBar)
        recyclerView = view.findViewById<RecyclerView>(R.id.fragmentLatestRecyclerView).apply {
            adapter = this@LatestFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        latestViewModel.getLatestEvent()
        latestViewModel.observerState.observe(this, observableState)

    }

    private val observableState = Observer<ViewModelState> {
        when(it) {
            is Loading -> {
                progressBar.visible()
                recyclerView.invisible()
            }

            is Success<*> -> {
                events = it.data as List<EventsResponse.Event>
                adapter.events = events

                progressBar.invisible()
                recyclerView.visible()
            }

            is Error -> {
                progressBar.invisible()
                recyclerView.visible()
            }
        }
    }
}

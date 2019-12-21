package com.dicoding.event.ui.upcoming


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
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


class UpcomingFragment : Fragment() {

    companion object {
        fun newInstance() : UpcomingFragment {
            return UpcomingFragment()
        }
    }

    private val upcomingViewModel: UpcomingViewModel by viewModel()
    private var events = listOf<EventsResponse.Event>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapter = HomeEventRecyclerAdapter().apply {
        events = this@UpcomingFragment.events
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.fragmentUpcomingProgressBar)
        recyclerView = view.findViewById<RecyclerView>(R.id.fragmentUpcomingRecyclerView).apply {
            adapter = this@UpcomingFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        upcomingViewModel.getUpcomingEvent()
        upcomingViewModel.observerState.observe(this, observableState)
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
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                progressBar.invisible()
            }
        }
    }
}
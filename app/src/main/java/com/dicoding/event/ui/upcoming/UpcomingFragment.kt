package com.dicoding.event.ui.upcoming


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dicoding.event.R


class UpcomingFragment : Fragment() {

    companion object {
        fun newInstance() : UpcomingFragment {
            return UpcomingFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }


}

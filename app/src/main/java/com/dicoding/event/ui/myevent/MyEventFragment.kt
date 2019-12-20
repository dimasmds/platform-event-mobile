package com.dicoding.event.ui.myevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.event.R


class MyEventFragment : Fragment() {

    companion object {
        fun newInstance() : MyEventFragment {
            return MyEventFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_event, container, false)
    }


}

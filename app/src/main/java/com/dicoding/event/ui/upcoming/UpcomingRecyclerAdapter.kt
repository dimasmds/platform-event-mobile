package com.dicoding.event.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.event.R
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.ext.formattedDate
import com.squareup.picasso.Picasso

class UpcomingRecyclerAdapter : RecyclerView.Adapter<UpcomingRecyclerAdapter.ViewHolder>() {

    var events: List<EventsResponse.Event> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_event, parent, false)
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageViewPoster: ImageView = view.findViewById(R.id.rieImageViewPoster)
        private val textViewTitle: TextView = view.findViewById(R.id.rieTextViewTitle)
        private val textViewDate: TextView = view.findViewById(R.id.rieTextViewDate)
        private val textViewCategory: TextView = view.findViewById(R.id.rieTextViewCategory)

        fun bindItem(event: EventsResponse.Event) {
            textViewTitle.text = event.eventName
            textViewCategory.text = event.eventCategory
            textViewDate.text = event.eventDate.formattedDate("yyyy-MM-dd", "MMM dd, yyyy")
            Picasso.get().load(event.eventPoster).centerCrop().fit().into(imageViewPoster)
        }
    }

}
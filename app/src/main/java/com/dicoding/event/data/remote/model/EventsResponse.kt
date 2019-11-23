package com.dicoding.event.data.remote.model

import com.squareup.moshi.Json

data class EventsResponse(
    @field:Json(name = "error") val error: Boolean,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "events") val events: List<Event>
) {
    data class Event(
        @field:Json(name = "eventId") val eventId: String,
        @field:Json(name = "eventName") val eventName: String,
        @field:Json(name = "eventPoster") val eventPoster: String,
        @field:Json(name = "eventCategory") val eventCategory: String,
        @field:Json(name = "eventDate") val eventDate: String
    )
}
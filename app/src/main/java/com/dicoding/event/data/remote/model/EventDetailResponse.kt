package com.dicoding.event.data.remote.model

import com.squareup.moshi.Json

data class EventDetailResponse(
    @field:Json(name = "error") val error: Boolean,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "event") val event: EventDetail
) {
    data class EventDetail(
        @field:Json(name = "eventId") val eventId: String,
        @field:Json(name = "eventName") val eventName: String,
        @field:Json(name = "eventOrganizer") val eventOrganizer: String,
        @field:Json(name = "eventQuotaTotal") val eventQuotaTotal: Int,
        @field:Json(name = "eventQuotaLeft") val eventQuotaLeft: Int,
        @field:Json(name = "eventLatitude") val eventLatitude: String,
        @field:Json(name = "eventLongitude") val eventLongitude: String,
        @field:Json(name = "eventAddress") val eventAddress: String,
        @field:Json(name = "eventBuilding") val eventBuilding: String,
        @field:Json(name = "eventCity") val eventCity: String,
        @field:Json(name = "eventProvince") val eventProvince: String,
        @field:Json(name = "eventCategory") val eventCategory: String,
        @field:Json(name = "eventDescription") val eventDescription: String,
        @field:Json(name = "eventBanner") val eventBanner: String,
        @field:Json(name = "eventPosterPath") val eventPosterPath: String

    )
}
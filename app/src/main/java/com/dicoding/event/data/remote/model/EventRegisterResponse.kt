package com.dicoding.event.data.remote.model

import com.squareup.moshi.Json

data class EventRegisterResponse(
    @field:Json(name = "error") val error: Boolean,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "eventRegister") val eventRegister: EventRegister
){
    data class EventRegister(
        @field:Json(name = "userId") val userId: String,
        @field:Json(name = "eventId") val eventId: String,
        @field:Json(name = "participantNumber") val participantNumber: Int,
        @field:Json(name = "waitingList") val waitingList: Boolean
    )
}
package com.dicoding.event.data.remote.model

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "error") val error: Boolean,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "auth") val auth: Auth
) {
    data class Auth(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "username") val username: String,
        @field:Json(name = "email") val email: String,
        @field:Json(name = "fullName") val fullName: String
    )
}
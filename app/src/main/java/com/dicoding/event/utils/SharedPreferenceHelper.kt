package com.dicoding.event.utils

import android.content.Context

data class UserPreference(
    var email: String? = "",
    var username: String? = ""
) {
    companion object {
        const val KEY_PREFERENCE = "keyPreference"
        const val KEY_EMAIL = "keyEmail"
        const val KEY_USERNAME = "keyUsername"
    }
}

fun Context.setUserPreference(userPreference: UserPreference) {
    val sharedPreferences = this.getSharedPreferences(UserPreference.KEY_PREFERENCE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString(UserPreference.KEY_EMAIL, userPreference.email)
        putString(UserPreference.KEY_USERNAME, userPreference.username)
        apply()
    }
}

fun Context.getUserPreference() : UserPreference {
    val sharedPreference = this.getSharedPreferences(UserPreference.KEY_PREFERENCE, Context.MODE_PRIVATE)
    return UserPreference().apply {
        email = sharedPreference.getString(UserPreference.KEY_EMAIL, "")
        username = sharedPreference.getString(UserPreference.KEY_USERNAME, "")
    }
}
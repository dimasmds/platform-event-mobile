package com.dicoding.event.utils

import android.content.Context

data class UserPreference(
    var id: String = "",
    var email: String = "",
    var username: String = ""
) {
    companion object {
        const val KEY_PREFERENCE = "keyPreference"
        const val KEY_ID = "keyId"
        const val KEY_EMAIL = "keyEmail"
        const val KEY_USERNAME = "keyUsername"
    }
}

fun Context.setUserPreference(userPreference: UserPreference) {
    val sharedPreferences = this.getSharedPreferences(UserPreference.KEY_PREFERENCE, Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString(UserPreference.KEY_ID, userPreference.id)
        putString(UserPreference.KEY_EMAIL, userPreference.email)
        putString(UserPreference.KEY_USERNAME, userPreference.username)
        apply()
    }
}

fun Context.getUserPreference() : UserPreference {
    val sharedPreference = this.getSharedPreferences(UserPreference.KEY_PREFERENCE, Context.MODE_PRIVATE)
    return UserPreference().apply {
        id = sharedPreference.getString(UserPreference.KEY_ID, "") ?: ""
        email = sharedPreference.getString(UserPreference.KEY_EMAIL, "") ?: ""
        username = sharedPreference.getString(UserPreference.KEY_USERNAME, "") ?: ""
    }
}
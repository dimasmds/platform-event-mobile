package com.dicoding.event.data.remote.repository

interface RepositoryCallback {
    fun <T>onSuccess(data: T)
    fun onError(message: String)
}
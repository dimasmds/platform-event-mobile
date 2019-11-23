package com.dicoding.event.utils

sealed class ViewModelState

object Loading: ViewModelState()
data class Error(val message: String): ViewModelState()
data class Success<T>(val data: T): ViewModelState()
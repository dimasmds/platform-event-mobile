package com.dicoding.event.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.event.data.remote.repository.RemoteRepository
import com.dicoding.event.data.remote.repository.RepositoryCallback
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import com.dicoding.event.utils.ViewModelState

class UpcomingViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    val observerState = MutableLiveData<ViewModelState>()

    fun getUpcomingEvent() {
        observerState.value = Loading
        remoteRepository.getUpcomingEvent(object : RepositoryCallback {
            override fun <T> onSuccess(data: T) {
                observerState.value = Success(data)
            }

            override fun onError(message: String) {
                observerState.value = Error(message)
            }
        })
    }
}
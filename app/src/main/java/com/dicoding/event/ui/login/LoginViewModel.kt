package com.dicoding.event.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.event.data.remote.repository.RemoteRepository
import com.dicoding.event.data.remote.repository.RepositoryCallback
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import com.dicoding.event.utils.ViewModelState

class LoginViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    val observerState = MutableLiveData<ViewModelState>()

    fun doLogin(email: String, password: String) {
        observerState.value = Loading
        remoteRepository.doLogin(email, password, object : RepositoryCallback {
            override fun <T> onSuccess(data: T) {
                observerState.value = Success(data)
            }

            override fun onError(message: String) {
                observerState.value = Error(message)
            }

        })
    }
}
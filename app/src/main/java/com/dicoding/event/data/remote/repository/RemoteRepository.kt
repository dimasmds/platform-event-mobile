package com.dicoding.event.data.remote.repository

import com.dicoding.event.data.remote.model.EventDetailResponse
import com.dicoding.event.data.remote.model.EventRegisterResponse
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.model.UserResponse
import com.dicoding.event.data.remote.network.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(private val service: Services) {

    fun getUpcomingEvent(callback: RepositoryCallback) {
        service.getUpcomingEvent().enqueue(object : Callback<EventsResponse> {
            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(
                call: Call<EventsResponse>,
                response: Response<EventsResponse>
            ) {
                if (response.isSuccessful) {
                    val eventResponse = response.body()
                    if (eventResponse != null) {
                        if (!eventResponse.error) {
                            callback.onSuccess(eventResponse.events)
                        } else {
                            callback.onError(eventResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }
        })
    }

    fun getLatestEvent(callback: RepositoryCallback) {
        service.getLatestEvent().enqueue(object : Callback<EventsResponse> {
            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(
                call: Call<EventsResponse>,
                response: Response<EventsResponse>
            ) {
                if (response.isSuccessful) {
                    val eventResponse = response.body()
                    if (eventResponse != null) {
                        if (!eventResponse.error) {
                            callback.onSuccess(eventResponse.events)
                        } else {
                            callback.onError(eventResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }
        })
    }

    fun getMyEvent(userId: String = "5", callback: RepositoryCallback) {
        service.getMyEvent(userId).enqueue(object : Callback<EventsResponse> {
            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(
                call: Call<EventsResponse>,
                response: Response<EventsResponse>
            ) {
                if (response.isSuccessful) {
                    val eventResponse = response.body()
                    if (eventResponse != null) {
                        if (!eventResponse.error) {
                            callback.onSuccess(eventResponse.events)
                        } else {
                            callback.onError(eventResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }
        })
    }

    fun getEventDetail(eventId: String, callback: RepositoryCallback) {
        service.getDetailEvent(eventId).enqueue(object : Callback<EventDetailResponse> {
            override fun onFailure(call: Call<EventDetailResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(
                call: Call<EventDetailResponse>,
                response: Response<EventDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val eventResponse = response.body()
                    if (eventResponse != null) {
                        if (!eventResponse.error) {
                            callback.onSuccess(eventResponse.event)
                        } else {
                            callback.onError(eventResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }
        })
    }

    fun registerEvent(userId: String = "5", eventId: String, callback: RepositoryCallback) {
        service.registerEvent(userId, eventId).enqueue(object : Callback<EventRegisterResponse> {
            override fun onFailure(call: Call<EventRegisterResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(
                call: Call<EventRegisterResponse>,
                response: Response<EventRegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val eventRegisterResponse = response.body()
                    if (eventRegisterResponse != null) {
                        if (!eventRegisterResponse.error) {
                            callback.onSuccess(eventRegisterResponse.eventRegister)
                        } else {
                            callback.onError(eventRegisterResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }

        })
    }

    fun doLogin(email: String, password: String, callback: RepositoryCallback) {
        service.doLogin(email, password).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        if (!userResponse.error) {
                            callback.onSuccess(userResponse.auth)
                        } else {
                            callback.onError(userResponse.message)
                        }
                    } else {
                        callback.onError("Tidak ditemukan")
                    }
                } else {
                    callback.onError(response.message())
                }
            }

        })
    }
}
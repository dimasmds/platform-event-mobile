package com.dicoding.event.data.remote.network

import com.dicoding.event.data.remote.model.EventDetailResponse
import com.dicoding.event.data.remote.model.EventRegisterResponse
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Services {

    @GET("upcoming.php")
    fun getUpcomingEvent(): Call<EventsResponse>

    @GET("latestevent.php")
    fun getLatestEvent(): Call<EventsResponse>

    @GET("myevent.php")
    fun getMyEvent(@Query("userId") userId: String) : Call<EventsResponse>

    @GET("eventdetail.php")
    fun getDetailEvent(@Query("eventId") eventId: String) : Call<EventDetailResponse>

    @FormUrlEncoded
    @POST("eventregister.php")
    fun registerEvent(@Field("userId") userId: String,
                      @Field("eventId") eventId: String) : Call<EventRegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun doLogin(@Field("email") email: String,
                @Field("password") password: String) : Call<UserResponse>

}
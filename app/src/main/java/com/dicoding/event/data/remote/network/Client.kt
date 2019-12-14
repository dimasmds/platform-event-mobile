package com.dicoding.event.data.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Client {
    fun getClient(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.21/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}


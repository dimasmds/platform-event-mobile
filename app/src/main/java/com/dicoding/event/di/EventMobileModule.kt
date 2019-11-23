package com.dicoding.event.di

import com.dicoding.event.data.remote.network.Client
import com.dicoding.event.data.remote.network.Services
import com.dicoding.event.data.remote.repository.RemoteRepository
import com.dicoding.event.ui.latest.LatestViewModel
import com.dicoding.event.ui.login.LoginViewModel
import com.dicoding.event.ui.myevent.MyEventViewModel
import com.dicoding.event.ui.upcoming.UpcomingViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { services }
}

val repositoryModule = module {
    factory { RemoteRepository(get()) }
}

val viewModelModule = module {
    viewModel { UpcomingViewModel(get()) }
    viewModel { LatestViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MyEventViewModel(get()) }
}


private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().apply {
        retryOnConnectionFailure(true)
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
    }.build()
}

private val services: Services = Client.getClient(getOkHttpClient()).create(Services::class.java)

object EventMobile {
    private val listModule = mutableListOf<Module>().apply {
        add(networkModule)
        add(repositoryModule)
        add(viewModelModule)
    }
    fun init() = loadKoinModules(listModule)
}
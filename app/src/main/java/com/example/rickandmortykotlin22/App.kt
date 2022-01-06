package com.example.rickandmortykotlin22

import android.app.Application
import com.example.rickandmortykotlin22.servicelocator.networkModule
import com.example.rickandmortykotlin22.servicelocator.repositoriesModel
import com.example.rickandmortykotlin22.servicelocator.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModel, viewModelModule)
        }
    }
}
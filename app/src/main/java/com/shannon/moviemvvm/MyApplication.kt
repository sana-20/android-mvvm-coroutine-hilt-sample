package com.shannon.moviemvvm

import android.app.Application
import com.shannon.moviemvvm.data.api.appModules
import com.shannon.moviemvvm.data.api.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
            modules(viewModelModules)
        }
    }
}
package com.example.stattrack

import android.app.Application
import com.example.stattrack.di.ServiceLocator


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}

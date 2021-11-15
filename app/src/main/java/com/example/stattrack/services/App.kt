package com.example.stattrack

import android.app.Application
import com.example.stattrack.services.ServiceLocator


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}

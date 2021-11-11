package com.example.stattrack.Services

import android.app.Application


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }

}

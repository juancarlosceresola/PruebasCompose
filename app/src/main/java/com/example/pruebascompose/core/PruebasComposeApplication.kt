package com.example.pruebascompose.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PruebasComposeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
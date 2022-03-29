package com.hassan

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SouqTest: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
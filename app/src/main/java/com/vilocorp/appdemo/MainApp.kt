package com.vilocorp.appdemo

import android.app.Application
import com.microsoft.appcenter.AppCenter

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCenter.configure(this, "dcacc93f-e0a7-40ff-ad1f-a4473fec3ee8")
    }
}
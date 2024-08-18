package com.gianghv.android

import android.app.Application
import com.gianghv.android.repository.api.FirebaseListener
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
//        FakeData.fakeData()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        FirebaseListener.getData()
    }
}

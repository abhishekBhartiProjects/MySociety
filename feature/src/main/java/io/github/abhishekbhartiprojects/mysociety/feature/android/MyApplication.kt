package io.github.abhishekbhartiprojects.mysociety.feature.android

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.github.abhishekbhartiprojects.mysociety.feature.BuildConfig
import io.github.abhishekbhartiprojects.mysociety.feature.R

class MyApplication: Application() {

    companion object {
        lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig

        fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
            return mFirebaseRemoteConfig
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (!FirebaseApp.getApps(this).isEmpty()) {
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        }

        MySharedPreference.instantiateSharedPreference(this)
    }



}
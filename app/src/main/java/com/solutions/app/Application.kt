package com.solutions.app



import com.therockakash.shaketrace.compose.ShakeTrace
import dagger.hilt.android.HiltAndroidApp

/**
 * @Created by akash on 23/11/2024.
 * Know more about author on <a href="https://akash.cloudemy.in">...</a>
 */
@HiltAndroidApp
class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
        {
            ShakeTrace.init(this)
        }

    }
}
package com.nka4349.chronoflow

import android.app.Application
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerFragmentDestinations

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.config.debugLoggingEnabled = BuildConfig.DEBUG
        Hotwire.config.webViewDebuggingEnabled = BuildConfig.DEBUG
        Hotwire.config.applicationUserAgentPrefix = "ChronoFlow;"

        // Replace Hotwire Native's default web fragment with our no-toolbar
        // fragment. This removes the native "← ChronoFlow" app bar while
        // leaving Android's system status/navigation bars intact.
        Hotwire.defaultFragmentDestination = WebFragment::class
        Hotwire.registerFragmentDestinations(WebFragment::class)

        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                assetFilePath = "json/android_v1.json",
                remoteFileUrl = "https://chrono-flow-mvp.onrender.com/configurations/android_v1.json"
            )
        )
    }
}

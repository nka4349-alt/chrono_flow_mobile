package com.nka4349.chronoflow

import android.app.Application
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.navigation.Hotwire
import dev.hotwire.navigation.config.PathConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.config.debugLoggingEnabled = BuildConfig.DEBUG
        Hotwire.config.webViewDebuggingEnabled = BuildConfig.DEBUG
        Hotwire.config.applicationUserAgentPrefix = "ChronoFlow;"

        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                assetFilePath = "json/android_v1.json",
                remoteFileUrl = "https://chrono-flow-mvp.onrender.com/configurations/android_v1.json"
            )
        )

        // Register extra bridge components here in v2 if needed.
        Hotwire.registerBridgeComponents()
    }
}

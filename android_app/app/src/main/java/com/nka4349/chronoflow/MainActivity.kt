package com.nka4349.chronoflow

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

class MainActivity : HotwireActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideNativeActionBar()

        // v1.0.4 hotfix:
        // Prefer startup stability over aggressive edge-to-edge behavior.
        // System bars stay outside the WebView, while the Hotwire web fragment
        // remains responsible for rendering the Rails screen.
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.main_nav_host).apply {
            applyDefaultImeWindowInsets()
        }
    }

    override fun onResume() {
        super.onResume()
        hideNativeActionBar()
    }

    private fun hideNativeActionBar() {
        supportActionBar?.hide()
        actionBar?.hide()
    }

    override fun navigatorConfigurations() = listOf(
        NavigatorConfiguration(
            name = "main",
            startLocation = "https://chrono-flow-mvp.onrender.com",
            navigatorHostId = R.id.main_nav_host
        )
    )
}

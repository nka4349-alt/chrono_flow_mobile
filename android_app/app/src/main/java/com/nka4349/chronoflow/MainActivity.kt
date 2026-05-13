package com.nka4349.chronoflow

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration

class MainActivity : HotwireActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideNativeActionBar()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.main_nav_host).apply {
            setBackgroundColor(CHRONOFLOW_SURFACE)
            applyNavigationBarInsets()
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

    private fun View.applyNavigationBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())

            view.updatePadding(
                left = systemBars.left,
                top = 0,
                right = systemBars.right,
                bottom = maxOf(systemBars.bottom, navigationBars.bottom, ime.bottom)
            )
            insets
        }
        ViewCompat.requestApplyInsets(this)
    }

    override fun navigatorConfigurations() = listOf(
        NavigatorConfiguration(
            name = "main",
            startLocation = "https://chrono-flow-mvp.onrender.com",
            navigatorHostId = R.id.main_nav_host
        )
    )

    private companion object {
        val CHRONOFLOW_SURFACE: Int = Color.rgb(8, 17, 43)
    }
}

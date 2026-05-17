package com.nka4349.chronoflow

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.Navigator
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets
import org.json.JSONObject

class MainActivity : HotwireActivity() {
    private val rootLocation = "https://chrono-flow-mvp.onrender.com/"
    private val personalAiLocation = "https://chrono-flow-mvp.onrender.com/#cf-personal-ai"
    private var webBackCheckInFlight = false

    private val chronoFlowBackCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            handleChronoFlowBack()
        }
    }

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

        // Install after HotwireActivity's own callback so this app-level policy wins.
        // The Web app is mostly a single Rails root screen, so URL-only native popping
        // can expose /login in the previous Hotwire stack. Ask the Web screen first;
        // when it has nothing to close, background the task instead of popping to login.
        onBackPressedDispatcher.addCallback(this, chronoFlowBackCallback)
    }

    override fun onResume() {
        super.onResume()
        hideNativeActionBar()
    }

    private fun hideNativeActionBar() {
        supportActionBar?.hide()
        actionBar?.hide()
    }

    private fun currentNavigator(): Navigator? {
        return delegate.currentNavigator
    }

    private fun handleChronoFlowBack() {
        val navigator = currentNavigator()
        if (navigator == null || !navigator.isReady()) {
            moveTaskToBack(true)
            return
        }

        val location = navigator.location ?: navigator.session.webView.url ?: rootLocation
        val path = normalizedPath(location)

        when {
            isAuthPath(path) -> moveTaskToBack(true)
            isRootPath(path) -> handleRootBack(navigator)
            isEventAiPath(path) -> routeReplacing(navigator, personalAiLocation)
            isPersonalAiPath(path) -> routeReplacing(navigator, rootLocation)
            isEventPath(path) -> routeReplacing(navigator, rootLocation)
            isGroupPath(path) && wouldPopToAuth(navigator) -> moveTaskToBack(true)
            isGroupPath(path) && !navigator.isAtStartDestination() -> navigator.pop()
            isGroupPath(path) -> moveTaskToBack(true)
            wouldPopToAuth(navigator) -> moveTaskToBack(true)
            !navigator.isAtStartDestination() -> navigator.pop()
            else -> moveTaskToBack(true)
        }
    }

    private fun handleRootBack(navigator: Navigator) {
        if (webBackCheckInFlight) return
        webBackCheckInFlight = true

        val script = """
            (function() {
              try {
                if (window.ChronoFlowMobile && typeof window.ChronoFlowMobile.handleBack === 'function') {
                  return window.ChronoFlowMobile.handleBack();
                }
                return 'pass:no_mobile_bridge';
              } catch (e) {
                return 'pass:' + ((e && e.message) ? e.message : 'mobile_bridge_error');
              }
            })();
        """.trimIndent()

        navigator.session.webView.evaluateJavascript(script) { rawResult ->
            webBackCheckInFlight = false
            val result = decodeJsString(rawResult)
            if (!result.startsWith("handled:")) {
                moveTaskToBack(true)
            }
        }
    }

    private fun routeReplacing(navigator: Navigator, location: String) {
        navigator.clearAll {
            navigator.route(location)
        }
    }

    private fun normalizedPath(location: String?): String {
        if (location.isNullOrBlank()) return "/"
        return try {
            val uri = location.toUri()
            val path = uri.path?.ifBlank { "/" } ?: "/"
            if (path.length > 1 && path.endsWith("/")) path.dropLast(1) else path
        } catch (_: Exception) {
            "/"
        }
    }

    private fun decodeJsString(raw: String?): String {
        if (raw.isNullOrBlank() || raw == "null") return ""
        return try {
            JSONObject("{\"value\":$raw}").optString("value", "")
        } catch (_: Exception) {
            raw.trim().trim('"')
        }
    }

    private fun isRootPath(path: String): Boolean {
        return path == "/" || path == "/home" || path == "/calendar"
    }

    private fun isAuthPath(path: String): Boolean {
        return path == "/login" || path == "/signup"
    }

    private fun isPersonalAiPath(path: String): Boolean {
        return path == "/ai_chat" || path == "/ai" || path == "/chat"
    }

    private fun isEventAiPath(path: String): Boolean {
        return Regex("^/events/[^/]+/(ai_chat|chat)$").matches(path) || path == "/event_ai_chat"
    }

    private fun isEventPath(path: String): Boolean {
        return Regex("^/events/[^/]+(/edit)?$").matches(path)
    }

    private fun isGroupPath(path: String): Boolean {
        return path.startsWith("/groups/")
    }

    private fun wouldPopToAuth(navigator: Navigator): Boolean {
        return isAuthPath(normalizedPath(navigator.previousLocation))
    }

    override fun navigatorConfigurations() = listOf(
        NavigatorConfiguration(
            name = "main",
            startLocation = rootLocation,
            navigatorHostId = R.id.main_nav_host
        )
    )
}
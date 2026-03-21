import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        // Bundled path configuration (always load this)
        let localPathConfigURL = Bundle.main.url(forResource: "path-configuration", withExtension: "json")!

        // Optional: enable after Rails serves /configurations/ios_v1.json
        let remotePathConfigURL = URL(string: "https://chrono-flow-mvp.onrender.com/configurations/ios_v1.json")!

        Hotwire.loadPathConfiguration(from: [
            .file(localPathConfigURL),
            .server(remotePathConfigURL)
        ])

        Hotwire.config.debugLoggingEnabled = true
        Hotwire.config.applicationUserAgentPrefix = "ChronoFlow;"

        return true
    }
}

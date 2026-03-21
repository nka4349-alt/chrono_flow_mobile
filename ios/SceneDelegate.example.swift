import HotwireNative
import UIKit

let rootURL = URL(string: "https://chrono-flow-mvp.onrender.com")!

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?

    private let navigator = Navigator(configuration: .init(
        name: "main",
        startLocation: rootURL
    ))

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = (scene as? UIWindowScene) else { return }

        let window = UIWindow(windowScene: windowScene)
        window.rootViewController = navigator.rootViewController
        self.window = window
        window.makeKeyAndVisible()

        navigator.start()
    }
}

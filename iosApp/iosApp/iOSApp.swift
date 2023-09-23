import SwiftUI
import shared
import FirebaseCore


@main
struct iOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        KoinKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
            ComposeView()
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.all)
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    FirebaseApp.configure()
    return true
  }

}

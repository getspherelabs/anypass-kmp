import SwiftUI
import shared
import FirebaseCore



@main
struct iOSApp: App {

    init() {
        FirebaseApp.configure()

        KoinKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
            ComposeView().ignoresSafeArea(.keyboard)
		}
	}
}

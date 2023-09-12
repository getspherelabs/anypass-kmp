import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            ComposeView().ignoresSafeArea(.keyboard)
		}
	}
}

import SwiftUI
import shared
import FirebaseCore
import GoogleMobileAds

@main
struct iOSApp: App {

    init() {
        initializeKoin()
        initializeFirebase()
        initializeGADMobileAds()
    }
	var body: some Scene {
		WindowGroup {
            ComposeView()
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.all)
		}
	}
}

func initializeKoin() {
    KoinKt.doInitKoin()
}

func initializeFirebase() {
    FirebaseApp.configure()
}

func initializeGADMobileAds() {
    GADMobileAds.sharedInstance().requestConfiguration.testDeviceIdentifiers = [ GADSimulatorID ]
}

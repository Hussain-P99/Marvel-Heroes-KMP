import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        setupKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: ViewModel())
        }
    }
    
    func setupKoin() {
        KoinHelperKt.doInitKoin()
    }
}

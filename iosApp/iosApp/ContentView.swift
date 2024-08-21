import SwiftUI
import Shared

struct ContentView: View {
    var viewModel : ViewModel
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

class ViewModel : ObservableObject {

    @Published var characters : NSArray = NSArray()
    
    var marvelRepository = KoinHelper().getMarvelRepository()
    
    init() {
        getAllCharacters()
    }
    
    func getAllCharacters() {
        marvelRepository.getAllCharacters(shouldRefresh: false) { execResult, error in
            if let successResult = execResult as? ExecResultSuccess {
                print(successResult.data ?? "No Data")
                if let successData = successResult.data {
                    self.characters = successData
                }
            } else if let errorResult = execResult as? ExecResultError {
                print(errorResult.message ?? "Unknown Error")
            } else {
                print("Loading")
            }
        }
    }
    
}

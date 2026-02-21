import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationStack {
            VStack(spacing: 20) {
                Text("VoiceOver Scroll Test")
                    .font(.largeTitle)
                    .padding()
                
                NavigationLink("1 to 100", destination: ScrollDetailView(title: "1 to 100", url: URL(string: "https://khsruru.com/android_scroll_test/#tab1")!))
                NavigationLink("101 to 200", destination: ScrollDetailView(title: "101 to 200", url: URL(string: "https://khsruru.com/android_scroll_test/#tab2")!))
                NavigationLink("201 to 300", destination: ScrollDetailView(title: "201 to 300", url: URL(string: "https://khsruru.com/android_scroll_test/#tab3")!))
            }
            .navigationTitle("Main")
            .navigationBarHidden(true)
        }
    }
}

struct ScrollDetailView: View {
    let title: String
    let url: URL
    
    var body: some View {
        WebView(url: url)
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
    }
}

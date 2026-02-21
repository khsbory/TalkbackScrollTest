# Accessibility Scroll Test

A cross-platform project (Android + iOS) demonstrating screen-reader behavior when a WebView scrolls programmatically. Both apps load the same remote web content and allow you to observe how TalkBack (Android) and VoiceOver (iOS) handle accessibility focus events during programmatic scrolling.

## Repository Structure

```
Android/   – Android app (Jetpack Compose + WebView)
iOS/       – iOS app (SwiftUI + WKWebView)
```

---

## Android

A demo Android app showing that when a WebView scrolls programmatically, it sends accessibility focus events, but TalkBack cannot act on them when its focus is outside the WebView because it does not receive those accessibility events.

The app is built with Jetpack Compose and includes:
- A home screen with selectable number ranges
- A detail screen that hosts a WebView per range
- Accessibility pane titles for each screen

### Requirements

- Android Studio Hedgehog or newer (or command-line Android SDK tools)
- JDK 17
- A connected Android device with ADB enabled

### Build

From the `Android/` directory:

```bash
./gradlew assembleDebug
```

On Windows:

```powershell
gradlew.bat assembleDebug
```

### Install And Run

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -W com.example.talkbackscrolltest/.MainActivity
```

### TalkBack Test Procedure

1. Enable TalkBack on the device.
2. Launch the app.
3. On the home screen, open one of the number-range pages.
4. Observe WebView content while programmatic scrolling occurs.
5. Move TalkBack focus between content inside and outside the WebView.
6. Verify whether TalkBack receives and acts on accessibility focus events when its current focus is outside the WebView.

---

## iOS

A demo iOS app that reproduces the same scenario using SwiftUI and WKWebView. It loads the same web content and lets you test VoiceOver behavior during programmatic scrolling.

The app includes:
- A home screen with navigation links to number-range pages (1-100, 101-200, 201-300)
- A detail screen that hosts a WKWebView for each range

### Requirements

- Xcode 15 or newer
- iOS 17+ device or simulator
- (Optional) [XcodeGen](https://github.com/yonaskolb/XcodeGen) if regenerating the project from `project.yml`

### Build

Open `iOS/VoiceOverScrollTest.xcodeproj` in Xcode and build, or from the `iOS/` directory:

```bash
xcodebuild -project VoiceOverScrollTest.xcodeproj -scheme VoiceOverScrollTest -sdk iphonesimulator build
```

### VoiceOver Test Procedure

1. Enable VoiceOver on the device (Settings > Accessibility > VoiceOver).
2. Launch the app.
3. On the home screen, tap one of the number-range links.
4. Observe WebView content while programmatic scrolling occurs.
5. Move VoiceOver focus between content inside and outside the WebView.
6. Verify whether VoiceOver receives and acts on accessibility focus events when its current focus is outside the WebView.

---

## Notes

- The debug build is easiest for local reproduction on both platforms.
- Both apps connect to the same remote web page for consistent test content.

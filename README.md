# TalkbackScrollTest

A demo Android app showing that when a WebView scrolls programmatically, it sends accessibility focus events, but TalkBack cannot act on them when its focus is outside the WebView because it does not receive those accessibility events.

The app is built with Jetpack Compose and includes:
- A home screen with selectable number ranges
- A detail screen that hosts a WebView per range
- Accessibility pane titles for each screen

## Project Purpose

This project is intended to reproduce and observe a TalkBack behavior gap in mixed Compose + WebView UIs where focus/event handling may diverge from user expectations during programmatic scrolling.

## Requirements

- Android Studio Hedgehog or newer (or command-line Android SDK tools)
- JDK 17
- A connected Android device with ADB enabled

## Build

From the project root:

```bash
./gradlew assembleDebug
```

On Windows:

```powershell
gradlew.bat assembleDebug
```

## Install And Run

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -W com.example.talkbackscrolltest/.MainActivity
```

## TalkBack Test Procedure

1. Enable TalkBack on the device.
2. Launch the app.
3. On the home screen, open one of the number-range pages.
4. Observe WebView content while programmatic scrolling occurs.
5. Move TalkBack focus between content inside and outside the WebView.
6. Verify whether TalkBack receives and acts on accessibility focus events when its current focus is outside the WebView.

## Notes

- The debug build is easiest for local reproduction.
- A release APK may be unsigned depending on your signing configuration.

# Aurora Settings

Standalone Jetpack Compose prototype for Aurora OS.

## Requirements

- Android Studio with JDK 17
- Android SDK Platform 35
- Android SDK Build-Tools 35
- Gradle 8.9

## Project layout

The Android Gradle project is configured at the repository root. The app module is `:app`; its Kotlin sources remain under `apps/aurora-settings/app/src/main/java` and are included through the app module's source set.

## Open and build

Open the repository root in Android Studio. Allow Gradle sync to complete, then select the `:app` run configuration.

The Gradle wrapper distribution is configured for Gradle 8.9. Wrapper scripts and the wrapper JAR are not committed yet. If Gradle is installed locally, generate them with:

```bash
gradle wrapper --gradle-version 8.9
```

Then build a debug APK:

```bash
./gradlew :app:assembleDebug
```

APK output:

`app/build/outputs/apk/debug/app-debug.apk`

## Device testing

Enable USB debugging on an Android test device, connect it, then run:

```bash
./gradlew :app:installDebug
```

This is a standalone app prototype. It is not yet the privileged/system Settings app and does not modify Android system settings.

## Current status

- [x] Initial Compose screen
- [x] Aurora base palette
- [x] Gradle project configuration
- [ ] Search interaction
- [ ] Settings category navigation
- [ ] Dark theme
- [ ] Accessibility and responsive layout review

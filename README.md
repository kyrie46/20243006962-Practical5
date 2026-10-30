# Chinese Municipalities — Practical 5

Android application that lets the user type the name of one of China's four
direct-administered municipalities (Beijing, Shanghai, Chongqing or Tianjin) and
then displays a dedicated information screen for that municipality.

## Features

- **Main screen** (`MainActivity`, `activity_main.xml`)
  - App title, short introduction and the supplied China map image.
  - A labelled single-line `EditText` whose keyboard shows a **Go** action.
  - Pressing **Enter** or **Go** validates the input with `TextProcessor`.
  - Empty or unsupported input shows an error and keeps the user on the screen.
  - Valid input is normalised (`beijing` → `Beijing`) and passed to the second
    Activity through an **explicit Intent** extra.

- **Second screen** (`Municipality`, `activity_municipality.xml`)
  - Displays the correctly capitalised municipality name, its photograph and its
    factual summary.
  - The name banner background, the information text colour and the Visit site
    button colour change to the named colour of the selected municipality.
  - The summary is read from `municipalities_array` using the same order as the
    colour and image tables.
  - Missing or invalid Intent data is handled safely (message + `finish()`).

- **Visit site button**
  - Builds an **implicit** `Intent.ACTION_VIEW` for the official website of the
    municipality that is currently displayed.
  - Handles `ActivityNotFoundException` when no browser is installed.

## Resources

| Resource | Purpose |
| --- | --- |
| `res/drawable/chinese_municipalities.jpg` | Map shown on the main screen |
| `res/drawable/beijing.jpg`, `shanghai.jpg`, `tianjin.jpg`, `chongqing.jpg` | Municipality photographs |
| `res/drawable/ic_launcher_foreground.png` | Foreground layer of the adaptive launcher icon |
| `res/mipmap-*` | Legacy launcher icons for Android 7 and below |
| `res/values/strings.xml` | All user-facing text |
| `res/values/colors.xml` | Named municipality and text colours |
| `res/values/arrays.xml` | `municipalities_array` with the four summaries (80–120 words each) |
| `res/values/themes.xml` | Material 3 theme using the named colours |

The `ic_launcher` name is unchanged; the icon uses `app_logo.png` as the
foreground layer on a solid black background layer.

## Official websites

| Municipality | Website |
| --- | --- |
| Beijing | https://english.beijing.gov.cn/ |
| Shanghai | https://english.shanghai.gov.cn/ |
| Chongqing | https://english.cq.gov.cn/ |
| Tianjin | https://en.tj.gov.cn/ |

## Build and run

1. Open the project folder in Android Studio (JDK 17 or newer, Android SDK 37).
2. Let Gradle sync, then run the `app` configuration on a device or emulator.

From the command line:

```bash
./gradlew assembleDebug        # build the debug APK
./gradlew test                 # run the TextProcessor unit tests
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

## Project structure

```
app/src/main/java/com/example/practicalfive/
├── MainActivity.java     # input, validation and explicit Intent
├── Municipality.java     # second screen, dynamic styling, implicit Intent
└── TextProcessor.java    # plain Java validation/normalisation class
```

## Notes

- Validation lives in one place (`TextProcessor`), so neither Activity duplicates
  the list of supported municipalities.
- Only named colour resources are used; no colour hex values are repeated in
  Java code.
- Unit tests cover lower/upper/mixed case input, whitespace, unsupported names,
  null/blank input and the summary-array index mapping.

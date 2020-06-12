# UsefulKotlinUtil


[![Release](https://jitpack.io/v/jitpack/android-example.svg)](https://jitpack.io/#izzi888/UsefulKotlinUtil)

This Android library contains a few useful extensions and utils which I frequently use to create projects.

Extensions for: 
- Activity (eg. Fragment Transactions, Launch Activity)
- Fragment
- Log
- SharedPreferences
- String
- View

Utils:
- Connection (Retorfit + OkHttp)
- Common utils

## Download
Gradle is the only supported build configuration, so just add to your root 'build.gradle' file the JitPack repository

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

and the dependency

```gradle
dependencies {
    implementation 'com.github.izzi888:UsefulKotlinUtil:$libraryVersion'
}
```


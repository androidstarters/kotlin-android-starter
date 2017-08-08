# Android Kotlin Mvp Starter [![Build Status](https://travis-ci.org/androidstarters/kotlin-android-starter.svg?branch=master)](https://travis-ci.org/androidstarters/kotlin-android-starter)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20MVP%20Starter-blue.svg?style=flat)](https://android-arsenal.com/details/3/5232)
[![Join the chat at https://gitter.im/android-mvp-starter/Lobby](https://badges.gitter.im/android-mvp-starter/Lobby.svg)](https://gitter.im/android-mvp-starter/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

An MVP Boilerplate to save me having to create the same project over from scratch every time! :)
<p align="center">
  <img src="http://g.recordit.co/L5selg7aIv.gif" width="250">
  <img src="http://g.recordit.co/xt4o5wTySc.gif">
</p>

## This project uses:
- [RxJava2](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrofit](http://square.github.io/retrofit/) / [OkHttp](http://square.github.io/okhttp/)
- [Moshi](https://github.com/square/moshi)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Google Play Services](https://developers.google.com/android/guides/overview)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide 3](https://github.com/bumptech/glide)
- [Stetho](http://facebook.github.io/stetho/)
- [Espresso](https://google.github.io/android-testing-support-library/) for UI tests
- [Robolectric](http://robolectric.org/) for framework specific unit tests
- [Mockito](http://mockito.org/)

## Create new project using yeoman [generator-kotlin-android](https://github.com/androidstarters/generator-kotlin-android)
```bash
npm install -g yo
npm install -g generator-kotlin-android
mkdir NewApp && cd $_
yo kotlin-android
```

## Building

To build, install and run a debug version, run this from the root of the project:
```sh
./gradlew app:assembleDebug
```
    
## Testing

To run **unit** tests on your machine:

```sh
./gradlew test
```

To run **instrumentation** tests on connected devices:

```sh
./gradlew connectedAndroidTest
```

## Code Analysis tools

The following code analysis tools are set up on this project:

* [PMD](https://pmd.github.io/)

```sh
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/)

```sh
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/)

```sh
./gradlew checkstyle
```

## The check task

To ensure that your code is valid and stable use check:

```sh
./gradlew check
```

## License
```
MIT License

Copyright (c) 2017 Ravindra Kumar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

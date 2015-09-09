# FidoAndroidRPApp

[![Build Status](https://travis-ci.org/apowers313/FidoAndroidRPApp.svg?branch=master)](https://travis-ci.org/apowers313/FidoAndroidRPApp)

## About
A [Relying Party](https://en.wikipedia.org/wiki/Relying_party) Application for [FIDO Universal Authentication Framework (UAF) Authentication](https://fidoalliance.org/specifications/overview/). For service providers (websites, banks, retailers, blogs, email providers, etc.) that wish to replace their outdated and insecure username-and-password authentication with biometric authentication (fingerprint, voice, iris, etc.), this application serves as a starting point for showing how that integration can work. The Relying Party Application (or RP App for short) sits between the FIDO Client/Authenticator that provides biometric authentication and the FIDO Server that authenticates the client and proxies the JSON messages from the client to the server and vice versa.

Normally an RP App, such as this one, would have other functionality integrated, such as for banking transactions or online retail purchasing. Most service providers already have those applications, and this application shows how to add FIDO UAF support to those applications.

If you have any questions, please feel free to contact me.

## Build

For information on how to build and run Android apps, please refer to: [Android Developer: Running Your App](https://developer.android.com/training/basics/firstapp/running-app.html)

	./gradlew assembleDebug
	adb install -rg ./build/outputs/apk/fidoRpApp-debug.apk

## Test

Currently no tests
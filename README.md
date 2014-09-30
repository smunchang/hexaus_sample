<h2>Prerequisites</h2>

Before you begin, you need to register your application. 
After registering application, your will get developer code and application code.



API Integration

Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.


Add the SDK & APK file to Your Project
-. Copy the SDK(hexaus-sdk.jar) libs directory into your project's libs directory.
-. Copy the APK(hexaus-wallet.apk) assets directory into your project's libs directory.

ADD Permission
-. The SDK require several android permissions so you need to add lines of codes below into  the AndroidManifest.xml file of your project 

	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />

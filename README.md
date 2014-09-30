<h2>Registration</h2>
Before you begin, you need to register your application on the HEXAUS developer page. 
After registering application, your will get developer code and application code.



<h2>Integrating API</h2>
Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.
Before you start, you need to follow these steps below.

Add the SDK & APK file to Your Project
- Copy the SDK(hexaus-sdk.jar) libs directory into your project's libs directory.
- Copy the APK(hexaus-wallet.apk) assets directory into your project's libs directory.

ADD Permission
- Add lines of codes below into the AndroidManifest.xml file of your project 


<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
  

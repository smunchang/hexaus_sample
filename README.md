HEXAUS API provides four "use cases" which are initializing, purchasing item, sending message to friends and sending SMS message in your applicaton. Before you integrate this API, please download and execute this sample application. It is better to understand how HEXAUS API interact within your application.

## Pre-requites
You need to register as a developer on the HEXAUS developer page, then you will get developer code and application code.



## Integrating API
Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.
Before you start, you need to follow these steps below.

#####Add the SDK & APK file to Your Project
- Copy the SDK(hexaus-sdk.jar) libs directory into your project's <b>libs</b> directory.
- Copy the APK(hexaus-wallet.apk) assets directory into your project's assets directory.
```
NOTE
Whenever you build your application, please include the newest our SDK and APK.
```

#####ADD Permission
Add lines of codes below into the AndroidManifest.xml file of your project 

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

####Import Class
```
import com.hexaus.sdk.Hexaus;
```

## Initializing
you are recommended to implement this sample when MainActivity start (in the onCreate method).
```
ex)
application number -> sampleapp001
```
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	Hexaus hexaus = new Hexaus(getApplicationContext());

	if(hexaus.checkInstall()){
		ComponentName compName = new ComponentName("com.hexaus.wallet","com.hexaus.wallet.InitActivity");
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setComponent(compName);
		
		intent.putExtra("app_no", "sampleapp001");
		startActivity(intent);
	}else{
		hexaus.installHexaus();
	}
}
```

## Purchase Item
If user have enough balance to purchase item, "purchase dialog" will be shown. Or if balance is insufficent, "topup dialog" will be shown.

```
ex)
application number -> sampleapp001
item number -> item-000-001
item name -> Excellent Weapon
amount -> 50000
```
```
public void purchaseItem(View v) {

	Hexaus hexaus = new Hexaus(getApplicationContext());

	if(hexaus.checkInstall()){
		ComponentName compName = new ComponentName("com.hexaus.wallet","com.hexaus.wallet.PurchaseActivity");
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setComponent(compName);
		
		intent.putExtra("app_no", "sampleapp001");
		intent.putExtra("item_no", "item-000-001");
		intent.putExtra("item_nm", "Excellent Weapon");
		intent.putExtra("amount", "50000");
		startActivityForResult(intent, 0);
	}else{
		hexaus.installHexaus();
	}
	//

}
```
```
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
			if(data.getStringExtra("activity").equals("purchase")){
				Toast.makeText(this,"Purchase OK",Toast.LENGTH_LONG).show();
			}
		}
	}
```

## Send Message to Friend

```
ex)
application number -> sampleapp001
message -> This is a really nice game~~~~~!!!!!\nEnjoy!!!nEnjoy!!!nEnjoy!!!
```
```
	public void sendMessage(View v) {

		Hexaus hexaus = new Hexaus(getApplicationContext());

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hexaus.wallet","com.hexaus.wallet.FriendsActivity");
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(compName);
			
			intent.putExtra("app_no", "sampleapp001");
			intent.putExtra("message", "This is a really nice game~~~~~!!!!!\nEnjoy!!!nEnjoy!!!nEnjoy!!!");
			startActivityForResult(intent, 0);
		}else{
			hexaus.installHexaus();
		}

	}
```
```
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data.getStringExtra("activity").equals("friends")){
			Toast.makeText(this,"Friends OK",Toast.LENGTH_LONG).show();
		}
	}
```

## Send SMS 

```
ex)
application number -> sampleapp001
message -> This is a really nice game
```
```
	public void sendSMS(View v) {

		Hexaus hexaus = new Hexaus(getApplicationContext());

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hexaus.wallet","com.hexaus.wallet.ContactsActivity");
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(compName);
			
			intent.putExtra("app_no", "sampleapp001");
			intent.putExtra("message", "This is a really nice game");
			startActivityForResult(intent, 0);
		}else{
			hexaus.installHexaus();
		}

	}
```
```
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data.getStringExtra("activity").equals("contacts")){
			Toast.makeText(this,"Contacts OK",Toast.LENGTH_LONG).show();
		}
	}
```

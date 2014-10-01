HEXAUS API provides four "use cases" which are <b>initializing, purchasing item, sending message to friend and sending SMS message</b> in your applicaton. Before you integrate this API, please download and execute this sample application. It is better to understand how HEXAUS API interacts within your application.

## Pre-requites
You need to register as a developer on the HEXAUS developer page, then you will get developer code and application code.



## Integrating API
Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.
Before you start, you need to follow these steps below.

#####Add the SDK & APK files to your project
- Copy the SDK(hexaus-sdk.jar) to <b>/libs</b> directory in your project.
- Copy the APK(hexaus-wallet.apk) to <b>/assets</b> directory in your project.
```
NOTE
Whenever you build your application, please include the newest our SDK and APK.
```

#####Add Permission
Add lines of codes below into the <b>AndroidManifest.xml</b> file of your project 

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
It is optional, but we recommend to implement this when MainActivity start (in the onCreate method) because it collect user's information. By this information we will analyze and make a report for you.
```
ex)
application code -> sampleapp001
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
If a user has enough balance to purchase item, "purchase dialog" will be shown. Or if balance is insufficent, "topup dialog" will be shown.

```
ex)
application code -> sampleapp001
item code -> item-000-001
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
It is optional too, but it helps promote your application using our messaginr server.
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
It is optional too, but it helps promote your application by sending SMS to to non-hexaus user.
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

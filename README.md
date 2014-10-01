## Registration
Before you begin, you need to register your application on the HEXAUS developer page. 
After registering application, your will get developer code and application code.



## Integrating API
Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.
Before you start, you need to follow these steps below.

####Add the SDK & APK file to Your Project
- Copy the SDK(hexaus-sdk.jar) libs directory into your project's libs directory.
- Copy the APK(hexaus-wallet.apk) assets directory into your project's libs directory.

####ADD Permission
- Add lines of codes below into the AndroidManifest.xml file of your project 

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

## Import Class
```
import com.hexaus.sdk.Hexaus;
```

## Initializing
you are recommended to implement this sample when MainActivity start (in the onCreate method).
```
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

## Purchasing

```
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

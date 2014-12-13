HEXAUS API provides four "use cases" which are <b>initializing, purchasing item, sending message to friend, sending SMS message and showing rank information</b> in your applicaton. Before you integrate this API, please download and execute this sample application. It is better to understand how HEXAUS API interacts within your application.

## Pre-requisite
You need to register as a developer to HEXAUS.<br>
Pleae conatct to us (hexa@hexaus.com), then you will get developer code and application code.



## Integrating API
Hexaus API provides one SDK which is Java Library and one APK which includes HEXAUS Application.
Before you start, you need to follow these steps below.

#####Add the SDK files to your project
- Copy the SDK(hexaus-sdk.jar) to <b>/libs</b> directory in your project.
- Copy the APK(com.hxplay.app.apk) to <b>/assets</b> directory in your project.
```
NOTE
Whenever you build your application, please include the newest hexaus SDK & APK.
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
It is optional, but we recommend to implement this when MainActivity starts (in the onCreate method) because it collect user's information. By this information we will analyze and make a report for you.
```
ex)
application code -> sampleapp001
```
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	Hexaus hexaus = new Hexaus(this);

	if(hexaus.checkInstall()){
		ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.InitActivity");
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

	Hexaus hexaus = new Hexaus(this);

	if(hexaus.checkInstall()){
		ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.PurchaseActivity");
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

}
```
```
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
			if(data.getStringExtra("activity").equals("purchase")){
				Toast.makeText(this,"Purchase OK",Toast.LENGTH_LONG).show();
				
				Log.d("sample", data.getStringExtra("purchase_no"));  //hexaus purchae ID
				Log.d("sample", data.getStringExtra("item_no"));
			}
		}
	}
```

## Send Message to Friend
It is optional too, but it helps promote your application using our messaging server.
```
ex)
application code -> sampleapp001
message -> This is a really nice game~~~~~!!!!!\nEnjoy!!!nEnjoy!!!nEnjoy!!!
```
```
	public void sendMessage(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.FriendsActivity");
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
application code -> sampleapp001
message -> This is a really nice game
```
```
	public void sendSMS(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.ContactsActivity");
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
## Send game record 
It is optional too. By sending game record, Hexaus will show ranking information in game.
```
ex)
application code -> sampleapp001
unit -> points
record -> long type value

hexaus.sendRecord("sampleapp001", "points", record);

```
```
	private TextView txt_record;
	public void sendRecord(View v) {
		txt_record = (TextView) findViewById(R.id.txt_record);
		
		long record =  Long.parseLong(txt_record.getText().toString());
		Hexaus hexaus = new Hexaus(this);
		
		if(hexaus.checkInstall()){
			hexaus.sendRecord("sampleapp001", "points", record);
			Toast.makeText(this,"the record has been sent.",Toast.LENGTH_LONG).show();
		}else{
			hexaus.installHexaus();
		}
	}
```

## Open ranking information.
It is optional too. If user execute this case, user can see rank information.
```
ex)
application code -> sampleapp001

```
```
	public void openRanking(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.RankingActivity");
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

## Get ranking information.
It is optional too. You can get JSON type sting date for ranking information by calling this function.
```
ex)
	application code -> sampleapp001
	limit -> 5

	hexaus.getRanking("sampleapp001", 5);

```
```
	public void showRanking(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			String result = "";
			try {
				result = hexaus.getRanking("sampleapp001", 5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(this,result,Toast.LENGTH_LONG).show();
		}else{
			hexaus.installHexaus();
		}

	}
```
```
ex) result
	{
		["user_name":"Michael Jackson", "user_image":"http://file.hexaus.com/image/image1.jpg", "record":10000, "unit":"POINTS"],
		["user_name":"John Denver", "user_image":"http://file.hexaus.com/image/image2.jpg", "record":998, "unit":"POINTS"],
		...
	}

```

package com.hexaus.sample;

import com.hexaus.sdk.Hexaus;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

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

	}
	
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
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		

		if(resultCode == Activity.RESULT_OK){
			if(data.getStringExtra("activity").equals("purchase")){
				Toast.makeText(this,"Purchase OK",Toast.LENGTH_LONG).show();
			}
			if(data.getStringExtra("activity").equals("friends")){
				Toast.makeText(this,"Friends OK",Toast.LENGTH_LONG).show();
			}
			if(data.getStringExtra("activity").equals("contacts")){
				Toast.makeText(this,"Contact OK",Toast.LENGTH_LONG).show();
			}
		}

	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}

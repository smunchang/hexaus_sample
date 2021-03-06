package com.hexaus.sample;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hexaus.sdk.Hexaus;

public class MainActivity extends Activity {

	
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
			startActivityForResult(intent, 0);
		}else{
			hexaus.installHexaus();
		}
		
		
	}
	
	public void purchaseItem(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.PurchaseActivity");
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(compName);
			
			intent.putExtra("app_no", "AP2015027831");
			intent.putExtra("item_no", "1");
			startActivityForResult(intent, 0);
		}else{
			hexaus.installHexaus();
		}

	}
	
	public void sendMessage(View v) {

		Hexaus hexaus = new Hexaus(this);

		if(hexaus.checkInstall()){
			ComponentName compName = new ComponentName("com.hxplay.app","com.hxplay.app.FriendsActivity");
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(compName);
			
			intent.putExtra("app_no", "sampleapp001");
			intent.putExtra("message", "This is a really nice game~~~~~!!!!!\nEnjoy!!!!!!");
			startActivityForResult(intent, 0);
		}else{
			hexaus.installHexaus();
		}

	}
	
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
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		

		if(resultCode == Activity.RESULT_OK){
			if(data.getStringExtra("activity").equals("initialize")){
				Toast.makeText(this,"Initialize OK",Toast.LENGTH_LONG).show();
				
				Log.d("sample", data.getStringExtra("device_no"));
				Log.d("sample", data.getStringExtra("device_nm"));
				Log.d("sample", data.getStringExtra("device_img"));
				
			}
			if(data.getStringExtra("activity").equals("purchase")){
				Toast.makeText(this,"Purchase OK",Toast.LENGTH_LONG).show();
				
				Log.d("sample", data.getStringExtra("item_no"));
				Log.d("sample", data.getStringExtra("purchase_no"));
				Log.d("sample", data.getStringExtra("purchase_cd"));
				
			}
			if(data.getStringExtra("activity").equals("friends")){
				Toast.makeText(this,"message has been sent to your friend",Toast.LENGTH_LONG).show();
			}
			if(data.getStringExtra("activity").equals("contacts")){
				Toast.makeText(this,"sms message has been sent to your contact",Toast.LENGTH_LONG).show();
			}
		}

	}
	
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
	
}

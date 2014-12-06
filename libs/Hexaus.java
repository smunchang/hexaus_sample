package com.hexaus.sdk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;



import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.LinearLayout;



public class Hexaus {
	


	private Context context;
	private String app_apk = "com.hxplay.app.apk";
	private String app_package = "com.hxplay.app";
	
	public Hexaus(Context c){
		this.context = c;
    }


	public Boolean checkInstall(){

		try {
			int versionCode = context.getPackageManager().getPackageInfo(app_package, 0).versionCode;
			return true;
		} catch (NameNotFoundException e){
			return false;			
		}
		
	}
	

/*	public void installHexaus(){

		Locale locale = context.getResources().getConfiguration().locale;
		String language =  locale.getLanguage();
		String title = "";
		String message = "";
		String yes = "";
		String no = "";
		
		if(language.equals("en")){
			title = "Install HEXAUS Platform";
			message = "Install a HEXAUS Mobile Game Platform app now! The HEXAUS platform provide In-App-Purchase and more secure / conviniece for your mobile life."
					+ "\n\n" + "Do you want to install the HEXAUS now?";
					//+ "\n\n" + "* No required internet connection for this process.";
			yes = "YES";
			no = "NO";
		}else{
			title = "INSTAL HEXAUS Platform";
			message = "Instal aplikasi HEXAUS Mobile Game platform sekarang! HEXAUS platform menyediakan In-App-Purchase dan lebih aman / kenyamanan untuk kehidupan mobile Anda."
					+ "\n\n" + "Apakah Anda ingin menginstal HEXAUS sekarang?";
					//+ "\n\n" + "* Tidak perlu koneksi internet untuk proses ini";
			yes = "YA";
			no = "TITAK";
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message);


		// Add the buttons
		builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				try {
					PackageInfo p = context.getPackageManager().getPackageInfo("com.android.vending", 0);
					Intent intent = new Intent(Intent.ACTION_VIEW);
	    			intent.setData(Uri.parse("market://details?id=com.hxplay.app"));
	    			context.startActivity(intent);
				} catch (NameNotFoundException e) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.hxplay.app"));
	            	context.startActivity(intent);
					
				}
			}
		});
		builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               
           }
		});
		AlertDialog dialog = builder.create();
		dialog.show();

	}*/
	
	public void installHexaus(){

//		Locale locale = context.getResources().getConfiguration().locale;
//		String language =  locale.getLanguage();
		String title = "";
		String message = "";
		String yes = "";
		String no = "";
		
		
		title = "INSTAL HEXAUS Platform";
		message = "Aplikasi ini beroperasi berdasarkan pada platform game HEXAUS. Anda harus menginstal platform sebelum pakai aplikasi in"
				+ "\n\n" + "Apakah Anda ingin menginstal HEXAUS sekarang?"
				+ "\n\n" + "* Tidak perlu koneksi internet untuk proses ini";
		yes = "YA";
		no = "TITAK";
//		if(language.equals("en")){
//			title = "Install HEXAUS Platform";
//			message = "Install a HEXAUS Mobile Game Platform app now! The HEXAUS platform provide In-App-Purchase and more secure / conviniece for your mobile life."
//					+ "\n\n" + "Do you want to install the HEXAUS now?"
//					+ "\n\n" + "* No required internet connection for this process.";
//			yes = "YES";
//			no = "NO";
//		}else{
//			title = "INSTAL HEXAUS Platform";
//			message = "Instal aplikasi HEXAUS Mobile Game platform sekarang! HEXAUS platform menyediakan In-App-Purchase dan lebih aman / kenyamanan untuk kehidupan mobile Anda."
//					+ "\n\n" + "Apakah Anda ingin menginstal HEXAUS sekarang?"
//					+ "\n\n" + "* Tidak perlu koneksi internet untuk proses ini";
//			yes = "YA";
//			no = "TITAK";
//		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message);


		// Add the buttons
		builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				AssetManager assetManager = context.getAssets();

				InputStream in = null;
				OutputStream out = null;
			
				try{

					in = assetManager.open(app_apk);
					File dir = new File (Environment.getExternalStorageDirectory() + "/hexaus");
		            dir.mkdirs();
					out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/hexaus/" + app_apk);

					
				    byte[] buffer = new byte[1024];

				    int read;
				    while((read = in.read(buffer)) != -1) {
				        out.write(buffer, 0, read);
				    }

				    in.close();
				    in = null;

				    out.flush();
				    out.close();
				    
				    out = null;

				    Intent intent = new Intent( Intent.ACTION_VIEW);
				    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/hexaus/" + app_apk)), "application/vnd.android.package-archive");
				    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    context.startActivity(intent);
				    
				    
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               
           }
		});
		AlertDialog dialog = builder.create();
		dialog.show();

	}
	
	public void sendRecord(String app_no, String unit, long record){
		Intent intent = new Intent();
		intent.setAction("com.hxplay.app.record"); // Action name
		intent.putExtra("app_no", app_no);
		intent.putExtra("unit", unit);
		intent.putExtra("record", record);
		context.sendBroadcast(intent);
		
		
		
		//Log.e("hexa", listRanking(app_no, 5)+"") ;
		
		try {
			Log.e("hexa", listRanking(app_no, 5)+"") ;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String listRanking(String app_no, int limit) throws ClientProtocolException, IOException{
		
		String result = "";
		try {
			result = new RestRanking().execute(app_no, String.valueOf(limit)).get();
			
			JSONObject jsonOBject = new JSONObject(result);
			JSONObject da = (JSONObject) jsonOBject.get("DeviceApps");
			
			result = da.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private class RestRanking  extends AsyncTask<String, Integer, String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);



		}	
	    @Override
	    protected String doInBackground(String... params) {
	    	String result = "";
	    	try{
	    		String url = "http://api.hexaus.com/ranking_all/" + params[0] + "/" + params[1];
	    		StringBuilder builder = new StringBuilder();
	    		
	    		HttpClient httpClient = new DefaultHttpClient();
	    		HttpGet httpGet = new HttpGet(url);
//	    		httpGet.addHeader("accept", "application/json");
	    		
	    		
	    		HttpResponse httpResponse = httpClient.execute(httpGet);
	    		StatusLine statusLine = httpResponse.getStatusLine();
	    	    int statusCode = statusLine.getStatusCode();
	    	    if (statusCode == 200) {
	    	        HttpEntity entity = httpResponse.getEntity();
	    	        InputStream content = entity.getContent();
	    	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	    	        String line;
	    	        while ((line = reader.readLine()) != null) {
	    	        	builder.append(line);
	    	        }
	    	    } else {
	    	        //Log.e(ParseJSON.class.toString(), "Failed to download file");
	    	    }

				return builder.toString();
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    		return result;
	    	}
	    }

	}
}

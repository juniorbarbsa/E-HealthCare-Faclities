package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.ehealthcarefacilities.Map;
import com.project.ehealthcarefacilities.R;
import com.project.ehealthcarefacilities.JSONParser;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @SuppressLint("NewApi") 

public class Activity_Main_Screen extends ActionBarActivity {

	NotificationManager NM;
	
	Button buton_signup;
	Button button_login;
	Button btnEmergency;
	
	EditText edittext_username;
	EditText edittext_pssword;
	
	String username = null;
	String password = null;
	String id = null;
	String name = null;
	
	
	private ProgressDialog pDialog;
	private static String login_url = Urls.login;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SESSION = "session";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_BLOOG_GROUP = "bg";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_AGE = "age";
	private static final String TAG_BIRTH_DATE = "dob";
	private static final String TAG_CONTACT_NO = "mob";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_USERNAME = "uname";
	private static final String TAG_PASSWORD = "password";
	private static final String TAG_TODAY = "today";
	private static final String TAG_TOMORROW = "tomorrow";
	private static final String TAG_DAY3 = "day3";
	private static final String TAG_DAY4 = "day4";
	int success;
	JSONParser jParser = new JSONParser();
	
	JSONObject json;
	JSONArray productObj;
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main_screen);
		
		final Context context = this;
		
		buton_signup = (Button) findViewById(R.id.main_screen_button_signup);
		button_login = (Button) findViewById(R.id.main_screen_button_login);
		edittext_username = (EditText) findViewById(R.id.main_screen_edit_text_username);
		edittext_pssword = (EditText) findViewById(R.id.main_screen_edit_text_password);
		btnEmergency = (Button) findViewById(R.id.BtnEmergency);
		
		buton_signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_Main_Screen.this,Activity_Sign_up.class));
			}
		});
		
		btnEmergency.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, Map.class);
                            startActivity(intent);   
 
			}
 
		});
		
		button_login.setOnClickListener(new OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username="";
				password="";
		
				username = edittext_username.getText().toString();
				password = edittext_pssword.getText().toString();
				
				if(username.trim().length() > 0){
					if(password.trim().length() > 0){
						//Uri soundUri = RingtoneManager.
						Uri alarmSound =  Uri.parse("file:///android_asset/Sound/Long/AlarmClock.mp3");
					   // intent triggered, you can add other intent for other actions
						 Intent intent = new Intent(Activity_Main_Screen.this, NotificationManager.class);
					PendingIntent pIntent = PendingIntent.getActivity(Activity_Main_Screen.this, 0, intent, 0);
						
					      // this is it, we'll build the notification!
						        // in the addAction method, if you don't want any icon, just set the first param to 0
					/*Resources r = getResources() ;
					PendingIntent pi = PendingIntent. getActivity(Activity_Main_Screen.this, 0, new Intent(Activity_Main_Screen.this, Activity_home. class) , 0) ;
					Notification notification = new NotificationCompat. Builder(Activity_Main_Screen.this)
					. setTicker(r. getString(R.string.Appointment) )
					. setSmallIcon(android. R. drawable.list_selector_background)
					. setSound (alarmSound)
					. setContentIntent(pi)
					. setAutoCancel(true)
					. build() ;
					NotificationManager notificationManager = (NotificationManager)
					getSystemService(NOTIFICATION_SERVICE) ;
					notificationManager. notify(0, notification) ;*/
						new validate().execute();
						}
					else{
						Toast.makeText(Activity_Main_Screen.this, "Enter Password", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(Activity_Main_Screen.this, "Enter Username", Toast.LENGTH_LONG).show();
				}//startActivity(new Intent(getApplicationContext(), Activity_Appointment.class));
				
			}
		});
			
	}

	 @Override
	    public void onBackPressed() {
	        //Display alert message when back button has been pressed
		 Intent j=new Intent(Intent.ACTION_MAIN);
		 j.addCategory(Intent.CATEGORY_HOME);
		 j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 startActivity(j);
		 finish();
		 System.exit(0);
	    }
	 
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__main__screen, menu);
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
	
	class validate extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Main_Screen.this);
			pDialog.setMessage("Validating... Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread

			// Check for success tag
			
			try {
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("uname", username));
				params1.add(new BasicNameValuePair("pword", password));
				json = jParser.makeHttpRequest(login_url,"POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
					if (success == 1) {
						//System.out.println("in success.");
						runOnUiThread(new Runnable() {
							public void run() {
								
						try {
							
							
							productObj = json.getJSONArray(TAG_SESSION);
						
						// get first product object from JSON Array
						JSONObject product = productObj.getJSONObject(0);

						// product with this pid found
						// Edit Text
						//System.out.println("after jarray.");
											
						SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
						Editor editor = pref.edit();
						
						editor.putBoolean("IS_LOGIN", true); // Storing boolean - true/false
						editor.putString("id",product.getString(TAG_ID)); 
						editor.putString("name", product.getString(TAG_NAME));
						editor.putString("address", product.getString(TAG_ADDRESS));
						editor.putString("bg", product.getString(TAG_BLOOG_GROUP));
						editor.putString("age", product.getString(TAG_AGE));
						editor.putString("dob", product.getString(TAG_BIRTH_DATE));
						editor.putString("mob", product.getString(TAG_CONTACT_NO));
						editor.putString("email", product.getString(TAG_EMAIL));
						editor.putString("uname", product.getString(TAG_USERNAME));
						editor.putString("gender", product.getString(TAG_GENDER));
						editor.putString("password", product.getString(TAG_PASSWORD));
						
						editor.putString("today", product.getString(TAG_TODAY)); 
						editor.putString("tomorrow", product.getString(TAG_TOMORROW));
						editor.putString("day3", product.getString(TAG_DAY3));
						editor.putString("day4", product.getString(TAG_DAY4));
						
						System.out.println(product.getString(TAG_TODAY));
						editor.commit(); // commit changes
						edittext_username.setText("");
						edittext_pssword.setText("");
						startActivity(new Intent(Activity_Main_Screen.this, Activity_home.class));
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // JSON Array
							}
						});
						
					}else{
						
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(Activity_Main_Screen.this, "Invalid Username/ Password..", Toast.LENGTH_LONG).show();
							}
						});
					
				}
			
				} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}
	
	
}

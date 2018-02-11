package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.ehealthcarefacilities.JSONParser;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Sign_up extends ActionBarActivity implements OnItemSelectedListener{

	Button button_signup;
	Spinner spinner_bg, spinner_gender;
	EditText edittext_name;
	EditText edittext_address;
	EditText edittext_blood_group;
	EditText edittext_gender;
	EditText edittext_age;
	EditText edittext_birth_date;
	EditText edittext_contact;
	EditText edittext_email;
	EditText edittext_username;
	EditText edittext_password;
	EditText edittext_confirm_password;
	
	String string_name;
	String string_address;
	String string_blood_group;
	String string_gender;
	String string_age;
	String string_birth_date;
	String string_contact;
	String string_email;
	String string_username;
	String string_password;
	String string_confirm_password;
	String id = null;
	String name = null;
	private ProgressDialog pDialog;
	private static String signup_url = Urls.signup;
	private static final String TAG_SUCCESS = "success";
	
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sign_up);
	
			
		spinner_bg=(Spinner)findViewById(R.id.signup_spinner_blood_group);
		spinner_gender=(Spinner)findViewById(R.id.signup_spinner_gender);
		
		
		List<String> list1 = new ArrayList<String>();
		list1.add("Select");
		list1.add("O+");
		list1.add("A+");
		list1.add("B+");
		list1.add("AB+");
		list1.add("O-");
		list1.add("A-");
		list1.add("B-");
		list1.add("AB-");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list1  );
         
		dataAdapter.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);
		spinner_bg.setAdapter(dataAdapter);
		
		
		List<String> list2 = new ArrayList<String>();
		list2.add("Select");
		list2.add("Male");
		list2.add("Female");
	
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list2  );
         
		dataAdapter2.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);
		spinner_gender.setAdapter(dataAdapter2);
		
		
		button_signup =(Button) findViewById(R.id.signup_button_signup);
		spinner_bg = (Spinner) findViewById(R.id.signup_spinner_blood_group);
		spinner_gender = (Spinner) findViewById(R.id.signup_spinner_gender);
		edittext_name = (EditText) findViewById(R.id.signup_edittext_name);
		edittext_address = (EditText) findViewById(R.id.signup_edittext_address);
		edittext_age = (EditText) findViewById(R.id.signup_edittext_age);
		edittext_birth_date = (EditText) findViewById(R.id.signup_edittext_date);
		edittext_contact = (EditText) findViewById(R.id.signup_edittext_contact_no);
		edittext_email = (EditText) findViewById(R.id.signup_edittext_email);
		edittext_username = (EditText) findViewById(R.id.signup_edittext_username);
		edittext_password = (EditText) findViewById(R.id.signup_edittext_password);
		edittext_confirm_password = (EditText) findViewById(R.id.signup_edittext_confirm_password);
		
		spinner_gender.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				string_gender=adapter.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		spinner_bg.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				string_blood_group=adapter.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button_signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				string_name=edittext_name.getText().toString();
				string_address=edittext_address.getText().toString();
				string_age=edittext_age.getText().toString();
				string_birth_date=edittext_birth_date.getText().toString();
				string_contact=edittext_contact.getText().toString();
				string_email=edittext_email.getText().toString();
				string_username=edittext_username.getText().toString();
				string_password=edittext_password.getText().toString();
				string_confirm_password=edittext_confirm_password.getText().toString();
				
				if(string_name.trim().length() > 0 && string_address.trim().length() > 0 && string_age.trim().length() > 0 && string_birth_date.trim().length() > 0 && string_contact.trim().length() > 0 && string_email.trim().length() > 0 && string_username.trim().length() > 0 && string_password.trim().length() > 0 && string_confirm_password.trim().length() > 0 ){
					
					if(string_contact.trim().length() < 10){
						runOnUiThread(new Runnable() {
							public void run() {
								edittext_contact.setText("");
								Toast.makeText(Activity_Sign_up.this, "Incorrect Contact number", Toast.LENGTH_LONG).show();
								
							}
						});
					}else{
						String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
						if(string_email.matches(emailreg)){
							if(string_password.equals(string_confirm_password)){
								if(string_blood_group.equals("Select")){
									runOnUiThread(new Runnable() {
										public void run() {
											Toast.makeText(Activity_Sign_up.this, "Select Blood Group", Toast.LENGTH_LONG).show();
											
										}
									});
									
								}else{
									if(string_gender.equals("Select")){
										runOnUiThread(new Runnable() {
											public void run() {
												Toast.makeText(Activity_Sign_up.this, "Select Gender", Toast.LENGTH_LONG).show();
												
											}
										});
									}else{
								new addtodb().execute();
									}
								}
							}else{
								runOnUiThread(new Runnable() {
									public void run() {
										edittext_password.setText("");
										edittext_confirm_password.setText("");
										Toast.makeText(Activity_Sign_up.this, "Passwords Do Not Match", Toast.LENGTH_LONG).show();
										
									}
								});
								
							}
							
						}else{
							
							runOnUiThread(new Runnable() {
								public void run() {
									edittext_email.setText("");
									Toast.makeText(Activity_Sign_up.this, "Incorrect Email ID", Toast.LENGTH_LONG).show();
									
								}
							});
							
						}
						
					}
					
					
					
					
					
					
				}else{
					runOnUiThread(new Runnable() {
						public void run() {
							
							Toast.makeText(Activity_Sign_up.this, "Enter the fields Properly", Toast.LENGTH_LONG).show();
							
						}
					});
				}
				
					
			}
		
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__sign_up, menu);
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	class addtodb extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Sign_up.this);
			pDialog.setMessage("Adding to database");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", string_name));
			params.add(new BasicNameValuePair("address", string_address));
			params.add(new BasicNameValuePair("age", string_age));
			params.add(new BasicNameValuePair("blood_group", string_blood_group));
			params.add(new BasicNameValuePair("gender", string_gender));
			params.add(new BasicNameValuePair("birth_date", string_birth_date)); 
			params.add(new BasicNameValuePair("contact", string_contact));
			params.add(new BasicNameValuePair("email", string_email));
			params.add(new BasicNameValuePair("username", string_username));
			params.add(new BasicNameValuePair("password", string_password));
			
			

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(signup_url,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					
					
					SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
					Editor editor = pref.edit();
					
					editor.putBoolean("IS_LOGIN", true); // Storing boolean - true/false
					//editor.putInt("id",Integer.parseInt(id) ); // Storing integer
					editor.putString("name", string_name); // Storing string
					editor.putString("uname", string_username); // Storing string
					editor.commit(); // commit changes
					string_name= "";
					string_address= "";
					string_age= "";
					string_birth_date= "";
					string_contact= "";
					string_email= "";
					string_username= "";
					string_password= "";
					string_confirm_password= "";
					string_blood_group= "";
					string_gender= "";
					runOnUiThread(new Runnable() {
						public void run() {
							
							Toast.makeText(Activity_Sign_up.this, "You are now registered. Please login to continue", Toast.LENGTH_LONG).show();
						}
					});
					startActivity(new Intent(Activity_Sign_up.this, Activity_Main_Screen.class));
				
					
					// closing this screen
					//
				} else {
					// failed to create product
					runOnUiThread(new Runnable() {
						public void run() {
							edittext_username.setText("");
							Toast.makeText(Activity_Sign_up.this, "Username already exist.", Toast.LENGTH_LONG).show();
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
			// dismiss the dialog once done
			pDialog.dismiss();
		}
	}
	
	
}

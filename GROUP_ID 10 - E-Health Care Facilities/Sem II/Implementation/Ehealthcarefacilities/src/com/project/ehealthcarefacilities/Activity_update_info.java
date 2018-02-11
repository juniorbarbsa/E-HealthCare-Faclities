package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.ehealthcarefacilities.JSONParser;


import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

public class Activity_update_info extends ActionBarActivity implements OnItemSelectedListener{

	
	Button button_update;
	Spinner spinner_bg, spinner_gender;
	EditText edittext_name;
	EditText edittext_address;
	EditText edittext_blood_group;
	EditText edittext_gender;
	EditText edittext_age;
	EditText edittext_birth_date;
	EditText edittext_contact;
	EditText edittext_email;
	TextView textview_username;
	EditText edittext_password;
	EditText edittext_confirm_password;
	
	String string_id;
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
	
	private ProgressDialog pDialog;
	private static String update_url = Urls.update;
	private static final String TAG_SUCCESS = "success";
	
	
	JSONParser jParser = new JSONParser();
	
	JSONObject json;
	JSONArray productObj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_update_info);
		
		Intent i =getIntent();
		
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		Editor editor = pref.edit();
		
		String id = pref.getString("id", null);
		System.out.println("after pref");
		if(id.equals(null)){
			startActivity(new Intent(Activity_update_info.this, Activity_Main_Screen.class));
		}else{
			System.out.println("after pref else ");
			spinner_bg = (Spinner) findViewById(R.id.update_spinner_blood_group);
			spinner_gender = (Spinner) findViewById(R.id.update_spinner_gender);
			edittext_name = (EditText) findViewById(R.id.update_edittext_name);
			edittext_address = (EditText) findViewById(R.id.update_edittext_address);
			edittext_age = (EditText) findViewById(R.id.update_edittext_age);
			edittext_birth_date = (EditText) findViewById(R.id.update_edittext_date);
			edittext_contact = (EditText) findViewById(R.id.update_edittext_contact_no);
			edittext_email = (EditText) findViewById(R.id.update_edittext_email);
			textview_username = (TextView) findViewById(R.id.update_textview_username1);
			edittext_password = (EditText) findViewById(R.id.update_edittext_password);
			edittext_confirm_password = (EditText) findViewById(R.id.update_edittext_confirm_password);
			System.out.println("after findview");
			List<String> list1 = new ArrayList<String>();
			list1.add(pref.getString("bg", null));
			list1.add("---Choose---");			
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
			list2.add(pref.getString("gender", null));
			list2.add("---Choose---");
			list2.add("Male");
			list2.add("Female");
		
			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
	        (this, android.R.layout.simple_spinner_item,list2  );
	         
			dataAdapter2.setDropDownViewResource
	        (android.R.layout.simple_spinner_dropdown_item);
			spinner_gender.setAdapter(dataAdapter2);
			
			string_id= pref.getString("id", "");
			edittext_name.setText(pref.getString("name", ""));
			edittext_address.setText(pref.getString("address", ""));
			edittext_age.setText(pref.getString("age", ""));
			edittext_birth_date.setText(pref.getString("dob", ""));
			edittext_contact.setText(pref.getString("mob", ""));
			edittext_email.setText(pref.getString("email", ""));
			textview_username.setText(pref.getString("uname", ""));
			edittext_password.setText(pref.getString("password", ""));
			edittext_confirm_password.setText(pref.getString("password", ""));
			System.out.println("after setting edittexts");
		}
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

		button_update = (Button) findViewById(R.id.update_button_update_info);
		button_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				string_name=edittext_name.getText().toString();
				string_address=edittext_address.getText().toString();
				string_age=edittext_age.getText().toString();
				string_birth_date=edittext_birth_date.getText().toString();
				string_contact=edittext_contact.getText().toString();
				string_email=edittext_email.getText().toString();
				string_username=textview_username.getText().toString();
				string_password=edittext_password.getText().toString();
				string_confirm_password=edittext_confirm_password.getText().toString();
				
if(string_name.trim().length() > 0 && string_address.trim().length() > 0 && string_age.trim().length() > 0 && string_birth_date.trim().length() > 0 && string_contact.trim().length() > 0 && string_email.trim().length() > 0 && string_username.trim().length() > 0 && string_password.trim().length() > 0 && string_confirm_password.trim().length() > 0 ){
					
					if(string_contact.trim().length() < 10){
						runOnUiThread(new Runnable() {
							public void run() {
								edittext_contact.setText("");
								Toast.makeText(Activity_update_info.this, "Incorrect Contact number", Toast.LENGTH_LONG).show();
								
							}
						});
					}else{
						String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
						if(string_email.matches(emailreg)){
							if(string_password.equals(string_confirm_password)){
								if(string_blood_group.equals("Select")){
									runOnUiThread(new Runnable() {
										public void run() {
											Toast.makeText(Activity_update_info.this, "Select Blood Group", Toast.LENGTH_LONG).show();
											
										}
									});
									
								}else{
									if(string_gender.equals("Select")){
										runOnUiThread(new Runnable() {
											public void run() {
												Toast.makeText(Activity_update_info.this, "Select Gender", Toast.LENGTH_LONG).show();
												
											}
										});
									}else{
										runOnUiThread(new Runnable() {
											public void run() {
												
										
								new updatedb().execute();
											}
										});
									}
								}
							}else{
								runOnUiThread(new Runnable() {
									public void run() {
										edittext_password.setText("");
										edittext_confirm_password.setText("");
										Toast.makeText(Activity_update_info.this, "Passwords Do Not Match", Toast.LENGTH_LONG).show();
										
									}
								});
								
							}
							
						}else{
							
							runOnUiThread(new Runnable() {
								public void run() {
									edittext_email.setText("");
									Toast.makeText(Activity_update_info.this, "Incorrect Email ID", Toast.LENGTH_LONG).show();
									
								}
							});
							
						}
						
					}
					
					
					
					
					
					
				}else{
					runOnUiThread(new Runnable() {
						public void run() {
							
							Toast.makeText(Activity_update_info.this, "Enter the fields Properly", Toast.LENGTH_LONG).show();
							
						}
					});
				}
				
				
			}
		});
					
	}
	
	public void onBackPressed() {
        //Display alert message when back button has been pressed
        backButtonHandler();
        return;
    }
 
    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Activity_update_info.this);
        // Setting Dialog Title
        alertDialog.setTitle("Move Back?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to go back without updating Datea?");
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	
        				Intent intent =new Intent(Activity_update_info.this,Activity_home.class);
        				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        				startActivity(intent);
        				
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_update_info, menu);
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
	
	class updatedb extends AsyncTask<String, String, String> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_update_info.this);
			pDialog.setMessage("Updating database");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			
			
					
				
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", string_id));
			params.add(new BasicNameValuePair("name", string_name));
			params.add(new BasicNameValuePair("address", string_address));
			params.add(new BasicNameValuePair("age", string_age));
			params.add(new BasicNameValuePair("blood_group", string_blood_group));
			params.add(new BasicNameValuePair("gender", string_gender));
			params.add(new BasicNameValuePair("birth_date", string_birth_date)); 
			params.add(new BasicNameValuePair("contact", string_contact));
			params.add(new BasicNameValuePair("email", string_email));
			params.add(new BasicNameValuePair("password", string_password));
			try {
			

			// getting JSON Object
			// Note that create product url accepts POST method
	
			JSONObject json = jParser.makeHttpRequest(update_url,
					"POST", params);
			System.out.println("req sent ");
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					
					
					SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
					Editor editor = pref.edit();
					
					editor.putBoolean("IS_LOGIN", true); // Storing boolean - true/false
					//editor.putInt("id",Integer.parseInt(id) ); // Storing integer
					editor.putString("name", string_name); // Storing string
					editor.putString("address", string_address);
					editor.putString("bg", string_blood_group);
					editor.putString("age", string_age);
					editor.putString("dob", string_birth_date);
					editor.putString("mob", string_contact);
					editor.putString("email", string_email);
					editor.putString("uname", string_username);
					editor.putString("gender", string_gender);
					editor.putString("password", string_password);
					
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
							Toast.makeText(Activity_update_info.this, "Information updated", Toast.LENGTH_LONG).show();
						}
					});
					startActivity(new Intent(Activity_update_info.this, Activity_home.class));
				
					
					// closing this screen
					//
				} else {
					// failed to create product
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Activity_update_info.this, "Error in connection..", Toast.LENGTH_LONG).show();
						}
					});
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
				

			return null;
		}

	
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}
	}
	
}


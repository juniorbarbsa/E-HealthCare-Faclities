package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.Calendar;

import com.project.ehealthcarefacilities.*;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;












import com.project.ehealthcarefacilities.R.id;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class Activity_Appointment extends Activity implements OnItemSelectedListener{

	
	private ProgressDialog pDialog;
	private static String url_getdoctor = Urls.getdoctor;
	private static String url_appointment = Urls.appointment;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_DOCTORS = "doctors";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_HOSPITAL = "hospital";
	private static final String TAG_APPID = "appid";


	
	ArrayAdapter<String> dataAdapter1,dataAdapter2,dataAdapter3,dataAdapter4;
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	List<String> list4 = new ArrayList<String>();
	List<String> list5 = new ArrayList<String>();
	List<String> list6 = new ArrayList<String>();
	
	int success;
	String appid;
	boolean flag,x;
	String doctor_id,doctor_name,time, app_date,speciality;
	//public static String[] ids;
	//public static int count=0;
	JSONParser jParser = new JSONParser();
	
	JSONObject json;
	JSONArray doctorObj;
	JSONArray dates=null;
	JSONArray doctors = null;
	
	String string_spinner_speciality;
	Spinner select_speciality, select_doctor, select_timings, select_date;
	Button button_take_appointment;
	String today,tomorrow,day3,day4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_appointment);
		
		
		
		
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		Editor editor = pref.edit();
		
		String name = null;
		
		name = pref.getString("name", name);
		if(name.equals(null)){
			startActivity(new Intent(Activity_Appointment.this, Activity_Main_Screen.class));
		}
			list5.clear();
			today=pref.getString("today", null);
			tomorrow=pref.getString("tomorrow", null);
			day3=pref.getString("day3", null);
			day4=pref.getString("day4", null);		
			
		
		select_speciality = (Spinner) findViewById(R.id.appointment_speciality);
		select_doctor = (Spinner) findViewById(R.id.appointment_doc_name);
		select_date = (Spinner) findViewById(R.id.appointment_date);
		select_timings = (Spinner) findViewById(R.id.appointment_timing);
		button_take_appointment = (Button) findViewById(R.id.appointment_take_appointment);
				
			
		list1.add("Select");
		list1.add("ENT");
		list1.add("CARDIOLOGIST");
		list1.add("GENERAL PHYSICIAN");
		list1.add("PEDIATRICIAN");
		list1.add("ORTHOPEDIST");
		
		
		
		
		dataAdapter1 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list1  );
         
		dataAdapter1.setDropDownViewResource
        (android.R.layout.simple_spinner_dropdown_item);
		select_speciality.setAdapter(dataAdapter1);
		
		button_take_appointment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				runOnUiThread(new Runnable() {
					public void run() {
						if(flag){
					new appointment().execute();
						}else{
							Toast.makeText(Activity_Appointment.this, "Select Speciality.", Toast.LENGTH_LONG).show();
										
							}
					}
				});
				
				
				
			}
		});//button ends
		select_date.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				app_date= adapter.getItemAtPosition(position).toString();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		select_doctor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				doctor_id= list4.get(position);		
				doctor_name = list6.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		select_timings.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				time= adapter.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		select_speciality.setOnItemSelectedListener(new OnItemSelectedListener() {


				@Override
				public void onItemSelected(AdapterView<?> adapter, View v, int position,
						long id) {
					// TODO Auto-generated method stub
					string_spinner_speciality=adapter.getItemAtPosition(position).toString();
					if(!string_spinner_speciality.equals("Select")){
						flag=true;
						speciality=adapter.getItemAtPosition(position).toString();
					runOnUiThread(new Runnable() {
						public void run() {
							doctor_id=null;time=null;
							list4.clear();
							list5.clear();
							new getdoctor().execute();
							
						}
					});
					
				}else{
					flag=false;
					list2.clear();
					dataAdapter2 = new ArrayAdapter<String>(Activity_Appointment.this, android.R.layout.simple_spinner_item, list2);
					
					dataAdapter2.setDropDownViewResource
			        (android.R.layout.simple_spinner_dropdown_item);
					select_doctor.setAdapter(dataAdapter2);
					list3.clear();
					dataAdapter3 = new ArrayAdapter<String>(Activity_Appointment.this, android.R.layout.simple_spinner_item, list3);
					
					dataAdapter3.setDropDownViewResource
			        (android.R.layout.simple_spinner_dropdown_item);
					select_timings.setAdapter(dataAdapter3);
				}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			
				
			});
		
		
		
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__appointment, menu);
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
	public void onBackPressed() {
        //Display alert message when back button has been pressed
		startActivity(new Intent(Activity_Appointment.this, Activity_home.class));
        return;
    }
 
  
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	class getdoctor extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Appointment.this);
			pDialog.setMessage("Fetching Doctors' names Please wait...");
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
				params1.add(new BasicNameValuePair("speciality", string_spinner_speciality));
				json = jParser.makeHttpRequest(url_getdoctor,"POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					// products found
					// Getting Array of Products
					doctors = json.getJSONArray(TAG_DOCTORS);

					// looping through All Products
					
					list2.clear();
					for (int i = 0; i < doctors.length(); i++) {
						JSONObject c = doctors.getJSONObject(i);
						
						// Storing each json item in variable
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String hospital = c.getString(TAG_HOSPITAL);
						
						//ids[count]=id;
						//count++;
						System.out.println("after count");
						list2.add("ID: "+ id +" |Name: "+ name + "| Hospital: "+hospital);
						list4.add(id);
						list6.add(name);

					}
					runOnUiThread(new Runnable() {
						public void run() {
					
						}
					});
					list3.add("10:00");
					list3.add("10:15");
					list3.add("10:30");
					list3.add("10:45");
					list3.add("11:00");
					list3.add("11:15");
					list3.add("11:30");
					list3.add("11:45");
					list3.add("12:00");
					list3.add("12:15");
					list3.add("12:30");
					list3.add("12:45");
					list3.add("13:00");
					list3.add("13:15");
					list3.add("13:30");
					list3.add("13:45");
					list3.add("15:00");
					list3.add("15:15");
					list3.add("15:30");
					list3.add("15:45");
					list3.add("16:00");
					list3.add("16:15");
					list3.add("16:30");
					list3.add("16:45");
					list3.add("17:00");
					list3.add("17:15");
					list3.add("17:30");
					list3.add("17:45");
					list5.add(today);
					list5.add(tomorrow);
					list5.add(day3);
					list5.add(day4);
					
				} else {
					runOnUiThread(new Runnable() {
						public void run() {
						
								list2.clear();
								list3.clear();
							Toast.makeText(Activity_Appointment.this, "Sorry No doctors found.", Toast.LENGTH_LONG).show();
							
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
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					dataAdapter2 = new ArrayAdapter<String>(Activity_Appointment.this, android.R.layout.simple_spinner_item, list2);
				
					dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					select_doctor.setAdapter(dataAdapter2);
					
					dataAdapter3 = new ArrayAdapter<String>(Activity_Appointment.this, android.R.layout.simple_spinner_item, list3);
					
					dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					select_timings.setAdapter(dataAdapter3);
					
					
					System.out.println(today+tomorrow+day3+day4);
					dataAdapter4 = new ArrayAdapter<String>(Activity_Appointment.this, android.R.layout.simple_spinner_item, list5);
					dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					select_date.setAdapter(dataAdapter4);
				}
			});

		}

	}
	
	
	class appointment extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Appointment.this);
			pDialog.setMessage("Requesting Appointment... Please wait...");
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
				
				SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
				
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("patient_id", pref.getString("id", null)));
				params1.add(new BasicNameValuePair("patient_name", pref.getString("name", null)));
				params1.add(new BasicNameValuePair("doctor_id", doctor_id));
				params1.add(new BasicNameValuePair("doctor_name", doctor_name));
				params1.add(new BasicNameValuePair("speciality", speciality));
				params1.add(new BasicNameValuePair("app_date", app_date));
				params1.add(new BasicNameValuePair("time", time));

				json = jParser.makeHttpRequest(url_appointment,"POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
					if (success == 1) {
						appid = ""+json.getInt(TAG_APPID);
						
						runOnUiThread(new Runnable() {
							public void run() {
								
						try {
							
							runOnUiThread(new Runnable() {
								public void run() {
									
									Toast.makeText(Activity_Appointment.this, "Appointment request has been sent to Doctor.", Toast.LENGTH_LONG).show();
									 Intent intent = new Intent(Activity_Appointment.this, Test.class);
									System.out.println("appid is : "+appid);
									intent.putExtra("appid",appid);
									 PendingIntent sender = PendingIntent.getBroadcast(Activity_Appointment.this,
							                    0, intent, 0);
									 
									 Calendar calendar = Calendar.getInstance();
							            calendar.setTimeInMillis(System.currentTimeMillis());

										calendar.add(Calendar.SECOND, 1*60*10);

							            // Schedule the alarm!
							            System.out.println(calendar.getTimeInMillis());
							            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
							            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);	
									 
									 pDialog.dismiss();
									/* try {
										 
											sender.send(getApplicationContext(), 0, intent);
										
										} catch (Exception e) {
											
											e.printStackTrace();
										}*/
									
									
									
									startActivity(new Intent(Activity_Appointment.this, Activity_home.class));
								}
							});
					
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // JSON Array
							}
						});
						
					}else{
						
						runOnUiThread(new Runnable() {
							public void run() {
								
								 AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							                Activity_Appointment.this);
							        // Setting Dialog Title
							        alertDialog.setTitle("Time Unavailable.");
							        // Setting Dialog Message
							        alertDialog.setMessage("Doctor is un available for chosen time.OR Your anothe appointment is already there for selected time.");
							        alertDialog.setNeutralButton("OK",new DialogInterface.OnClickListener() {
					                    public void onClick(DialogInterface dialog, int which) {
					                    
					        				dialog.cancel();
					        				
					                    }
					                });
							        alertDialog.show();
								//Toast.makeText(Activity_Appointment.this, "Doctor is un available for chosen time./ Your anothe appointment is already there for selected time.", Toast.LENGTH_LONG).show();
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

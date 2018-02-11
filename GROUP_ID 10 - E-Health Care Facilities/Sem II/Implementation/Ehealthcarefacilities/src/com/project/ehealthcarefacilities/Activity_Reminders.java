package com.project.ehealthcarefacilities;

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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import android.app.ListActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Activity_Reminders extends ListActivity {

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_APPOINTMENTS = "appointments";
	private static final String TAG_TIME = "time";
	private static final String TAG_APPDATE = "appointment_date";
	private static final String TAG_STATUS = "status";
	private static final String TAG_DOC = "doctor_name";

	String a1, a2, a3, a4;

	private ProgressDialog pDialog;
	private static String url_chkapp = Urls.chkapp;
	ListAdapter adapter;

	ArrayList<HashMap<String, String>> appointmentsList;
	JSONArray appointments = null;
	int success;
	String status;
	JSONParser jParser = new JSONParser();

	JSONObject json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_reminders);

		Intent i = getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		Editor editor = pref.edit();

		String name = null;

		name = pref.getString("name", name);
		if (name.equals(null)) {
			startActivity(new Intent(Activity_Reminders.this,
					Activity_Main_Screen.class));
		}
			appointmentsList = new ArrayList<HashMap<String, String>>();
		
		new chkapp().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__reminders, menu);
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

	class chkapp extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Reminders.this);
			pDialog.setMessage("Loading Appointments. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters

			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences("MyPref", 0);
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("patient_id", pref.getString(
					"id", null)));

			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_chkapp, "POST",
					params1);

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					appointments = json.getJSONArray(TAG_APPOINTMENTS);

					// looping through All Products
					for (int i = 0; i < appointments.length(); i++) {
						JSONObject c = appointments.getJSONObject(i);

						// Storing each json item in variable
						String appdate = c.getString(TAG_APPDATE);
						String time = c.getString(TAG_TIME);
						String status = c.getString(TAG_STATUS);
						String doctor_name = c.getString(TAG_DOC);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						a1 = "Doctor :  " + doctor_name;
						a2 = "Date   :  " + appdate;
						a3 = "Time   :  " + time;
						if (status.equals("null")) {
							status = "Not Confirmed yet";
						}
						a4 = "Status : " + status + "!";

						// adding each child node to HashMap key => value
						map.clear();
						map.put(TAG_DOC, a1);
						map.put(TAG_APPDATE, a2);
						map.put(TAG_TIME, a3);
						map.put(TAG_STATUS, a4);

						// adding HashList to ArrayList
						appointmentsList.add(map);
						adapter = new SimpleAdapter(Activity_Reminders.this,
								appointmentsList, R.layout.lists, new String[] {
										TAG_DOC, TAG_APPDATE, TAG_TIME,
										TAG_STATUS }, new int[] {
										R.id.layout_lists_textvies1,
										R.id.layout_lists_textvies2,
										R.id.layout_lists_textvies3,
										R.id.layout_lists_textvies4 });
					}
				} else {
					// no products found
					// Launch Add New product ActivityrunOnUiThread(new
					// Runnable() {
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Activity_Reminders.this,
									"No previous appointments!",
									Toast.LENGTH_LONG).show();
						}
					});

					Intent i = new Intent(getApplicationContext(),
							Activity_home.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
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

					setListAdapter(adapter);
				}
			});

		}

	}
}

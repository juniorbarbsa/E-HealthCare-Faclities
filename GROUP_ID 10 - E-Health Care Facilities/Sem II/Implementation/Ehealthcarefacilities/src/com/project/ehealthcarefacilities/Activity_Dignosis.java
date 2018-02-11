package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Dignosis extends ActionBarActivity {

	Spinner s1, s2, s3, s4, s5;
	Button b;
	String ss1 = "s0";
	String ss2 = "s0";
	String ss3 = "s0";
	String ss4 = "s0";
	String ss5 = "s0";

	ArrayList<HashMap<String, String>> appointmentsList;

	ArrayAdapter<String> dataAdapter1, dataAdapter2, dataAdapter3,
			dataAdapter4, dataAdapter5;
	ListAdapter adapter;
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();
	List<String> list3 = new ArrayList<String>();
	List<String> list4 = new ArrayList<String>();
	List<String> list5 = new ArrayList<String>();
	List<String> list6 = new ArrayList<String>();

	ProgressDialog pDialog;
	private static String url_getsymptom = Urls.getsymptom;
	private static String url_getsid = Urls.getsid;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SYMPTOM = "symptom";
	private static final String TAG_SYMPTOMS = "symptoms";
	private static final String TAG_PROBS = "probs";
	private static final String TAG_DISEASE = "disease";
	private static final String TAG_PROB = "prob";

	int success;

	JSONParser jParser = new JSONParser();

	JSONObject json;
	JSONArray symptoms = null;
	JSONArray probs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dignosis);

		Intent i = getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		Editor editor = pref.edit();

		String name = null;

		name = pref.getString("name", name);
		if (name.equals(null)) {
			startActivity(new Intent(Activity_Dignosis.this,
					Activity_Main_Screen.class));
		} else {

			s1 = (Spinner) findViewById(R.id.spinner1);
			s2 = (Spinner) findViewById(R.id.spinner2);
			s3 = (Spinner) findViewById(R.id.spinner3);
			s4 = (Spinner) findViewById(R.id.spinner4);
			s5 = (Spinner) findViewById(R.id.spinner5);
			b = (Button) findViewById(R.id.BtnEmergency);
			list1.add("Select");
			list2.add("Select");
			list3.add("Select");
			list4.add("Select");
			list5.add("Select");
			list6.add("Select");
		}
		new getsymptoms().execute();

		s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub

				ss1 = adapter.getItemAtPosition(position).toString();
				if (ss1 != "Select") {
					list2.clear();
					list2.addAll(list1);
					list2.remove(position);

				} else {
					list2.clear();
					list3.clear();
					list4.clear();
					list6.clear();
					ss1 = "s0";

				}
				dataAdapter2 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list2);
				dataAdapter2
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s2.setAdapter(dataAdapter2);

				dataAdapter3 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list3);
				dataAdapter3
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s3.setAdapter(dataAdapter3);

				dataAdapter4 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list4);
				dataAdapter4
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s4.setAdapter(dataAdapter4);

				dataAdapter5 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list6);
				dataAdapter5
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s5.setAdapter(dataAdapter5);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		s2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				ss2 = adapter.getItemAtPosition(position).toString();
				if (ss2 != "Select") {
					list3.clear();
					list3.addAll(list2);
					list3.remove(position);

				} else {
					list3.clear();
					list4.clear();
					list6.clear();
					ss2 = "s0";

				}

				dataAdapter3 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list3);
				dataAdapter3
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s3.setAdapter(dataAdapter3);

				dataAdapter4 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list4);
				dataAdapter4
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s4.setAdapter(dataAdapter4);

				dataAdapter5 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list6);
				dataAdapter5
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s5.setAdapter(dataAdapter5);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		s3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub

				ss3 = adapter.getItemAtPosition(position).toString();
				if (ss3 != "Select") {
					list4.clear();
					list4.addAll(list3);
					list4.remove(position);

				} else {

					list4.clear();
					list6.clear();
					ss3 = "s0";

				}

				dataAdapter4 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list4);
				dataAdapter4
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s4.setAdapter(dataAdapter4);

				dataAdapter5 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list6);
				dataAdapter5
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s5.setAdapter(dataAdapter5);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		s4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub

				ss4 = adapter.getItemAtPosition(position).toString();
				if (ss4 != "Select") {
					list6.clear();
					list6.addAll(list4);
					list6.remove(position);

				} else {

					list6.clear();
					ss4 = "s0";
				}

				dataAdapter5 = new ArrayAdapter<String>(Activity_Dignosis.this,
						android.R.layout.simple_spinner_item, list6);
				dataAdapter5
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				s5.setAdapter(dataAdapter5);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getApplicationContext()
						.getSharedPreferences("MyPref", 0);
				Editor editor = pref.edit();

				editor.putString("s1", ss1);
				editor.putString("s2", ss2);
				editor.putString("s3", ss3);
				editor.putString("s4", ss4);
				editor.putString("s5", ss5);

				editor.commit();
				startActivity(new Intent(Activity_Dignosis.this,
						Activity_subdigagnosis.class));
			}
		});

		s5.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				// TODO Auto-generated method stub

				ss5 = adapter.getItemAtPosition(position).toString();
				if (ss4 == "Select") {
					ss4 = "s0";
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
		getMenuInflater().inflate(R.menu.activity__dignosis, menu);
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

	class getsymptoms extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Dignosis.this);
			pDialog.setMessage("Fetching Symptoms names Please wait...");
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

				json = jParser.makeHttpRequest(url_getsymptom, "POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					symptoms = json.getJSONArray(TAG_SYMPTOMS);

					for (int i = 0; i < symptoms.length(); i++) {
						JSONObject c = symptoms.getJSONObject(i);

						// Storing each json item in variable
						String symptom_name = c.getString(TAG_SYMPTOM);

						list1.add(symptom_name);
						list5.add(symptom_name);

					}

				} else {
					runOnUiThread(new Runnable() {
						public void run() {

							Toast.makeText(Activity_Dignosis.this,
									"Sorry No symptoms found.",
									Toast.LENGTH_LONG).show();

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

					dataAdapter1 = new ArrayAdapter<String>(
							Activity_Dignosis.this,
							android.R.layout.simple_spinner_item, list1);
					dataAdapter1
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					s1.setAdapter(dataAdapter1);

				}
			});

		}

	}

}

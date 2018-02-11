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
import android.app.ListActivity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_subdigagnosis extends ListActivity {

	ArrayList<HashMap<String, String>> appointmentsList;
	ProgressDialog pDialog;
	TextView tv;

	ArrayAdapter<String> dataAdapter1;
	ArrayList<String> list1 = new ArrayList<String>();
	private static String url_getsid = Urls.getsid;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PROBS = "probs";
	private static final String TAG_UN = "";
	private static final String TAG_DISEASE = "disease";
	private static final String TAG_PROB = "prob";

	private static final String TAG_RECENT = "recent";
	private static final String TAG_PRENO = "preno";
	private static final String TAG_SYMP = "symp";
	private static final String TAG_SYMPS = "symps";
	ListAdapter adapter;
	Button rec;
	String recent, preno, abc;
	Object[] ob;
	int success;
	String sym;
	JSONParser jParser = new JSONParser();

	JSONObject json;
	JSONArray symptoms = null;
	JSONArray probs = null;
	JSONArray un = null;
	JSONArray symps = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_subdigagnosis);

		Intent i = getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		Editor editor = pref.edit();

		String name = null;

		name = pref.getString("name", name);
		if (name.equals(null)) {
			startActivity(new Intent(Activity_subdigagnosis.this,
					Activity_Main_Screen.class));
		} else {

			appointmentsList = new ArrayList<HashMap<String, String>>();
			tv = (TextView) findViewById(R.id.textView1);
		}
		new diagnosis().execute();

		rec = (Button) findViewById(R.id.BtnEmergency);

		rec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_subdigagnosis.this,
						Activity_recent_trends.class));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_subdigagnosis, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// Display alert message when back button has been pressed

		startActivity(new Intent(Activity_subdigagnosis.this,
				Activity_home.class));

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

	class diagnosis extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_subdigagnosis.this);
			pDialog.setMessage("Diagnosis process may take some time. Please wait...");
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
				SharedPreferences pref = getApplicationContext()
						.getSharedPreferences("MyPref", 0);
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("s1", pref.getString("s1",
						null)));
				params1.add(new BasicNameValuePair("s2", pref.getString("s2",
						null)));
				params1.add(new BasicNameValuePair("s3", pref.getString("s3",
						null)));
				params1.add(new BasicNameValuePair("s4", pref.getString("s4",
						null)));
				params1.add(new BasicNameValuePair("s5", pref.getString("s5",
						null)));

				json = jParser.makeHttpRequest(url_getsid, "POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					probs = json.getJSONArray(TAG_PROBS);

					for (int i = 0; i < probs.length(); i++) {
						JSONObject c = probs.getJSONObject(i);

						// Storing each json item in variable

						String disease = c.getString(TAG_DISEASE);
						String prob = c.getString(TAG_PROB);

						prob = prob + "% Symptom match for disease: " + disease;
						// disease = "Disease Name : "+ disease;

						HashMap<String, String> map = new HashMap<String, String>();
						// adding each child node to HashMap key => value
						map.clear();
						// if(disease != "-"){
						// map.put(TAG_DISEASE, disease);
						map.put(TAG_PROB, prob);
						// }

						appointmentsList.add(map);
						adapter = new SimpleAdapter(
								Activity_subdigagnosis.this, appointmentsList,
								R.layout.list2, new String[] { TAG_PROB },
								new int[] { R.id.layout_lists2_textvies1 });

					}

					System.out.println("added in hash map");

					System.out.println(recent + " " + preno);

					symps = json.getJSONArray(TAG_SYMPS);
					for (int i = 0; i < symps.length(); i++) {
						JSONObject c = symps.getJSONObject(i);

						if (sym == null)
							sym = c.getString(TAG_SYMP) + ", ";
						else if (i != symps.length() - 1) {
							sym = sym + c.getString(TAG_SYMP) + ", ";
						} else
							sym = sym + c.getString(TAG_SYMP);

						recent = json.getString(TAG_RECENT);

						preno = json.getString(TAG_PRENO);

						runOnUiThread(new Runnable() {
							public void run() {
								SharedPreferences pref = getApplicationContext()
										.getSharedPreferences("MyPref", 0);
								Editor editor = pref.edit();

								editor.putString("recent", recent); // Storing
																	// string
								editor.putString("preno", preno);
								editor.putString("sym", sym);
								editor.commit();

							}
						});

					}

				} else {
					runOnUiThread(new Runnable() {
						public void run() {

							Toast.makeText(Activity_subdigagnosis.this,
									"Something went wrong.", Toast.LENGTH_LONG)
									.show();

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

			setListAdapter(adapter);

		}

	}

}
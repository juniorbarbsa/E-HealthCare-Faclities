package com.project.ehealthcarefacilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_check_precription extends ActionBarActivity {

	Toast mToast;
	TextView texview_priscription_med1;
	TextView texview_priscription_med2;
	TextView texview_priscription_med3;
	TextView texview_priscription_med4;
	TextView texview_priscription_med5;

	TextView texview_priscription_dosage1;
	TextView texview_priscription_dosage2;
	TextView texview_priscription_dosage3;
	TextView texview_priscription_dosage4;
	TextView texview_priscription_dosage5;

	TextView texview_priscription_m1;
	TextView texview_priscription_m2;
	TextView texview_priscription_m3;
	TextView texview_priscription_m4;
	TextView texview_priscription_m5;

	TextView texview_priscription_a1;
	TextView texview_priscription_a2;
	TextView texview_priscription_a3;
	TextView texview_priscription_a4;
	TextView texview_priscription_a5;

	TextView texview_priscription_e1;
	TextView texview_priscription_e2;
	TextView texview_priscription_e3;
	TextView texview_priscription_e4;
	TextView texview_priscription_e5;

	TextView texview_priscription_duration1;
	TextView texview_priscription_duration2;
	TextView texview_priscription_duration3;
	TextView texview_priscription_duration4;
	TextView texview_priscription_duration5;

	private ProgressDialog pDialog;
	int success, i, j;
	JSONParser jParser = new JSONParser();
	String a;
	String m;
	String e;
	JSONObject json;
	JSONArray productObj;
	private static String dosage_url = Urls.dosage;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_DOSAGE = "dosage";
	private static final String TAG_MED1 = "med1";
	private static final String TAG_MED2 = "med2";
	private static final String TAG_MED3 = "med3";
	private static final String TAG_MED4 = "med4";
	private static final String TAG_MED5 = "med5";

	private static final String TAG_DOSAGE1 = "dosage1";
	private static final String TAG_DOSAGE2 = "dosage2";
	private static final String TAG_DOSAGE3 = "dosage3";
	private static final String TAG_DOSAGE4 = "dosage4";
	private static final String TAG_DOSAGE5 = "dosage5";

	private static final String TAG_A1 = "a1";
	private static final String TAG_A2 = "a2";
	private static final String TAG_A3 = "a3";
	private static final String TAG_A4 = "a4";
	private static final String TAG_A5 = "a5";

	private static final String TAG_M1 = "m1";
	private static final String TAG_M2 = "m2";
	private static final String TAG_M3 = "m3";
	private static final String TAG_M4 = "m4";
	private static final String TAG_M5 = "m5";

	private static final String TAG_E1 = "e1";
	private static final String TAG_E2 = "e2";
	private static final String TAG_E3 = "e3";
	private static final String TAG_E4 = "e4";
	private static final String TAG_E5 = "e5";

	private static final String TAG_DURATION1 = "duration1";
	private static final String TAG_DURATION2 = "duration2";
	private static final String TAG_DURATION3 = "duration3";
	private static final String TAG_DURATION4 = "duration4";
	private static final String TAG_DURATION5 = "duration5";

	String med1, med2, med3, med4, med5;
	String dosage1, dosage2, dosage3, dosage4, dosage5;
	String m1, m2, m3, m4, m5;
	String a1, a2, a3, a4, a5;
	String e1, e2, e3, e4, e5;
	String duration1 = "0";
	String duration2 = "0";
	String duration3 = "0";
	String duration4 = "0";
	String duration5 = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_check_precription);

		Intent i = getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		Editor editor = pref.edit();

		String name = null;

		name = pref.getString("name", name);
		if (name.equals(null)) {
			startActivity(new Intent(Activity_check_precription.this,
					Activity_Main_Screen.class));
		} else {

			texview_priscription_med1 = (TextView) findViewById(R.id.layout_priscription_med1);
			texview_priscription_med2 = (TextView) findViewById(R.id.layout_priscription_med2);
			texview_priscription_med3 = (TextView) findViewById(R.id.layout_priscription_med3);
			texview_priscription_med4 = (TextView) findViewById(R.id.layout_priscription_med4);
			texview_priscription_med5 = (TextView) findViewById(R.id.layout_priscription_med5);

			texview_priscription_dosage1 = (TextView) findViewById(R.id.layout_priscription_dosage1);
			texview_priscription_dosage2 = (TextView) findViewById(R.id.layout_priscription_dosage2);
			texview_priscription_dosage3 = (TextView) findViewById(R.id.layout_priscription_dosage3);
			texview_priscription_dosage4 = (TextView) findViewById(R.id.layout_priscription_dosage4);
			texview_priscription_dosage5 = (TextView) findViewById(R.id.layout_priscription_dosage5);

			texview_priscription_m1 = (TextView) findViewById(R.id.layout_priscription_m1);
			texview_priscription_m2 = (TextView) findViewById(R.id.layout_priscription_m2);
			texview_priscription_m3 = (TextView) findViewById(R.id.layout_priscription_m3);
			texview_priscription_m4 = (TextView) findViewById(R.id.layout_priscription_m4);
			texview_priscription_m5 = (TextView) findViewById(R.id.layout_priscription_m5);

			texview_priscription_a1 = (TextView) findViewById(R.id.layout_priscription_a1);
			texview_priscription_a2 = (TextView) findViewById(R.id.layout_priscription_a2);
			texview_priscription_a3 = (TextView) findViewById(R.id.layout_priscription_a3);
			texview_priscription_a4 = (TextView) findViewById(R.id.layout_priscription_a4);
			texview_priscription_a5 = (TextView) findViewById(R.id.layout_priscription_a5);

			texview_priscription_e1 = (TextView) findViewById(R.id.layout_priscription_n1);
			texview_priscription_e2 = (TextView) findViewById(R.id.layout_priscription_n2);
			texview_priscription_e3 = (TextView) findViewById(R.id.layout_priscription_n3);
			texview_priscription_e4 = (TextView) findViewById(R.id.layout_priscription_n4);
			texview_priscription_e5 = (TextView) findViewById(R.id.layout_priscription_n5);

			texview_priscription_duration1 = (TextView) findViewById(R.id.layout_priscription_duration1);
			texview_priscription_duration2 = (TextView) findViewById(R.id.layout_priscription_duration2);
			texview_priscription_duration3 = (TextView) findViewById(R.id.layout_priscription_duration3);
			texview_priscription_duration4 = (TextView) findViewById(R.id.layout_priscription_duration4);
			texview_priscription_duration5 = (TextView) findViewById(R.id.layout_priscription_duration5);
		}
		new dosages().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_check_precription, menu);
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

	class dosages extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_check_precription.this);
			pDialog.setMessage("Fetching prescriptions... Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		@SuppressLint("NewApi")
		protected String doInBackground(String... params) {

			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences("MyPref", 0);
			Editor editor = pref.edit();

			try {
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("id", pref.getString("id",
						null)));

				json = jParser.makeHttpRequest(dosage_url, "POST", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// System.out.println("in success.");
					runOnUiThread(new Runnable() {
						public void run() {

							try {

								productObj = json.getJSONArray(TAG_DOSAGE);

								// get first product object from JSON Array
								JSONObject dosage = productObj.getJSONObject(0);
								if (!dosage.getString(TAG_MED1).equals("null")) {
									texview_priscription_med1.setText(dosage
											.getString(TAG_MED1));
									med1 = dosage.getString(TAG_MED1);
									texview_priscription_dosage1.setText(dosage
											.getString(TAG_DOSAGE1));
									dosage1 = dosage.getString(TAG_DOSAGE1);
									texview_priscription_a1.setText(dosage
											.getString(TAG_A1));
									a1 = dosage.getString(TAG_A1);
									texview_priscription_m1.setText(dosage
											.getString(TAG_M1));
									m1 = dosage.getString(TAG_M1);
									texview_priscription_e1.setText(dosage
											.getString(TAG_E1));
									e1 = dosage.getString(TAG_E1);
									texview_priscription_duration1
											.setText(dosage
													.getString(TAG_DURATION1));
									duration1 = dosage.getString(TAG_DURATION1);
								}
								if (!dosage.getString(TAG_MED2).equals("null")) {
									texview_priscription_med2.setText(dosage
											.getString(TAG_MED2));
									med2 = dosage.getString(TAG_MED2);
									texview_priscription_dosage2.setText(dosage
											.getString(TAG_DOSAGE2));
									dosage2 = dosage.getString(TAG_DOSAGE2);
									texview_priscription_a2.setText(dosage
											.getString(TAG_A2));
									a2 = dosage.getString(TAG_A2);
									texview_priscription_m2.setText(dosage
											.getString(TAG_M2));
									m2 = dosage.getString(TAG_M2);
									texview_priscription_e2.setText(dosage
											.getString(TAG_E2));
									e2 = dosage.getString(TAG_E2);
									texview_priscription_duration2
											.setText(dosage
													.getString(TAG_DURATION2));
									duration2 = dosage.getString(TAG_DURATION2);
								}
								if (!dosage.getString(TAG_MED3).equals("null")) {
									texview_priscription_med3.setText(dosage
											.getString(TAG_MED3));
									med3 = dosage.getString(TAG_MED3);
									texview_priscription_dosage3.setText(dosage
											.getString(TAG_DOSAGE3));
									dosage3 = dosage.getString(TAG_DOSAGE3);
									texview_priscription_a3.setText(dosage
											.getString(TAG_A3));
									a3 = dosage.getString(TAG_A3);
									texview_priscription_m3.setText(dosage
											.getString(TAG_M3));
									m3 = dosage.getString(TAG_M3);
									texview_priscription_e3.setText(dosage
											.getString(TAG_E3));
									e3 = dosage.getString(TAG_E3);
									texview_priscription_duration3
											.setText(dosage
													.getString(TAG_DURATION3));
									duration3 = dosage.getString(TAG_DURATION3);
								}
								if (!dosage.getString(TAG_MED4).equals("null")) {
									texview_priscription_med4.setText(dosage
											.getString(TAG_MED4));
									med4 = dosage.getString(TAG_MED4);
									texview_priscription_dosage4.setText(dosage
											.getString(TAG_DOSAGE4));
									dosage4 = dosage.getString(TAG_DOSAGE4);
									texview_priscription_a4.setText(dosage
											.getString(TAG_A4));
									a4 = dosage.getString(TAG_A4);
									texview_priscription_m4.setText(dosage
											.getString(TAG_M4));
									m4 = dosage.getString(TAG_M4);
									texview_priscription_e4.setText(dosage
											.getString(TAG_E4));
									e4 = dosage.getString(TAG_E4);
									texview_priscription_duration4
											.setText(dosage
													.getString(TAG_DURATION4));
									duration4 = dosage.getString(TAG_DURATION4);
								}
								if (!dosage.getString(TAG_MED5).equals("null")) {
									texview_priscription_med5.setText(dosage
											.getString(TAG_MED5));
									med5 = dosage.getString(TAG_MED5);
									texview_priscription_dosage5.setText(dosage
											.getString(TAG_DOSAGE5));
									dosage5 = dosage.getString(TAG_DOSAGE5);
									texview_priscription_a5.setText(dosage
											.getString(TAG_A5));
									a5 = dosage.getString(TAG_A5);
									texview_priscription_m5.setText(dosage
											.getString(TAG_M5));
									m5 = dosage.getString(TAG_M5);
									texview_priscription_e5.setText(dosage
											.getString(TAG_E5));
									e5 = dosage.getString(TAG_E5);
									texview_priscription_duration5
											.setText(dosage
													.getString(TAG_DURATION5));
									duration5 = dosage.getString(TAG_DURATION5);
								}

								int[] dur = { Integer.parseInt(duration1),
										Integer.parseInt(duration2),
										Integer.parseInt(duration3),
										Integer.parseInt(duration4),
										Integer.parseInt(duration5) };
								SharedPreferences pref = getApplicationContext()
										.getSharedPreferences("MyPref", 0);
								for (i = 0; i < 5; i++) {
									if (dur[i] != 0) {
										Calendar c = Calendar.getInstance();
										int date = c.get(Calendar.DATE);
										/*
										 * c.add(Calendar.DATE, dur[i]); int
										 * date_final = c.get(Calendar.DATE);
										 * c.clear(); System.out.println(date);
										 * System.out.println(date_final);
										 */

										/*
										 * Calendar c2 = Calendar.getInstance();
										 * Date d1,d2; d1 = c2.getTime();
										 * c2.add(Calendar.DATE, dur[i]); d2 =
										 * c2.getTime();
										 * System.out.println("today: " + d1 +
										 * "dur: "+d2); c2.clear(); boolean flag
										 * = false;
										 */

										// Calendar cc = Calendar.getInstance();

										int interval = 0;
										for (int k = 0; k < dur[i]; k++) {
											date += k;

											if (i == 0) {
												a = a1;
												m = m1;
												e = e1;
											} else if (i == 1) {
												a = a2;
												m = m2;
												e = e2;
											} else if (i == 2) {
												a = a3;
												m = m3;
												e = e3;
											} else if (i == 3) {
												a = a4;
												m = m4;
												e = e4;
											} else if (i == 4) {
												a = a5;
												m = m5;
												e = e5;
											}
											if (m == "yes" || m == "YES") {
												Calendar calendar = Calendar
														.getInstance();
												calendar.set(
														Calendar.MONTH,
														calendar.get(Calendar.MONTH));
												calendar.set(
														Calendar.YEAR,
														calendar.get(Calendar.YEAR));
												calendar.set(
														Calendar.DAY_OF_MONTH,
														date);
												calendar.set(
														Calendar.HOUR_OF_DAY, 9);
												calendar.set(Calendar.MINUTE, 0);
												calendar.set(Calendar.SECOND, 0);
												calendar.set(Calendar.AM_PM,
														Calendar.AM);

												Intent myIntent = new Intent(
														Activity_check_precription.this,
														Test2.class);
												PendingIntent pendingIntent = PendingIntent
														.getBroadcast(
																Activity_check_precription.this,
																0, myIntent, 0);

												AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
												alarmManager
														.set(AlarmManager.RTC,
																calendar.getTimeInMillis(),
																pendingIntent);
											}
											if (a == "yes" || a == "YES") {
												Calendar calendar = Calendar
														.getInstance();
												calendar.set(
														Calendar.MONTH,
														calendar.get(Calendar.MONTH));
												calendar.set(
														Calendar.YEAR,
														calendar.get(Calendar.YEAR));
												calendar.set(
														Calendar.DAY_OF_MONTH,
														date);
												calendar.set(
														Calendar.HOUR_OF_DAY,
														13);
												calendar.set(Calendar.MINUTE, 0);
												calendar.set(Calendar.SECOND, 0);
												calendar.set(Calendar.AM_PM,
														Calendar.AM);

												Intent myIntent = new Intent(
														Activity_check_precription.this,
														Test2.class);
												PendingIntent pendingIntent = PendingIntent
														.getBroadcast(
																Activity_check_precription.this,
																0, myIntent, 0);

												AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
												alarmManager
														.set(AlarmManager.RTC,
																calendar.getTimeInMillis(),
																pendingIntent);
											}
											if (e == "yes" || e == "YES") {
												Calendar calendar = Calendar
														.getInstance();
												calendar.set(
														Calendar.MONTH,
														calendar.get(Calendar.MONTH));
												calendar.set(
														Calendar.YEAR,
														calendar.get(Calendar.YEAR));
												calendar.set(
														Calendar.DAY_OF_MONTH,
														date);
												calendar.set(
														Calendar.HOUR_OF_DAY,
														20);
												calendar.set(Calendar.MINUTE, 0);
												calendar.set(Calendar.SECOND, 0);
												calendar.set(Calendar.AM_PM,
														Calendar.AM);

												Intent myIntent = new Intent(
														Activity_check_precription.this,
														Test2.class);
												PendingIntent pendingIntent = PendingIntent
														.getBroadcast(
																Activity_check_precription.this,
																0, myIntent, 0);

												AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
												alarmManager
														.set(AlarmManager.RTC,
																calendar.getTimeInMillis(),
																pendingIntent);
											}
											// System.out.println("in while"+date);

										}
										/*
										 * if(dur[i]>1) for(j=0;j<dur[i];j++){
										 * interval = 1000*60*60*12*dur[i];
										 * alarmManager
										 * .setInexactRepeating(AlarmManager
										 * .RTC,
										 * calendar.getTimeInMillis(),interval
										 * ,pendingIntent);
										 * 
										 * 
										 * }
										 */

										/*
										 * date = cc.get(Calendar.DATE);
										 * cc.add(Calendar.DATE, 1); date =
										 * cc.get(Calendar.DATE); cc.clear();
										 */

									}

								}

								/*
								 * try {
								 * 
								 * sender.send(getApplicationContext(), 0,
								 * intent);
								 * 
								 * } catch (CanceledException e) { // TODO
								 * Auto-generated catch block
								 * e.printStackTrace(); }
								 */

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} // JSON Array
						}
					});

				} else {

					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Activity_check_precription.this,
									"No Prescription given!.",
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
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}

}

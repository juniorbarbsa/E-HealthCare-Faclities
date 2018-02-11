package com.project.ehealthcarefacilities;

import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_home extends ActionBarActivity {
	TextToSpeech ttobj;
	Button button_logout;
	Button button_update;
	Button button_appointment;
	Button button_reminder;
	Button button_prescription;
	Button button_diagnosis;
	TextView textview_welcome;
	Button BtnHomeEmergency;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		final Context context = this;

		ttobj = new TextToSpeech(Activity_home.this,
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							ttobj.setLanguage(Locale.US);
							System.out.println("startedcspch");
						}
					}
				});

		Intent i = getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0);
		Editor editor = pref.edit();

		String name = null;

		name = pref.getString("name", name);
		if (name.equals(null)) {
			startActivity(new Intent(Activity_home.this,
					Activity_Main_Screen.class));
		} else {

			textview_welcome = (TextView) findViewById(R.id.home_textview_welcome);
			textview_welcome.setText("Welcome " + pref.getString("name", name));

			String toSpeak = pref.getString("name", null);

			ttobj.speak("Speak", TextToSpeech.QUEUE_ADD, null);

			button_logout = (Button) findViewById(R.id.home_logout);
			button_update = (Button) findViewById(R.id.home_update);
			button_appointment = (Button) findViewById(R.id.home_appointment);
			button_prescription = (Button) findViewById(R.id.home_priscription);
			button_reminder = (Button) findViewById(R.id.home_reminders);
			button_diagnosis = (Button) findViewById(R.id.home_diagnosis);
			BtnHomeEmergency = (Button) findViewById(R.id.BtnHomeEmergency);

		}
		button_prescription.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_home.this,
						Activity_check_precription.class));
			}
		});
		
		BtnHomeEmergency.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, Map.class);
                            startActivity(intent);   
 
			}
 
		});

		button_diagnosis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_home.this,
						Activity_Dignosis.class));
			}
		});

		button_reminder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_home.this,
						Activity_Reminders.class));
			}
		});
		button_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(Activity_home.this,
						Activity_update_info.class));

			}
		});

		button_logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getApplicationContext()
						.getSharedPreferences("MyPref", 0);
				Editor editor = pref.edit();

				editor.clear();
				editor.commit(); // commit changes
				startActivity(new Intent(Activity_home.this,
						Activity_Main_Screen.class));
				finish();
			}
		});

		button_appointment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Activity_home.this,
						Activity_Appointment.class));
			}
		});

	}

	public void onBackPressed() {
		// Display alert message when back button has been pressed
		backButtonHandler();
		return;
	}

	public void backButtonHandler() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				Activity_home.this);
		// Setting Dialog Title
		alertDialog.setTitle("Close Application?");
		// Setting Dialog Message
		alertDialog
				.setMessage("CAUTION: You will be loged out the application.");
		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						SharedPreferences pref = getApplicationContext()
								.getSharedPreferences("MyPref", 0);
						Editor editor = pref.edit();

						editor.clear();
						editor.commit(); // commit changes
						Intent j = new Intent(Intent.ACTION_MAIN);
						j.addCategory(Intent.CATEGORY_HOME);
						j.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(j);
						finish();
						System.exit(0);

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
	public void onPause() {
		if (ttobj != null) {
			ttobj.stop();
			ttobj.shutdown();
		}
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
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

}

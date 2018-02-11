package com.project.ehealthcarefacilities;

import java.util.ArrayList;

import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Activity_recent_trends extends ActionBarActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_recent_trends);
		
		
		Intent i =getIntent();
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		Editor editor = pref.edit();
		
		String name = null;
		
		name = pref.getString("name", name);
		if(name.equals(null)){
			startActivity(new Intent(Activity_recent_trends.this, Activity_Main_Screen.class));
		}else{
			
		
		
		
		String recent, preno, sym,abc1, abc2;
		recent = pref.getString("recent", "not found");
		preno = pref.getString("preno", "not found");
		sym = pref.getString("sym", "not found");
		abc1 = "There is possiblity that, you are victim of disease: "+recent+".";
		abc2 = "In the past month, "+preno +" people have suffered from "+recent +". Please consult with the doctor regarding "+recent +", if you are suffering from symptoms: " + sym +".";
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		tv1.setText(abc1);
		tv2.setText(abc2);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_recent_trends, menu);
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

package com.project.ehealthcarefacilities;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.ehealthcarefacilities.Activity_Appointment.getdoctor;

import android.R.string;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.PendingIntent.CanceledException;
import android.app.backup.FullBackupDataOutput;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Test extends BroadcastReceiver {

	NotificationManager nm,mManager;
	int success;
	String status = null;
	String id;
	int a;
	String url_chkStatus = Urls.chkstatus;
	final String TAG_SUCCESS = "success";
	final String TAG_STATUS = "status";
	//Context context;
	Intent intent;
	JSONParser jParser = new JSONParser();
	// Context context;
	JSONObject json;

	@Override
	public void onReceive(Context context, Intent intent) {
		id = intent.getStringExtra("appid");

		new chksts(context).execute();

	}

	class chksts extends AsyncTask<String, String, String> {
		  private Context context;

		    public chksts(Context context){
		        this.context=context;
		    }
		@SuppressWarnings({ "deprecation" })
		protected String doInBackground(String... params) {

			// updating UI from Background Thread

			// Check for success tag

			try {

				System.out.println("aaaaaaaaaaaa:" + id);

				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("appid", id));

				json = jParser.makeHttpRequest(url_chkStatus, "GET", params1);

				// check your log for json response
				Log.d("Single Product Details", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// flag = true;
					System.out.println(json.getString(TAG_STATUS));
					if (json.getString(TAG_STATUS).equals("Confirmed")) {

						try {

							mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
						       Intent intent1 = new Intent(context,Activity_home.class);
						     
						       Notification notification = new Notification(R.drawable.logo1,"Appointment!", System.currentTimeMillis());
						       intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						 
						       PendingIntent pendingNotificationIntent = PendingIntent.getActivity( context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
						       notification.flags |= Notification.FLAG_AUTO_CANCEL;
						       notification.defaults |= Notification.DEFAULT_SOUND;
						       notification.setLatestEventInfo(context, "Appointment Notification", "Your appointment is Confirmed.", pendingNotificationIntent);
						 
						       mManager.notify(0, notification);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (json.getString(TAG_STATUS).equals("Rejected")
							|| json.getString(TAG_STATUS).equals("null")) {
						try {
							System.out.println("innnn else");

							/*
							 * Intent service1 = new Intent(context, Abc.class);
							 * context.startService(service1);
							 */
							try {
								
								

								 mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
							       Intent intent1 = new Intent(context,Activity_home.class);
							     
							       Notification notification = new Notification(R.drawable.logo1,"Appointment!", System.currentTimeMillis());
							       intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
							 
							       PendingIntent pendingNotificationIntent = PendingIntent.getActivity( context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
							       notification.flags |= Notification.FLAG_AUTO_CANCEL;
							       notification.defaults |= Notification.DEFAULT_SOUND;
							       notification.setLatestEventInfo(context, "Appointment Notification", "Sorry! Your appointment is Rejected.", pendingNotificationIntent);
							 
							       mManager.notify(0, notification);

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			} catch (JSONException e) {
				e.printStackTrace();

			}

			return null;
		}

	}

}

/*
 * public class Test extends WakefulBroadcastReceiver { NotificationManager nm;
 * int success; String status = null; String id; int a; String url_chkStatus =
 * Urls.chkstatus; final String TAG_SUCCESS = "success"; final String TAG_STATUS
 * = "status";
 * 
 * JSONParser jParser = new JSONParser(); //Context context; JSONObject json;
 * 
 * @SuppressWarnings("deprecation") public void onReceive(Context context,
 * Intent intent) { id = intent.getStringExtra("appid");
 * 
 * 
 * 
 * 
 * 
 * 
 * try {
 * 
 * System.out.println("aaaaaaaaaaaa:"+id);
 * 
 * List<NameValuePair> params1 = new ArrayList<NameValuePair>(); params1.add(new
 * BasicNameValuePair("appid", id));
 * 
 * 
 * json = jParser.makeHttpRequest(url_chkStatus,"POST", params1);
 * 
 * // check your log for json response Log.d("Single Product Details",
 * json.toString());
 * 
 * // json success tag success = json.getInt(TAG_SUCCESS);
 * 
 * if (success == 1) { //flag = true;
 * System.out.println(json.getString(TAG_STATUS));
 * if(json.getString(TAG_STATUS).equals("Confirmed")){
 * 
 * try {
 * 
 * nm = (NotificationManager) context
 * .getSystemService(Context.NOTIFICATION_SERVICE); CharSequence from =
 * "Appointment"; CharSequence message = "Your appointment is Confirmed.";
 * PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new
 * Intent(), 0);
 * 
 * @SuppressWarnings("deprecation") Notification notif = new
 * Notification(R.drawable.logo, "Appointment", System.currentTimeMillis());
 * notif.setLatestEventInfo(context, from, message, contentIntent);
 * notif.defaults |= Notification.DEFAULT_SOUND; nm.notify(1, notif);
 * 
 * } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * 
 * 
 * 
 * 
 * 
 * }else
 * if(json.getString(TAG_STATUS).equals("Rejected")||json.getString(TAG_STATUS
 * ).equals("null")){ try {
 * 
 * 
 * nm = (NotificationManager) context
 * .getSystemService(Context.NOTIFICATION_SERVICE); CharSequence from =
 * "Appointment"; CharSequence message = "Sorry! Your appointment is Rejected.";
 * PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new
 * Intent(), 0);
 * 
 * @SuppressWarnings("deprecation") Notification notif = new
 * Notification(R.drawable.logo, "Appointment", System.currentTimeMillis());
 * notif.setLatestEventInfo(context, from, message, contentIntent);
 * notif.defaults |= Notification.DEFAULT_SOUND; nm.notify(1, notif);
 * 
 * 
 * } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }
 * 
 * }
 * 
 * } catch (JSONException e) { e.printStackTrace();
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * }
 */
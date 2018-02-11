package com.project.ehealthcarefacilities;


import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.os.IBinder;
import android.widget.Toast;

public class Test2 extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
    	System.out.println("in test2");
    	Intent service1 = new Intent(context, Abc2.class);
	       context.startService(service1);
     
       
    }
}


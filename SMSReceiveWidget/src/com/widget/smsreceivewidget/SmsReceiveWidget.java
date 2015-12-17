package com.widget.smsreceivewidget;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;



@SuppressLint("ShowToast")
public class SmsReceiveWidget extends AppWidgetProvider {
	
	  private static final String TAG = "HelloWidgetProvider";
	  private static final int WIDGET_UPDATE_INTERVAL = 500000;
	  private static PendingIntent mSender;
	  private static AlarmManager mManager;
	  
	  @Override
//	  public void onReceive(Context context, Intent intent)
//	  {
//	    super.onReceive(context, intent);
//	 
//	    String action = intent.getAction();
//	    // ���� ������Ʈ ����Ʈ�� �������� ��
//	    if(action.equals("android.appwidget.action.APPWIDGET_UPDATE"))
//	    {
//	      Log.w(TAG, "android.appwidget.action.APPWIDGET_UPDATE");
//	      removePreviousAlarm();
//	 
//	      long firstTime = System.currentTimeMillis() + WIDGET_UPDATE_INTERVAL;
//	      mSender = PendingIntent.getBroadcast(context, 0, intent, 0);
//	      mManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//	      mManager.set(AlarmManager.RTC, firstTime, mSender);
//	    }
//	    // ���� ���� ����Ʈ�� �������� ��
//	    else if(action.equals("android.appwidget.action.APPWIDGET_DISABLED"))
//	    {
//	      Log.w(TAG, "android.appwidget.action.APPWIDGET_DISABLED");
//	      removePreviousAlarm();
//	    }
//	  }
	
	
	@SuppressLint("ShowToast")
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		final int size = appWidgetIds.length;
		
		for (int i = 0; i < size; i++){
			int appWidgetId = appWidgetIds[i];
			
			String uriString = "";
			Uri uri = Uri.parse(uriString);
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
			@SuppressWarnings("unused")
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
			
			SharedPreferences prefs = context.getSharedPreferences("PrefName", 0);
			String text = prefs.getString("SMSMSG", "");
			
			
			
			if (text == ""){
				views.setTextViewText(R.id.smsWidget, "Test message");
				Toast.makeText(context, "Test message", 10);
			}else{
				views.setTextViewText(R.id.smsWidget, text);
				Toast.makeText(context, text, 10);
			}			

			appWidgetManager.updateAppWidget(appWidgetId,  views);					
			
		}
		
	}
	
	  /**
	   * ����Ǿ��ִ� �˶��� ����մϴ�.
	   */
	  public void removePreviousAlarm()
	  {
	    if(mManager != null && mSender != null)
	    {
	      mSender.cancel();
	      mManager.cancel(mSender);
	    }
	  }
}

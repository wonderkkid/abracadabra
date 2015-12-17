package com.widget.smsreceivewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;


public class SmsReceiver extends BroadcastReceiver{
	static final String logTag = "SmsReceiver";
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            //Bundel null check
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                   return;
            }

            //pdu object null check
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                   return;
            }
           
            String str = ""; // ����Ʈ�� �ֱ� ���� ���� String ���� ����

            //message
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
            for (int i = 0; i < pdusObj.length; i++) {
                   smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                   str = smsMessages[i].getMessageBody(); // ������ String �������� ����
            }           
		
		     intent.putExtra("smsmsg", str);		     
		     intent.setClass(context, RedirectActivity.class);
		     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		     context.startActivity(intent);
        }
    }
}

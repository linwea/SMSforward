package org.linwea.smsforward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < pdus.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            content.append(messages[i].getMessageBody());
        }
        String number = messages[0].getOriginatingAddress();

        Log.d("test",number+" "+content.toString());

        Toast.makeText(context,"收到信息",Toast.LENGTH_LONG).show();
        Intent serviceIntent = new Intent(context, SMSSendIntentService.class);
        serviceIntent.putExtra(SMSSendIntentService.FROM_NUMBER,number);
        serviceIntent.putExtra(SMSSendIntentService.MSG,content.toString());

        SharedPreferences preference=context.getSharedPreferences("shared", MODE_PRIVATE);
        String callnumber=preference.getString("phone","aaa");

        if(checkPhone.isPhone(callnumber)) {
            serviceIntent.putExtra(SMSSendIntentService.CALL_NUMBER, callnumber);
            context.startService(serviceIntent);
        }else
        {
            Toast.makeText(context,"号码："+callnumber+" 非电话号码",Toast.LENGTH_LONG).show();
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }


//    public static boolean isPhone(String inputText) {
//        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
//        Matcher m = p.matcher(inputText);
//        return m.matches();
//    }
}

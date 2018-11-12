package org.linwea.smsforward;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SMSSendIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "org.linwea.smsforward.action.FOO";
    private static final String ACTION_BAZ = "org.linwea.smsforward.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "org.linwea.smsforward.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "org.linwea.smsforward.extra.PARAM2";

    public static final String CALL_NUMBER = "org.linwea.smsforward.extra.CALL_NUMBER";
    public static final String FROM_NUMBER = "org.linwea.smsforward.extra.FROM_NUMBER";
    public static final String MSG = "org.linwea.smsforward.extra.MSG";

    public SMSSendIntentService() {
        super("SMSSendIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SMSSendIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SMSSendIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        if (intent != null) {
//            final String action = intent.getAction();
//            if (ACTION_FOO.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionFoo(param1, param2);
//            } else if (ACTION_BAZ.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
//            }




//        }


        final String _CALL_NUMBER = intent.getStringExtra(CALL_NUMBER);
        final String _FROM_NUMBER = intent.getStringExtra(FROM_NUMBER);
        final String _MSG = intent.getStringExtra(MSG);
        Log.d("test","service"+_CALL_NUMBER+" "+_MSG);

      //  Toast.makeText(SMSSendIntentService.this,"转发信息到:"+_CALL_NUMBER,Toast.LENGTH_LONG).show();

        sendMsg(_CALL_NUMBER,_FROM_NUMBER+":\n"+_MSG);

    }


//    public static boolean isPhone(String inputText) {
//        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
//        Matcher m = p.matcher(inputText);
//        return m.matches();
//    }


    private void sendMsg( String number,  String content) {

        if(!checkPhone.isPhone(number))
        {
            return;
        }
//        String number = mNumberText.getText().toString();
//
//        String content = mContentText.getText().toString();
        try {
            if (TextUtils.isEmpty(number)) {
                //  showToast("请输入手机号");
                return;
            }
            if (TextUtils.isEmpty(content)) {
                //  showToast("请输入内容");
                return;
            }
            ArrayList<String> messages = SmsManager.getDefault().divideMessage(content);
            for (String text : messages) {
                SmsManager.getDefault().sendTextMessage(number, null, text, null, null);
            }

            //showToast("Success!");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

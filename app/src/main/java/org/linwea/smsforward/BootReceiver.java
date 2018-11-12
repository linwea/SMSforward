package org.linwea.smsforward;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG_BOOT_BROADCAST_RECEIVER = "BOOT_BROADCAST_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
      //  throw new UnsupportedOperationException("Not yet implemented");


//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//           // MainService.enqueueWork(context, new Intent());
//        }
        Log.d("xxx","bbbbb");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
//            Intent i = new Intent(context, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);



                startServiceDirectly(context);

//            startServiceByAlarm(context);

        }




//        Log.d("WWWWWWWWWWWWWWWWWWWWW", "WWWWWWWWWWWWWWWWWWWWWWWW");
//        Toast.makeText(context,"boot成功",Toast.LENGTH_LONG).show();
//        Intent mBootIntent = new Intent(context, MainService.class);
//        //context.startService(mBootIntent);
//        Log.d("CCCCCCCCCCCCCCCCCCCCC", "CCCCCCCCCCCCCCCCCCCCCCCC");
    }


    private void startServiceDirectly(Context context)
    {
        try {
            while (true) {
                String message = "BootDeviceReceiver onReceive start service directly.";

               // Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                Log.d(TAG_BOOT_BROADCAST_RECEIVER, message);

                // This intent is used to start background service. The same service will be invoked for each invoke in the loop.
                Intent startServiceIntent = new Intent(context, MainService.class);
                context.startService(startServiceIntent);

                // Current thread will sleep one second.
                Thread.sleep(10000);
            }
        }catch(InterruptedException ex)
        {
            Log.e(TAG_BOOT_BROADCAST_RECEIVER, ex.getMessage(), ex);
        }
    }

    /* Create an repeat Alarm that will invoke the background service for each execution time.
     * The interval time can be specified by your self.  */
    private void startServiceByAlarm(Context context)
    {
        // Get alarm manager.
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Create intent to invoke the background service.
        Intent intent = new Intent(context, MainService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long startTime = System.currentTimeMillis();
        long intervalTime = 60*1000;

        String message = "Start service use repeat alarm. ";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        Log.d("xxxx", message);

        // Create repeat alarm.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);
    }
}

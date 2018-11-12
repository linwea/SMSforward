package org.linwea.smsforward;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service {
    public MainService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("xxxx", "o cs");
       // Toast.makeText(MainService.this,"onCreate成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("xxxx", "o c b s");
      //  showToast("Success!");
      //  Toast.makeText(MainService.this,"启动成功",Toast.LENGTH_LONG).show();
        return null;
       // throw new UnsupportedOperationException("Not yet implemented");
    }
}

package org.linwea.smsforward;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView tv1=(TextView) findViewById(R.id.tv1);
        final EditText editText=(EditText) findViewById(R.id.editText);

        SharedPreferences preference=getSharedPreferences("shared", MODE_PRIVATE);
        String callnumber=preference.getString("phone","aaa");
        tv1.setText(callnumber);


        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
               // sendMsg("13725283530","app 测试");
                String v=editText.getText().toString().trim();

                SharedPreferences preferences=MainActivity.this.getSharedPreferences("shared", Context.MODE_PRIVATE);

                         SharedPreferences.Editor editor=preferences.edit();
                         editor.putString("phone", v);


                        editor.commit();//提交数据

                tv1.setText(v);

                if(checkPhone.isPhone(v))
                {
                    Snackbar.make(view, "接收电话号码改为：" + v, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else
                {
                    Snackbar.make(view, "接收电话号码改为：" + v+" 当前设置不转发", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

//    private void sendMsg( String number,  String content) {
//
////        String number = mNumberText.getText().toString();
////
////        String content = mContentText.getText().toString();
//        try {
//            if (TextUtils.isEmpty(number)) {
//                //  showToast("请输入手机号");
//                return;
//            }
//            if (TextUtils.isEmpty(content)) {
//                //  showToast("请输入内容");
//                return;
//            }
//            ArrayList<String> messages = SmsManager.getDefault().divideMessage(content);
//            for (String text : messages) {
//                SmsManager.getDefault().sendTextMessage(number, null, text, null, null);
//            }
//            Log.d("MainActivity", "1");
//            //showToast("Success!");
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

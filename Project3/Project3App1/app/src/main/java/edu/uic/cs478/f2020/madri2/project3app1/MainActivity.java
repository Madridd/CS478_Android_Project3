package edu.uic.cs478.f2020.madri2.project3app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Button app1Button; //button to check permission
    protected TextView app1Textview; //welcome text
    private IntentFilter filter ; //intentFilter
    private BroadcastReceiver receiver; //broadcastReceiver

    private static final String PERMISSION = "edu.uic.cs478.f20.kaboom" ;
    private static final String TOAST_INTENT = "edu.uic.cs478.BroadcastReceiverApp1.showToast";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app1Button = (Button) findViewById(R.id.main_activitybutton1);
        app1Textview = (TextView) findViewById(R.id.mainactivity1text);

        //listener for button
        app1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermissionAndBroadcast();
            }
        }) ;

    }//end onCreate()

    @Override
    protected void onResume() {
        super.onResume();
        filter = new IntentFilter(TOAST_INTENT);
        filter.setPriority(2);
        receiver = new BroadCastReceiverApp1();
        registerReceiver(receiver, filter);
    }//end onResume()

    private void checkPermissionAndBroadcast() {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            Intent aIntent = new Intent(TOAST_INTENT) ;
            sendOrderedBroadcast(aIntent, PERMISSION) ;
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, 0);
        }

    }//end checkPermission()

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Intent aIntent = new Intent(TOAST_INTENT);
                sendOrderedBroadcast(aIntent, PERMISSION);
            }
            else {
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_LONG).show();
                //checkPermissionAndBroadcast();
            }
        }
    }//end onRequestPermission


    protected void onPause() {
        super.onPause() ;
        unregisterReceiver(receiver) ;
    }//end onPause()

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }//end onDestroy()


}
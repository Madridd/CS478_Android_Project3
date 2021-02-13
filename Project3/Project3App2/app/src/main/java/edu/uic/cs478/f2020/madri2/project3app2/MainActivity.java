package edu.uic.cs478.f2020.madri2.project3app2;

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
    private Button app2Button;  //button to check permission
    private TextView app2Textview;  //welcome text
    private BroadcastReceiverApp2 receiver; //broadcastReceiver
    private IntentFilter filter;   //intentFilter

    private static final String PERMISSION = "edu.uic.cs478.f20.kaboom" ;
    private static final String TOAST_INTENT = "edu.uic.cs478.BroadcastReceiverApp2.showToast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //welcome text and check permission button
        app2Button = (Button) findViewById(R.id.app2button);
        app2Textview = (TextView) findViewById(R.id.app2textview);

        //listener for button
        app2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermissionAndBroadcast();
            }
        }) ;


    }//end onCreate()

    @Override
    protected void onResume() {
        super.onResume();
        filter = new IntentFilter(TOAST_INTENT);
        filter.setPriority(1);
        receiver = new BroadcastReceiverApp2();
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
                finish();
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

}//end class
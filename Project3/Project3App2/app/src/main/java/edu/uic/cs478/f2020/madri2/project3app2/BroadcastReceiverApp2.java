package edu.uic.cs478.f2020.madri2.project3app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class BroadcastReceiverApp2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "app2 receiver! ", Toast.LENGTH_LONG).show() ;
        PackageManager pm = context.getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage("edu.uic.cs478.f2020.madri2.project3app3");
        launchIntent.putExtra("some_data", "value");
        context.startActivity(launchIntent);




    }
}

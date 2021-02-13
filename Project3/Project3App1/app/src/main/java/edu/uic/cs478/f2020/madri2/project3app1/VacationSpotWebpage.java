package edu.uic.cs478.f2020.madri2.project3app1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VacationSpotWebpage extends AppCompatActivity {

    private int position;

    private String webpages[] = {"https://en.parisinfo.com/",
                                "https://www.tahiti.com/island/bora-bora",
                                "https://www.gohawaii.com/islands/maui",
                                "https://www.japan-guide.com/e/e2164.html",
                                "https://www.lonelyplanet.com/italy/rome"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity2);
//
//        position = getIntent().getIntExtra("website",0);
////
////        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpages[position]));
////        startActivity(browserIntent);
//
//        Uri uri = Uri.parse(webpages.get(position));
//
//
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);



    }
}

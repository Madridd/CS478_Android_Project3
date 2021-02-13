package edu.uic.cs478.f2020.madri2.project3app3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  implements TitlesFragment.ListSelectionListener {

    public static String[] mTitleArray;
    public static Integer[] mImageArray = {R.drawable.paris,
                                            R.drawable.borabora,
                                            R.drawable.maui,
                                            R.drawable.tokyo,
                                            R.drawable.rome};

    //Keep shown index in activity to save and restore state
    int mShownIndex = -1 ;
    static String OLD_ITEM = "old_item" ;

    // Get a reference to the ImageFragment
    private final ImageFragment mImageFragment = new ImageFragment();
    private TitlesFragment mTitleFragment = null ;
//    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
//    private FrameLayout title_frame, quote_frame;
    private Toolbar toolbar_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_1);
        //getSupportActionBar().setIcon(R.drawable.vaca);



        //Get the string arrays with the titles and images
        mTitleArray = getResources().getStringArray(R.array.Titles);



        // Get references to the TitleFragment and to the QuotesFragment
        //title_frame = (FrameLayout) findViewById(R.id.title_frame);
        //quote_frame = (FrameLayout) findViewById(R.id.quote_frame);

        //Get a reference to support.v4 version of the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // Add the TitlesFragment
        // overlapping of fragments on same activity
        mTitleFragment = new TitlesFragment() ;
        mShownIndex = -1 ;

//        fragmentManager.addOnBackStackChangedListener(
//                // UB 2/24/2019 -- Use support version of Listener
//                new FragmentManager.OnBackStackChangedListener() {
//                    public void onBackStackChanged() {
//                        setLayout();
//                    }
//                });

        fragmentTransaction.replace(R.id.title_frame, mTitleFragment) ;

        // Add the ImageFragment
        fragmentTransaction.replace(R.id.quote_frame, mImageFragment);

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Retrieve old state if present
        if (savedInstanceState != null) {
            mShownIndex = savedInstanceState.getInt(OLD_ITEM) ;
        }
    }

//    private void setLayout() {
//
//        // Determine whether the QuoteFragment has been added
//        if (!mImageFragment.isAdded()) {
//
//            // Make the TitleFragment occupy the entire layout
//            title_frame.setLayoutParams(new LinearLayout.LayoutParams(
//                    MATCH_PARENT, MATCH_PARENT));
//            quote_frame.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT));
//        } else {
//
//
//            // Make the TitleLayout take 1/3 of the layout's width
//            title_frame.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT, 1f));
//
//            // Make the QuoteLayout take 2/3's of the layout's width
//            quote_frame.setLayoutParams(new LinearLayout.LayoutParams(0,
//                    MATCH_PARENT, 2f));
//        }
//    }

    //Activity about to be visible: Retrieve previous image, if saved
    //reset state of titles fragment
    public void onStart() {
        super.onStart() ;
        if (mShownIndex >= 0) {
            mImageFragment.showImageAtIndex(mShownIndex);
            mTitleFragment.setSelection(mShownIndex);
            mTitleFragment.getListView().setItemChecked(mShownIndex,true);
        }
    }
    // Implement interface ListSelectionListener
    // The TitlesFragment calls this method when the user selects a list item
    @Override
    public void onListSelection(int index) {
        if (mShownIndex != index) {
            // Tell the ImageFragment to show the image at position index
            mImageFragment.showImageAtIndex(index);
            // and update the shownIndex
            mShownIndex = index ;
        }
    }

    //Save currently shown image for later retrieval
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState) ;
        if (mShownIndex >= 0) {
            outState.putInt(OLD_ITEM, mShownIndex) ;
        }
        else {
            outState.putInt(OLD_ITEM, -1 ) ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_launchApps:
                //need to launch apps
                PackageManager pm = getPackageManager();
                Intent launchIntent = pm.getLaunchIntentForPackage("edu.uic.cs478.f2020.madri2.project3app1");
                Intent launchIntent2 = pm.getLaunchIntentForPackage("edu.uic.cs478.f2020.madri2.project3app2");

                //startActivity(launchIntent);
                Intent intents[] = {launchIntent2, launchIntent};
                startActivities(intents);
                return true;
            case R.id.action_quit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

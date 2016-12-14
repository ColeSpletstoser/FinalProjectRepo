package cs188.drakeactivities;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public CustomAdapter adapter;
    public FragmentManager mFragmentManager;
    public static final String PREFS_NAME = "DrakeActivities";

    //make shared preference to store user points. Placeholder for now
    public int userPoints = 0;

    ArrayList<EventClass> events;
    public ArrayList<Integer> savedEvents = new ArrayList<Integer>();
    Set<String> eventSet = new HashSet<String>();

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isLoggedIn = false;

    Fragment switchable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchable = new FirstFragment();

        adapter = new CustomAdapter(getSupportFragmentManager(), getApplicationContext());

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

//        if(!settings.contains("isLoggedIn"))
//        {
//            Toast.makeText(getApplicationContext(), "isNotLoggedIn", Toast.LENGTH_SHORT);
//        }

        //Returns NPE in the for each loop
//        if(settings.contains("isLoggedIn"))
//        {
//           eventSet = new HashSet<String>();
//            savedEvents = new ArrayList<Integer>();
//            Toast.makeText(getApplicationContext(), "isLoggedIn", Toast.LENGTH_SHORT);
//            isLoggedIn = settings.getBoolean("isLoggedIn", false);
//            userPoints = settings.getInt("points", 0);
//            eventSet = settings.getStringSet("eventSet", null);
//
//            for(String id: eventSet)
//            {
//                savedEvents.add(Integer.parseInt(id));
//            }
//        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Drake Rewards will not reward points unless Location Services are enabled");
            dialog.setPositiveButton(this.getResources().getString(R.string.common_google_play_services_enable_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(this.getString(R.string.cast_tracks_chooser_dialog_cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            Log.v("hi", "hi2");
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        EventClass proj1 = new EventClass();
        EventClass proj2 = new EventClass();
        EventClass proj3 = new EventClass();
        EventClass proj4 = new EventClass();
        EventClass proj5 = new EventClass();
        EventClass proj6 = new EventClass();
        EventClass proj7 = new EventClass();

        proj1.setEventTitle("Drake MBB vs Iona University");
        proj1.setOrganizationUsername("Drake Athletics");
        proj1.setEventTime("2:05 pm");
        proj1.setEventDay(20);
        proj1.setEventMonth(12);
        proj1.setEventYear(2016);
        proj1.setEventDescription("Come to the Knapp and support the Drake Men’s Basketball as they take on Iona!");
        proj1.setEventCode("SportsGoSports");
        proj1.setEventIcon(R.mipmap.bball);
        proj1.setLongitude(41.6036036);
        proj1.setLatitude(-93.6374793);
        proj1.setEventCode("DRAKE");
        proj1.setEventID(1);

        proj2.setEventTitle("Christmas Dinner");
        proj2.setOrganizationUsername("Sodexo");
        proj2.setEventTime("5:00 - 7:00 pm");
        proj2.setEventDay(13);
        proj2.setEventMonth(12);
        proj2.setEventYear(2016);
        proj2.setEventDescription("Join us for a delicious Christmas dinner this Wednesday.");
        proj2.setEventCode("XmasYumYum");
        proj2.setEventIcon(R.mipmap.dinner);
        proj2.setLongitude(41.6036036);
        proj2.setLatitude(-93.6374793);
        proj2.setEventCode("DRAKE");
        proj2.setEventID(2);

        proj3.setEventTitle("Snowman Building Contest");
        proj3.setOrganizationUsername("SAB");
        proj3.setEventTime("1:00 - 3:00 pm");
        proj3.setEventDay(14);
        proj3.setEventMonth(12);
        proj3.setEventYear(2016);
        proj3.setEventDescription("Come build your best snowman, and see how you fare against other students. President Martin will be there to judge the snowmen.");
        proj3.setEventCode("FrostyTheSnowBulldog");
        proj3.setEventIcon(R.mipmap.snowman);
        proj3.setLongitude(41.6036036);
        proj3.setLatitude(-93.6374793);
        proj3.setEventCode("DRAKE");
        proj3.setEventID(3);

        proj4.setEventTitle("Squirrel Watching");
        proj4.setOrganizationUsername("Drake University");
        proj4.setEventTime("3:00 pm");
        proj4.setEventDay(15);
        proj4.setEventMonth(12);
        proj4.setEventYear(2016);
        proj4.setEventDescription("Bring your friends, phones, and nuts as we gather to watch the squirrel in its natural habitat! Hand feed at your own risk.");
        proj4.setEventCode("FriendsPhonesNuts");
        proj4.setEventIcon(R.mipmap.squirrel);
        proj4.setLongitude(41.6036036);
        proj4.setLatitude(-93.6374793);
        proj4.setEventCode("DRAKE");
        proj4.setEventID(4);

        proj5.setEventTitle("Speed Dating");
        proj5.setOrganizationUsername("SAB");
        proj5.setEventTime("8:00 pm");
        proj5.setEventDay(15);
        proj5.setEventMonth(12);
        proj5.setEventYear(2016);
        proj5.setEventDescription("Don’t have any plans on Valentines day? Are you forever alone? Then come to Upper Olmsted and join us as we try to match students through 2 minutes speed dates! Dress to impress.");
        proj5.setEventCode("Sad-N-Lonely");
        proj5.setEventIcon(R.mipmap.speeddate);
        proj5.setLongitude(41.6036036);
        proj5.setLatitude(-93.6374793);
        proj5.setEventCode("DRAKE");
        proj5.setEventID(5);

        proj6.setEventTitle("Blood Drive");
        proj6.setOrganizationUsername("Drake University/Blood America");
        proj6.setEventTime("9:00 am – 5:00 pm");
        proj6.setEventDay(18);
        proj6.setEventMonth(12);
        proj6.setEventYear(2017);
        proj6.setEventDescription("Come to blood mobile and donate your blood! – No vampires here.");
        proj6.setEventCode("TeamEdward");
        proj6.setEventIcon(R.mipmap.blooddrive);
        proj6.setLongitude(41.6036036);
        proj6.setLatitude(-93.6374793);
        proj6.setEventCode("DRAKE");
        proj6.setEventID(6);

        proj7.setEventTitle("Arm-wrestling tournament");
        proj7.setOrganizationUsername("SAB");
        proj7.setEventTime("7:00 pm");
        proj7.setEventDay(20);
        proj7.setEventMonth(12);
        proj7.setEventYear(2016);
        proj7.setEventDescription("Think you got what it takes to be the arm-wrestling champ of Drake University? – Then join us at 7:00 pm in Upper Olmsted. Tables will be provided.");
        proj7.setEventCode("EgoDeflater5000");
        proj7.setEventIcon(R.mipmap.armwrestle);
        proj7.setLongitude(41.6036036);
        proj7.setLatitude(-93.6374793);
        proj7.setEventCode("DRAKE");
        proj7.setEventID(7);

        events = new ArrayList<EventClass>();
        events.add(proj1);
        events.add(proj2);
        events.add(proj3);
        events.add(proj4);
        events.add(proj5);
        events.add(proj6);
        events.add(proj7);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0)
                {
                    if (mFragmentManager.getBackStackEntryCount() > 0){
                        mFragmentManager.popBackStack();
                    }
                }
                else if (tab.getPosition() == 1)
                {
                    if (mFragmentManager.getBackStackEntryCount() > 0){
                        mFragmentManager.popBackStack();
                    }
                }
                else if (tab.getPosition() == 2)
                {
                    if (mFragmentManager.getBackStackEntryCount() > 0){
                        mFragmentManager.popBackStack();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Catch back action and pops from backstack
        // (if you called previously to addToBackStack() in your transaction)
        if (mFragmentManager.getBackStackEntryCount() > 0){
            mFragmentManager.popBackStack();
        }
        // Default action on back pressed
        else super.onBackPressed();
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        eventSet = new HashSet<String>();
        //add things here. ArrayList needs to be converted to a set to be saved,
        //and converted back to be called

        for(Integer event: savedEvents)
        {
            eventSet.add(Integer.toString(event));
        }

        //points, isloggedin,

        editor.putStringSet("eventset", eventSet);
        editor.putInt("points", userPoints);
        editor.putBoolean("isLoggedIn", isLoggedIn);

        editor.commit();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.v("hi", "hi3");

        //Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.INTERNET
                }, 10);

                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getLocation();
                return;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("hi", "failed");
    }

    double[] getLocation() {
        double[] userLocation = new double[]{0, 0};
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return userLocation;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            userLocation[0] = mLastLocation.getLatitude();
            userLocation[1] = mLastLocation.getLongitude();
        }
        return userLocation;
    }

    public void addPoints(int pts)
    {
        userPoints =  userPoints + pts;
    }

    public int getPoints() { return userPoints; }

    public void addSavedEvent(int eventID)
    {
        savedEvents.add(eventID);
    }

    public ArrayList<Integer> getSavedEvents() { return savedEvents; }

    private class CustomAdapter extends FragmentStatePagerAdapter {

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
            mFragmentManager = supportFragmentManager;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }




}

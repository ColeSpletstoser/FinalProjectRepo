package cs188.drakeactivities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public CustomAdapter adapter;
    public FragmentManager mFragmentManager;

    Fragment switchable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchable = new FirstFragment();

        adapter = new CustomAdapter(getSupportFragmentManager(), getApplicationContext());

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
        proj1.setEventDay(29);
        proj1.setEventMonth(12);
        proj1.setEventYear(2016);
        proj1.setEventDescription("Come to the Knapp and support the Drake Men’s Basketball as they take on Iona!");
        proj1.setEventCode("SportsGoSports");

        proj2.setEventTitle("Snowman building contest");
        proj2.setOrganizationUsername("SAB");
        proj2.setEventTime("1:00-3:00 pm");
        proj2.setEventDay(15);
        proj2.setEventMonth(12);
        proj2.setEventYear(2016);
        proj2.setEventDescription("");
        proj2.setEventCode("");

        proj3.setEventTitle("");
        proj3.setOrganizationUsername("");
        proj3.setEventTime("");
        proj3.setEventDay(16);
        proj3.setEventMonth(12);
        proj3.setEventYear(2016);
        proj3.setEventDescription("");
        proj3.setEventCode("");

        proj4.setEventTitle("");
        proj4.setOrganizationUsername("");
        proj4.setEventTime("");
        proj4.setEventDay(17);
        proj4.setEventMonth(12);
        proj4.setEventYear(2016);
        proj4.setEventDescription("");
        proj4.setEventCode("");

        proj5.setEventTitle("");
        proj5.setOrganizationUsername("");
        proj5.setEventTime("");
        proj5.setEventDay(18);
        proj5.setEventMonth(12);
        proj5.setEventYear(2016);
        proj5.setEventDescription("");
        proj5.setEventCode("");

        proj6.setEventTitle("");
        proj6.setOrganizationUsername("");
        proj6.setEventTime("");
        proj6.setEventDay(19);
        proj6.setEventMonth(12);
        proj6.setEventYear(2016);
        proj6.setEventDescription("");
        proj6.setEventCode("");

        proj7.setEventTitle("");
        proj7.setOrganizationUsername("");
        proj7.setEventTime("");
        proj7.setEventDay(20);
        proj7.setEventMonth(12);
        proj7.setEventYear(2016);
        proj7.setEventDescription("");
        proj7.setEventCode("");






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

    private class CustomAdapter extends FragmentStatePagerAdapter {

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
            mFragmentManager = supportFragmentManager;
        }

        public boolean userHasAccount = true;

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    if(userHasAccount) //change to check for if the user has an account
                        return new ProfileFragment();
                    else
                        return new LogInFragment();
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

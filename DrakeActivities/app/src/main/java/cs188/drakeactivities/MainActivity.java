package cs188.drakeactivities;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public CustomAdapter adapter;
    public FragmentManager mFragmentManager;

    ArrayList<EventClass> events;

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
        proj1.setEventIcon(R.drawable.drake_basketball);

        proj2.setEventTitle("Christmas Dinner");
        proj2.setOrganizationUsername("Sodexo");
        proj2.setEventTime("5:00 - 7:00 pm");
        proj2.setEventDay(14);
        proj2.setEventMonth(12);
        proj2.setEventYear(2016);
        proj2.setEventDescription("Join us for a delicious Christmas dinner this Wednesday.");
        proj2.setEventCode("XmasYumYum");
        proj2.setEventIcon(R.drawable.christmas_dinner);

        proj3.setEventTitle("Snowman Building Contest");
        proj3.setOrganizationUsername("SAB");
        proj3.setEventTime("1:00 - 3:00 pm");
        proj3.setEventDay(14);
        proj3.setEventMonth(12);
        proj3.setEventYear(2016);
        proj3.setEventDescription("Come build your best snowman, and see how you fare against other students. President Martin will be there to judge the snowmen.");
        proj3.setEventCode("FrostyTheSnowBulldog");
        proj3.setEventIcon(R.drawable.snowman_building);

        proj4.setEventTitle("Squirrel Watching");
        proj4.setOrganizationUsername("Drake University");
        proj4.setEventTime("3:00 pm");
        proj4.setEventDay(15);
        proj4.setEventMonth(12);
        proj4.setEventYear(2016);
        proj4.setEventDescription("Bring your friends, phones, and nuts as we gather to watch the squirrel in its natural habitat! Hand feed at your own risk.");
        proj4.setEventCode("FriendsPhonesNuts");
        proj4.setEventIcon(R.drawable.squirrel_watching);

        proj5.setEventTitle("Speed Dating");
        proj5.setOrganizationUsername("SAB");
        proj5.setEventTime("8:00 pm");
        proj5.setEventDay(14);
        proj5.setEventMonth(2);
        proj5.setEventYear(2016);
        proj5.setEventDescription("Don’t have any plans on Valentines day? Are you forever alone? Then come to Upper Olmsted and join us as we try to match students through 2 minutes speed dates! Dress to impress.");
        proj5.setEventCode("Sad-N-Lonely");
        proj5.setEventIcon(R.drawable.maxresdefault);

        proj6.setEventTitle("Blood Drive");
        proj6.setOrganizationUsername("Drake University/Blood America");
        proj6.setEventTime("9:00 am – 5:00 pm");
        proj6.setEventDay(1);
        proj6.setEventMonth(12);
        proj6.setEventYear(2016);
        proj6.setEventDescription("Come to blood mobile and donate your blood! – No vampires here.");
        proj6.setEventCode("TeamEdward");
        proj6.setEventIcon(R.drawable.blooddrive);

        proj7.setEventTitle("Arm-wrestling tournament");
        proj7.setOrganizationUsername("SAB");
        proj7.setEventTime("7:00 pm");
        proj7.setEventDay(20);
        proj7.setEventMonth(1);
        proj7.setEventYear(2016);
        proj7.setEventDescription("Think you got what it takes to be the arm-wrestling champ of Drake University? – Then join us at 7:00 pm in Upper Olmsted. Tables will be provided.");
        proj7.setEventCode("EgoDeflater5000");
        proj7.setEventIcon(R.drawable.armwhrestling);


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

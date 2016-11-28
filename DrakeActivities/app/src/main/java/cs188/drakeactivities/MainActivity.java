package cs188.drakeactivities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        adapter = new CustomAdapter(getSupportFragmentManager(), getApplicationContext());

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
                if (tab.getPosition() == 0 && (switchable instanceof EventDescription))
                {
                    switchable = null;
                    adapter.notifyDataSetChanged();
                }
            }
        });

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged()
            {
//              no clue what it wants here
                switchable = null;
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void switchFragments()
    {
        mFragmentManager.beginTransaction().remove(switchable).add(switchable, "fragment").addToBackStack().commit();
        switchable = new EventDescription();
        adapter.notifyDataSetChanged();
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
                    if (switchable == null)
                    {
                        switchable = new DayFragment();
                    }
                    return switchable;
                case 1:
                    return new CalendarFragment();
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

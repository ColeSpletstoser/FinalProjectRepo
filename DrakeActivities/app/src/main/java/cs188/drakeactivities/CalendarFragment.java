package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by Spletz on 11/15/16.
 */
public class CalendarFragment extends Fragment {

    public FragmentManager fragmentManager;

    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.calendar_fragment, container, false);

        calendarView = (CalendarView) layout.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                ((MainActivity)getActivity()).switchFragments1();
            }
        });

//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            public void onBackStackChanged()
//            {
////              no clue what it wants here
////                switchable = null;
////                adapter.notifyDataSetChanged();
//            }
//        });

        return layout;
    }
}

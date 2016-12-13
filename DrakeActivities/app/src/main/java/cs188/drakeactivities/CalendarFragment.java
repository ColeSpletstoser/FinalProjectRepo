package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.ArrayList;

/**
 * Created by Spletz on 11/15/16.
 */
public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    ArrayList<EventClass> events;
    ArrayList<EventClass> dayEvents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.calendar_fragment, container, false);

        calendarView = (CalendarView) layout.findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                events = ((MainActivity)getActivity()).events;

                dayEvents = new ArrayList<EventClass>();

                for (EventClass event: events)
                {
                    if(event.getEventDay() == dayOfMonth && event.getEventMonth() == month+1 && event.getEventYear() == year)
                    {
                        dayEvents.add(event);
                    }
                }
                if (dayEvents == null || dayEvents.isEmpty()){
                    return;
                }

                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                Fragment dayFragment = new DayFragment();

                Bundle args = new Bundle();
                args.putInt("eventYear", year);
                args.putInt("eventMonth", month+1);
                args.putInt("eventDay", dayOfMonth);
                dayFragment.setArguments(args);

                trans.replace(R.id.holder1, dayFragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return layout;
    }
}

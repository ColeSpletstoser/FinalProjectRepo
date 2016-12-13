package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Spletz on 11/15/16.
 */
public class TodayFragment extends Fragment {

    private TextView noEventsTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.today_fragment, container, false);

        noEventsTextView = (TextView) layout.findViewById(R.id.noEventsTextView);

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        final int currentDay = localCalendar.get(Calendar.DATE);
        final int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        final int currentYear = localCalendar.get(Calendar.YEAR);

        final ArrayList<EventClass> events = ((MainActivity)getActivity()).events;

        final ArrayList<EventClass> dayEvents = new ArrayList<EventClass>();

        for (EventClass event: events)
        {
            if(event.getEventDay() == currentDay && event.getEventMonth() == currentMonth && event.getEventYear() == currentYear)
            {
                dayEvents.add(event);
            }
        }

        if(dayEvents == null || dayEvents.isEmpty())
        {
            noEventsTextView.setText("There are no events today :(");
        }
        else
        {
            ListView list;

            CustomListAdapter adapter = new CustomListAdapter(getActivity(), dayEvents);
            list =(ListView)layout.findViewById(android.R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    FragmentTransaction trans = getFragmentManager()
                            .beginTransaction();

                    Fragment eventDescription = new EventDescription();

                    //title, desc, date, time, pic

                    Bundle args = new Bundle();

                    args.putString("eventTitle", dayEvents.get(position).getEventTitle());
                    args.putString("eventDescription", dayEvents.get(position).getEventDescription());
                    args.putInt("eventYear", currentYear);
                    args.putInt("eventMonth", currentMonth);
                    args.putInt("eventDay", currentDay);
                    args.putString("eventTime", dayEvents.get(position).getEventTime());
                    args.putString("eventTitle", dayEvents.get(position).getEventTitle());
                    args.putInt("eventIcon", dayEvents.get(position).getEventIcon());
                    args.putDouble("longitude", dayEvents.get(position).getLongitude());
                    args.putDouble("latitude", dayEvents.get(position).getLatitude());
                    eventDescription.setArguments(args);

                    trans.replace(R.id.holder, eventDescription);
                    trans.addToBackStack(null);
                    trans.commit();
                }
            });

        }
        return layout;
    }
}

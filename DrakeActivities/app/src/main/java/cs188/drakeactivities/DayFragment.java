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

/**
 * Created by Spletz on 11/15/16.
 */
public class DayFragment extends Fragment {

    private Button testButton;
    private TextView dayTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.today_fragment, container, false);

//        dayTextView = (TextView) layout.findViewById(R.id.dayTextView);


        final int eventYear = getArguments().getInt("eventYear", 0);
        final int eventDay = getArguments().getInt("eventDay", 0);
        final int eventMonth = getArguments().getInt("eventMonth", 0);


//        dayTextView.append(" ");
//        dayTextView.append(String.valueOf(eventMonth));
//        dayTextView.append(" ");
//        dayTextView.append(String.valueOf(eventDay));
//        dayTextView.append(" ");
//        dayTextView.append(String.valueOf(eventYear));
//
//        testButton = (Button) layout.findViewById(R.id.testButton);
//
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction trans = getFragmentManager()
//                                .beginTransaction();
//                trans.replace(R.id.holder1, new EventDescription());
//                trans.addToBackStack(null);
//                trans.commit();
//            }
//        });

        final ArrayList<EventClass> events = ((MainActivity)getActivity()).events;

        final ArrayList<EventClass> dayEvents = new ArrayList<EventClass>();

        for (EventClass event: events)
        {
            if(event.getEventDay() == eventDay && event.getEventMonth() == eventMonth && event.getEventYear() == eventYear)
            {
                dayEvents.add(event);
            }
        }

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
                args.putInt("eventYear", eventYear);
                args.putInt("eventMonth", eventMonth);
                args.putInt("eventDay", eventDay);
                args.putString("eventTime", dayEvents.get(position).getEventTime());
                args.putString("eventTitle", dayEvents.get(position).getEventTitle());
                args.putInt("eventIcon", dayEvents.get(position).getEventIcon());
                eventDescription.setArguments(args);

                trans.replace(R.id.holder1, eventDescription);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return layout;
    }
}

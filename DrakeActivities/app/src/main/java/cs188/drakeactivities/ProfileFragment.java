package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spletz on 11/15/16.
 */
public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.profile_fragment, container, false);

        int currentPoints = getArguments().getInt("points", 0);

        String total = "/1000 points";
        String current;

        ImageView profPic = (ImageView) layout.findViewById(R.id.profPic);
        TextView name = (TextView) layout.findViewById(R.id.name);
        TextView email = (TextView) layout.findViewById(R.id.email);
        ProgressBar progress = (ProgressBar) layout.findViewById(R.id.progress);
        TextView points = (TextView) layout.findViewById(R.id.points);

        int pts = ((MainActivity)getActivity()).getPoints();
        progress.setProgress(currentPoints);

        current = Integer.toString(currentPoints);
        total = current + total;
        points.setText(total);

        final ArrayList<EventClass> events = ((MainActivity)getActivity()).events;

        final ArrayList<Integer> savedEvents = ((MainActivity)getActivity()).getSavedEvents();

        final ArrayList<EventClass> listEvents = new ArrayList<EventClass>();

        for (EventClass event: events)
        {
            for(int id: savedEvents)
            {
                if(event.getEventID() == id)
                {
                    listEvents.add(event);
                }
            }
        }

        if(listEvents != null && !listEvents.isEmpty())
        {
            ListView list;

            CustomListAdapter adapter = new CustomListAdapter(getActivity(), listEvents);
            list =(ListView)layout.findViewById(R.id.savedEvents);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    FragmentTransaction trans = getFragmentManager()
                            .beginTransaction();

                    Fragment eventDescription = new EventDescription();

                    Bundle args = new Bundle();

                    args.putString("eventTitle", listEvents.get(position).getEventTitle());
                    args.putString("eventDescription", listEvents.get(position).getEventDescription());
                    args.putInt("eventYear", listEvents.get(position).getEventYear());
                    args.putInt("eventMonth", listEvents.get(position).getEventMonth());
                    args.putInt("eventDay", listEvents.get(position).getEventDay());
                    args.putString("eventTime", listEvents.get(position).getEventTime());
                    args.putString("eventTitle", listEvents.get(position).getEventTitle());
                    args.putInt("eventIcon", listEvents.get(position).getEventIcon());
                    args.putDouble("longitude", listEvents.get(position).getLongitude());
                    args.putDouble("latitude", listEvents.get(position).getLatitude());
                    args.putString("eventCode", listEvents.get(position).getEventCode());
                    eventDescription.setArguments(args);

                    trans.replace(R.id.holder2, eventDescription);
                    trans.addToBackStack(null);
                    trans.commit();
                }
            });
        }

        return layout;
    }
}

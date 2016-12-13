package cs188.drakeactivities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class EventDescription extends Fragment {
    @Nullable
    String eventCode;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_event_description, container, false);

        final String checkCode = "DRAKE";

       final int eventYear = getArguments().getInt("eventYear", 0);
       final int eventDay = getArguments().getInt("eventDay", 0);
        final int eventMonth = getArguments().getInt("eventMonth", 0);
        final String eventTitle = getArguments().getString("eventTitle", "");
        final String eventDescription = getArguments().getString("eventDescription", "");
        final int eventIcon = getArguments().getInt("eventIcon", 0);
        //final int eventTime = getArguments().getInt("eventTime", 0);

        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        Button enter = (Button) v.findViewById(R.id.enterCode);
        TextView titleText = (TextView) v.findViewById(R.id.EventTitle);
        TextView descText = (TextView) v.findViewById(R.id.Description);


        titleText.setText(eventTitle);
        descText.setText(eventDescription);







        image.setImageResource(eventIcon);


        final EditText code = (EditText) v.findViewById(R.id.eventCode);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int duration = Toast.LENGTH_SHORT;
                Context context = getActivity();
                //Toast toast = Toast.makeText(context, "Saved event to calendar", duration);
                Calendar cal = Calendar.getInstance();

                cal.set(eventYear, eventMonth, eventDay);
                //will need to figure out how to split time into hour/minute
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setData(CalendarContract.Events.CONTENT_URI);
                calIntent.setType("vnd.android.cursor.item/event");
                //calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis()); specify start time
                calIntent.putExtra(CalendarContract.Events.TITLE, eventTitle);
                //calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, );
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, eventDescription);
                startActivity(calIntent);
                //toast.show();
            }
        });

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                double[] userLocation = ((MainActivity) getActivity()).getLocation();
                Toast.makeText(getActivity(), String.valueOf(userLocation[0]), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), String.valueOf(userLocation[1]), Toast.LENGTH_SHORT).show();

                eventCode = code.getText().toString();
                //int duration = Toast.LENGTH_SHORT;
                //Context context = getActivity();
                //Toast toast = Toast.makeText(context, eventCode, duration);
                //toast.show();

//                if(eventCode.equals(checkCode))
//                {
                    ((MainActivity)getActivity()).addPoints(100);
               // }

            }
        });

        return v;
    }
}



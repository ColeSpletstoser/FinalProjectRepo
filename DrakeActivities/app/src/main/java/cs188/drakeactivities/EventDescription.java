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
        String eventTitle, eventDesc;
        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        Button enter = (Button) v.findViewById(R.id.enterCode);
        TextView titleText = (TextView) v.findViewById(R.id.EventTitle);
        TextView descText = (TextView) v.findViewById(R.id.Description);

        eventTitle = "Tommy";
        eventDesc = "Tommy";

        titleText.setText(eventTitle);
        descText.setText(eventDesc);


        //image.setImageResource(R.drawable.imagename) for setting the image in the top


        final EditText code = (EditText) v.findViewById(R.id.eventCode);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duration = Toast.LENGTH_SHORT;
                Context context = getActivity();
                Toast toast = Toast.makeText(context, "Saved event to calendar", duration);
                Calendar cal = Calendar.getInstance();

                //cal.set(year month day hour minute second)
                //will need to figure out how to split time into hour/minute
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setData(CalendarContract.Events.CONTENT_URI);
                calIntent.setType("vnd.android.cursor.item/event");
                //calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis()); specify start time
                calIntent.putExtra(CalendarContract.Events.TITLE, "Tommy");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Tommy");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Tommy");
                startActivity(calIntent);
                toast.show();
            }
        });

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                eventCode = code.getText().toString();
                int duration = Toast.LENGTH_SHORT;
                Context context = getActivity();
                Toast toast = Toast.makeText(context, eventCode, duration);
                toast.show();

            }
        });

        return v;
    }
}



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
import android.widget.Toast;


public class EventDescription extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_event_description, container, false);

        Button enter = (Button) v.findViewById(R.id.enterCode);

        final EditText code = (EditText) v.findViewById(R.id.eventCode);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duration = Toast.LENGTH_SHORT;
                Context context = getActivity();
                Toast toast = Toast.makeText(context, "Saved event to calendar", duration);
                toast.show();
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setData(CalendarContract.Events.CONTENT_URI);

                startActivity(calIntent);

            }
        });

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int duration = Toast.LENGTH_SHORT;
                Context context = getActivity();
                Toast toast = Toast.makeText(context, "Points awarded", duration);
                toast.show();
            }
        });

        return v;
    }
}



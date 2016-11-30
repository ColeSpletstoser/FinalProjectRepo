package cs188.drakeactivities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class EventDescription extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_event_description, container, false);
        View v =  inflater.inflate(R.layout.fragment_event_description, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
       final FloatingActionButton cal = (FloatingActionButton) v.findViewById(R.id.saveCal);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.setVisibility(View.VISIBLE);
                //TODO ask Clayton about saving to calendars, bringing up login screen
                //Intent in = new Intent(getActivity(), .class);
                //startActivity(in);
            }
        });

        return layout;
    }
}

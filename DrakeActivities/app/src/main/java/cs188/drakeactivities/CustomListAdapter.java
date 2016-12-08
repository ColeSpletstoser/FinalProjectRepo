package cs188.drakeactivities;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<EventClass> {

    private final Activity context;
    private final ArrayList<EventClass> events;

    public CustomListAdapter(Activity context, ArrayList<EventClass> events) {
        super(context, R.layout.day_list_item, events);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.events=events;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.day_list_item, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(events.get(position).getEventTitle());
        imageView.setImageResource(events.get(position).getEventIcon());
        extratxt.setText(events.get(position).getEventTime());
        return rowView;

    };
}

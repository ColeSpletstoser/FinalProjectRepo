package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        int eventYear = getArguments().getInt("eventYear", 0);
        int eventDay = getArguments().getInt("eventDay", 0);
        int eventMonth = getArguments().getInt("eventMonth", 0);

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

        return layout;
    }
}

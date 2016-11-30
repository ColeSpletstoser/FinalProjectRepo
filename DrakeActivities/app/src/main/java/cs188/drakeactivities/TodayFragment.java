package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

/**
 * Created by Spletz on 11/15/16.
 */
public class TodayFragment extends Fragment {

    private Button testButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.today_fragment, container, false);

        testButton = (Button) layout.findViewById(R.id.testButton);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                                .beginTransaction();
                trans.replace(R.id.holder, new EventDescription());
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        TextView txtView = (TextView) layout.findViewById(R.id.todayfrag);

        return layout;
    }
}

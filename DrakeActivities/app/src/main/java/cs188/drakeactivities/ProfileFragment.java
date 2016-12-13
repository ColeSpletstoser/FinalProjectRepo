package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        return layout;
    }
}

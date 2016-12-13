package cs188.drakeactivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    private Fragment fragment;

    public ThirdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        //check locally saved boolean
        transaction.replace(R.id.holder2, new LogInFragment());
        transaction.commit();
        return view;
    }

}

package cs188.drakeactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Spletz on 11/15/16.
 */
public class LogInFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.login_fragment, container, false);

        //do work here
        final EditText username = (EditText) layout.findViewById(R.id.username);
        final EditText password = (EditText) layout.findViewById(R.id.password);
        Button login = (Button) layout.findViewById(R.id.login);



        final String fakeUser = "123456789";
        final String fakePass = "drakerewards";

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user,pass;
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals(fakeUser) && pass.equals(fakePass))
                {
                    //this is where we would have a database and check passwords
//                    Fragment ProfileFragment = new ProfileFragment();
//                    FragmentTransaction trans = getFragmentManager()
//                            .beginTransaction();
//                    trans.replace(R.id.holder2, ProfileFragment);
//                    trans.commit();
//
//                    FragmentTransaction trans = getFragmentManager()
//                            .beginTransaction();

                    FragmentTransaction trans = getFragmentManager()
                            .beginTransaction();

                    Fragment ProfileFragment = new ProfileFragment();

                    Bundle args = new Bundle();

                    args.putInt("points", 0);
                    ProfileFragment.setArguments(args);

                    trans.replace(R.id.holder2, ProfileFragment);
                    trans.commit();
                }
            }
        });

        return layout;
    }
}

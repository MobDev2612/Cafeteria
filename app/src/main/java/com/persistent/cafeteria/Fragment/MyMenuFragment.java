package com.persistent.cafeteria.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.persistent.cafeteria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMenuFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton savedMenu,personalisedMenu;

    public MyMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.my_menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_menu, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        savedMenu = (ImageButton) view.findViewById(R.id.saved_menu_button);
        personalisedMenu = (ImageButton) view.findViewById(R.id.personalised_menu_button);
        savedMenu.setOnClickListener(this);
        personalisedMenu.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.my_menu);
    }

    @Override
    public void onClick(View v) {
        if(v == savedMenu){
            //TODO
        } else if(v == personalisedMenu){
            changeFragment(new DietPlanFormFragment());
        }
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack("home");
        fragmentTransaction.commit();
    }
}

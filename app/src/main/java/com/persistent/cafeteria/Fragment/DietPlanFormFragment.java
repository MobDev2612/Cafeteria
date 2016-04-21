package com.persistent.cafeteria.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.persistent.cafeteria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietPlanFormFragment extends Fragment {


    public DietPlanFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.personal_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_plan_form, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.personal_info);
    }
}

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
public class ReserveTableFragment extends Fragment {


    public ReserveTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve_table, container, false);
    }

}

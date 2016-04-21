package com.persistent.cafeteria.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.CommonMethods;
import com.persistent.cafeteria.datamodels.Facilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Spinner locationSpinner;
    private TextView locationStatus;
    private ImageButton myMenuButton, createEventButton, menuButton, reserveTableButton;
    private Context mContext;
    Facilities[] facilities;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_home);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.title_activity_home);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String[] status = new String[]{"Off Hour", "Moderate", "Rush Hour"};
        final String[] color = new String[]{"#04B431", "#FFFF00", "#FF0000"};
        locationSpinner = (Spinner) view.findViewById(R.id.facilities_list);
        locationStatus = (TextView) view.findViewById(R.id.facilities_status);
        myMenuButton = (ImageButton) view.findViewById(R.id.home_my_menu_button);
        menuButton = (ImageButton) view.findViewById(R.id.home_menu_button);
        createEventButton = (ImageButton) view.findViewById(R.id.home_create_event_button);
        reserveTableButton = (ImageButton) view.findViewById(R.id.home_reserve_table_button);
        myMenuButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        createEventButton.setOnClickListener(this);
        reserveTableButton.setOnClickListener(this);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String statusID = facilities[position].getStatus();
                int statusNo = Integer.parseInt(statusID);
                String locationStatusText = "<font color=#333333>Status : </font><font color="+color[statusNo]+">" + status[statusNo] + "</font>";
                locationStatus.setText(Html.fromHtml(locationStatusText));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadLocations();
    }

    private void loadLocations() {
        String locationData = CommonMethods.loadJSONFromAsset(mContext, "facilities_dropdown_json.json");
        Gson gson = new Gson();
        facilities = gson.fromJson(locationData, Facilities[].class);
        String[] locations = new String[facilities.length];
        for (int i = 0; i < facilities.length; i++) {
            locations[i] = facilities[i].getName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, locations);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == myMenuButton) {
            changeFragment(new MyMenuFragment());
        } else if (v == menuButton){
            changeFragment(new MenuFragment());
        }
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack("home");
        fragmentTransaction.commit();
    }
}

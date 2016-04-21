package com.persistent.cafeteria.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {

    View view;

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Wallet");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Wallet");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wallet, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) view.findViewById(R.id.wallet_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.fromPlaceOrder){
                    changeFragment(new PlaceOrderFragment(),false);
                } else {
                    changeFragment(new HomeFragment(),false);
                }
            }
        });
    }

    private void changeFragment(Fragment fragment,boolean back) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if(back)
            fragmentTransaction.addToBackStack("home");
        fragmentTransaction.commit();
    }
}

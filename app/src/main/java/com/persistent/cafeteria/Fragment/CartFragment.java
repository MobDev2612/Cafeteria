package com.persistent.cafeteria.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.persistent.cafeteria.Adapters.MenuAdapter;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.Config;
import com.persistent.cafeteria.Utils.CustomTimePicker;
import com.persistent.cafeteria.datamodels.FoodItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements MenuAdapter.OnItemClickListener {

    Context mContext;
    View view;
    private GridView gridView;
    EditText deliveryTime;
    Button continueButton;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.title_activity_cart);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialiseComponents();
    }

    private void initialiseComponents(){
        gridView = (GridView) view.findViewById(R.id.menu_list);
        deliveryTime = (EditText) view.findViewById(R.id.delivery_time);
        deliveryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTimePicker.openDatePicker(getActivity().getSupportFragmentManager(), deliveryTime, mContext);
            }
        });
        continueButton = (Button) view.findViewById(R.id.cart_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new PlaceOrderFragment());
            }
        });
        FoodItem[] foodItems = (FoodItem[]) Config.cartItems.toArray(new FoodItem[Config.cartItems.size()]);
        MenuAdapter menuAdapter = new MenuAdapter(mContext, foodItems,this,false);
        gridView.setAdapter(menuAdapter);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        getActivity().finish();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack("home");
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view, int position) {

    }
}

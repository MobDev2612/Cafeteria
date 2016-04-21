package com.persistent.cafeteria.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.persistent.cafeteria.Adapters.MenuAdapter;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.Cafeteria;
import com.persistent.cafeteria.Utils.Config;
import com.persistent.cafeteria.datamodels.FoodItem;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceOrderFragment extends Fragment implements MenuAdapter.OnItemClickListener {
    private GridViewWithHeaderAndFooter gridView;
    Context mContext;
    View view;

    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place_order, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialiseComponents();
    }

    private void initialiseComponents() {
        gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.menu_list);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View footerView = layoutInflater.inflate(R.layout.place_order_footer, null);
        ImageButton imageButton = (ImageButton) footerView.findViewById(R.id.plus_icon);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.fromPlaceOrder = true;
                changeFragment(new WalletFragment(), true);
            }
        });
        Button placeOrder = (Button) footerView.findViewById(R.id.place_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });
        gridView.addFooterView(footerView);
        FoodItem[] foodItems = (FoodItem[]) Config.cartItems.toArray(new FoodItem[Config.cartItems.size()]);
        MenuAdapter menuAdapter = new MenuAdapter(mContext, foodItems, this,true);
        gridView.setAdapter(menuAdapter);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
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

    private void showAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Thank You!")
                .setMessage("Your Order has been Placed. You will receive confirmation of your order within 5 minutes")
                .setCancelable(false)
                .setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                placeOrder();
                            }
                        })
                .show();
    }

    @Override
    public void onClick(View view, int position) {

    }

    private void placeOrder() {
//        Config.orderItems = new ArrayList<>(Config.cartItems);
//        orderItems = new ArrayList<>();
//        for (FoodItem foodItem: Config.cartItems){
//            orderItems.add(new FoodItem(foodItem));
//        }
//        Collections.copy(Config.orderItems,Config.cartItems);
//        Config.orderItems.addAll(Config.cartItems);
        for (FoodItem foodItem : Config.cartItems) {
            Cafeteria.order2.add(new FoodItem(foodItem));
        }
        Config.orderPlaced = true;
        Config.cartItems.clear();
        Log.e("test", Config.cartItems.size()+":"+Cafeteria.order2.size()+"");
        changeFragment(new HomeFragment(), false);
        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    private void changeFragment(Fragment fragment, boolean back) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if (back)
            fragmentTransaction.addToBackStack("home");
        fragmentTransaction.commit();
    }
}

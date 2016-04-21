package com.persistent.cafeteria.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.google.gson.Gson;
import com.persistent.cafeteria.Adapters.MenuAdapter;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.CommonMethods;
import com.persistent.cafeteria.Utils.Config;
import com.persistent.cafeteria.datamodels.FoodItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements MenuAdapter.OnItemClickListener, View.OnClickListener {

    private View view;
    private Context mContext;
    private GridView gridView;
    FoodItem[] foodItems;
    private List<FoodItem> selectedItems;
    Button addToCartButton,addToMyMenuButton;
    MenuAdapter menuAdapter;

    public MenuFragment() {
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
        getActivity().setTitle(R.string.menu);
//        setHasOptionsMenu(true);
//        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        if(actionBar!=null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            ((HomeActivity) getActivity()).showArrow();
////            mDrawerActivity.showArrow();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.menu_list);
        addToCartButton = (Button) view.findViewById(R.id.menu_add_to_cart);
        addToMyMenuButton = (Button) view.findViewById(R.id.menu_add_to_my_menu);
        selectedItems = new ArrayList<>();
        addToCartButton.setOnClickListener(this);
        addToMyMenuButton.setOnClickListener(this);
        loadMenuItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.menu);
        if(menuAdapter!=null){
            menuAdapter.notifyDataSetChanged();
        }
    }

    private void loadMenuItem() {
        String menuData = CommonMethods.loadJSONFromAsset(mContext, "menu_json.json");
        Gson gson = new Gson();
        foodItems = gson.fromJson(menuData, FoodItem[].class);
        menuAdapter = new MenuAdapter(mContext, foodItems,this,false);
        gridView.setAdapter(menuAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        selectedItems.add(foodItems[position]);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                getActivity().onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        if(v== addToCartButton){
            for(FoodItem foodItem: selectedItems) {
                CommonMethods.addProductInCart(foodItem,mContext);
            }
//            Config.cartItems.addAll(selectedItems);
        } else if(v== addToMyMenuButton){
            Config.myMenuItems.addAll(selectedItems);
        }
    }
}

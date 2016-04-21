package com.persistent.cafeteria.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anton46.stepsview.StepsView;
import com.persistent.cafeteria.Adapters.MenuAdapter;
import com.persistent.cafeteria.R;
import com.persistent.cafeteria.Utils.Cafeteria;
import com.persistent.cafeteria.datamodels.FoodItem;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderProgressFragment extends Fragment implements MenuAdapter.OnItemClickListener {

    private GridViewWithHeaderAndFooter gridView;
    Context mContext;
    View view;

    public OrderProgressFragment() {
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
        getActivity().setTitle("Order Progress");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Order Progress");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_progress, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialiseComponents();
    }

    private void initialiseComponents() {
        final String[] labels = {"Order", "Accepted", "Preparing", "Ready"};
        gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.menu_list);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View footerView = layoutInflater.inflate(R.layout.order_progree_footer, null);
        gridView.addFooterView(footerView);
        StepsView stepsView = (StepsView) footerView.findViewById(R.id.stepsView);
        stepsView.setLabels(labels).setBarColorIndicator(getContext().getResources().getColor(R.color.dark_grey))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.dark_red))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.blackq))
                .setCompletedPosition(2)
                .drawView();
        FoodItem[] foodItems = (FoodItem[]) Cafeteria.order2.toArray(new FoodItem[Cafeteria.order2.size()]);
        MenuAdapter menuAdapter = new MenuAdapter(mContext, foodItems, this,true);
        gridView.setAdapter(menuAdapter);
    }

    @Override
    public void onClick(View view, int position) {

    }
}

package com.persistent.cafeteria.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.persistent.cafeteria.R;
import com.persistent.cafeteria.datamodels.FoodItem;

public class MenuAdapter extends BaseAdapter {
    private Context mContext;
    FoodItem[] dataList;
    private LayoutInflater inflater;
    private OnItemClickListener mListener;
    private boolean isPreView;

    public MenuAdapter(Context mContext, FoodItem[] foodItems, OnItemClickListener mListener,boolean isPreView) {
        this.mContext = mContext;
        dataList = foodItems;
        this.mListener = mListener;
        this.isPreView = isPreView;
    }

    @Override
    public int getCount() {
        return dataList.length;
    }

    @Override
    public FoodItem getItem(int position) {
        return dataList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            inflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.menuImage = (ImageView) convertView.findViewById(R.id.menu_list_item_image);
            viewHolder.qty = (TextView) convertView.findViewById(R.id.menu_list_item_qty);
            viewHolder.menuName = (TextView) convertView.findViewById(R.id.menu_list_item_name);
            viewHolder.menuVendor = (TextView) convertView.findViewById(R.id.menu_list_item_vendor);
            viewHolder.menuPrice = (TextView) convertView.findViewById(R.id.menu_list_item_price);
            viewHolder.menuCalorie = (TextView) convertView.findViewById(R.id.menu_list_item_calorie_value);
            viewHolder.plusButton = (ImageButton) convertView.findViewById(R.id.menu_list_item_plus);
            viewHolder.minusButton = (ImageButton) convertView.findViewById(R.id.menu_list_item_minus);
            viewHolder.favoriteButton = (ImageButton) convertView.findViewById(R.id.menu_list_item_favorite);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        loadImages(dataList[position].getImageUrl(),viewHolder.menuImage);
        viewHolder.menuName.setText(dataList[position].getName());
        viewHolder.menuVendor.setText(dataList[position].getVendorName());
        viewHolder.menuPrice.setText(dataList[position].getPrice()+"");
        viewHolder.menuCalorie.setText(dataList[position].getCalories()+"");
        viewHolder.qty.setText(dataList[position].getQuantity()+"");
        if(isPreView){
            viewHolder.plusButton.setVisibility(View.GONE);
            viewHolder.minusButton.setVisibility(View.GONE);
        }
        loadFavoriteIcon(viewHolder.favoriteButton, dataList[position].isFavourite());
        viewHolder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean value = dataList[position].isFavourite();
                dataList[position].setIsFavourite(!value);
                notifyDataSetChanged();
            }
        });
        viewHolder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,position);
                int qty = dataList[position].getQuantity();
                dataList[position].setQuantity(qty + 1);
                notifyDataSetChanged();
            }
        });
        viewHolder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,position);
                int qty = dataList[position].getQuantity();
                if(qty > 0)
                dataList[position].setQuantity(qty - 1);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView menuImage;
        TextView qty,menuName,menuVendor,menuPrice,menuCalorie;
        ImageButton plusButton,minusButton,favoriteButton;
    }

    private void loadImages(String imageName,ImageView imageView){
        int resID = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        imageView.setImageResource(resID);
    }

    private void loadFavoriteIcon(ImageButton imageButton,boolean value){
        int resID;
        if(value) {
            resID = mContext.getResources().getIdentifier("ic_heart_selected", "drawable", mContext.getPackageName());
        } else {
            resID = mContext.getResources().getIdentifier("ic_heart_unselected", "drawable", mContext.getPackageName());
        }
        imageButton.setImageResource(resID);
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }
}

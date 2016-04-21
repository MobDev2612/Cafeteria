package com.persistent.cafeteria.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.persistent.cafeteria.datamodels.FoodItem;

import java.io.IOException;
import java.io.InputStream;

public class CommonMethods {

    public static String loadJSONFromAsset(Context context,String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean addProductInCart(FoodItem productObject,Context mContext) {
        boolean found = false;
        for (int i = 0; i < Config.cartItems.size(); i++) {
            if (productObject.getId() == Config.cartItems.get(i).getId()) {
                found = true;
//                Config.addToCart.get(i).setQtyBought(Config.addToCart.get(i).getQtyBought() + productObject.getQtyBought());
            }
        }
        if (!found) {
            FoodItem newProduct;
            newProduct = productObject;
            Config.cartItems.add(newProduct);
            Toast.makeText(mContext, "Added to Cart", Toast.LENGTH_SHORT).show();
            found = true;
            Intent intent = new Intent("custom-event-name");
            intent.putExtra("message", "This is my message!");
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
        return found;
    }

    public static FoodItem checkProductInCart(FoodItem productObject) {
        FoodItem cartProduct = null;
        for (int i = 0; i < Config.cartItems.size(); i++) {
            if (productObject.getId() == Config.cartItems.get(i).getId()) {
                cartProduct = Config.cartItems.get(i);
            }
        }
        return cartProduct;
    }
}

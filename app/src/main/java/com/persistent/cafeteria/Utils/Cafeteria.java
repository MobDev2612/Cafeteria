package com.persistent.cafeteria.Utils;

import android.app.Application;

import com.persistent.cafeteria.datamodels.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class Cafeteria extends Application {

    public static List<FoodItem> order2;

    @Override
    public void onCreate() {
        super.onCreate();
        order2 = new ArrayList<>();
        Config.cartItems = new ArrayList<FoodItem>();
        Config.myMenuItems = new ArrayList<FoodItem>();
    }
}

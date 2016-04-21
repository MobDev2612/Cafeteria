package com.persistent.cafeteria.datamodels;

public class FoodItem implements Cloneable {
    int id;
    String name;
    int calories;
    int price;
    String mealType;
    boolean isVeg;
    int rating;
    int waitingTime;
    int vendorId;
    String vendorName;
    String imageUrl;
    int quantity;
    boolean isItemSelected;
    boolean isFavourite;

    public FoodItem(){

    }

    public FoodItem(FoodItem foodItem){
        this.id = new Integer(foodItem.id);
        this.name = new String(foodItem.name);
        this.calories = new Integer(foodItem.calories);
        this.price = new Integer(foodItem.price);
        this.mealType = new String(foodItem.mealType);
        this.isVeg = new Boolean(foodItem.isVeg);
        this.rating = new Integer(foodItem.rating);
        this.waitingTime = new Integer(foodItem.waitingTime);
        this.vendorId = new Integer(foodItem.vendorId);
        this.vendorName = new String(foodItem.vendorName);
        this.imageUrl = new String(foodItem.imageUrl);
        this.quantity = new Integer(foodItem.quantity);
        this.isItemSelected = new Boolean(foodItem.isItemSelected);
        this.isFavourite = new Boolean(foodItem.isFavourite);
    }

    public FoodItem clone(){
        FoodItem p;
        try {
            p = (FoodItem) super.clone();
            p.id = id;
            p.name = name;
            p.calories = calories;
            p.price = price;
            p.mealType = mealType;
            p.isVeg = isVeg;
            p.rating = rating;
            p.waitingTime = waitingTime;
            p.vendorId = vendorId;
            p.vendorName = vendorName;
            p.imageUrl = imageUrl;
            p.quantity = quantity;
            p.isItemSelected = isItemSelected;
            p.isFavourite = isFavourite;
            return p;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getPrice() {
        return price;
    }

    public String getMealType() {
        return mealType;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public int getRating() {
        return rating;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

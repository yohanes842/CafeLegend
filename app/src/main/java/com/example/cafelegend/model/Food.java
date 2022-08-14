package com.example.cafelegend.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String foodName;
    private String description;
    private long foodPrice;
    private int foodImage;

    public Food(String foodName, String description, long foodPrice, int foodImage) {
        this.foodName = foodName;
        this.description = description;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(long foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(foodName);
        parcel.writeString(description);
        parcel.writeLong(foodPrice);
        parcel.writeInt(foodImage);
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    private Food(Parcel parcel) {
        this.foodName = parcel.readString();
        this.description = parcel.readString();
        this.foodPrice = parcel.readLong();
        this.foodImage = parcel.readInt();
    }
}

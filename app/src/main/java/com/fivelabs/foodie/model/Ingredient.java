package com.fivelabs.foodie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by breogangf on 5/4/16.
 */
public class Ingredient implements Parcelable{

    String name;
    String quantity;

    protected Ingredient(Parcel in) {
        name = in.readString();
        quantity = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(quantity);
    }
}

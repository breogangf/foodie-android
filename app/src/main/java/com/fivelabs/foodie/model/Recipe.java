package com.fivelabs.foodie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by breogangf on 26/3/16.
 */
public class Recipe implements Parcelable {

    String name;
    String category;
    int cook_time;
    String _id;

    protected Recipe(Parcel in) {
        name = in.readString();
        category = in.readString();
        cook_time = in.readInt();
        _id = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCook_time() {
        return cook_time;
    }

    public void setCook_time(int cook_time) {
        this.cook_time = cook_time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", cook_time=" + cook_time +
                ", _id='" + _id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(cook_time);
        dest.writeString(_id);
    }
}

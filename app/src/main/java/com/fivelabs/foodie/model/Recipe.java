package com.fivelabs.foodie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fivelabs.foodie.util.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by breogangf on 26/3/16.
 */
public class Recipe implements Parcelable {

    String name;
    String category;
    int cook_time;
    String image;
    String _id;
    List<Ingredient> ingredients = new ArrayList<Ingredient>();
    List<Step> directions = new ArrayList<Step>();

    protected Recipe(Parcel in) {
        name = in.readString();
        category = in.readString();
        cook_time = in.readInt();
        image = in.readString();
        _id = in.readString();
        ingredients = in.readArrayList(Ingredient.class.getClassLoader());
        directions = in.readArrayList(Step.class.getClassLoader());
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

    public String getImage() {
        return Global.IMAGES_URL + "/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return directions;
    }

    public void setSteps(List<Step> steps) {
        this.directions = steps;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", cook_time=" + cook_time +
                ", image='" + image + '\'' +
                ", _id='" + _id + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + directions +
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
        dest.writeString(image);
        dest.writeString(_id);
        dest.writeValue(ingredients);
        dest.writeValue(directions);
    }
}

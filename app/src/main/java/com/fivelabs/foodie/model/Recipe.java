package com.fivelabs.foodie.model;

/**
 * Created by breogangf on 26/3/16.
 */
public class Recipe {

    String name;
    String category;
    int cook_time;

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
}

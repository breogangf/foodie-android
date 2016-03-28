package com.fivelabs.foodie.util;

import com.fivelabs.foodie.model.Recipe;

import java.util.List;

/**
 * Created by breogangf on 30/9/15.
 */
public class Session {


    public static List<Recipe> srecipes;

    public static List<Recipe> getSrecipes() {
        return srecipes;
    }

    public static void setSrecipes(List<Recipe> srecipes) {
        Session.srecipes = srecipes;
    }
}

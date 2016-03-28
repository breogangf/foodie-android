package com.fivelabs.foodie.api;

import com.fivelabs.foodie.model.Recipe;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by breogangf on 28/9/15.
 */
public interface recipe {

    @GET("/recipe/")
    void getRecipes(Callback<List<Recipe>> response);

}
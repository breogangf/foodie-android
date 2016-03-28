package com.fivelabs.foodie.api;

import com.fivelabs.foodie.model.Recipe;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface recipe {

    @GET("/recipe/")
    void getRecipes(Callback<List<Recipe>> response);

    @GET("/recipe/{id}")
    void getRecipeById(@Path("id") String recipeId, Callback<Recipe> callback);

}
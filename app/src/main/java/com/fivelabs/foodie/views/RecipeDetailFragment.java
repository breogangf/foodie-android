package com.fivelabs.foodie.views;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fivelabs.foodie.R;
import com.fivelabs.foodie.model.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeDetailFragment extends Fragment {

    public RecipeDetailFragment() {
    }

    private OnFragmentInteractionListener mListener;

    static Recipe mRecipe;


    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("RECIPE", recipe);
        recipeDetailFragment.setArguments(bundle);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipe = getArguments().getParcelable("RECIPE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myInflatedView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        TextView recipe_name = (TextView) myInflatedView.findViewById(R.id.detail_recipe_name);
        TextView cooking_time = (TextView) myInflatedView.findViewById(R.id.detail_cooking_time);
        TextView category = (TextView) myInflatedView.findViewById(R.id.detail_category);
        ImageView image = (ImageView) myInflatedView.findViewById(R.id.recipe_detail_picture);

        recipe_name.setText(mRecipe.getName());
        cooking_time.setText(String.valueOf(mRecipe.getCook_time()));
        category.setText(mRecipe.getCategory());

        //Download image using picasso library
        Picasso.with(getActivity()).load(mRecipe.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image);

        return myInflatedView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    private void showError(String errorMessage) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), errorMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

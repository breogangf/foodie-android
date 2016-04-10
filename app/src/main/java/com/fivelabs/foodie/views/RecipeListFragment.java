package com.fivelabs.foodie.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fivelabs.foodie.R;
import com.fivelabs.foodie.api.recipe;
import com.fivelabs.foodie.helpers.ItemClickSupport;
import com.fivelabs.foodie.helpers.RVAdapterRecipes;
import com.fivelabs.foodie.model.Recipe;
import com.fivelabs.foodie.util.Global;
import com.fivelabs.foodie.util.Session;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mRecyclerView;
    private View mProgressView;

    private View myInflatedView;
    private RecyclerView rv;

    String name;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RefuelListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeListFragment newInstance(String param1, String param2) {
        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRecyclerView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myInflatedView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mRecyclerView = myInflatedView.findViewById(R.id.rv);
        mProgressView = myInflatedView.findViewById(R.id.login_refuels_progress);

        rv = (RecyclerView) myInflatedView.findViewById(R.id.rv);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                loadRecipeById(Session.getSrecipes().get(position).get_id());

            }
        }).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {

                return true;
            }
        });


        FloatingActionButton floatingActionButtonAdd = (FloatingActionButton) myInflatedView.findViewById(R.id.add_recipe);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //TODO add recipe feature
            }
        });

        loadRecipes();

        return myInflatedView;
    }


    private void loadRecipes() {

        showProgress(true);

        RestAdapter restAdapter = (new RestAdapter.Builder())
                .setEndpoint(Global.API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("RETROFIT", msg);
                    }
                }).build();

        recipe recipe = restAdapter.create(recipe.class);

        recipe.getRecipes(new Callback<List<Recipe>>() {
            @Override
            public void success(List<Recipe> recipes, Response response) {
                Session.setSrecipes(recipes);

                rv.setHasFixedSize(true);

                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rv.setLayoutManager(llm);

                RVAdapterRecipes adapter = new RVAdapterRecipes(getActivity(), Session.getSrecipes());
                rv.setAdapter(adapter);

                showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                showProgress(false);
            }
        });
    }


    private void loadRecipeById(String id) {

        showProgress(true);

        RestAdapter restAdapter = (new RestAdapter.Builder())
                .setEndpoint(Global.API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("RETROFIT", msg);
                    }
                }).build();

        recipe recipe = restAdapter.create(recipe.class);

        recipe.getRecipeById(id, new Callback<Recipe>() {

            @Override
            public void success(Recipe recipe, Response response) {

                Fragment fragment;
                FragmentManager fragmentManager = getFragmentManager();
                fragment = RecipeDetailFragment.newInstance(recipe);
                beginFragmentTransaction(fragment, fragmentManager);

                showProgress(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.getCause();
                showProgress(false);
            }
        });
    }

    private void beginFragmentTransaction(Fragment fragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
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
        void onFragmentInteraction(Uri uri);
    }

}

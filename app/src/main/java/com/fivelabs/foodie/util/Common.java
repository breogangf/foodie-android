package com.fivelabs.foodie.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.fivelabs.foodie.R;

/**
 * Created by breogangf on 28/9/15.
 */
public class Common {

    public static void shareViaWhatsapp(Activity activity, String message) {

        PackageManager pm = activity.getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, message);
            activity.startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(activity, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public static void shareAnyApp(Activity activity, String subject, String message) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        activity.startActivity(Intent.createChooser(sharingIntent, activity.getResources().getString(R.string.share_using)));
    }

    public static String generateRecipeURL(String recipeId) {
        return Global.BASE_URL + "#recipe/" + recipeId;

    }

}

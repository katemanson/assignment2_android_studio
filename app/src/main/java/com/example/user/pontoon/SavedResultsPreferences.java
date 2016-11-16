package com.example.user.pontoon;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
/**
 * Created by user on 16/11/2016.
 */

public class SavedResultsPreferences {

    private static final String PREF_SAVEDRESULTS = "savedResults";

    public static void setSavedResults(Context context, String resultsText) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(PREF_SAVEDRESULTS, resultsText);
        editor.apply();
    }

    public static String getSavedResults(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(PREF_SAVEDRESULTS, null);
    }
}

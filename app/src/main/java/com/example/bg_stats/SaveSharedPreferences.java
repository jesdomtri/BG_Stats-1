package com.example.bg_stats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.bg_stats.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.bg_stats.PreferencesUtility.LOGGED_USER;


public class SaveSharedPreferences {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOGGED_USER, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        return getPreferences(context).getString(LOGGED_USER, "");
    }
}

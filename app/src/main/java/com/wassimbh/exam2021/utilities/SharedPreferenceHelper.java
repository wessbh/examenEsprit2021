package com.wassimbh.exam2021.utilities;


import android.content.Context;
import android.content.SharedPreferences;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class SharedPreferenceHelper {
    private static SharedPreferences sharedPreferences = null;

    private static SharedPreferences getPrefs(Context activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void insertString(String key, String value, Context activity) {

        getPrefs(activity).edit().putString(key, value).apply();
    }

    public static void insertLong(String key, long value, Context activity) {

        getPrefs(activity).edit().putLong(key, value).apply();
    }

    public static long getLong(String key, Context activity) {

        return getLong(key, activity, 1);
    }

    public static long getLong(String key, Context activity, long defaultValue) {

        return getPrefs(activity).getLong(key, defaultValue);
    }

    public static void insertFloat(String key, float value, Context activity) {


        getPrefs(activity).edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key, Context activity, float defaultValue) {

        return getPrefs(activity).getFloat(key, defaultValue);
    }

    public static float getFloat(String key, Context activity) {

        return getFloat(key, activity, 0);
    }

    public static void insertBool(String key, boolean value, Context activity) {

        getPrefs(activity).edit().putBoolean(key, value).apply();
    }

    public static boolean getBool(String key, Context activity) {

        return getBool(key, activity, false);
    }

    public static boolean getBool(String key, Context activity, boolean defaultValue) {

        return getPrefs(activity).getBoolean(key, defaultValue);
    }

    public static void insertInt(String key, int value, Context activity) {

        getPrefs(activity).edit().putInt(key, value).apply();
    }

    public static int getInt(String key, Context activity) {

        return getInt(key, activity, 0);
    }

    public static int getInt(String key, Context activity, int defaultValue) {


        return getPrefs(activity).getInt(key, defaultValue);
    }

    public static String getString(String key, Context activity) {

        return getString(key, "", activity);
    }

    public static String getString(String key, String defaultone, Context activity) {

        return getPrefs(activity).getString(key, defaultone);
    }
    public static void clearSharedPrefs(Context activity) {

         getPrefs(activity).edit().clear().apply();
    }


}

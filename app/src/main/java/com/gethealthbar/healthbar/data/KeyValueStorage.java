package com.gethealthbar.healthbar.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.gethealthbar.healthbar.R;

/**
 * Created by Alexandr.
 */
public class KeyValueStorage {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String KEY_VISITED = "key_visited";

    @SuppressLint("CommitPrefEdits")
    public KeyValueStorage(@NonNull Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name), 0);
        editor = preferences.edit();
    }

    public void setVisited(boolean visited) {
        editor.putBoolean(KEY_VISITED, visited);
        editor.commit();
    }

    public boolean getVisited() {
        return preferences.getBoolean(KEY_VISITED, false);
    }

}

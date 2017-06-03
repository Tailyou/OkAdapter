package com.hengda.zwf.commonutil;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPrefUtil(Context context, String fileName) {
        sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setPrefString(String key, String value) {
        editor.putString(key, value).commit();
        editor.commit();
    }

    public String getPrefString(String key) {
        return sp.getString(key, "");
    }

    public String getPrefString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void setPrefInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getPrefInt(String key) {
        return sp.getInt(key, 0);
    }

    public int getPrefInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void setPrefBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getPrefBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getPrefBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void setPrefFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getPrefFloat(String key) {
        return sp.getFloat(key, 0f);
    }

    public float getPrefFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void setPrefLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getPrefLong(String key) {
        return sp.getLong(key, 0l);
    }

    public long getPrefLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }

}

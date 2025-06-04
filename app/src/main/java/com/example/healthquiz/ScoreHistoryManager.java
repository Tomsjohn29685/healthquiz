package com.example.healthquiz;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScoreHistoryManager {
    private static final String PREF_NAME = "score_history";
    private static final String KEY_HISTORY = "history";

    public static void saveScore(Context context, int score, int total) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String existing = prefs.getString(KEY_HISTORY, "[]");

        try {
            JSONArray array = new JSONArray(existing);
            JSONObject obj = new JSONObject();
            obj.put("score", score);
            obj.put("total", total);
            obj.put("dateTime", java.text.DateFormat.getDateTimeInstance().format(new java.util.Date()));
            array.put(obj);
            prefs.edit().putString(KEY_HISTORY, array.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ScoreRecord> getHistory(Context context) {
        List<ScoreRecord> list = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_HISTORY, "[]");

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                list.add(new ScoreRecord(
                        obj.getString("dateTime"),
                        obj.getInt("score"),
                        obj.getInt("total")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}


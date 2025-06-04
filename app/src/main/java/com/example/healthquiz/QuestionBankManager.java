package com.example.healthquiz;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuestionBankManager {
    public static List<Question> loadQuestions(Context context) {
        List<Question> questionList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("question_bank.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String questionText = obj.getString("question");

                JSONArray optionsJson = obj.getJSONArray("options");
                List<String> options = new ArrayList<>();
                for (int j = 0; j < optionsJson.length(); j++) {
                    options.add(optionsJson.getString(j));
                }
                int correctIndex = obj.getInt("correctIndex");
                String explanation = obj.getString("explanation");

                Question q = new Question(questionText, options, correctIndex, explanation);
                questionList.add(q);
            }
        } catch (Exception e) {
            Log.e("QuestionLoader", "Error loading questions: " + e.getMessage());
        }
        return questionList;
    }
}

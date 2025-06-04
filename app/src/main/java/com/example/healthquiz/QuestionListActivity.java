package com.example.healthquiz;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private ListView lvQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        lvQuestions = findViewById(R.id.lv_questions);

        List<Question> questions = QuestionBankManager.loadQuestions(this);

        List<String> displayList = new ArrayList<>();
        for (Question q : questions) {
            StringBuilder sb = new StringBuilder();
            sb.append("题目：").append(q.getQuestion()).append("\n");
            List<String> options = q.getOptions();
            for (int i = 0; i < options.size(); i++) {
                sb.append((char)('A' + i)).append(". ").append(options.get(i)).append("\n");
            }
            sb.append("正确答案：").append((char)('A' + q.getCorrectIndex())).append("\n");
            sb.append("解析：").append(q.getExplanation()).append("\n");
            sb.append("——————————————");
            displayList.add(sb.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );

        lvQuestions.setAdapter(adapter);
    }
}

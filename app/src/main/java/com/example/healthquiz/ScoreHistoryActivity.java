package com.example.healthquiz;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ScoreHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> historyStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);

        listView = findViewById(R.id.lv_history);

        List<ScoreRecord> records = ScoreHistoryManager.getHistory(this);

        // 倒序展示（最新在前）
        for (int i = records.size() - 1; i >= 0; i--) {
            ScoreRecord r = records.get(i);
            historyStrings.add(r.getDateTime() + " - 得分：" + r.getScore() + "/" + r.getTotal());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, historyStrings);
        listView.setAdapter(adapter);
    }
}

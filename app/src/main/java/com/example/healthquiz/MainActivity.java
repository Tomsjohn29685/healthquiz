package com.example.healthquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame, btnViewQuestions, btnViewHistory, btnOtherFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = findViewById(R.id.btnStartGame);
        btnViewQuestions = findViewById(R.id.btnViewQuestions);
        btnViewHistory = findViewById(R.id.btnViewHistory);
        btnOtherFeatures = findViewById(R.id.btnOtherFeatures);

        // 跳转到游戏界面
        btnStartGame.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, QuizActivity.class)));

        // 跳转到题库查看界面
        btnViewQuestions.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, QuestionListActivity.class)));

        // 跳转到历史得分界面
        btnViewHistory.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ScoreHistoryActivity.class)));

        // 跳转到其他功能界面
        btnOtherFeatures.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, OtherFeaturesActivity.class)));
    }
}

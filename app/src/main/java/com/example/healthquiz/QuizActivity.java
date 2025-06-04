package com.example.healthquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView tvQuestionNumber, tvQuestion, tvFeedback, tvExplanation;
    private Button[] optionButtons = new Button[4];
    private Button btnNext;
    private Question currentQuestion;
    private int correctAnswerIndex; // 记录当前正确答案在 shuffled 之后的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        tvQuestionNumber = findViewById(R.id.tv_question_number);
        tvQuestion = findViewById(R.id.tv_question);
        tvFeedback = findViewById(R.id.tv_feedback);
        tvExplanation = findViewById(R.id.tv_explanation);
        btnNext = findViewById(R.id.btn_next);
        optionButtons[0] = findViewById(R.id.btn_option_1);
        optionButtons[1] = findViewById(R.id.btn_option_2);
        optionButtons[2] = findViewById(R.id.btn_option_3);
        optionButtons[3] = findViewById(R.id.btn_option_4);
        // 模拟加载题库（后续替换为 JSON 导入）
        questionList = QuestionBankManager.loadQuestions(this);
        Collections.shuffle(questionList); // 打乱题目顺序
        if (questionList.size() > 5) {
            questionList = questionList.subList(0, 5); // 只取前 5 题
        }

        showNextQuestion();
        btnNext.setOnClickListener(v -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                showNextQuestion();
            } else {
                showFinalScore();
            }
        });
    }
    private void showNextQuestion() {
        tvFeedback.setText("");
        tvExplanation.setText("");
        btnNext.setVisibility(View.GONE);
        currentQuestion = questionList.get(currentQuestionIndex);
        tvQuestionNumber.setText("第 " + (currentQuestionIndex + 1) + " / " + questionList.size() + " 题");
        tvQuestion.setText(currentQuestion.getQuestion());
        // 打乱选项顺序
        List<String> options = new ArrayList<>(currentQuestion.getOptions());
        List<String> shuffledOptions = new ArrayList<>(options);
        Collections.shuffle(shuffledOptions);
        correctAnswerIndex = shuffledOptions.indexOf(options.get(currentQuestion.getCorrectIndex()));
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(shuffledOptions.get(i));
            optionButtons[i].setEnabled(true);
            int finalI = i;
            optionButtons[i].setOnClickListener(v -> checkAnswer(finalI));
        }
    }
    private void checkAnswer(int selectedIndex) {
        for (Button btn : optionButtons) {
            btn.setEnabled(false);
        }
        if (selectedIndex == correctAnswerIndex) {
            tvFeedback.setText("答对了！");
            score++;
        } else {
            tvFeedback.setText("答错了！");
        }
        tvExplanation.setText("解析：" + currentQuestion.getExplanation());
        btnNext.setVisibility(View.VISIBLE);
    }
    private void showFinalScore() {
        tvQuestion.setText("恭喜你完成本关！");
        tvQuestionNumber.setText("得分：" + score + " / " + questionList.size());
        // 保存历史记录
        ScoreHistoryManager.saveScore(this, score, questionList.size());
        tvFeedback.setText("");
        tvExplanation.setText("");
        btnNext.setText("返回主界面");
        btnNext.setVisibility(View.VISIBLE);
        btnNext.setOnClickListener(v -> finish());
        for (Button btn : optionButtons) btn.setVisibility(View.GONE);
    }
}

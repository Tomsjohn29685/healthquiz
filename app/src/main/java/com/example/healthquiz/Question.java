package com.example.healthquiz;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int correctIndex;
    private String explanation;
    public Question(String question, List<String> options, int correctIndex, String explanation) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanation = explanation;
    }
    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
    public String getExplanation() { return explanation; }
}


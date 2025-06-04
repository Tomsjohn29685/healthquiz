package com.example.healthquiz;

public class ScoreRecord {
    private String dateTime;
    private int score;
    private int total;

    public ScoreRecord(String dateTime, int score, int total) {
        this.dateTime = dateTime;
        this.score = score;
        this.total = total;
    }

    public String getDateTime() { return dateTime; }
    public int getScore() { return score; }
    public int getTotal() { return total; }
}

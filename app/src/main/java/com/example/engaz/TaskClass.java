package com.example.engaz;

public class TaskClass {
    private int tId;
    private String tTitle;
    private String tDetails;
    private int tColor;
    private int tStatue;

    public TaskClass(int tId, String tTitle, String tDetails, int tColor, int tStatue) {
        this.tId = tId;
        this.tTitle = tTitle;
        this.tDetails = tDetails;
        this.tColor = tColor;
        this.tStatue = tStatue;
    }

    public TaskClass(String tTitle, String tDetails, int tColor, int tStatue) {
        this.tTitle = tTitle;
        this.tDetails = tDetails;
        this.tColor = tColor;
        this.tStatue = tStatue;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String gettDetails() {
        return tDetails;
    }

    public void settDetails(String tDetails) {
        this.tDetails = tDetails;
    }

    public int gettColor() {
        return tColor;
    }

    public void settColor(int tColor) {
        this.tColor = tColor;
    }

    public int gettStatue() {
        return tStatue;
    }

    public void settStatue(int tStatue) {
        this.tStatue = tStatue;
    }
}

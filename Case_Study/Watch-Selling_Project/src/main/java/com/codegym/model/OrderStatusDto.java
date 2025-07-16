package com.codegym.model;

public class OrderStatusDto {
    private String status;
    private String text;
    private String color;
    private int percent;

    public OrderStatusDto(String status, String text, String color, int percent) {
        this.status = status;
        this.text = text;
        this.color = color;
        this.percent = percent;
    }

    // Getters & Setters
    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public int getPercent() {
        return percent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}

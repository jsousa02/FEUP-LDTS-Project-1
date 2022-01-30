package com.g0301.model;

public class Button extends Element {

    private String textColor;
    private String text;
    private int width;
    private int height;
    private boolean isHighlighted = false;

    public Button(Position position, String color, String textColor, String text, int width, int height) {
        super(position, color);
        this.textColor = textColor;
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getText() {
        return text;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void highlight(String color) {
        this.color = color;
    }

    public void lowlight(String color) {
        this.color = color;
    }
}

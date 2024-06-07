package com.example.examenProjet1.model;

public class Affirmation {
    private int id;
    private String text;
    private Boolean value;

    public Affirmation(int id, String text, Boolean value) {
        this.id = id;
        this.text = text;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}

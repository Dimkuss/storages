package com.example.storages;

public class Sample {
    private String text;
    private int length;

    public Sample(String text){
        this.text = text;
        length=text.length();
    }

    public String getText() {
        return text;
    }

    public int getLength() {
        return length;
    }
}

package com.carvia.upload;

public class SlackPayload {
    private String text;

    public SlackPayload() {
    }

    public SlackPayload(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.musicquint.core;

import java.util.ArrayList;

import com.musicquint.util.MQList;

public final class Score extends MQList<Part> {

    private String title;

    private String composer;

    public Score() {
        super(ArrayList::new);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}

package com.musicquint.implementation;

import java.util.ArrayList;

import com.musicquint.core.Part;

public final class Score extends MQList<Part> implements com.musicquint.core.Score {

    private String title;

    private String composer;

    public Score() {
        super(ArrayList::new);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getComposer() {
        return composer;
    }

    @Override
    public void setComposer(String composer) {
        this.composer = composer;
    }
}

package com.musicquint.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.musicquint.api.Part;
import com.musicquint.api.Score;
import com.musicquint.util.ForwardingList;

public class MQScore extends ForwardingList<Part> implements Score {

    private String composer;

    private String title;

    private String subtitle;

    public MQScore() {
        super(ArrayList::new);
    }

    public MQScore(Collection<Part> collection) {
        this();
        addAll(collection);
    }

    @Override
    public void setComposer(String name) {
        this.composer = name;
    }

    @Override
    public String getComposer() {
        return composer;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }
}

package com.musicquint.api;

import java.util.List;

import com.musicquint.impl.MQScore;

public interface Score extends List<Part> {

    public static Score create() {
        return new MQScore();
    }

    void setComposer(String name);

    String getComposer();

    void setTitle(String title);

    String getTitle();

    void setSubtitle(String subtitle);

    String getSubtitle();

}

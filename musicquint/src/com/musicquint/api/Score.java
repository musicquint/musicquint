package com.musicquint.api;

import java.util.Collection;
import java.util.List;

import com.musicquint.impl.MQScore;

public interface Score extends List<Part> {

    public static Score create() {
        return new MQScore();
    }

    public static Score create(Collection<Part> collection) {
        return new MQScore(collection);
    }

    void setComposer(String name);

    String getComposer();

    void setTitle(String title);

    String getTitle();

    void setSubtitle(String subtitle);

    String getSubtitle();

}

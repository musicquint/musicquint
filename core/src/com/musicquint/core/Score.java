package com.musicquint.core;

import java.util.List;

public interface Score extends List<Part> {

    public static Score create() {
        return new com.musicquint.implementation.Score();
    }

    public String getTitle();

    public void setTitle(String title);

    public String getComposer();

    public void setComposer(String composer);

}

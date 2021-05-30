package com.musicquint.api;

import java.util.List;

public interface Score extends List<Part> {

    void setComposer(String name);

    String getComposer();

    void setTitle(String title);

    String getTitle();

    void setSubtitle(String title);

    String getSubtitle();

}

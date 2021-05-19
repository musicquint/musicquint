package com.musicquint.core;

import java.util.List;

public interface Part extends List<Bar> {

    public static Part create() {
        return new com.musicquint.implementation.Part();
    }

    public String getPartName();

    public void setPartName(String partName);

}

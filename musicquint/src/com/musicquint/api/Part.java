package com.musicquint.api;

import java.util.List;

import com.musicquint.impl.MQPart;

public interface Part extends List<Bar> {

    public static Part create() {
        return new MQPart();
    }

    String getPartName();

    void setPartName(String partName);

}

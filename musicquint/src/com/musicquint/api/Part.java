package com.musicquint.api;

import java.util.Collection;
import java.util.List;

import com.musicquint.impl.MQPart;

public interface Part extends List<Bar> {

    public static Part create() {
        return new MQPart();
    }

    public static Part create(Collection<Bar> collection) {
        return new MQPart(collection);
    }

    String getPartName();

    void setPartName(String partName);

}

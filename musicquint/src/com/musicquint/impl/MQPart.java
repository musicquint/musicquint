package com.musicquint.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.musicquint.api.Bar;
import com.musicquint.api.Part;
import com.musicquint.util.ForwardingList;

public class MQPart extends ForwardingList<Bar> implements Part {

    private String partName;

    public MQPart() {
        super(ArrayList::new);
    }

    public MQPart(Collection<Bar> collection) {
        this();
        addAll(collection);
    }

    @Override
    public String getPartName() {
        return partName;
    }

    @Override
    public void setPartName(String partName) {
        this.partName = partName;
    }

}

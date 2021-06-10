package com.musicquint.impl;

import java.util.ArrayList;

import com.musicquint.api.Bar;
import com.musicquint.api.Part;
import com.musicquint.util.ForwardingList;

public class MQPart extends ForwardingList<Bar> implements Part {

    private String partName;

    public MQPart() {
        super(ArrayList::new);
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

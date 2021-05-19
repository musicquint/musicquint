package com.musicquint.implementation;

import java.util.ArrayList;

import com.musicquint.core.Bar;

public final class Part extends MQList<Bar> implements com.musicquint.core.Part {

    private String partName;

    public Part() {
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

package com.musicquint.core;


import java.util.ArrayList;

import com.musicquint.util.MQList;

public final class Part extends MQList<Bar>  {

    private String partName;

    public Part() {
        super(ArrayList::new);
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

}
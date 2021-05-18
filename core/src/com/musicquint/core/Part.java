package com.musicquint.core;

import java.util.List;

public interface Part extends List<Bar> {

    public String getPartName();

    public void setPartName(String partName);

}

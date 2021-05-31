package com.musicquint.api;

import java.util.List;

public interface Part extends List<Bar> {

    String getPartName();

    void setPartName(String partName);

}

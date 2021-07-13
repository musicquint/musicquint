package com.musicquint.io;

public interface PartFactory {

    BarFactory getBarFactory();

    void setDivisor(int divisor);

}

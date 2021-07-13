package com.musicquint.io;

public interface BarFactory {

    VoiceFactory getVoiceFactory();

    void setDivisor(int divisor);

}

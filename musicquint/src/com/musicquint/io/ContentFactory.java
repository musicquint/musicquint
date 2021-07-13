package com.musicquint.io;

import com.musicquint.api.BarTime;

public interface ContentFactory {

    void setDuration(int numerator);

    void setDivisor(int divisor);

    void setDuration(BarTime duration);

    void increaseDots();

    void setDots(int dots);

}

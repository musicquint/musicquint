package com.musicquint.core;

//TODO documentation
public enum Step {

    C(0), D(2), E(4), F(5), G(7), A(9), B(11);

    private final int step;

    Step(int i) {
        this.step = i;
    }

    public int asInt() {
        return step;
    }

}

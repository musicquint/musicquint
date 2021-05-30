package com.musicquint.core;

import com.musicquint.api.BarTime;
import com.musicquint.api.Pitch;
import com.musicquint.api.Type;

public class Note extends AbstractNote {

    public Note(Pitch pitch, BarTime duration, Type type, int dots) {
        super(pitch, duration, type, dots);
    }

    public Note(BarTime duration, Type type, int dots) {
        super(null, duration, type, dots);
    }

    public Note(Pitch pitch, Type type, int dots) {
        super(pitch, type, dots);
    }

    public Note(Type type, int dots) {
        super(null, type, dots);
    }

    @Override
    public Category getCategory() {
        return Category.PRINCIPAL;
    }
}

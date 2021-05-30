package com.musicquint.core;

import com.musicquint.api.BarTime;
import com.musicquint.api.Pitch;
import com.musicquint.api.Type;

public class GraceNote extends AbstractNote {

    public GraceNote(Pitch pitch, BarTime duration, Type type, int dots) {
        super(pitch, duration, type, dots);
    }

    public GraceNote(Pitch pitch, Type type, int dots) {
        super(pitch, type, dots);
    }

    public GraceNote(Type type, int dots) {
        super(null, type, dots);
    }

    @Override
    public Category getCategory() {
        return Category.OPTIONAL;
    }
}

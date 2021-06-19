package com.musicquint.api;

public class GraceNote extends AbstractNote implements OptionalItem {

    public GraceNote(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractNote.Builder<GraceNote> {

        @Override
        public GraceNote build() {
            return new GraceNote(this);
        }
    }
}

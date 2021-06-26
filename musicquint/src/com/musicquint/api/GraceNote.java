package com.musicquint.api;

public class GraceNote extends AbstractNote implements OptionalItem {

    public GraceNote(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractNote.Builder<GraceNote> {

        public Builder() {
            super();
        }

        public Builder(Builder builder) {
            super(builder);
        }

        @Override
        public GraceNote build() {
            return new GraceNote(this);
        }
    }
}

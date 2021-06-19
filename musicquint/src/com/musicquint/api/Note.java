package com.musicquint.api;

public final class Note extends AbstractNote implements PrincipalItem {

    public Note(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractNote.Builder<Note> {

        @Override
        public Note build() {
            return new Note(this);
        }
    }

}

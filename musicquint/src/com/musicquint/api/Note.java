package com.musicquint.api;

public final class Note extends AbstractNote implements PrincipalItem {

    private Note(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractNote.Builder<Note> {

        public Builder() {
            super();
        }

        public Builder(AbstractNote.Builder<?> builder) {
            super(builder);
        }

        @Override
        public Note build() {
            return new Note(this);
        }
    }

}

package com.musicquint.api;

public abstract class AbstractNote implements ContentItem {

    @Override
    public BarTime getDuration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pitch getPitch() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDots() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Type getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addNoteAttribute(NoteAttribute attribute) {
        // TODO Auto-generated method stub

    }

    @Override
    public <T extends NoteAttribute> T getNoteAttribute(Class<T> key) {
        // TODO Auto-generated method stub
        return null;
    }

}

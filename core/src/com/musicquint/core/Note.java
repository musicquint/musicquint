package com.musicquint.core;

public class Note extends AbstractNote {

	public Note(BarTime duration, Pitch pitch, int dots, Type type) {
		super(duration, pitch, dots, type);
	}

	@Override
	public ContentType getContentType() {
		return ContentType.PRINCIPAL;
	}

}

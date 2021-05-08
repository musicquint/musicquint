package com.musicquint.core;

public final class Note extends AbstractNote {

	private Note(BarTime duration, Pitch pitch, int dots, Type type) {
		super(duration, pitch, dots, type);
	}
	
	public static Note of(BarTime duration, Pitch pitch, int dots, Type type) {
		return new Note(duration, pitch, dots, type);
	}

	@Override
	public ContentType getContentType() {
		return ContentType.PRINCIPAL;
	}

}

package com.musicquint.core;

public interface ContentItem extends BarItem {
	
	BarTime getDuration();
	
	Pitch getPitch();
	
	int getDots();
	
	Type getType();
	
	public enum ContentType {
		PRINCIPAL, OPTIONAL
	}
}

package com.musicquint.core;

public interface ContentItem extends BarItem {
	
	BarTime getDuration();
	
	Pitch getPitch();
	
	void setPitch(Pitch pitch);
	
	int getDots();
	
	Type getType();
	
	ContentType getContentType();
	
	boolean isPitched();
	
	boolean isRest();
	
	<T extends ContentAttribute> T getContentAttribute(Class<T> attributeClass);
	
	void addContentAttribute(ContentAttribute attribute);
	
	public enum ContentType {
		PRINCIPAL, OPTIONAL
	}
}

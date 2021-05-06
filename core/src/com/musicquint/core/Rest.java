package com.musicquint.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rest implements ContentItem {
	
	private final BarTime duration;
	
	private final int dots;
	
	private final Type type;
	
	private int staffNumber;
	
	private Map<Class<? extends ContentAttribute>, ContentAttribute> attributes;

	public Rest(BarTime duration, int dots, Type type) {
		this.duration = Objects.requireNonNull(duration);
		this.dots = dots;
		this.type = Objects.requireNonNull(type);
		attributes = new HashMap<>();
	}

	@Override
	public int getStaffNumber() {
		return staffNumber;
	}

	@Override
	public BarTime getDuration() {
		return duration;
	}

	@Override
	public Pitch getPitch() {
		throw new UnsupportedOperationException("A Rest as no pitch.");
	}
	
	@Override
	public void setPitch(Pitch pitch) {
		throw new UnsupportedOperationException("A Rest as no pitch.");
	}

	@Override
	public int getDots() {
		return dots;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public ContentType getContentType() {
		return ContentType.PRINCIPAL;
	}

	@Override
	public boolean isPitched() {
		return false;
	}

	@Override
	public boolean isRest() {
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ContentAttribute> T getContentAttribute(Class<T> attributeClass) {
		if(attributes.containsKey(attributeClass)) {
			return (T) attributes.get(attributeClass);
		} else {
			return null;
		}
	}
	
	@Override
	public void addContentAttribute(ContentAttribute attribute) {
		Objects.requireNonNull(attribute, "Cannot add null as attribute");
		attributes.put(attribute.getClass(), attribute);
	}

}

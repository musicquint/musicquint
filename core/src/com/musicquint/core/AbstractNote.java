package com.musicquint.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractNote implements ContentItem {
	
	private final BarTime duration;
	
	private Pitch pitch;
	
	private final int dots;
	
	private final Type type;
	
	private int staffNumber;
	
	private Map<Class<? extends ContentAttribute>, ContentAttribute> attributes;

	public AbstractNote(BarTime duration, Pitch pitch, int dots, Type type) {
		this.duration = Objects.requireNonNull(duration);
		this.pitch = Objects.requireNonNull(pitch);
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
		return pitch;
	}
	
	@Override
	public void setPitch(Pitch pitch) {
		this.pitch = Objects.requireNonNull(pitch);
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
	public boolean isPitched() {
		return true;
	}

	@Override
	public boolean isRest() {
		return false;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dots;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((pitch == null) ? 0 : pitch.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNote other = (AbstractNote) obj;
		if (dots != other.dots)
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (pitch == null) {
			if (other.pitch != null)
				return false;
		} else if (!pitch.equals(other.pitch))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}

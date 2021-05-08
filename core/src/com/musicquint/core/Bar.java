package com.musicquint.core;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public final class Bar extends AbstractList<Voice> {

	private List<Voice> voices;

	private TreeMap<BarTime, Set<BarAttribute>> attributes;

	private Time time;

	private final BarTime capacity;

	public Bar(BarTime capacity) {
		this.capacity = Objects.requireNonNull(capacity);
		voices = new ArrayList<>();
		attributes = new TreeMap<>();
	}

	public Bar() {
		this.capacity = Objects.requireNonNull(BarTime.FOUR_QUARTER);
		voices = new ArrayList<>();
		attributes = new TreeMap<>();
	}

	public void add(BarTime time, BarAttribute attribute) {
		if (attributes.containsKey(time)) {
			attributes.get(time).add(attribute);
		} else {
			Set<BarAttribute> attributeSet = new HashSet<>();
			attributeSet.add(attribute);
			attributes.put(time, attributeSet);
		}
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public BarTime getCapacity() {
		return capacity;
	}

	@Override
	public void add(int index, Voice element) {
		// TODO Auto-generated method stub
		super.add(index, element);
	}
	
	@Override
	public Voice get(int index) {
		return voices.get(index);
	}

	@Override
	public int size() {
		return voices.size();
	}

	@Override
	public Voice remove(int index) {
		return voices.remove(index);
	}
}

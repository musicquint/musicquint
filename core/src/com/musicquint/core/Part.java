package com.musicquint.core;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public final class Part extends AbstractList<Bar> {

	private String partName;

	private List<Bar> bars;

	public Part() {
		bars = new ArrayList<>();
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Override
	public Bar get(int index) {
		return bars.get(index);
	}

	@Override
	public int size() {
		return bars.size();
	}

	@Override
	public void add(int index, Bar element) {
		bars.add(index, element);
	}

	@Override
	public Bar remove(int index) {
		return bars.remove(index);
	}
}

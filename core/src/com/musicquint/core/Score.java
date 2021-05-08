package com.musicquint.core;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Score extends AbstractList<Part> {
	
	private String title;
	
	private String composer;
	
	private List<Part> parts;
	
	public Score() {
		parts = new ArrayList<>();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	@Override
	public Part get(int index) {
		return parts.get(index);
	}

	@Override
	public int size() {
		return parts.size();
	}
	
	@Override
	public void add(int index, Part element) {
		parts.add(index, element);
	}
	
	@Override
	public Part remove(int index) {
		return parts.remove(index);
	}
}

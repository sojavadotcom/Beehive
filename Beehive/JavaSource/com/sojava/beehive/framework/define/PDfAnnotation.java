package com.sojava.beehive.framework.define;

import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;

public class PDfAnnotation {

	private String id;
	private PDGamma colour;

	public PDfAnnotation() {
		setId(null);
		setColour(new PDGamma());
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PDGamma getColour() {
		return colour;
	}
	public void setColour(PDGamma colour) {
		this.colour = colour;
	}
}

package com.sojava.beehive.framework.define;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PDFAnnotationRectangle extends PDfAnnotation {
	private PDRectangle rectangle;

	public PDFAnnotationRectangle() {
		super();
		setRectangle(new PDRectangle());
	}

	public PDRectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(PDRectangle rectangle) {
		this.rectangle = rectangle;
	}
}

package com.sojava.beehive.framework.util;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

import com.sojava.beehive.framework.define.PDFAnnotationRectangle;

public class PDFUtil {

	public PDDocument loadDocument(byte[] buffer) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		GZIPInputStream gzDecompress = new GZIPInputStream(in);
		try {
			return PDDocument.load(gzDecompress);
		}
		finally {
			gzDecompress.close();
			in.close();
		}
	}
	public String getString(PDDocument pd) throws Exception {
		String result = null;
		StringWriter writer = new StringWriter();
		PDFTextStripper textStripper = new PDFTextStripper(System.getProperty("system.encoding", "UTF-8"));
		textStripper.writeText(pd, writer);
		writer.close();
		result = writer.getBuffer().toString();

		return result;
	}
	public String getString(PDDocument pd, boolean sortByPosition) throws Exception {
		String result = null;
		StringWriter writer = new StringWriter();
		PDFTextStripper textStripper = new PDFTextStripper(System.getProperty("system.encoding", "UTF-8"));
		textStripper.setSortByPosition(sortByPosition);
		textStripper.writeText(pd, writer);
		writer.close();
		result = writer.getBuffer().toString();

		return result;
	}

	public String getHTML(PDDocument pd) throws Exception {
		String result = null;
		PDFText2HTML text2Html = new PDFText2HTML(System.getProperty("system.encoding", "UTF-8"));
		StringWriter writer = new StringWriter();
		text2Html.writeText(pd, writer);
		writer.close();
		result = writer.getBuffer().toString();

		return result;
	}

	public String getHTML(PDDocument pd, boolean sortByPosition) throws Exception {
		String result = null;
		PDFText2HTML text2Html = new PDFText2HTML(System.getProperty("system.encoding", "UTF-8"));
		text2Html.setSortByPosition(sortByPosition);
		StringWriter writer = new StringWriter();
		text2Html.writeText(pd, writer);
		writer.close();
		result = writer.getBuffer().toString();

		return result;
	}

	public List<byte[]> toImage(PDDocument pd) throws Exception {
		List<byte[]> result = new ArrayList<byte[]>();
		PDDocumentCatalog catalog = pd.getDocumentCatalog();
		for (Object page: catalog.getAllPages()) {
			BufferedImage image = ((PDPage) page).convertToImage(BufferedImage.TYPE_INT_RGB, 300);
			image.flush();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, "png", out);
			out.close();
			result.add(out.toByteArray());
		}

		return result;
	}

	public String getTextByRange(PDPage page, PDRectangle rectangle) throws Exception {
		String text = null;

		int rotation = page.findRotation();
		float x = rectangle.getLowerLeftX();
		float y = rectangle.getUpperRightY();
		float width = rectangle.getWidth();
		float height = rectangle.getHeight();
		if(rotation == 0) {
			PDRectangle pageSize = page.findMediaBox();
			y = pageSize.getHeight() - y;
		}
		Rectangle2D.Double awtRect = new Rectangle2D.Double(x, y, width, height);
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.addRegion("RangeText", awtRect);
		stripper.extractRegions(page);
		text = stripper.getTextForRegion("RangeText").trim();

		return text;
	}

	public void addAnnotationRectangle(PDPage page, PDFAnnotationRectangle settings) throws IOException {
		PDAnnotationSquareCircle annot = new PDAnnotationSquareCircle("Square");
		if (settings.getId() != null) annot.setAnnotationName(settings.getId());
		annot.setColour(settings.getColour());
		annot.setRectangle(settings.getRectangle());
		page.getAnnotations().add(annot);
	}
}

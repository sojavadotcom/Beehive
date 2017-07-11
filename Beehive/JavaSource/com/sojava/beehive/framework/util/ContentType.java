package com.sojava.beehive.framework.util;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class ContentType {

	private static Dictionary<String, String> contentType = new Hashtable<String, String>();

	public static Dictionary<String, String> getContentType() {
		return contentType;
	}

	public static void setContentType(Dictionary<String, String> contentType) {
		ContentType.contentType = contentType;
	}

	public static String getContentType(String extension) {
		init();
		String contentType = null;
		if (getContentType().isEmpty()) contentType = null;
		else {
			Enumeration<String> keys = getContentType().keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = getContentType().get(key).toLowerCase().trim();
				if (value.equalsIgnoreCase(extension.toLowerCase().trim()) ||
					value.indexOf(extension.toLowerCase().trim() + " ") == 0 ||
					value.indexOf(" " + extension.toLowerCase().trim() + " ") > 1 ||
					value.lastIndexOf(" " + extension.toLowerCase().trim()) > 1) {
					contentType = key;
					break;
				}
			}
		}
		return contentType;
	}

	public static String getExtension(String contentType) {
		init();
		return getContentType().get(contentType.trim());
	}

	public static void init() {
		if (contentType.isEmpty()) {
			contentType.put("application/x-gzip", "gz tgz");
			contentType.put("application/x-gtar", "gtar");
			contentType.put("application/java-archive", "jar");
			contentType.put("application/javascript", "js");
			contentType.put("application/json", "json");
			contentType.put("application/mp4", "mp4s");
			contentType.put("application/msword", "doc");
			contentType.put("application/msword", "dot");
			contentType.put("application/octet-stream", "iso");
			contentType.put("application/pdf", "pdf");
			contentType.put("application/vnd.lotus-1-2-3", "123");
			contentType.put("application/vnd.ms-powerpoint", "ppt pps pot");
			contentType.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
			contentType.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
			contentType.put("application/x-7z-compressed", "7z");
			contentType.put("application/x-msmetafile", "wmf");
			contentType.put("application/x-rar-compressed", "rar");
			contentType.put("application/x-shockwave-flash", "swf");
			contentType.put("application/x-tar", "tar");
			contentType.put("application/xml", "xml");
			contentType.put("application/xslt+xml", "xslt");
			contentType.put("application/zip", "zip");
			contentType.put("audio/midi", "mid midi kar rmi");
			contentType.put("audio/mp4", "mp4a mpga mp2 mp2a mp3 m2a m3a");
			contentType.put("audio/ogg", "oga ogg spx");
			contentType.put("audio/x-mpegurl", "m3u");
			contentType.put("audio/x-ms-wma", "wma");
			contentType.put("audio/x-pn-realaudio", "ram ra");
			contentType.put("audio/x-wav", "wav");
			contentType.put("image/bmp", "bmp");
			contentType.put("image/gif", "gif");
			contentType.put("image/jpeg", "jpeg jpg jpe");
			contentType.put("image/png", "png");
			contentType.put("image/svg+xml", "svg svgz");
			contentType.put("image/tiff", "tiff tif");
			contentType.put("image/vnd.adobe.photoshop", "psd");
			contentType.put("image/x-icon", "ico");
			contentType.put("image/x-pcx", "pcx");
			contentType.put("image/x-pict", "pic pct");
			contentType.put("text/css", "css");
			contentType.put("text/csv", "csv");
			contentType.put("text/html", "html htm");
			contentType.put("text/plain", "txt text conf def list log in");
			contentType.put("text/sgml", "sgml sgm");
			contentType.put("text/uri-list", "uri uris urls");
			contentType.put("text/vnd.wap.wml", "wml");
			contentType.put("text/vnd.wap.wmlscript", "wmls");
			contentType.put("text/x-c", "c cc cxx cpp h hh dic");
			contentType.put("text/x-pascal", "p pas");
			contentType.put("text/x-java-source", "java");
			contentType.put("video/3gpp", "3gp");
			contentType.put("video/3gpp2", "3g2");
			contentType.put("video/jpeg", "jpgv");
			contentType.put("video/mp4", "mp4 mp4v mpg4");
			contentType.put("video/mpeg", "mpeg mpg mpe m1v m2v");
			contentType.put("video/ogg", "ogv");
			contentType.put("video/quicktime", "qt mov");
			contentType.put("video/vnd.mpegurl", "mxu m4u");
			contentType.put("video/x-flv", "flv");
			contentType.put("video/x-m4v", "m4v");
			contentType.put("video/x-ms-asf", "asf asx");
			contentType.put("video/x-ms-wm", "wm");
			contentType.put("video/x-ms-wmv", "wmv");
			contentType.put("video/x-flv", "flv");
			contentType.put("video/x-m4v", "m4v");
			contentType.put("video/x-ms-asf", "asf asx");
			contentType.put("video/x-ms-wm", "wm");
			contentType.put("video/x-ms-wmv", "wmv");
			contentType.put("video/x-msvideo", "avi");
			contentType.put("video/x-sgi-movie", "movie");
		}
	}
}

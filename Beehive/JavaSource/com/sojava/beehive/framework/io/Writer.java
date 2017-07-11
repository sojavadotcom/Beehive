package com.sojava.beehive.framework.io;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument;

import net.sf.json.JSON;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.sojava.beehive.framework.component.type.ClientType;
import com.sojava.beehive.framework.component.type.ResultType;
import com.sojava.beehive.framework.define.result.Result;
import com.sojava.beehive.framework.exception.CommonException;
import com.sojava.beehive.framework.exception.ErrorException;
import com.sojava.beehive.framework.util.RecordUtil;

public class Writer {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Integer start;
	private Integer end;
	private Integer total;

	protected Writer() {}

	public Writer(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.total = 0;
		setRange(request.getHeader("Range"));
	}

	public Writer(HttpServletRequest request, HttpServletResponse response, int total) {
		this.request = request;
		this.response = response;
		this.total = total;
		setRange(request.getHeader("Range"));
	}

	protected void setRange(String range) {
		try  {
			String[] ranges = range.split("\\Q-\\E");
			this.start = Integer.parseInt(ranges[0]);
			this.end = Integer.parseInt(ranges[1]);
		}
		catch(Exception ex) {
			this.start = 0;
			this.end = 0;
		}
	}

	public void output(ClientType clientType, ResultType resultType, Object ... content) throws Exception {
		if (content == null) throw new ErrorException(this.getClass(), "content不能为空");

		RecordUtil recordUtil = new RecordUtil();
		if (resultType.equals(ResultType.Xml)) {
			if (content.length == 1 && content[0] instanceof Document) output(content[0]);
			else output(recordUtil.generateXmlByMapping(content, null));
		} else if (resultType.equals(ResultType.Json)) {
			if (content.length == 1 && content[0] instanceof JSON) output(content[0]);
			else output(recordUtil.generateJsonByMapping(content));
		} else if (resultType.equals(ResultType.Text)) {
			Result r = (Result) content[0];
			output(r.isSuccess() + ";" + r.getMessage());
		} else if (resultType.equals(ResultType.JavaScript)) {
		} else if (resultType.equals(ResultType.VBScript)) {
		}
	}

	public void output(ClientType clientType, ResultType resultType, Object content) throws Exception {
		if (content == null) throw new ErrorException(this.getClass(), "content不能为空");

		RecordUtil recordUtil = new RecordUtil();
		if (resultType.equals(ResultType.Xml)) {
			if (content instanceof Document) output(content);
			else output(recordUtil.generateXmlByMapping(content, null));
		} else if (resultType.equals(ResultType.Json)) {
			if (content instanceof JSON) output(content);
			else output(recordUtil.generateJsonByMapping(content));
		} else if (resultType.equals(ResultType.Text)) {
			Result r = (Result) content;
			output(r.isSuccess() + ";" + r.getMessage());
		} else if (resultType.equals(ResultType.JavaScript)) {
		} else if (resultType.equals(ResultType.VBScript)) {
		}
	}

	public void output(Object content) throws CommonException {
		if (content == null) throw new ErrorException(this.getClass(), "content不能为空");

//		String contentType = "text/plain; charset=UTF-8";

//		response.setHeader("Pragma","No-cache");
//		response.setHeader("Cache-Control","no-cache");
//		response.setDateHeader("Expires", 0);

//		boolean locateOutput = true;
		if (content instanceof Document) {
			sendXMLDocument((Document) content);
//			locateOutput = false;
		} else if (content instanceof JSON) {
//			contentType = "application/json; charset=UTF-8";
			output(content, ContentType.json);
		} else if (content instanceof HTMLDocument) {
			output(content, ContentType.html);
//			contentType = "text/html; charset=UTF-8";
		} else {
			output(content, ContentType.text);
		}
//
//		if (locateOutput) {
//			OutputStream out = null;
//			try {
//				String encodings = request.getHeader("Accept-Encoding");
//	
//				if ((encodings != null) && (encodings.indexOf("gzip") != -1)) {
//					out = new GZIPOutputStream(response.getOutputStream());
//					response.setHeader("Content-Encoding", "gzip");
//				} else {
//					out = response.getOutputStream();
//				}
//	
//				response.setHeader("Content-type", contentType);
//				response.setHeader("Pragma","No-cache");    
//				response.setHeader("Cache-Control","no-cache");    
//				response.setDateHeader("Expires", 0);
//	
//				out.write(content.toString().getBytes());
//				try {out.flush();} catch(Exception e) {}
//			} catch (Exception ex) {
//				throw new ErrorException(this, ex);
//			}
//			finally {
//				try {out.close();} catch(Exception e) {}
//			}
//		}
	}

	public void output(Object content, ContentType contentType) throws CommonException {
		if (content == null) throw new ErrorException(this.getClass(), "content不能为空");

		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);

		String charsetStr = "; charset=" + System.getProperty("system.encoding", "UTF-8");
		String ctype = "text/html";
		switch(contentType) {
		case html:
			ctype = "text/html";
			break;
		case xhtml:
			ctype = "text/xhtml";
			break;
		case json:
			ctype = "application/json";
			break;
		case xml:
			ctype = "text/xml";
			break;
		case text:
			ctype = "text/plain";
			break;
		case javascript:
			ctype = "text/javascript";
			break;
		}
		ctype += charsetStr;
		boolean locateOutput = true;

		if (locateOutput) {
			OutputStream out = null;
			try {
				String encodings = request.getHeader("Accept-Encoding");
	
				if ((encodings != null) && (encodings.indexOf("gzip") != -1)) {
					out = new GZIPOutputStream(response.getOutputStream());
					response.setHeader("Content-Encoding", "gzip");
				} else {
					out = response.getOutputStream();
				}

				response.setHeader("Content-type", ctype);
				if (this.start != null && this.end != null && this.total != null)
					response.setHeader("Content-Range", "items " + (this.start) + "-" + (this.end) + "/" + this.total);

				out.write(content.toString().getBytes(System.getProperty("system.encoding", "UTF-8")));
				try {out.flush();} catch(Exception e) {}
			} catch (Exception ex) {
				throw new ErrorException(this.getClass(), ex);
			}
			finally {
				try {out.close();} catch(Exception e) {}
			}
		}
	}

	public void sendXMLDocument(Document doc) throws CommonException {
		XMLWriter writer = null;
		OutputStream out = null;
		try {
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);

			String encodings = request.getHeader("Accept-Encoding");

			if ((encodings != null) && (encodings.indexOf("gzip") != -1)) {
				out = new GZIPOutputStream(response.getOutputStream());
				response.setHeader("Content-Encoding", "gzip");
			} else {
				out = response.getOutputStream();
			}
			OutputFormat format = OutputFormat.createCompactFormat();
			format.setEncoding(System.getProperty("system.encoding", "UTF-8"));
			response.setContentType("text/xml; charset=" + System.getProperty("system.encoding", "UTF-8"));

			writer = new XMLWriter(out, format);
			writer.write(doc);
			try {writer.flush();} catch(Exception e) {}
			try {out.flush();} catch(Exception e) {}
		} catch (Exception ex) {
			throw new ErrorException(this.getClass(), ex);
		}
		finally {
			try {writer.close();} catch(Exception e) {}
			try {out.close();} catch(Exception e) {}
		}
	}

}

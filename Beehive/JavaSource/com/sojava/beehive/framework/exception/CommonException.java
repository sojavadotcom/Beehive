package com.sojava.beehive.framework.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.sojava.beehive.framework.component.type.ResultType;
import com.sojava.beehive.framework.io.Writer;
import com.sojava.beehive.framework.util.ExceptionUtil;


public class CommonException extends Exception {

	private static final long serialVersionUID = -7554437730653767259L;

	public CommonException(Throwable t) {
		super(t);
	}
	public CommonException(Object instance, Throwable t) {
		super(t);
	}
	public CommonException(String msg) {
		super(msg);
	}
	public CommonException(Object instance, String msg) {
		super(msg);
	}
	public CommonException(Object instance, String code, String msg) {
		super(msg);
	}

	public static void sendError(HttpServletRequest request, HttpServletResponse response, Object instance, int code, Throwable t, ResultType resultType) throws ServletException {
		switch(resultType) {
		case Forward:
			throw new ServletException(t);
		case Json:
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("id", code);
			json.put("code", code);
			json.put("message", ExceptionUtil.getMessage(t));
			try {
				new Writer(request, response).output(json);
			}
			catch(CommonException ex) {
				sendError(request, response, instance, 601, ex, ResultType.Forward);
			}
			break;
		case Xml:
			Document doc = DocumentFactory.getInstance().createDocument();
			Element root = doc.addElement("result");
			root.addAttribute("success", "false");
			root.addElement("code").addText(code+"");
			root.addElement("msg").addCDATA(ExceptionUtil.getMessage(t));
			try {
				new Writer(request, response).output(doc);
			} catch(CommonException ex) {
				sendError(request, response, instance, 601, ex.getLocalizedMessage(), ResultType.Forward);
			}
			break;
		default:
			break;
		}
	}
	public static void sendError(HttpServletRequest request, HttpServletResponse response, Object instance, int code, String msg, ResultType resultType) throws ServletException {
		switch(resultType) {
		case Forward:
			throw new ServletException(msg);
		case Json:
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("id", code);
			json.put("code", code);
			json.put("msg", msg);
			try {
				new Writer(request, response).output(json);
			}
			catch(CommonException ex) {
				sendError(request, response, instance, 601, ex, ResultType.Forward);
			}
			break;
		case Xml:
			Document doc = DocumentFactory.getInstance().createDocument();
			Element root = doc.addElement("result");
			root.addAttribute("success", "false");
			root.addElement("code").addText(code+"");
			root.addElement("msg").addCDATA(msg);
			try {
				new Writer(request, response).output(doc);
			} catch(CommonException ex) {
				sendError(request, response, instance, 601, ex.getLocalizedMessage(), ResultType.Forward);
			}
			break;
		default:
			break;
		}
	}
}

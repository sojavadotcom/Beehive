package com.sojava.beehive.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

/*
 * 检查工具
 */
public class CheckUtil {

	private static CodepageDetectorProxy detector = null;
	private static boolean inited = false;

	//判断字符集对象初始化
	private static void init() {
		if (inited) return;

		detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(UnicodeDetector.getInstance());
		detector.add(JChardetFacade.getInstance());//内部引用了 chardet.jar的类
		detector.add(ASCIIDetector.getInstance());

		inited = true;
	}

	//获取流字符集
	public final static Charset getCharset(InputStream in) throws IOException {
		init();

		Charset charset = CheckUtil.detector.detectCodepage(in, Integer.MAX_VALUE);
		if (charset == null) charset = Charset.forName("GBK");

		return charset;
	}

	//获取URL字符集
	public final static Charset getCharset(URL url) throws IOException {
		init();

		Charset charset = CheckUtil.detector.detectCodepage(url);
		if (charset == null) charset = Charset.forName("GBK");

		return charset;
	}
}

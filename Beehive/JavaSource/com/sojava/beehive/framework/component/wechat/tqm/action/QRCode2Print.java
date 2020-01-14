package com.sojava.beehive.framework.component.wechat.tqm.action;
import com.sojava.beehive.framework.util.QRCode;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;

public class QRCode2Print {

	private PrintService printer;
	private PrintRequestAttributeSet psattr;
	private DocFlavor psInFormat;
	private DocAttributeSet docattr;

	private final int COPIES = 1;

	public QRCode2Print() {
		//设置打印属性
		this.psattr = new HashPrintRequestAttributeSet();
		this.psattr.add(OrientationRequested.PORTRAIT);
		this.psattr.add(PrintQuality.HIGH);
		this.psattr.add(new Copies(COPIES));//打印份数

		//设置打印数据的格式，此处为图片gif格式
		this.psInFormat = DocFlavor.INPUT_STREAM.PNG;
		//创建打印数据
		this.docattr = new HashDocAttributeSet();//设置文档属性
		docattr.add(new MediaPrintableArea(0, 0, 25, 25, MediaPrintableArea.MM));

		this.printer = PrintServiceLookup.lookupDefaultPrintService();
		//查找所有打印服务
/*
		PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);

		// this step is necessary because I have several printers configured
		//将所有查找出来的打印机与自己想要的打印机进行匹配，找出自己想要的打印机
		PrintService printer = null;
		for (int i = 0; i < services.length; i++) {
			System.out.println("service found: " + services[i]);
			String svcName = services[i].toString();
			if (svcName.contains("Foxit PhantomPDF Printer")) {
				printer = services[i];
				System.out.println("my printer found: " + svcName);
				System.out.println("my printer found: " + myPrinter);
				break;
			}
		}
*/

		//可以输出打印机的各项属性
/*
		AttributeSet attrs = printer.getAttributes();
		for (Attribute a : attrs.toArray()) {

			String attributeName;
			String attributeValue;

			attributeName = a.getName();
			attributeValue = att.get(a.getClass()).toString();

			System.out.println(attributeName + " : " + attributeValue);
		}
*/
/*
		if (printer == null) {
			PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, psattr);
			for (int i = 0; i < services.length; i++) {
				System.out.println("service found: " + services[i]);
				String svcName = services[i].toString();
				if (svcName.contains("Foxit PhantomPDF Printer")) {
					printer = services[i];
					System.out.println("my printer found: " + svcName);
					System.out.println("my printer found: " + printer);
					break;
				}
			}
		}
*/
	}

	public static void main(String[] args) {
		QRCode2Print q = new QRCode2Print();
		try {
			String bgfile = null;
			int start = 1, pages = 1;
			for (String arg : args) {
				if (arg.startsWith("bgfile")) bgfile = arg.replaceFirst("\\Qbgfile\\E", "");
				if (arg.startsWith("start")) start = Integer.parseInt(arg.replaceFirst("\\Qstart\\E", ""));
				if (arg.startsWith("pages")) pages = Integer.parseInt(arg.replaceFirst("\\Qpages\\E", ""));
			}
			FileInputStream in = new FileInputStream(bgfile);
			byte[] logo = new byte[in.available()];
			in.read(logo);
			in.close();
//			List<byte[]> queue = new ArrayList<byte[]>();
			for (int i = 0; i < pages; i ++) {
				String num = "0000" + start;
				byte[] buff = QRCode.encode("http://weixin.qq.com/r/2Uzq8sbELJtTrYKR9xnL?cn.org.jxszyyy.casehistory.evidence." + start, 300, 300, logo, num.substring(num.length() - 4));
				start ++;
				q.toPrint(buff);
//				queue.add(buff);
			}
//			q.toPrint(queue);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void toPrint(List<byte[]> queue) {
		if (this.printer == null) {
			System.out.println("Can't find the Default print service!");
			return;
		}
		DocPrintJob job = printer.createPrintJob();//创建文档打印作业
		for (byte[] buff : queue) {
			ByteArrayInputStream in = null;
			try {
				in = new ByteArrayInputStream(buff);
				Doc doc = new SimpleDoc(in, psInFormat, docattr);
				job.print(doc, psattr);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				try {in.close();} catch(IOException ex) {}
			}
		}
	}

	public void toPrint(byte[] content) {
		ByteArrayInputStream in = null;

		if (printer != null) {
			try {
				in = new ByteArrayInputStream(content);
				Doc doc = new SimpleDoc(in, psInFormat, docattr);
				DocPrintJob job = printer.createPrintJob();//创建文档打印作业
				job.print(doc, psattr);//打印文档
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				try {in.close();} catch(IOException ex) {}
			}
		} else {
			System.out.println("no printer services found");
		}
	}

}

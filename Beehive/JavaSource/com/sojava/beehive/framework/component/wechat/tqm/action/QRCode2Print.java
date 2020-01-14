package com.sojava.beehive.framework.component.wechat.tqm.action;
import com.sojava.beehive.framework.util.QRCode;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

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

	public static void main(String[] args) {
		try {
			FileInputStream in = new FileInputStream("D:\\MyDocument\\git\\Beehive\\Beehive\\WebContent\\images\\logo\\logo128bg.png");
			byte[] logo = new byte[in.available()];
			in.read(logo);
			in.close();
			for (int i = 1; i <= 1; i ++) {
				String num = "0000" + i;
				byte[] buff = QRCode.encode("cn.org.jxszyyy.casehistory.evidence." + i, 300, 300, logo, num.substring(num.length() - 4));
				toPrint(buff, 1);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void toPrint(byte[] content, int copies) {

		ByteArrayInputStream in = new ByteArrayInputStream(content);

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
		PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
		//设置打印属性
		PrintRequestAttributeSet psattr = new HashPrintRequestAttributeSet();
		psattr.add(OrientationRequested.PORTRAIT);
		psattr.add(PrintQuality.HIGH);
		psattr.add(new Copies(copies));//打印份数

		//设置打印数据的格式，此处为图片gif格式
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.PNG;
		//创建打印数据
		DocAttributeSet docattr = new HashDocAttributeSet();//设置文档属性
		docattr.add(new MediaPrintableArea(0, 0, 25, 25, MediaPrintableArea.MM));

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

		if (printer != null) {
			DocPrintJob job = null;
			try {

				Doc doc = new SimpleDoc(in, psInFormat, docattr);
				job = printer.createPrintJob();//创建文档打印作业
				job.print(doc, psattr);//打印文档

			} catch (Exception pe) {
				pe.printStackTrace();
			}
		} else {
			System.out.println("no printer services found");
		}
	}

}

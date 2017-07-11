package com.sojava.beehive.framework.component.document;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.sojava.beehive.framework.util.FileOperator;

public class WordToHTML {

	/**
	* 回车符ASCII码
	*/
	private final short ENTER_ASCII = 13;
	/**
	* 空格符ASCII码
	*/
	private final short SPACE_ASCII = 32;
	/**
	* 水平制表符ASCII码
	*/
	private final short TABULATION_ASCII = 9;
	public StringBuffer htmlSource = new StringBuffer();
	public int counter = 0;
	public int beginPosi=  0;
	public int endPosi = 0;
	public int beginArray[];
	public int endArray[];
	public String htmlTextArray[];
	public boolean tblExist = false;
	private byte[] wordDocument = null;
	private String attachmentPath = null;
	private String attachmentDirectory = null;

	protected WordToHTML() {}

	public WordToHTML(byte[] wordDocument, String attachmentPath) throws Exception {
		this.wordDocument = wordDocument;
		this.attachmentPath = attachmentPath;
		File attachmentFile = new File(this.attachmentPath);
		this.attachmentDirectory = attachmentFile.getParent();
		FileOperator.newFolder(this.attachmentDirectory);
	}

	public WordToHTML(String filePath, String attachmentPath) throws FileNotFoundException, IOException, Exception {
		File file = new File(filePath);
		if (!file.exists()) throw new FileNotFoundException("未找到Word文件(" + filePath + ")");
		this.attachmentPath = attachmentPath;
		File attachmentFile = new File(this.attachmentPath);
		this.attachmentDirectory = attachmentFile.getParent();
		FileOperator.newFolder(this.attachmentDirectory);
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] buff = new byte[1024];
			int len = 0;
			while((len = in.read(buff)) >= 0) {
				out.write(buff, 0, len);
				out.flush();
			}
			in.close();
			out.close();
			this.wordDocument = out.toByteArray();
		}
		catch(FileNotFoundException ex) {}
		catch(IOException ex) {
			throw ex;
		}
	}

	public static void main(String args[]) {
		FileOutputStream out = null;
		BufferedWriter bw = null;
		try {
			WordToHTML export = new WordToHTML("c:/Temp/卫生部部署各地做好今冬明春消除麻疹工作.doc", "C:/Temp");
			File file = new File("c://abc.html");
			out = new FileOutputStream(file);
			export.write(out);
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {if (bw != null) bw.close();} catch (IOException ie) {}
		}
	}

	/**
	* 读取每个文字样式
	*
	* @param fileName
	* @throws Exception
	*/
	public String getHTML() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(wordDocument);
		HWPFDocument doc = new HWPFDocument(in);
		in.close();

		Range rangetbl = doc.getRange();//得到文档的读取范围
		TableIterator it = new TableIterator(rangetbl);
		int num = 100;

		beginArray = new int[num];
		endArray = new int[num];
		htmlTextArray = new String[num];

		// 取得文档中字符的总数
		int length = doc.characterLength();
		// 创建图片容器
		PicturesTable pTable = doc.getPicturesTable();

		htmlSource.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">").append("\n");
		htmlSource.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("\n");
		htmlSource.append("<head>").append("\n");
		htmlSource.append("<title>" + doc.getSummaryInformation().getTitle() + "</title>").append("\n");
		htmlSource.append("<meta name=\"Author\" content=\"贾茗凯(John.M.Jia).北京华通利亨科技有限公司\" />").append("\n");
		htmlSource.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />").append("\n");
		htmlSource.append("</head>").append("\n");
		htmlSource.append("<body>").append("\n");
		//创建临时字符串,好加以判断一串字符是否存在相同格式
		if (it.hasNext()) readTable(it, rangetbl);

		int cur=0;
		String tempString = "";

		for (int i = 0; i < length - 1; i++) {
			//整篇文章的字符通过一个个字符的来判断,range为得到文档的范围
			Range range = new Range(i, i + 1, doc);

			CharacterRun cr = range.getCharacterRun(0);
			//beginArray = new int[num];
			//endArray = new int[num];
			//htmlTextArray = new String[num];
			if (tblExist) {
				if (i == beginArray[cur]) {
					htmlSource.append(tempString + htmlTextArray[cur]).append("\n");
					tempString = "";
					i = endArray[cur]-1;
					cur ++;
					continue;
				}
			}
			if (pTable.hasPicture(cr)) {
				htmlSource.append(tempString).append("\n");
				//读写图片
				readPicture(pTable, cr);
				tempString = "";
			} else {
				Range range2 = new Range(i + 1, i + 2, doc);
				//第二个字符
				CharacterRun cr2 = range2.getCharacterRun(0);
				char c = cr.text().charAt(0);

//				System.out.println(i+"::"+range.getEndOffset()+"::"+range.getStartOffset()+"::"+c);

				// 判断是否为回车符
				if (c == ENTER_ASCII) tempString += "<br/>";
				else if (c == SPACE_ASCII) tempString += "&nbsp;";//判断是否为空格符
				else if (c == TABULATION_ASCII) tempString += "&nbsp;&nbsp;&nbsp;&nbsp;";// 判断是否为水平制表符
				// 比较前后2个字符是否具有相同的格式
				boolean flag = compareCharStyle(cr, cr2);
				if (flag) {
					tempString += cr.text();
				} else {
					String fontStyle = "<span style=\"font-family:" + cr.getFontName() + ";font-size:" + cr.getFontSize() / 2 + "pt;";

					if (cr.isBold()) fontStyle += "font-weight:bold;";
					if (cr.isItalic()) fontStyle += "font-style:italic;";

					fontStyle += "\" mce_style=\"font-family:" + cr.getFontName() + ";font-size:" + cr.getFontSize() / 2 + "pt;";

					if (cr.isBold()) fontStyle += "font-weight:bold;";
					if (cr.isItalic()) fontStyle += "font-style:italic;";

					htmlSource.append(fontStyle + "\">" + tempString + cr.text() + "</span>").append("\n");

					tempString = "";
				}
			}
		}

		htmlSource.append(tempString).append("\n");
		htmlSource.append("</body>").append("\n");
		htmlSource.append("</html>").append("\n");

		return htmlSource.toString();
	}

	/**
	* 读写文档中的表格
	* 
	* @param pTable
	* @param cr
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public void readTable(TableIterator it, Range rangetbl) throws Exception {
		//迭代文档中的表格
		counter = -1;
		while (it.hasNext()) {
			tblExist = true;
			Table table = (Table) it.next();
			beginPosi = table.getStartOffset() ;
			endPosi = table.getEndOffset();

//			System.out.println("............"+beginPosi+"...."+endPosi);
			counter = counter + 1;
			//迭代行，默认从0开始
			beginArray[counter] = beginPosi;
			endArray[counter] = endPosi;

			int maxCellNum = 0;
			Document htmlTableDoc = DocumentFactory.getInstance().createDocument();
			Element htmlTableRoot = htmlTableDoc.addElement("table").addAttribute("border", "1").addAttribute("cellpadding", "0").addAttribute("cellspacing", "0");
			for (int i = 0; i < table.numRows(); i ++) {
				TableRow row = table.getRow(i);
				Element htmlTableRow = htmlTableRoot.addElement("tr");
				//迭代列，默认从0开始
				maxCellNum = row.numCells() > maxCellNum ? row.numCells() : maxCellNum;
				for (int j = 0; j < row.numCells(); j ++) {
					TableCell cell = row.getCell(j);
					int cellWidth = cell.getWidth();
					String text = cell.text().trim().replaceAll(Pattern.quote("\n"), "<br/>").replaceAll(Pattern.quote("\r"), "<br/>");
					Element htmlTableCell = htmlTableRow.addElement("td").addAttribute("width", cellWidth+"");
					if (text.equals("")) text = "&nbsp;";
					htmlTableCell.addText(text);
					//取得单元格的内容
//					for (int n = 0; n < cell.numParagraphs(); n ++) {
//						Paragraph paragraph = cell.getParagraph(n);
//						String text = paragraph.text().trim();
//						if (text.equals("")) text = "&nbsp;";
//						htmlTableCell.addText(text);
//					}
				}
			}
			for (Element _row: (List<Element>) htmlTableRoot.selectNodes("tr")) {
				List<Element> _cells = _row.elements();
				if (_cells.size() < maxCellNum) {
					_cells.get(_cells.size()-1).addAttribute("colspan", (maxCellNum - _cells.size() + 1)+"");
				}
			}
			htmlTextArray[counter] = htmlTableRoot.asXML().replaceAll(Pattern.quote("&amp;nbsp;"), "&nbsp;").replaceAll(Pattern.quote("&lt;"), "<").replaceAll(Pattern.quote("&gt;"), ">");
		}//end while
	}

	/**
	* 读写文档中的图片
	*
	* @param pTable
	* @param cr
	* @throws Exception
	*/
	//提取图片
	public void readPicture(PicturesTable pTable, CharacterRun cr) throws Exception {
		Picture pic = pTable.extractPicture(cr, false);
		// 返回POI建议的图片文件名
		String afileName = pic.suggestFullFileName();
		OutputStream out = new FileOutputStream(new File("" + this.attachmentDirectory + "/" + afileName));
		pic.writeImageContent(out);
		out.close();
		htmlSource.append("<img src=\"" + this.attachmentPath + "/" + afileName + "\" mce_src=\"c://test//" + afileName + "\"/>").append("\n");
	}

	public boolean compareCharStyle(CharacterRun cr1, CharacterRun cr2) {
		boolean flag = false;
		if (cr1.isBold() == cr2.isBold() && cr1.isItalic() == cr2.isItalic() && cr1.getFontName().equals(cr2.getFontName()) && cr1.getFontSize() == cr2.getFontSize()) {
			flag = true;
		}
		return flag;
	}

	/**
	* 输出
	*
	* @param s
	*/
	public void write(OutputStream out) throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write(htmlSource.toString());
		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
				if (bw != null) bw.close();
			} catch (IOException ie) {}
		}
	}
}

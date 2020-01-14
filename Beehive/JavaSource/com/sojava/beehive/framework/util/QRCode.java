package com.sojava.beehive.framework.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class QRCode{

	private static final String FILE_EXT = "png";
	/**
	 * 生成二维码
	 */
	public static byte[] encode(String code, int width, int height, byte[] logo, String note) throws WriterException, IOException {

		ByteArrayOutputStream out;
		byte[] qrcode = null;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");//内容编码格式
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//指定纠错等级
		hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数
		BitMatrix bitMatrix = new MultiFormatWriter().encode(code, BarcodeFormat.QR_CODE, width, height, hints);
		out = new ByteArrayOutputStream();
		/*
			问题：生成二维码正常,生成带logo的二维码logo变成黑白
			原因：MatrixToImageConfig默认黑白，需要设置BLACK、WHITE
			解决：https://ququjioulai.iteye.com/blog/2254382
		 */
		if (logo != null) {
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
			BufferedImage bufferedImage = logo(MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig), logo);
			bufferedImage = note(bufferedImage, note);
			ImageIO.write(bufferedImage, "png", out);//输出带logo图片
		} else {
			MatrixToImageWriter.writeToStream(bitMatrix, FILE_EXT, out);
		}
		qrcode = out.toByteArray();
		out.close();

		return qrcode;
	}

	/**
	 * 识别二维码
	 */
	public static String reader(byte[] img) throws IOException, NotFoundException {

		MultiFormatReader formatReader = new MultiFormatReader();
		//读取指定的二维码文件
		ByteArrayInputStream in = new ByteArrayInputStream(img);
		BufferedImage bufferedImage = ImageIO.read(in);
		BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
		//定义二维码参数
		Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		com.google.zxing.Result result = formatReader.decode(binaryBitmap, hints);
		//输出相关的二维码信息
		bufferedImage.flush();

		return result.getText();
	}

	/**
	 * 二维码添加logo
	 * @param matrixImage 源二维码图片
	 * @param logoFile logo图片
	 * @return 返回带有logo的二维码图片
	 * 参考：https://blog.csdn.net/weixin_39494923/article/details/79058799
	 */
	public static BufferedImage logo(BufferedImage matrixImage, byte[] logoFile) throws IOException{
		/**
		 * 读取二维码图片，并构建绘图对象
		 */
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();

		/**
		 * 读取Logo图片
		 */
		ByteArrayInputStream in = new ByteArrayInputStream(logoFile);
		BufferedImage logo = ImageIO.read(in);

		//开始绘制图片
		g2.drawImage(logo, matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制
		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);// 设置笔画对象
		//指定弧度的圆角矩形
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, 20, 20);
		g2.setColor(Color.white);
		g2.draw(round);// 绘制圆弧矩形

		//设置logo 有一道灰色边框
/*
		BasicStroke stroke2 = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);// 设置笔画对象
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4, 20, 20);
		g2.setColor(new Color(128, 128, 128));
		g2.draw(round2);// 绘制圆弧矩形
*/
		g2.dispose();
		matrixImage.flush();
		in.close();

		return matrixImage;
	}

	public static BufferedImage note(BufferedImage matrixImage, String note) {

		int width = matrixImage.getWidth();
		int heigh = matrixImage.getHeight();
		BufferedImage qrImage = new BufferedImage(width, heigh + 20, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = qrImage.createGraphics();
		g2.drawImage(matrixImage, 0, 0, width, heigh, null);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, width/7));
		g2.drawString(note, width/3*0, heigh + 13);// ÷3居中；0居左

		return qrImage;
	}
}

package com.sojava.beehive.framework.message;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.geronimo.mail.util.Base64Encoder;


public class Email {

	public void send(String to, String subject, String text) throws MessagingException, Exception {
		send(null, to, subject, text, null);
	}

	public void send(String to, String subject, String text, File[] files) throws MessagingException, Exception {
		send(null, to, subject, text, files);
	}

	public void send(String from, String to, String subject, String text, File[] files) throws MessagingException, Exception {
		Session session = Session.getInstance(System.getProperties());
		MimeMessage message = new MimeMessage(session);
		//发送时间
		message.setSentDate(new Date());
		//发送人
		if (from == null) message.setFrom(); else message.setFrom(new InternetAddress(from));
		//接收人
		message.addRecipients(RecipientType.TO, to);
		//标题
		message.setSubject(subject, System.getProperty("system.encoding", "UTF-8"));
		//内容
		Multipart multipart = new MimeMultipart();
		BodyPart content = new MimeBodyPart();
		StringBuffer emailContent = new StringBuffer();
		new Base64Encoder().encodeWord(text.getBytes(), emailContent, System.getProperty("system.encoding", "UTF-8"));
		content.setContent(text, "text/html; charset=" + System.getProperty("system.encoding", "UTF-8"));
		content.setHeader("Content-Transfer-Encoding", "base64");
		multipart.addBodyPart(content);
		//附件
		if (files != null) {
			for (File file: files) {
				DataSource ds = new FileDataSource(file);
				BodyPart attachment = new MimeBodyPart();
				attachment.setDataHandler(new DataHandler(ds));
				attachment.setFileName(file.getName());
				multipart.addBodyPart(attachment);
			}
		}

		message.setContent(multipart);

		Transport.send(message);
	}

}

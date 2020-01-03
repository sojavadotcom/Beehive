package com.sojava.beehive.framework.util;

import com.sojava.beehive.framework.exception.ErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetworkUtil {

	private X509TrustManager x509 = new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
	};

	public static boolean ping(String host, Integer timeout) {
		boolean result = false;
		try {
			InetAddress inetAddress = InetAddress.getByName(host);
			result = true;
			if (timeout != null) result = inetAddress.isReachable(timeout);
		}
		catch (UnknownHostException ex) {}
		catch (IOException ex) {}

		return result;
	}

	public String getHttpsResponse(String hsUrl,String requestMethod) throws ErrorException {
		URL url;
		InputStream is = null;
		String resultData = "";
		try {
			url = new URL(hsUrl);
			HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
			TrustManager[] tm = {x509};

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tm, null);

			connect.setSSLSocketFactory(ctx.getSocketFactory());
			connect.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			connect.setDoInput(true); //允许输入流，即允许下载

			//在android中必须将此项设置为false
			connect.setDoOutput(false); //允许输出流，即允许上传
			connect.setUseCaches(false); //不使用缓冲
			if(null != requestMethod && !requestMethod.equals("")) {
				connect.setRequestMethod(requestMethod); //使用指定的方式
			} else {
				connect.setRequestMethod("GET"); //使用get请求
			}
			is = connect.getInputStream(); //获取输入流，此时才真正建立链接
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bufferReader = new BufferedReader(isr);
			String inputLine = "";
			while ((inputLine = bufferReader.readLine()) != null) {
				resultData += inputLine + "\n";
			}

/*
			Certificate[] certs = connect.getServerCertificates();
			int certNum = 1;
	
			for (Certificate cert : certs) {
				X509Certificate xcert = (X509Certificate) cert;
			}
*/
		} catch (Exception ex) {
			throw new ErrorException(getClass(), ex);
		}
		return resultData;
	}
}

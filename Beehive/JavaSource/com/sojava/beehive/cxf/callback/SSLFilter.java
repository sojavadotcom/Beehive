package com.sojava.beehive.cxf.callback;

import javax.security.auth.x500.X500Principal;
import javax.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.bus.CXFBusImpl;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class SSLFilter extends AbstractPhaseInterceptor<Message> {

	private CXFBusImpl bus;

	public SSLFilter() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message msg) throws Fault {
		HttpServletRequest request = (HttpServletRequest) msg.get(AbstractHTTPDestination.HTTP_REQUEST);
		if (request == null) return;

		String certSubject = null;
		X509Certificate[] certChain = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
		if (certChain != null) {
			int len = certChain.length;
			if (len > 0) {
				X509Certificate cert = (X509Certificate) certChain[0];
				X500Principal pSubject = (X500Principal) cert.getSubjectDN();
				certSubject = pSubject.getName();

				System.out.println(certSubject);
			}
		}
	}

	public CXFBusImpl getBus() {
		return bus;
	}

	public void setBus(CXFBusImpl bus) {
		this.bus = bus;
	}

}

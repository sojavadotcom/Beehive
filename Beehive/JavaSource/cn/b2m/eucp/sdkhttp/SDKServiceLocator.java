/**
 * SDKServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.b2m.eucp.sdkhttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

public class SDKServiceLocator extends Service implements SDKService {

    /**
	 * 
	 */
	private static final long serialVersionUID = -104836713479697273L;

	public SDKServiceLocator() {
    }


    public SDKServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SDKServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SDKService
    private String SDKService_address = "http://sdkhttp.eucp.b2m.cn:80/sdk/SDKService";

    public String getSDKServiceAddress() {
        return SDKService_address;
    }

    // The WSDD service name defaults to the port name.
    private String SDKServiceWSDDServiceName = "SDKService";

    public String getSDKServiceWSDDServiceName() {
        return SDKServiceWSDDServiceName;
    }

    public void setSDKServiceWSDDServiceName(String name) {
        SDKServiceWSDDServiceName = name;
    }

    public SDKClient getSDKService() throws ServiceException {
       URL endpoint;
        try {
            endpoint = new URL(SDKService_address);
        }
        catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getSDKService(endpoint);
    }

    public SDKClient getSDKService(URL portAddress) throws ServiceException {
        try {
            SDKServiceBindingStub _stub = new SDKServiceBindingStub(portAddress, this);
            _stub.setPortName(getSDKServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSDKServiceEndpointAddress(String address) {
        SDKService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (SDKClient.class.isAssignableFrom(serviceEndpointInterface)) {
                SDKServiceBindingStub _stub = new SDKServiceBindingStub(new URL(SDKService_address), this);
                _stub.setPortName(getSDKServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new ServiceException(t);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SDKService".equals(inputPortName)) {
            return getSDKService();
        }
        else  {
            Remote _stub = getPort(serviceEndpointInterface);
            ((Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public QName getServiceName() {
        return new QName("http://sdkhttp.eucp.b2m.cn/", "SDKService");
    }

    @SuppressWarnings("rawtypes")
	private HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator getPorts() {
        if (ports == null) {
            ports = new HashSet();
            ports.add(new QName("http://sdkhttp.eucp.b2m.cn/", "SDKService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws ServiceException {
        
if ("SDKService".equals(portName)) {
            setSDKServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(QName portName, String address) throws ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

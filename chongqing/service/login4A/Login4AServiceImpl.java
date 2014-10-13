package service.login4A;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.springframework.stereotype.Service;


/**
 * <p>Title: Login4AServiceImpl</p>
 * <p>Description: 4A登录service</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-17 上午10:55:57
 *
 */
@Service("Login4AService")
public class Login4AServiceImpl implements Login4AService {

	@Override
	public String validateTokenBy4A(String[] receiveInfo) throws ServiceException, RemoteException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
		buffer.append("<USERREQ><HEAD><CODE></CODE><SID></SID>");
		buffer.append("<TIMESTAMP>").append(timestamp).append("</TIMESTAMP>");
		buffer.append("<SERVICEID>CQCLOUD</SERVICEID>");
		buffer.append("</HEAD><BODY>");
		buffer.append("<APPACCTID>").append(receiveInfo[1]).append("</APPACCTID>");
		buffer.append("<TOKEN>").append(receiveInfo[0]).append("</TOKEN>");
		buffer.append("</BODY></USERREQ>");
		String xml = buffer.toString();
		System.out.println(xml);
		// FIXME 4AWebService地址
//		String url = "http://localhost:8080/cloud/WebService/userinfoManageFor4A?wsdl";
		String url ;
		if(null!=receiveInfo[2]&&"1".equals(receiveInfo[2])){
			url = URLFLAG[0];
		} else {
			url = URLFLAG[1];
		}
		String method = "CheckAiuapTokenSoap";
		String parameterName = "RequestInfo";
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		Call call;
		String msg = null;
		try {
			call = (Call) service.createCall();
			// 设置超时
			call.setTimeout(60*1000);
			call.setTargetEndpointAddress(url);
			QName qName = new QName(method, method);
			call.setOperationName(qName);
			call.setUseSOAPAction(true);
			call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			msg = call.invoke(new Object[]{xml}).toString();
			System.out.println(msg);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
		return msg;
	}

}

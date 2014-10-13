package service.login4A;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

/**
 * <p>Title: Login4AService</p>
 * <p>Description: 4A登录service</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-17 上午10:37:30
 *
 */
public interface Login4AService {
	
	/**
	 * 验证token的地址，根据flag的不同，地址不同
	 * [0]正常；[1]应急
	 */
	public static final String[] URLFLAG = {"http://10.191.106.39:9081/uap/services/CheckAiuapTokenSoap?wsdl"
		,"http://10.191.106.39:9081/uap/services/CheckAiuapTokenSoap?wsdl"};


	/**
	 * 
	 * @Title: validateTokenBy4A
	 * @Description: 将拿到的信息去4A校验
	 * @param receiveInfo [0]token;[1]appAccid;[2]flag
	 * @return String 收到的返回报文
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @createtime 2014-5-17 上午10:40:10
	 */
	public String validateTokenBy4A(String [] receiveInfo) throws ServiceException, RemoteException;
	
}

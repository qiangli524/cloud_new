package service.tbSysUserinfoFor4AService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;



/**
 * @description 
 * @author chenyu
 * @date 上午10:47:41
 */
@WebService(targetNamespace="UpdateAppAcctSoap")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface TbSysUserinfoFor4AWebService {

	public final static String INSERT = "add";
	public final static String UPDATE = "change";
	public final static String DELETE = "delete";
	public final static String CHGSTATUS = "chgstatus";
	public final static String RESETPWD = "resetpwd";
	
	// 校验不通过返回
	public final static int TYPEERROR = 0;
	// 执行结果返回
	public final static int TYPERESULT = 1;

	
	/**
	 * 处理4A过来的操作（add、delete、change、chgstatus、resetpwd）新增、删除、变更、加/解锁、重置密码
	 * @author chenyu:下午7:22:15
	 * @param requestInfo
	 * @return
	 */
	@WebMethod
	public String UpdateAppAcctSoap(@WebParam(name = "RequestInfo") String requestInfo);
}

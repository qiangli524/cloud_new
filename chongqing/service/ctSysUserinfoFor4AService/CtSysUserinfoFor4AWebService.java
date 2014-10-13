package service.ctSysUserinfoFor4AService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="CtSysUserinfoFor4AWebServiceImplService",targetNamespace="http://ctSysUserinfoFor4AService.service/")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public interface CtSysUserinfoFor4AWebService {
	
	@WebMethod
    public String findUser(@WebParam(name = "userID") String userID);
	
	/*
	@WebMethod
	public String getUserAmount();
	*/
	
	@WebMethod
	public String queryUsers();
	
	/*
	@WebMethod
	public String queryUsersByPage(@WebParam(name = "pageSize") String pageSize,@WebParam(name = "pageNum") String pageNum);
	*/
	
	/*
	@WebMethod
	public String queryOrgs();
	*/
	
	@WebMethod
	public String addUserInfo(@WebParam(name = "userInfos") String userInfos);
	
	@WebMethod
	public String modifyUserInfo(@WebParam(name = "userInfos") String userInfos);
	
	@WebMethod
	public String delUser(@WebParam(name = "userIDs") String userIDs);
	

}

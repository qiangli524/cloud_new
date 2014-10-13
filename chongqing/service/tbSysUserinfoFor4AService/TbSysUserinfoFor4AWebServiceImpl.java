package service.tbSysUserinfoFor4AService;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.uap.util.des.EncryptInterface;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.ssd.sx.aaaa.util.JAXBMarshaller;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;
import com.sitech.utils.encrypt.BASE64Util;

import domain.tbSysUserinfo4A.RequestBody;
import domain.tbSysUserinfo4A.RequestHead;
import domain.tbSysUserinfo4A.ResponseBody;
import domain.tbSysUserinfo4A.ResponseHead;
import domain.tbSysUserinfo4A.UserInfo;
import domain.tbSysUserinfo4A.UserModifyRSP;
import domain.tbSysUserinfo4A.UserModifyReq;

/**
 * @description 
 * @author chenyu
 * @date 下午3:08:25
 */
@Service("userinfoManageFor4A")
@WebService(endpointInterface="service.tbSysUserinfoFor4AService.TbSysUserinfoFor4AWebService"
,targetNamespace="UpdateAppAcctSoap")
public class TbSysUserinfoFor4AWebServiceImpl implements
		TbSysUserinfoFor4AWebService {

	@Autowired
	private TbSysUserinfoDao userinfoDao;
	



	@SuppressWarnings("finally")
	@Override
	public String UpdateAppAcctSoap(String requestInfo) {
		String returnString = null;
		String errorMsg = null;

		UserModifyReq req = new UserModifyReq();
		// 校验帐号密码
		try {
			req = JAXBUnmarshaller.xml2java(UserModifyReq.class, requestInfo);
			System.out.println("req===="+requestInfo);
			RequestBody body = req.getBody();

			String modifyMode = body.getModifyMode();
			boolean flag = validateOperator(body);
			if(!flag){
				throw new RuntimeException("account or password error!");
			}
			flag = validateAuthority(body);
			if(!flag){
				throw new RuntimeException("authority error");
			}
			
			// 帐号密码正确，且有操作用户的权限
			if(INSERT.equals(modifyMode)){
				returnString = insertSysUserFor4A(req);
			} else if (UPDATE.equals(modifyMode)){
				returnString = updateSysUserFor4A(req);
			} else if (DELETE.equals(modifyMode)){
				returnString = deleteSysUserFor4A(req);
			} else if (CHGSTATUS.equals(modifyMode)){
				returnString = changeStatusFor4A(req);
			} else if (RESETPWD.equals(modifyMode)){
				returnString = resetPwdFor4A(req);
			} else {
				throw new RuntimeException("operation error");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
			returnString = createReturnMsg(req,0,TYPEERROR,errorMsg,0);
		} finally{
			System.out.println(requestInfo);
			return returnString;
		}
		
	}
	
	

	@SuppressWarnings("finally")
	private String insertSysUserFor4A(UserModifyReq req) {
		String errorMsg = "";
		int id = 0;
		int success = 0;
		int type = TYPERESULT;
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
			objectConvert(req, userinfoObj);
			TbSysUserinfoObj userinfoObj_t = userinfoDao.queryByObj(userinfoObj);
			if(null!=userinfoObj_t){
				success=1;
				errorMsg = "login_no exist";
			} else {
				userinfoDao.insertByObj(userinfoObj);
				userinfoObj_t = new TbSysUserinfoObj();
				userinfoObj_t.setACCOUNT(userinfoObj.getACCOUNT());
				userinfoObj_t = userinfoDao.queryByObj(userinfoObj);
				id =  userinfoObj_t.getID();
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in insert:"+e.getMessage();
			success = 1;
			type = TYPEERROR;
		} finally{
			return createReturnMsg(req,success,type,errorMsg,id);
		}
	}
	
	@SuppressWarnings("finally")
	private String updateSysUserFor4A(UserModifyReq req) {

		String errorMsg ="";
		int id = 0;
		int success = 0;
		int type = TYPERESULT;
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
			objectConvert(req, userinfoObj);
			TbSysUserinfoObj userinfoObj_t = new TbSysUserinfoObj();
			userinfoObj_t.setID(userinfoObj.getID());
			userinfoObj_t = userinfoDao.queryByObj(userinfoObj_t);
			if(null==userinfoObj_t){
				success=1;
				errorMsg = "userid not exist";
			} else {
				userinfoObj.setPASSWORD(userinfoObj_t.getPASSWORD());
				userinfoObj_t.setNAME(userinfoObj.getNAME());
				userinfoObj_t.setEMAIL(userinfoObj.getEMAIL());
				userinfoObj_t.setMOBILE(userinfoObj.getMOBILE());
				userinfoDao.updateByObj(userinfoObj_t);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in change:"+e.getMessage();
			success = 1;
			type = TYPEERROR;
		} finally{
			return createReturnMsg(req,success,type,errorMsg,id);
		}
	}

	@SuppressWarnings("finally")
	private String deleteSysUserFor4A(UserModifyReq req) {
		UserInfo userinfo = req.getBody().getUserInfo();

		String errorMsg ="";
		int id = 0;
		int success = 0;
		int type = TYPERESULT;
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
			userinfoObj.setID(Integer.parseInt(userinfo.getUserId()));
			userinfoDao.deleteByObj(userinfoObj);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in delete :"+e.getMessage();
			success = 1;
			type = TYPEERROR;
		} finally{
			return createReturnMsg(req,success,type,errorMsg,id);
		}
	}

	@SuppressWarnings("finally")
	private String changeStatusFor4A(UserModifyReq req){
		UserInfo userinfo = req.getBody().getUserInfo();
		String errorMsg ="";
		int id = 0;
		int success = 0;
		int type = TYPERESULT;
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
			userinfoObj.setID(Integer.parseInt(userinfo.getUserId()));
			System.out.println(userinfo.getUserId());
			userinfoObj = userinfoDao.queryByObj(userinfoObj);
			if(null==userinfoObj){
				success=1;
				errorMsg = "login not exist";
			} else {
				userinfoObj.setSTATUS(Integer.parseInt(userinfo.getStatus()));
				userinfoDao.updateByObj(userinfoObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in changestatus:"+e.getMessage();
			success = 1;
			type = TYPEERROR;
		} finally{
			return createReturnMsg(req,success,type,errorMsg,id);
		}
	}
	
	@SuppressWarnings("finally")
	private String resetPwdFor4A(UserModifyReq req){
		UserInfo userinfo = req.getBody().getUserInfo();
		String errorMsg ="";
		int id = 0;
		int success = 0;
		int type = TYPERESULT;
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
			userinfoObj.setID(Integer.parseInt(userinfo.getUserId()));
			userinfoObj = userinfoDao.queryByObj(userinfoObj);
			if(null==userinfoObj){
				success=1;
				errorMsg = "login not exist";
			} else {
				String password = userinfo.getPassWord();
				password = EncryptInterface.desUnEncryptData(password);
				password = BASE64Util.encode(password);
				userinfoObj.setPASSWORD(password);
				userinfoDao.updateByObj(userinfoObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in resetpwd:"+e.getMessage();
			success = 1;
			type = TYPEERROR;
		} finally{
			return createReturnMsg(req,success,type,errorMsg,id);
		}
	}
	
	
	/**
	 * 校验管理员的权限
	 * @author chenyu:下午4:16:40
	 * @param userInfo
	 * @return 有权限，返回true;否则false
	 */
	private boolean validateAuthority(RequestBody body){
		// FIXME chenyu
		return true;
	}
	
	/**
	 * 校验管理者的帐号和密码是否正确，如果没有帐号和密码会抛出runtimeException
	 * @author chenyu:下午4:18:15
	 * @param userInfo
	 * @return 正确，返回true;否则false
	 */
	private boolean validateOperator(RequestBody body) {
		if (null == body) {
			throw new RuntimeException("no body");
		}
		String operator = body.getOperator();
		String pwd = body.getOperatorPwd();
		if (null == operator) {
			throw new RuntimeException("no OPERATORID");
		}
		if (null == pwd) {
			throw new RuntimeException("no OPERATORPWD");
		}
		if ("".equals(operator.trim())) {
			throw new RuntimeException("no OPERATORID");
		}
		if ("".equals(pwd.trim())) {
			throw new RuntimeException("no OPERATORPWD");
		}
		TbSysUserinfoObj userObj = new TbSysUserinfoObj();
		userObj.setACCOUNT(operator);
//		userObj.setPASSWORD(pwd);
		TbSysUserinfoObj obj = userinfoDao.queryByObj(userObj);
		boolean flag = false;
		if(null==obj){
			
		} else {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * 对象转换
	 * @author chenyu:下午7:32:00
	 * @param req
	 * @param userinfoObj
	 */
	private void objectConvert(UserModifyReq req,TbSysUserinfoObj userinfoObj){
		UserInfo userinfo = req.getBody().getUserInfo();
		RequestHead head = req.getHead();
		if(null!=userinfo.getUserId()&&!"".equals(userinfo.getUserId().trim())){
			userinfoObj.setID(Integer.parseInt(userinfo.getUserId()));
		}
		userinfoObj.setACCOUNT(userinfo.getLoginNo());
		userinfoObj.setPASSWORD(userinfo.getPassWord());
		userinfoObj.setNAME(userinfo.getUserName());
		userinfoObj.setMOBILE(userinfo.getMobile());
		userinfoObj.setEMAIL(userinfo.geteMail());
		userinfoObj.setSTATUS(null==userinfo.getStatus()?0:Integer.parseInt(userinfo.getStatus()));
		userinfoObj.setCREATEUSER(null==head.getSid()?0:Integer.parseInt(head.getSid()));
		userinfoObj.setDATAAUTHORITY(null);
		userinfoObj.setDomain("domain.type.IT");
		
	}

	/**
	 * 
	 * @Title: a
	 * @Description: 生成返回报文,新增操作时
	 * @param req 4a原对象
	 * @param result 0成功，非0，失败
	 * @param returnType 返回原因,参见TbSysUserinfoFor4AWebService静态字段
	 * @param errorMsg 错误信息
	 * @param id 用户标识,新增的才有效
	 * @return String
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-5-16 下午3:06:42
	 */
	private String createReturnMsg(UserModifyReq req,int result,int returnType,String errorMsg,int id){

		System.out.println("errorMsg:" + errorMsg);
		UserModifyRSP rsp = new UserModifyRSP();
		UserInfo userinfo = req.getBody().getUserInfo();
		RequestHead reqHead = req.getHead();
		RequestBody reqBody = req.getBody();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		
		
		ResponseHead head = new ResponseHead();
		BeanUtils.copyProperties(reqHead, head);
		head.setCode("");
		head.setSid("");
		head.setTimestamp(timestamp);
		rsp.setHead(head);
		
		ResponseBody body = new ResponseBody();
		// 校验不通过返回
		if(returnType==TYPEERROR){
			body.setKey(userinfo.getLoginNo());
			body.setErrCode("");
		} else if(returnType==TYPERESULT){
			body.setModifyMode(reqBody.getModifyMode());
			if (null != userinfo.getUserId()
					&& !"".equals(userinfo.getUserId().trim())) {
				body.setUserId(userinfo.getUserId());
			} else {
				body.setUserId(String.valueOf(id));
			}
			body.setLoginNo(userinfo.getLoginNo());
			body.setRsp(String.valueOf(result));
		}
		body.setErrDesc(errorMsg);
		rsp.setBody(body);
		String msg = JAXBMarshaller.java2xmlNoStandalone(rsp);
		System.out.println(msg);
		return msg;
	}
	
	
}

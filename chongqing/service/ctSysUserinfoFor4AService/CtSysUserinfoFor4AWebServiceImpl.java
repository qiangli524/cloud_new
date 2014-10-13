package service.ctSysUserinfoFor4AService;


import java.util.List;


import javax.jws.WebService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;



import domain.ctSysUserinfo4A.UserInfo;


import com.thoughtworks.xstream.XStream;   
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.uap.util.des.EncryptInterface;
import com.sitech.ssd.sx.aaaa.util.JAXBMarshaller;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;
import com.sitech.utils.encrypt.BASE64Util;

@Service("userinfoManageForCT4A")
@WebService(endpointInterface="service.ctSysUserinfoFor4AService.CtSysUserinfoFor4AWebService",targetNamespace="http://ctSysUserinfoFor4AService.service/")
public class CtSysUserinfoFor4AWebServiceImpl implements  CtSysUserinfoFor4AWebService {

	
	@Autowired
	private TbSysUserinfoDao userinfoDao;
	
	
	public CtSysUserinfoFor4AWebServiceImpl() {

    }
	
	private void objectConvert(TbSysUserinfoObj userinfoObj,UserInfo userinfo){
		
		
		userinfo.setAccId(userinfoObj.getACCOUNT());
		userinfo.setUserPasswordMD5(userinfoObj.getPASSWORD());
		userinfo.setuserPasswordSHA1("");
		userinfo.setName(userinfoObj.getNAME());
		userinfo.setEmail(userinfoObj.getEMAIL());
		userinfo.setGender("");
		userinfo.setMobile(userinfoObj.getMOBILE());
		userinfo.setIdCardNumber("");
      }
	
	
   private void objectConvert2(UserInfo userinfo,TbSysUserinfoObj userinfoObj){
	
	
		
	userinfoObj.setACCOUNT(userinfo.getAccId());
	userinfoObj.setPASSWORD(userinfo.getUserPasswordMD5());
	userinfoObj.setNAME(userinfo.getName());
	userinfoObj.setMOBILE(userinfo.getMobile());
	userinfoObj.setEMAIL(userinfo.getEmail());
	userinfoObj.setSTATUS(0);
	userinfoObj.setCREATEUSER(8888);
	userinfoObj.setDATAAUTHORITY(null);
	userinfoObj.setDomain("domain.type.CT");
	
     }
	

	@Override
	public String findUser(String userID) {
		
		    System.out.println("单个从账号查询接口" + userID);
		    String tempStr=""; 
	        StringBuffer buffer = new StringBuffer();
	        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        buffer.append("\n");
	        buffer.append("<accounts>");
	        buffer.append("\n");
	        
	        try {
	        UserInfo userinfo = new UserInfo();
	        TbSysUserinfoObj userinfoObj_t = new TbSysUserinfoObj();
			userinfoObj_t.setACCOUNT(userID);
			userinfoObj_t.setDomain("domain.type.CT");
			userinfoObj_t = userinfoDao.queryByObj(userinfoObj_t);
			
			 if (userinfoObj_t != null) {
				 
				
	        	
				 XStream xs=new XStream();
	        	 
	        	 /*类重命名*/
	        	 xs.alias("account", UserInfo.class); 
	        	 
	        	 /*属性注册*/
	        	/* xs.useAttributeFor(UserInfo.class, "accId");   
	             xs.useAttributeFor(UserInfo.class,"userPasswordMD5");  
	             xs.useAttributeFor(UserInfo.class, "name");   
	             xs.useAttributeFor(UserInfo.class,"email");  
	             xs.useAttributeFor(UserInfo.class, "gender");   
	             xs.useAttributeFor(UserInfo.class, "mobile");   
	             xs.useAttributeFor(UserInfo.class, "idCardNumber");   */
	             
	             
	       
	             
	             /*包重命名*/
	             /* xs.aliasPackage("", "");*/
	             
	             /*属性重命名*/
	             /* xs.aliasField("accId", UserInfo.class, "loginNo");
	             xs.aliasField("userPasswordMD5", UserInfo.class, "passWord");
	             xs.aliasField("name", UserInfo.class, "userName");
	             xs.aliasField("email", UserInfo.class, "eMail"); */
	             objectConvert(userinfoObj_t,userinfo);
	             tempStr=xs.toXML(userinfo);
	             buffer.append(tempStr); 
	 	         buffer.append("\n");
	 	         buffer.append("</accounts>");
	             
	         
	        } else {
	        
	        	buffer.append("</accounts>");
	        	
	        }
	        
	        }catch (Exception e) {
					e.printStackTrace();
					} 
	        
	        System.out.println("单个从账号查询结果" + buffer.toString());
	        return buffer.toString();
	}

	/*
	@Override
	public String getUserAmount() {
		// TODO Auto-generated method stub
		return null;
	}
    */
	
	
	@Override
	public String queryUsers() {
		    System.out.println("查询所有从帐号接口");
		    String tempStr=""; 
	        StringBuffer buffer = new StringBuffer();
		    
		    XStream xs=new XStream();
		    xs.alias("account", UserInfo.class); 
		    
	        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        buffer.append("\n");
	        buffer.append("<accounts>");
	        buffer.append("\n");
		    
	        TbSysUserinfoObj userObj = new TbSysUserinfoObj();
	        userObj.setDomain("domain.type.CT");
		    List<TbSysUserinfoObj> objList = userinfoDao.queryForListByObj(userObj);
	
		for (TbSysUserinfoObj obj : objList) {
			UserInfo userinfo = new UserInfo();
	        objectConvert(obj,userinfo);
            tempStr=xs.toXML(userinfo);
            buffer.append(tempStr); 
            buffer.append("\n");
            }
		    buffer.append("</accounts>");
	      
		    return buffer.toString();
		
	}

	/*
	@Override
	public String queryUsersByPage(String pageSize, String pageNum) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	/*
	@Override
	public String queryOrgs() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public String addUserInfo(String userInfos) {
		
		String errorMsg = "";
		System.out.println("账号添加接口" + userInfos);
		
		XStream xs=new XStream(new DomDriver());  
		UserInfo userinfo1 = new UserInfo();
		xs.alias("account", UserInfo.class); 
		userInfos=userInfos.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
		userInfos=userInfos.replace("<accounts>","");
		userInfos=userInfos.replace("</accounts>","");
		//userInfos=userInfos.replace("<userPasswordSHA1>M3dkdm41RjJaMzBTZlhhalhpaXhPNXZURTc0PQ==</userPasswordSHA1>","");
		userinfo1=(UserInfo)xs.fromXML(userInfos); 
		
		//userinfo1 = JAXBUnmarshaller.xml2java(UserInfo.class, userInfo);
		//System.out.println("req===="+userInfo);
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
		objectConvert2(userinfo1, userinfoObj);
		
		TbSysUserinfoObj userinfoObj_t = userinfoDao.queryByObj(userinfoObj);
		if(null!=userinfoObj_t){
			
			
			errorMsg = "账号已存在";
			buffer.append("<results>");
			buffer.append("<result returncode=\"1301\">");
			buffer.append("<accId>");
			buffer.append(userinfoObj.getACCOUNT());
			buffer.append("</accId>");
			buffer.append("</result>");
			buffer.append("<errorMsg>");
			buffer.append(errorMsg);
			buffer.append("</errorMsg>");
			buffer.append("</results>");
			
			
		} else {
			
			try {
			userinfoDao.insertByObj(userinfoObj); 
			}catch (Exception e) { 
			e.printStackTrace();
			errorMsg = "error in insert:"+e.getMessage();  
			}
			
			
			buffer.append("<results>");
			buffer.append("<result returncode=\"1300\">");
			buffer.append("<accId>");
			buffer.append(userinfoObj.getACCOUNT());
			buffer.append("</accId>");
			buffer.append("</result>");
			buffer.append("<errorMsg>");
			buffer.append(errorMsg);
			buffer.append("</errorMsg>");
			buffer.append("</results>");
			
		}
		 System.out.println("账号添加结果" + buffer.toString());
		 return buffer.toString();
	
		
	}

	@Override
	public String modifyUserInfo(String userInfos) {
        
		String errorMsg = "";
		System.out.println("账号修改接口" + userInfos);
		XStream xs=new XStream(new DomDriver());  
		UserInfo userinfo = new UserInfo();
		xs.alias("account", UserInfo.class); 
		userInfos=userInfos.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>",""); 
		userInfos=userInfos.replace("<accounts>","");
		userInfos=userInfos.replace("</accounts>","");
		//userInfos=userInfos.replace("<userPasswordSHA1>M3dkdm41RjJaMzBTZlhhalhpaXhPNXZURTc0PQ==</userPasswordSHA1>","");
        userinfo=(UserInfo)xs.fromXML(userInfos); 
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
		objectConvert2(userinfo, userinfoObj);
		
		TbSysUserinfoObj userinfoObj_t = new TbSysUserinfoObj();
		userinfoObj_t.setACCOUNT(userinfoObj.getACCOUNT());
		userinfoObj_t = userinfoDao.queryByObj(userinfoObj_t);
		

		if(null==userinfoObj_t){
			
			
			errorMsg = "账号不存在";
			buffer.append("<results>");
			buffer.append("<result returncode=\"1303\">");
			buffer.append("<accId>");
			buffer.append(userinfoObj.getACCOUNT());
			buffer.append("</accId>");
			buffer.append("</result>");
			buffer.append("<errorMsg>");
			buffer.append(errorMsg);
			buffer.append("</errorMsg>");
			buffer.append("</results>");
			
			
		} else {
			
			try {
				userinfoObj_t.setPASSWORD(userinfoObj.getPASSWORD());
				userinfoObj_t.setNAME(userinfoObj.getNAME());
				userinfoObj_t.setEMAIL(userinfoObj.getEMAIL());
				userinfoObj_t.setMOBILE(userinfoObj.getMOBILE());
				userinfoDao.updateByObj(userinfoObj_t);
			}catch (Exception e) { 
			e.printStackTrace();
			errorMsg = "error in insert:"+e.getMessage();  
			}
			
			
			buffer.append("<results>");
			if(errorMsg.trim().equals("")){
				buffer.append("<result returncode=\"1302\">");
				}else { 
			    buffer.append("<result returncode=\"1303\">"); 
				}
			buffer.append("<accId>");
			buffer.append(userinfoObj.getACCOUNT());
			buffer.append("</accId>");
			buffer.append("</result>");
			buffer.append("<errorMsg>");
			buffer.append(errorMsg);
			buffer.append("</errorMsg>");
			buffer.append("</results>");
			
		}
		System.out.println("账号修改结果" + buffer.toString());
		 return buffer.toString();
	}

	@Override
	public String delUser(String userIDs) {
		
         String errorMsg = "";
         System.out.println("账号删除接口before" + userIDs);
         userIDs=userIDs.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
         userIDs=userIDs.replace("<accounts>","");
         userIDs=userIDs.replace("</accounts>","");
         userIDs=userIDs.replace("<accId>","");
         userIDs=userIDs.replace("</accId>","");
		 System.out.println("账号删除接口after" + userIDs);
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	
		try {
			TbSysUserinfoObj userinfoObj = new TbSysUserinfoObj();
		
			TbSysUserinfoObj userinfoObj_t = new TbSysUserinfoObj();
			userinfoObj_t.setACCOUNT(userIDs);
			userinfoObj_t = userinfoDao.queryByObj(userinfoObj_t);
			System.out.println("userinfoObj_t.getID():" + userinfoObj_t.getID());
			userinfoObj.setID(userinfoObj_t.getID());
			userinfoDao.deleteByObj(userinfoObj);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "error in delete :"+e.getMessage();
			
		} 
		
		buffer.append("<results>");
		if(errorMsg.trim().equals("")){
		buffer.append("<result returncode=\"1304\">");
		}else { 
	    buffer.append("<result returncode=\"1305\">"); 
		}
		buffer.append("<accId>");
		buffer.append(userIDs);
		buffer.append("</accId>");
		buffer.append("</result>");
		buffer.append("<errorMsg>");
		buffer.append(errorMsg);
		buffer.append("</errorMsg>");
		buffer.append("</results>");
		System.out.println("账号删除结果" + buffer.toString());
	    return buffer.toString();
		
	}
	
	
}

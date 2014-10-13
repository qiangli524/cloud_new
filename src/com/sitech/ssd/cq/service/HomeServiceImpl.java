/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.cq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysGrpmemberDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.util.session.UserSession;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Jun 20, 2014,10:46:56 AM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Service("homeService")
public class HomeServiceImpl implements HomeService {
	@Resource
	private FunctionsService functionsService;
	@Autowired
	private TbSysGrpmemberDao tbSysGrpmemberDao;// 用于查询用户组

	private static final String ROLE_ID = "${roleId}";// 用户组ID
	
	private static final String LOG_USER_NAME = "${logUserName}";// 用户名session.get("account").toString();如：admin
	/**
	 *
	 * @see com.sitech.ssd.cq.service.HomeService#getMenus(com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj)
	 */
	@Override
	public List getMenus(FuncRoleObj funcRoleObj) {
		List list = new ArrayList();
		List<TbSysFunctionsObj> listTree = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		List<TbSysFunctionsObj> fList = this.getSubMenus(listTree,"\\d{2}0{8}");
		for(TbSysFunctionsObj obj1:fList){
			/**
			 * 当菜单url中包括${logUserName}和${roleId}时, 
			 * 替换${logUserName}为当前登陆账号如admin,替换${roleId}为用户组ID(多个用户组Id用","隔开,最后的","会被截取掉)
			 */
			obj1 = this.replaceUrl(obj1);
			Map map1 = new HashMap();
			map1.put("id", obj1.getFUNCID());
			map1.put("name", obj1.getFUNNAME());
			map1.put("url", obj1.getFUNCREQUEST());
			map1.put("id_p", obj1.getID());
			String str1 = obj1.getFUNCID().substring(0, 2);
			List<TbSysFunctionsObj> sList = this.getSubMenus(listTree,str1+"\\d{2}0{6}");
			List list2 = new ArrayList();
			for(TbSysFunctionsObj obj2:sList){
				obj2 = this.replaceUrl(obj2);
				Map map2 = new HashMap();
				map2.put("id", obj2.getFUNCID());
				map2.put("name", obj2.getFUNNAME());
				map2.put("url", obj2.getFUNCREQUEST());
				map2.put("id_p", obj2.getID());
				String str2 = obj2.getFUNCID().substring(0, 4);
				List<TbSysFunctionsObj> tList = this.getSubMenus(listTree,str2+"\\d{2}0{4}");
				List list3 = new ArrayList();
				for(TbSysFunctionsObj obj3:tList){
					obj3 = this.replaceUrl(obj3);
					Map map3 = new HashMap();
					map3.put("id", obj3.getFUNCID());
					map3.put("name", obj3.getFUNNAME());
					map3.put("url", obj3.getFUNCREQUEST());
					map3.put("id_p", obj3.getID());
					list3.add(map3);
				}
				map2.put("list", list3);
				list2.add(map2);
			}
			map1.put("list", list2);
			list.add(map1);
		}
		return list;
	}
	/**
	 * 
	 * <p></p>
	 * 
	 * @param listTree
	 * @return
	 */
	private List<TbSysFunctionsObj> getSubMenus(List<TbSysFunctionsObj> listTree,String regex){
		List<TbSysFunctionsObj> list = new ArrayList<TbSysFunctionsObj>();
		for(TbSysFunctionsObj func:listTree){
			String funcid = func.getFUNCID();
			Pattern p = Pattern.compile(regex);  
			Matcher m = p.matcher(funcid);  
			if(m.matches()){
				list.add(func);
			}
		}
		for(TbSysFunctionsObj func:list){			
			listTree.remove(func);
		}
		return list;
	}
	
	public static void main (String[] args){
		String xx = "1402020000";
		Pattern p = Pattern.compile("1402\\d{2}0{41}");  
		  
		Matcher m = p.matcher(xx);  
		System.out.println(m.matches());
	}
	
	/**
	 * 
	 * @Title: replaceUrl
	 * @Description: 	
	 * 		 start 2014.08.23 by yanggl 当菜单url中包括${logUserName}和${roleId}时
	 *		  替换${logUserName}为当前登陆账号如admin,替换${roleId}为用户组ID(多个用户组Id用","隔开,最后的","会被截取掉)
	 *		  如：url是tab_linkTableConfig.do?logUserName=${logUserName}&roleId=${roleId}
	 * @param
	 * @return TbSysFunctionsObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-8-29 上午9:59:00
	 */
	private TbSysFunctionsObj replaceUrl(TbSysFunctionsObj obj){
		HttpSession session = UserSession.getHttpSession();
		String userId = session.getAttribute("id").toString();// 从session中获取当前用户id
		String logUserName = session.getAttribute("account").toString();// 从session中获取当前登陆账号如：admin
		//如果连接中包含ROLE_ID和LOG_USER_NAME(页面手动输入的url,在功能管理中输入的url)
		if(obj.getFUNCREQUEST() != null && !"".equals(obj.getFUNCREQUEST())){
			if (obj.getFUNCREQUEST().contains(ROLE_ID)
					&& obj.getFUNCREQUEST().contains(LOG_USER_NAME)) {
				String userGroupId = "";
				TbSysGrpmemberObj tbSysGrpmemberObj = new TbSysGrpmemberObj();
				tbSysGrpmemberObj.setUSERID(Integer.parseInt(userId));
				// 查询tb_sys_grpmember(存放userId和userGroupId的关系)
				List<TbSysGrpmemberObj> userGroupList = tbSysGrpmemberDao
						.queryForListByObj(tbSysGrpmemberObj);
				for (TbSysGrpmemberObj memberObj : userGroupList) {
					userGroupId += memberObj.getGROUPID() + ",";// 用户可能存在多个用户组中，多个用户组Id用","隔开
				}
				if (userGroupId != null && !"".equals(userGroupId)) {
					String groupId = userGroupId.substring(0, userGroupId.length() - 1);// 去掉最后一位的","
					String funr = obj.getFUNCREQUEST().replace("＆","&");//特殊字符转换
					String funcRequest_groupId = funr.replace(ROLE_ID, groupId);// 替换ROLE_ID为用户组ID
					String funcRequest = funcRequest_groupId.replace(LOG_USER_NAME, logUserName);//替换LOG_USER_NAME为当前登陆账号
					obj.setFUNCREQUEST(funcRequest);
				}else{
					String funr = obj.getFUNCREQUEST().replace("＆","&");//特殊字符转换
					String funcRequest_groupId = funr.replace(ROLE_ID, "请把"+logUserName+"添加到用户组中");// 把用户添加到用户组中
					String funcRequest = funcRequest_groupId.replace(LOG_USER_NAME, logUserName);//替换LOG_USER_NAME为当前登陆账号
					obj.setFUNCREQUEST(funcRequest);
				}
			}
		}
		return obj;
	}
}

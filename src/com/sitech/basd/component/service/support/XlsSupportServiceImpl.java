package com.sitech.basd.component.service.support;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.support.XlsSupportDao;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.util.data.ReadExcel;

@Service("xlsSupportService")
public class XlsSupportServiceImpl implements XlsSupportService {
	@Resource
	private XlsSupportDao xlsSupportDao;
	private Logger logger = Logger.getLogger(XlsSupportServiceImpl.class);
	private static final String ERROR = "error";

	@Override
	public String importFromXls(InputStream is) {
		String result = "";
		try {
			/**
			 * read excel file , only one sheet with ip ,username ,password etc.
			 */
			List<List<String>>[] listArray = ReadExcel.readExcelFile(is);

			Map<String, List> listMap = new HashMap<String, List>();
			List<TbBusiHostObj> hostList = new ArrayList<TbBusiHostObj>();// 部署主机tb_busi_host
			List<TbBusiHostObj> appHostList = new ArrayList<TbBusiHostObj>();// 基准应用主机tb_busi_host
			List<UserObj> userList = new ArrayList<UserObj>();// 部署主机用户tb_user_manage
			List<UserObj> appUserList = new ArrayList<UserObj>();// 基准应用主机用户tb_user_manage
			List<TbBusiHostConfigObj> configList = new ArrayList<TbBusiHostConfigObj>();// 部署主机配置
			List<TbBusiHostConfigObj> appConfigList = new ArrayList<TbBusiHostConfigObj>();// 基准应用主机配置
			List<TbBusiDeployExampleObj> exampleList = new ArrayList<TbBusiDeployExampleObj>();
			List<TbSysAppObj> appList = new ArrayList<TbSysAppObj>();
			/** 应用列表 */
			List<List<String>> sheet1List = listArray[0];
			/** 基准应用列表,根据模板就一个基准应用 */
			List<List<String>> sheet2List = listArray[1];
			for (int i = 1; i < sheet2List.size(); i++) { // column
				List<String> stringList = sheet2List.get(i);// row
				/** infomation for host */
				TbBusiHostObj hostObj = new TbBusiHostObj();
				hostObj.setIP(stringList.get(0));
				// ensure ip is only
				if (!hostList.contains(hostObj) && !appHostList.contains(hostObj)) {
					appHostList.add(hostObj);
				}
				/** user info of host */
				UserObj userObj = new UserObj();
				userObj.setIp(stringList.get(0));
				userObj.setUsername(stringList.get(1));
				userObj.setPassword(stringList.get(2));
				// type=0 普通用户
				userObj.setType("0");
				// ensure ip&username is only
				if (!userList.contains(userObj) && !appUserList.contains(userObj)) {
					appUserList.add(userObj);
				}
				/** config info of host */
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setAppPath(stringList.get(5));
				appConfigList.add(configObj);
				/** create base app */
				TbSysAppObj appObj = new TbSysAppObj();
				appObj.setSTATUS("1");// 已注册
				appObj.setHostIP(stringList.get(0));
				appObj.setAPPNAME(stringList.get(3));
				appObj.setAPP_IDENTIFY(stringList.get(4));
				appObj.setBASEPATH(stringList.get(5));
				/**
				 * @author chenjlc 20131122 优化，导入时根据EXCEL内容判断应用所属业务系统 start
				 */
				appObj.setBUSISYS(stringList.get(6));
				/**
				 * end
				 */
				appList.add(appObj);
			}

			for (int i = 1; i < sheet1List.size(); i++) {// column
				List<String> stringList = sheet1List.get(i);// row
				/** infomation for host */
				TbBusiHostObj hostObj = new TbBusiHostObj();
				hostObj.setIP(stringList.get(0));
				// ensure ip is only
				if (!hostList.contains(hostObj) && !appHostList.contains(hostObj)) {
					hostList.add(hostObj);
				}
				/** user info of host */
				UserObj userObj = new UserObj();
				userObj.setIp(stringList.get(0));
				userObj.setUsername(stringList.get(1));
				userObj.setPassword(stringList.get(2));
				// ensure ip&username is only
				if (!userList.contains(userObj) && !appUserList.contains(userObj)) {
					userList.add(userObj);
				}
				/** config info of host */
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setBase_path(stringList.get(3));
				configObj.setAppPath(stringList.get(4));
				configList.add(configObj);
				/** create deploy example */
				TbBusiDeployExampleObj exampleObj = new TbBusiDeployExampleObj();
				exampleObj.setIP(stringList.get(0));
				exampleObj.setHostUsername(stringList.get(1));
				exampleObj.setExampleName(stringList.get(0) + "@" + stringList.get(1));
				exampleList.add(exampleObj);
			}
			listMap.put("appHostList", appHostList);
			listMap.put("appUserList", appUserList);
			listMap.put("appConfigList", appConfigList);
			listMap.put("hostList", hostList);
			listMap.put("userList", userList);
			listMap.put("configList", configList);
			listMap.put("exampleList", exampleList);
			listMap.put("appList", appList);
			result = xlsSupportDao.importFromXls(listMap);
		} catch (Exception e) {
			result = ERROR;
			logger.error("from excel import host ,user, error:" + e.getMessage());
		}

		return result;
	}
}

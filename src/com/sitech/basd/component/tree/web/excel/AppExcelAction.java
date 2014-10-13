package com.sitech.basd.component.tree.web.excel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.excel.BaseAppVO;
import com.sitech.basd.component.domain.excel.ProcessDataObj;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.service.excel.AppExcelService;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: AppExcelAction
 * </p>
 * <p>
 * Description: 智能部署在线Excel编辑界面Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-3 下午2:37:33
 * 
 */
@Controller("appExcelAction")
@Scope("prototype")
public class AppExcelAction extends BaseAction {
	@Autowired
	private AppExcelService appExcelService;
	// 基准应用ID
	private int appId;
	private String appData;
	private String appProcessData;

	public String getAppProcessData() {
		return appProcessData;
	}

	public void setAppProcessData(String appProcessData) {
		this.appProcessData = appProcessData;
	}

	public String getAppData() {
		return appData;
	}

	public void setAppData(String appData) {
		this.appData = appData;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**
	 * 
	 * @Title: initAppExcel
	 * @Description: 实例Excel界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午2:43:44
	 */
	public String initAppExcel() throws Exception {
		return "init";
	}

	/**
	 * 
	 * @Title: initProcessExcel
	 * @Description: 实例应用进程Excel界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午2:43:44
	 */
	public String initAppProcessExcel() throws Exception {
		return "initprocess";
	}

	/**
	 * 
	 * @Title: initExcelData
	 * @Description: 实例进程Excel数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午2:55:46
	 */
	public String initAppExcelData() throws Exception {
		List<BaseAppVO> list = appExcelService.initAppExcelData(appId);
		printJson(JacksonUtil.toJson(list));
		return null;
	}

	/**
	 * 
	 * @Title: initAppProcessExcelData
	 * @Description: 实例Excel数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午2:55:46
	 */
	public String initAppProcessExcelData() throws Exception {
		ProcessDataObj processDataObj = new ProcessDataObj();
		List<ProcessObj> list = appExcelService.initAppProcessExcelData(appId);
		List<String> exampleList = appExcelService.queryExampleNameList(appId);
		processDataObj.setProcessList(list);
		processDataObj.setExampleNameList(exampleList);
		printJson(JacksonUtil.toJson(processDataObj));
		return null;
	}

	/**
	 * 
	 * @Title: tabs
	 * @Description: 进入tab界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-4 上午10:58:45
	 */
	public String tabs() {
		return "tabs";
	}

	/**
	 * 
	 * @Title: saveAppExcelData
	 * @Description: 分析并保存Excel数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-6 上午10:01:38
	 */
	public String saveAppExcelData() throws Exception {
		if (appData != null) {
			List<BaseAppVO> list = JacksonUtil.fromJSON(appData,
					new JacksonUtil.TypeReference<List<BaseAppVO>>() {
					});
			System.out.println(list);
		}
		return null;
	}

	/**
	 * 
	 * @Title: saveAppProcessExcelData
	 * @Description: 保存智能部署实例
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-6 下午5:18:55
	 */
	public String saveAppProcessExcelData() throws Exception {
		String result = "{\"result\" : \"%s\"}";
		if (appProcessData != null) {
			List<ProcessObj> list = JacksonUtil.fromJSON(appProcessData,
					new JacksonUtil.TypeReference<List<ProcessObj>>() {
					});
			appExcelService.saveAppProcessData(list);
			result = String.format(result, "保存数据成功！");
		} else {
			result = String.format(result, "保存数据失败，Excel中未获取到数据！");
		}
		printJson(result);
		return null;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				System.out.println(params[i]);
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}
}

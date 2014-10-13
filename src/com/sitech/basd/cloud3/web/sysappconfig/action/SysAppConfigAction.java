package com.sitech.basd.cloud3.web.sysappconfig.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.sysapp.SysAppObj;
import com.sitech.basd.cloud3.domain.sysappconfig.SysAppConfigObj;
import com.sitech.basd.cloud3.service.sysapp.SysAppService;
import com.sitech.basd.cloud3.service.sysappconfig.SysAppConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * @author lipp 处理基准应用环境检测指标配置页面的相关操作的action
 */
@SuppressWarnings("serial")
@Controller("sysAppConfigAction")
@Scope("prototype")
public class SysAppConfigAction extends CRUDBaseAction {

	@Autowired
	private SysAppConfigService sysAppConfigService;

	@Autowired
	private SysAppService sysAppService;

	private SysAppConfigObj sysAppConfigObj;

	private Integer sysappconfigid;

	private String oper;

	private List<SysAppConfigObj> resultList;

	private List<SysAppObj> appList;

	public List<SysAppConfigObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<SysAppConfigObj> resultList) {
		this.resultList = resultList;
	}

	public List<SysAppObj> getAppList() {
		return appList;
	}

	public void setAppList(List<SysAppObj> appList) {
		this.appList = appList;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public SysAppConfigObj getSysAppConfigObj() {
		return sysAppConfigObj;
	}

	public void setSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		this.sysAppConfigObj = sysAppConfigObj;
	}

	public Integer getSysappconfigid() {
		return sysappconfigid;
	}

	public void setSysappconfigid(Integer sysappconfigid) {
		this.sysappconfigid = sysappconfigid;
	}

	/**
	 * @Title: insertSysAppConfig
	 * @Description: 进入添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午8:53:11
	 */
	public String insertSysAppConfig() {
		appList = sysAppService.queryAllAttach();
		return "add";
	}

	/**
	 * @Title: deleteSysAppConfig
	 * @Description: 删除配置信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午8:54:17
	 */
	public void deleteSysAppConfig() {
		if (sysAppConfigObj == null) {
			sysAppConfigObj = new SysAppConfigObj();
		}
		sysAppConfigObj.setCONFIG_ID(sysappconfigid);
		int ret = sysAppConfigService.deleteSysAppConfigObj(sysAppConfigObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: updateSysAppConfig
	 * @Description: 进入修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午8:57:37
	 */
	public String updateSysAppConfig() {
		if (sysAppConfigObj == null) {
			sysAppConfigObj = new SysAppConfigObj();
		}
		sysAppConfigObj.setCONFIG_ID(sysappconfigid);
		sysAppConfigObj = sysAppConfigService.queryOne(sysAppConfigObj);
		return "update";
	}

	/**
	 * @Title: listMappings
	 * @Description: 进入展示页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:47:48
	 */
	public String listMappings() {
		if (sysAppConfigObj == null) {
			sysAppConfigObj = new SysAppConfigObj();
		}
		if (sysAppConfigObj.getCONFIG_ID() != null && sysAppConfigObj.getCONFIG_ID() == -1) {
			sysAppConfigObj.setCONFIG_ID(null);
		}
		sysAppConfigObj.setPagination(this.getPaginater().initPagination(request));
		resultList = sysAppConfigService.query4List(sysAppConfigObj);
		appList = sysAppService.querySysAppConfiged();
		return SUCCESS;
	}

	/**
	 * @Title: saveSysAppConfig
	 * @Description: 保存配置信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:49:01
	 */
	public void saveSysAppConfig() {
		int ret = -1;
		if ("add".equals(oper)) {
			ret = sysAppConfigService.insertSysAppConfigObj(sysAppConfigObj);
		} else {
			ret = sysAppConfigService.updateSysAppConfigObj(sysAppConfigObj);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

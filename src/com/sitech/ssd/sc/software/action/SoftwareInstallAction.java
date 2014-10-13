package com.sitech.ssd.sc.software.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.sc.software.domain.SoftwareInstallObj;
import com.sitech.ssd.sc.software.service.SoftwareInstallService;
import com.sitech.utils.servlet.PrintWriterOut;

@Component("softwareInstallAction")
@Scope("prototype")
public class SoftwareInstallAction extends BaseAction {
	private SoftwareInstallObj obj;
	private List<?> resultList;
	@Autowired
	private SoftwareInstallService softwareInstallService;

	public String list() {
		if (obj == null) {
			obj = new SoftwareInstallObj();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = softwareInstallService.queryForList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description:进入添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-2 下午1:53:21
	 */
	public String add() {
		return "add";
	}

	/**
	 * 
	 * @Title: install
	 * @Description: 执行安装操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-3 下午5:01:59
	 */
	public String install() {
		if (obj == null) {
			obj = new SoftwareInstallObj();
		}
		int ret = softwareInstallService.install(obj);
		PrintWriterOut.printWirter(response, ret);
		return null;
	}

	public SoftwareInstallObj getObj() {
		return obj;
	}

	public void setObj(SoftwareInstallObj obj) {
		this.obj = obj;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

}

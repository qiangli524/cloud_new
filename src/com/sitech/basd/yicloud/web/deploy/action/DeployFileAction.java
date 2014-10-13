package com.sitech.basd.yicloud.web.deploy.action;



import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.service.deploy.TbCloud2DepFileService;
import com.sitech.basd.yicloud.web.deploy.form.DepFileForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class DeployFileAction extends CRUDBaseAction {
	private DepFileForm depForm;


	public DepFileForm getDepForm() {
		return depForm;
	}

	public void setDepForm(DepFileForm depForm) {
		this.depForm = depForm;
	}

	/**
	 * @Title:增加功能管理模块信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addDeployFile() throws BaseException {
		depForm = new DepFileForm();
		List lis=new ArrayList();
		lis.add("127.0.0.1");
		
		List<Integer> lis1=new ArrayList();
		lis1.add(12);
		lis1.add(13);
		tbCloud2DepFileService.insertByObj(lis, 11, lis1);
		return INPUT;
	}

	/**
	 * @Title:增加功能管理模块信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addDeployFile1() throws BaseException {
		depForm = new DepFileForm();
		List lis=new ArrayList();
		lis.add("127.0.0.1");
		
		List<Integer> lis1=new ArrayList();
		lis1.add(12);
		lis1.add(13);
		//tbCloud2DepFileService.insertByObj(lis, 11, lis1);
		return INPUT;
	}
	
	private TbCloud2DepFileService tbCloud2DepFileService;

	public void setTbCloud2DepFileService(
			TbCloud2DepFileService tbCloud2DepFileService) {
		this.tbCloud2DepFileService = tbCloud2DepFileService;
	}


}

package com.sitech.ssd.sc.os.web;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.os.domain.HostAdapter;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.service.OsHostAdapterService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

/**
  * @Title: 主机 网络接口
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-11 下午04:37:00
 */
@SuppressWarnings("serial")
@Controller("osHostAdapterAction")
public class OsHostAdapterAction extends BaseAction {
	
	@Resource
	private OsHostAdapterService osHostAdapterService;
	@Resource
	private OsHostService osHostService;
	@Resource
	private OsTemplateService osTemplateService;
	
	private OsHostAdapterForm theForm;
	
	public String updateHostAdapter(){
		HostAdapter adapter = new HostAdapter();
		adapter.setId(theForm.getId());
		adapter = osHostAdapterService.queryHostAdapter(adapter);
		
		BeanUtils.copyProperties(adapter, theForm);
		return "updateHostAdapter";
	}
	
	public String modifyHostAdapter(){
		HostAdapter adapter = new HostAdapter();
		BeanUtils.copyProperties(theForm,adapter);		
		osHostAdapterService.modifyHostAdapter(adapter);
		return null;
	}

	public String queryHostAdapter(){	
		
		HostModel host = new HostModel(theForm.getOs_host_id());
		host = osHostService.queryForObject(host);
		
		HostAdapter adapter = new HostAdapter(theForm.getOs_host_id());		
		theForm.setAdapterList(osHostAdapterService
				.queryHostAdapterList(adapter));	
		
		theForm.setTemplate_id(host.getOs_template());
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "hostAdapter";
	}

	public OsHostAdapterForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsHostAdapterForm theForm) {
		this.theForm = theForm;
	}
}
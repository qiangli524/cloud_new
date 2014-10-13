package com.sitech.basd.bol.web.boltask;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.boltask.BolTaskObj;
import com.sitech.basd.bol.service.boltask.BolTaskService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * <p>
 * Title: BolTaskAction
 * </p>
 * <p>
 * Description: 操作资源任务表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-7 下午5:14:26
 * 
 */
@Controller("bolTaskAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class BolTaskAction extends BaseAction {

	@Autowired
	private BolTaskService bolTaskService;

	private BolTaskObj bolTaskObj;

	private String host;

	private String op;

	private String pid;

	private String pname;

	public BolTaskObj getBolTaskObj() {
		return bolTaskObj;
	}

	public void setBolTaskObj(BolTaskObj bolTaskObj) {
		this.bolTaskObj = bolTaskObj;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @Title: dealBolTask
	 * @Description: 处理资源任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:44:13
	 */
	public void dealBolTask() {
		if (bolTaskObj == null) {
			bolTaskObj = new BolTaskObj();
		}
		bolTaskObj.setHOST(host);
		bolTaskObj.setOP(op);
		bolTaskObj.setRESOURCE_ID(pid);
		bolTaskObj.setRESOURCE_NAME(pname);
		bolTaskObj.setTIME(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		bolTaskObj.setRESOURCE_TYPE("HLA");
		int ret = bolTaskService.insertByObj(bolTaskObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.write(ret);
			PrintWriterOut.printWirter(response, ret);
			// pw.flush();
			// pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

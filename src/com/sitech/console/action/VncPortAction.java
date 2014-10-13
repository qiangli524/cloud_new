package com.sitech.console.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.console.domain.TbVncPortVO;
import com.sitech.console.service.VncPortService;

/**
 * 
 * <p>
 * Title: VncPortAction
 * </p>
 * <p>
 * Description: tb_vnc_port表操作界面
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
 * @createtime 2014-7-31 上午9:28:27
 * 
 */
@Controller("vncPortAction")
@Scope("prototype")
public class VncPortAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(VncPortAction.class);
	@Autowired
	private VncPortService vncPortService;
	@Autowired
	private HostInfoService hostInfoService;
	private List<TbVncPortVO> vncPostList;
	private TbVncPortVO tbVncPortVO;
	private String error_msg;
	private List<TbCloud2HostInfoObj> hostList;
	private List<String> portList;

	public List<String> getPortList() {
		return portList;
	}

	public void setPortList(List<String> portList) {
		this.portList = portList;
	}

	public List<TbCloud2HostInfoObj> getHostList() {
		return hostList;
	}

	public void setHostList(List<TbCloud2HostInfoObj> hostList) {
		this.hostList = hostList;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public TbVncPortVO getTbVncPortVO() {
		return tbVncPortVO;
	}

	public void setTbVncPortVO(TbVncPortVO tbVncPortVO) {
		this.tbVncPortVO = tbVncPortVO;
	}

	public List<TbVncPortVO> getVncPostList() {
		return vncPostList;
	}

	public void setVncPostList(List<TbVncPortVO> vncPostList) {
		this.vncPostList = vncPostList;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 查询端口列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:09:04
	 */
	public String list() {
		if (tbVncPortVO == null) {
			tbVncPortVO = new TbVncPortVO();
		}
		System.out.println(tbVncPortVO.getHost_ip());
		tbVncPortVO.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		try {
			vncPostList = vncPortService.queryVncPortList(tbVncPortVO);
		} catch (SQLException e) {
			LOG.error("查询端口列表异常！" + e.getMessage(), e);
			error_msg = "查询端口列表异常！" + e.getMessage();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: addvncport
	 * @Description: 进入VncPort添加界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午12:32:19
	 */
	public String addvncport() {
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		hostList = hostInfoService.queryForListByObj(obj);
		if (hostList == null) {
			hostList = new ArrayList<TbCloud2HostInfoObj>();
		}
		portList = vncPortService.initPortList();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: modvncport
	 * @Description: 进图vncport修改界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午12:34:26
	 */
	public String modvncport() {
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: savevncport
	 * @Description: 保存vncport
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午12:32:52
	 */
	public String savevncport() {
		try {
			vncPortService.saveVncPort(tbVncPortVO);
		} catch (SQLException e) {
			LOG.error("保存端口信息异常！" + e.getMessage(), e);
			error_msg = "保存端口信息异常！" + e.getMessage();
		}
		return null;
	}

	/**
	 * 
	 * @Title: deletevncport
	 * @Description: 删除端口
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:51:51
	 */
	public String deletevncport() {
		try {
			vncPortService.deleteVncPort(tbVncPortVO);
		} catch (SQLException e) {
			LOG.error("保存端口信息异常！" + e.getMessage(), e);
			error_msg = "保存端口信息异常！" + e.getMessage();
		}
		return null;
	}

	public String path() {
		String resourcePath = Thread.currentThread().getContextClassLoader().getResource("")
				.getFile();
		System.out.println(resourcePath);
		return null;
	}
}

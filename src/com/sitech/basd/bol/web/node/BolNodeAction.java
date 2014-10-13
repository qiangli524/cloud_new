package com.sitech.basd.bol.web.node;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.cluster.BolClusterVO;
import com.sitech.basd.bol.domain.host.BolHostVO;
import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * 
 * <p>
 * Title: BolNodeAction
 * </p>
 * <p>
 * Description: BOL_Node界面展示Action
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
 * @createtime 2014-2-25 上午9:39:29
 * 
 */
@Controller("bolNodeAction")
@Scope("prototype")
public class BolNodeAction extends BaseAction {
	@Autowired
	private BolNodeService bolNodeService;
	@Autowired
	private BolHostService bolHostService;
	@Autowired
	private BolClusterService bolClusterService;
	
	private List<BolNodeVO> resultList;
	private BolNodeVO bolNodeVO;
	private List clusterList;
	private List hostIpList;

	
	public List getClusterList() {
		return clusterList;
	}

	public void setClusterList(List clusterList) {
		this.clusterList = clusterList;
	}

	public List getHostIpList() {
		return hostIpList;
	}

	public void setHostIpList(List hostIpList) {
		this.hostIpList = hostIpList;
	}

	public List<BolNodeVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolNodeVO> resultList) {
		this.resultList = resultList;
	}

	public BolNodeVO getBolNodeVO() {
		return bolNodeVO;
	}

	public void setBolNodeVO(BolNodeVO bolNodeVO) {
		this.bolNodeVO = bolNodeVO;
	}

	/**
	 * 
	 * @Title: listBolNode
	 * @Description: 获取集群列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午10:30:23
	 */
	public String listBolNode() {
		if (bolNodeVO == null) {
			bolNodeVO = new BolNodeVO();
		}
		bolNodeVO.setPagination(this.getPaginater().initPagination(request));
		try {
			resultList = bolNodeService.queryForListByObj(bolNodeVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-2-26 上午9:59:59
	 */
	public String add() throws SQLException{
		this.queryList();
		return "add";
	}
	
	/**
	 * 
	 * @Title: mod
	 * @Description: 修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-2-26 上午10:00:44
	 */
	public String mod() throws SQLException{
		bolNodeVO = bolNodeService.queryByBolNodeVO(bolNodeVO);
		this.queryList();
		return "mod";
	}
	
	/**
	 * 
	 * @Title: queryList
	 * @Description: 查询主机和集群
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-26 上午11:11:06
	 */
	private void queryList() throws SQLException{
		BolClusterVO bolClusterVO = new BolClusterVO();
		BolHostVO bolHostVO = new BolHostVO();
		clusterList = bolClusterService.queryForListByObj(bolClusterVO);
		hostIpList = bolHostService.queryForListByObj(bolHostVO);
	}
	
	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-2-26 上午11:11:13
	 */
	public String save() throws SQLException{
		if(bolNodeVO.getId() > 0){
			bolNodeService.updateByBolNodeVO(bolNodeVO);
		}else {
			bolNodeService.insertByBolNodeVO(bolNodeVO);
		}
		return "save";
	}
	
	/**
	 * 
	 * @Title: del
	 * @Description: 删除操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-2-26 上午10:02:20
	 */
	public String del() throws SQLException {
		bolNodeService.deleteByBolNodeVO(bolNodeVO);
		return "del";
	}
}

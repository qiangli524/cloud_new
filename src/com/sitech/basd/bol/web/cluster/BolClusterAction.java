package com.sitech.basd.bol.web.cluster;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.cluster.BolClusterVO;
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * 
 * <p>
 * Title: BolClusterAction
 * </p>
 * <p>
 * Description: BOL_CLUSTER界面展示Action
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
@Controller("bolClusterAction")
@Scope("prototype")
public class BolClusterAction extends BaseAction {
	@Autowired
	private BolClusterService bolClusterService;
	private List<BolClusterVO> resultList;
	private BolClusterVO bolClusterVO;
	private BolClusterVO theForm;
	private int clusterId;

	public BolClusterVO getTheForm() {
		return theForm;
	}

	public void setTheForm(BolClusterVO theForm) {
		this.theForm = theForm;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public List<BolClusterVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolClusterVO> resultList) {
		this.resultList = resultList;
	}

	public BolClusterVO getBolClusterVO() {
		return bolClusterVO;
	}

	public void setBolClusterVO(BolClusterVO bolClusterVO) {
		this.bolClusterVO = bolClusterVO;
	}

	/**
	 * 
	 * @Title: listBolCluster
	 * @Description: 获取集群列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午10:30:23
	 */
	public String listBolCluster() {
		if (theForm == null) {
			theForm = new BolClusterVO();
		}
		theForm.setPagination(this.getPaginater().initPagination(request));
		try {
			resultList = bolClusterService.queryForListByObj(theForm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 跳转到添加集群页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2014 5:10:02 PM
	 */
	public String add() {
		return "add";
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 修改主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午9:43:08
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new BolClusterVO();
		}
		BolClusterVO obj = new BolClusterVO();
		if (clusterId > 0) {
			obj.setId(clusterId);
			try {
				theForm = bolClusterService.queryForListByObj(obj).get(0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			theForm = null;
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:09:40
	 */
	public String save() {
		if (theForm == null) {
			theForm = new BolClusterVO();
		}
		BolClusterVO obj = new BolClusterVO();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			bolClusterService.insertByBolClusterVO(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "insert";
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:09:40
	 */
	public String update() {
		if (theForm == null) {
			theForm = new BolClusterVO();
		}
		BolClusterVO obj = new BolClusterVO();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			bolClusterService.updateByBolClusterVO(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "update";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午11:00:49
	 */
	public String del() {
		if (clusterId > 0) {
			BolClusterVO obj = new BolClusterVO();
			obj.setId(clusterId);
			try {
				bolClusterService.deleteByBolClusterVO(obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "del";
	}
}

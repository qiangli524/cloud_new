package com.sitech.ssd.ah.paas.action.host;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;
import com.sitech.ssd.ah.paas.service.host.PaasHostInfoService;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: HadoopHostAction
 * </p>
 * <p>
 * Description: 主机管理相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-10 下午2:35:40
 * 
 */
@Controller("paasHostInfoAction")
@Scope("prototype")
public class PaasHostInfoAction extends BaseAction {
	@Autowired
	private PaasHostInfoService paasHostInfoService;
	
	private List<PaasHostInfoObj> resultList;

	private PaasHostInfoObj hostInfoObj = new PaasHostInfoObj();

	public List<PaasHostInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<PaasHostInfoObj> resultList) {
		this.resultList = resultList;
	}

	public PaasHostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(PaasHostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}

	/**
	 * 
	 * @Title: listPaasHostInfo
	 * @Description:查询主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:24:23
	 */
	public String listPaasHostInfo() {
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasHostInfoService.queryForHostList(hostInfoObj);
		return "list";
	}

	/**
	 * 
	 * @Title: addHostInfo
	 * @Description: 添加主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:25:02
	 */
	public String addHostInfo() {
		if (hostInfoObj.getId() != null && !"".equals(hostInfoObj.getId())) {
			hostInfoObj = paasHostInfoService.queryByObj(hostInfoObj);
		}
		return "edit";
	}

	/**
	 * 
	 * @Title: saveHostInfo
	 * @Description: 保存主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:25:13
	 */
	public String saveHostInfo() {
		if (hostInfoObj.getId() != null && !"".equals(hostInfoObj.getId())) {
			paasHostInfoService.updateByObj(hostInfoObj);
		} else {
			String id = RandomUUID.getUuid();
			hostInfoObj.setId(id);
			paasHostInfoService.insertByObj(hostInfoObj);
		}
		return null;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:25:29
	 */
	public String deleteByObj() {
		paasHostInfoService.deleteByObj(hostInfoObj);// 删除主机
		// HadoopTreeObj treeObj = new HadoopTreeObj();
		// treeObj.setUuid(hostInfoObj.getId());
		// hadoopTreeService.deleteByObj(treeObj);// 删除树表中主机节点
		return null;
	}
}

package action.host;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.host.HadoopHostInfoService;
import service.tree.HadoopTreeService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.randomid.RandomUUID;

import domain.host.HadoopHostInfoObj;

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
@Controller("hadoopHostInfoAction")
@Scope("prototype")
public class HadoopHostInfoAction extends BaseAction {
	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;
	@Autowired
	private HadoopTreeService hadoopTreeService;
	
	private List<HadoopHostInfoObj> resultList;

	private HadoopHostInfoObj hostInfoObj = new HadoopHostInfoObj();

	public List<HadoopHostInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopHostInfoObj> resultList) {
		this.resultList = resultList;
	}

	public HadoopHostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(HadoopHostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}

	/**
	 * 
	 * @Title: listHadoopHost
	 * @Description: 查询主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午2:43:58
	 */
	public String listHadoopHost() {
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));
		resultList = hadoopHostInfoService.queryHostList(hostInfoObj);
		return "listHadoopHost";
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
	 * @createtime 2014-1-10 下午2:52:33
	 */
	public String addHostInfo() {
		if (hostInfoObj.getId() != null && !"".equals(hostInfoObj.getId())) {
			hostInfoObj = hadoopHostInfoService.queryByObj(hostInfoObj);
		}
		return "addHostInfo";
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
	 * @createtime 2014-1-10 下午3:07:56
	 */
	public String saveHostInfo() {
		if (hostInfoObj.getId() != null && !"".equals(hostInfoObj.getId())) {
			hadoopHostInfoService.updHostInfoByObj(hostInfoObj);
		} else {
			String id = RandomUUID.getUuid();
			hostInfoObj.setId(id);
			hadoopHostInfoService.insertByObj(hostInfoObj);
		}
		return null;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息同时删除树表中主机节点
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午3:04:27
	 */
	public String deleteByObj() {
		hadoopHostInfoService.deleteByObj(hostInfoObj);// 删除主机
		// HadoopTreeObj treeObj = new HadoopTreeObj();
		// treeObj.setUuid(hostInfoObj.getId());
		// hadoopTreeService.deleteByObj(treeObj);// 删除树表中主机节点
		return null;
	}
}

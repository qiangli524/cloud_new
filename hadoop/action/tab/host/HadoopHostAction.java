package action.tab.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.cluster.HadoopClusterServer;
import service.host.HadoopHostInfoService;
import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

import domain.cluster.HadoopClusterObj;
import domain.host.HadoopHostInfoObj;
import domain.tree.HadoopTreeObj;

@Controller("hadoopHostAction")
@Scope("prototype")
public class HadoopHostAction extends BaseAction {

	private List resultlist;

	private HadoopHostInfoObj hostForm;

	private String entityId;// 主机ID

	private String type;// 节点类型

	private String nodeId;// 节点ID

	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	@Autowired
	private HadoopTreeService hadoopTreeService;

	@Autowired
	private HadoopClusterServer hadoopClusterServer;

	public String listHostInfo() {
		if (hostForm == null) {
			hostForm = new HadoopHostInfoObj();
		}
		HadoopHostInfoObj obj = new HadoopHostInfoObj();
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));
		if (hostForm.getLabel_name() != null
				&& !"".equals(hostForm.getLabel_name())) {
			obj.setLabel_name(hostForm.getLabel_name().trim());
		}
		if (hostForm.getHost_name() != null
				&& !"".equals(hostForm.getHost_name())) {
			obj.setHost_name(hostForm.getHost_name().trim());
		}
		if (hostForm.getHost_type() != null
				&& !"-1".equals(hostForm.getHost_type())) {
			obj.setHost_type(hostForm.getHost_type());
		}
		if (hostForm.getService() != null
				&& !"".equals(hostForm.getHost_name())) {
			obj.setService(hostForm.getService().trim());
		}
		if (hostForm.getService_type() != null
				&& !"-1".equals(hostForm.getService_type())) {
			obj.setService_type(hostForm.getService_type().trim());
		}
		if (hostForm.getIp() != null && !"".equals(hostForm.getIp())) {
			obj.setIp(hostForm.getIp().trim());
		}
		resultlist = hadoopHostInfoService.queryForHostList(obj);
		return "listHostInfo";
	}

	/**
	 * 
	 * @Title: queryHostInfoByNode
	 * @Description: 通过节点查询主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午10:09:57
	 */
	public String queryHostInfoByNode() {
		if (hostForm == null) {
			hostForm = new HadoopHostInfoObj();
		}
		HadoopHostInfoObj obj = new HadoopHostInfoObj();
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));
		if (hostForm.getHost_name() != null
				&& !"".equals(hostForm.getHost_name())) {
			obj.setHost_name(hostForm.getHost_name().trim());
		}
		if (hostForm.getHost_type() != null
				&& !"-1".equals(hostForm.getHost_type())) {
			obj.setHost_type(hostForm.getHost_type());
		}
		if (hostForm.getService_type() != null
				&& !"-1".equals(hostForm.getService_type())) {
			obj.setService_type(hostForm.getService_type().trim());
		}
		if (hostForm.getIp() != null && !"".equals(hostForm.getIp())) {
			obj.setIp(hostForm.getIp().trim());
		}

		/*---------update by lipengpeng--------*/
		// 获取集群cluster_name
		HadoopTreeObj treeObj = new HadoopTreeObj();
		treeObj.setId(nodeId);
		List<HadoopTreeObj> treeList = hadoopTreeService
				.queryForListByObj(treeObj);
		if (treeList.size() > 0) {
			treeObj = treeList.get(0);
		}
		String clustername = this.getClusterName(treeObj);
		obj.setCluster_name(clustername);

		// 获取当前节点下的主机节点集合
		treeList.clear();
		if (HadoopConstant.hostNode.equals(type)) {
			treeList.add(treeObj);
		} else {
			try {
				treeList = this.acquireChildNode(HadoopConstant.hostNode, null,
						treeObj, new ArrayList<HadoopTreeObj>());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		List<String> uuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : treeList) {
			if (!uuidList.contains(hadoopTreeObj.getUuid())) {
				uuidList.add(hadoopTreeObj.getUuid());
			}
		}
		obj.setNodeIdList(uuidList);
		// obj.setKpi_id("swap_total");
		resultlist = hadoopHostInfoService.queryForHostListJoinColl(obj);
		/*----------update end---------*/

		return "listHostInfo";
	}
	
	/**
	 * @Title: acquireChildNode
	 * @Description: 递归获取某种类型的子节点集合
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:21:40
	 */
	private List<HadoopTreeObj> acquireChildNode(String childNodeType,
			String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList) throws Exception {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
					if (parentNodeServiceType != null
							&& !"".equals(parentNodeServiceType)) {
						if (parentNodeServiceType.equals(htObj
								.getService_type())) {
							retList.add(htObj);
						}
					} else {
						retList.add(htObj);
					}
				} else {
					acquireChildNode(childNodeType, parentNodeServiceType,
							htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}

	/**
	 * @Title: getClusterName
	 * @Description: 获取集群名称，监控表中的cluster_name
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:58:16
	 */
	private String getClusterName(HadoopTreeObj hadoopTreeObj) {
		String clusterId = this.getClusterId(hadoopTreeObj);
		HadoopClusterObj clusterObj = new HadoopClusterObj();
		clusterObj.setId(clusterId);
		List<HadoopClusterObj> cluList = hadoopClusterServer
				.queryClusterList(clusterObj);
		String clusterName = null;
		if (cluList.size() > 0) {
			clusterName = cluList.get(0).getCluster_id();
		}
		return clusterName;
	}

	/**
	 * @Title: getClusterId
	 * @Description: 获取集群id
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:13:45
	 */
	private String getClusterId(HadoopTreeObj treeObj) {
		String clusterId = null;
		if (!HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
			try {
				clusterId = this.acquireClusterName(treeObj, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			clusterId = treeObj.getUuid();
		}
		return clusterId;
	}

	/**
	 * @Title: acquireClusterName
	 * @Description: 获取集群编号
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:14:14
	 */
	private String acquireClusterName(HadoopTreeObj hadoopTreeObj, int count)
			throws Exception {
		HadoopTreeObj treeObj = new HadoopTreeObj();
		count = count + 1;
		try {
			treeObj.setId(hadoopTreeObj.getParent_id());
			treeObj = hadoopTreeService.queryForListByObj(treeObj).get(0);
			if (HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
				return treeObj.getUuid();
			} else {
				if (count >= 6) {// 不能进入死循环，一定次数后强制终止
					return null;
				}
				return acquireClusterName(treeObj, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归获取集群名称出错，错误原因：", e);
		}
	}

	public List getResultlist() {
		return resultlist;
	}

	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}

	public HadoopHostInfoObj getHostForm() {
		return hostForm;
	}

	public void setHostForm(HadoopHostInfoObj hostForm) {
		this.hostForm = hostForm;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}

package action.tab.servicenode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import service.tab.HadoopServiceNodeService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

import dao.datanode.HadoopDataNodeDao;
import dao.datanode.HadoopDataNodeDaoImpl;
import domain.service.DataNodeObj;
import domain.service.NodeManagerObj;
import domain.tree.HadoopTreeObj;

@Component("hadoopServiceNodeAction")
@Scope("prototype")
public class HadoopServiceNodeAction extends BaseAction {
	@Autowired
	private HadoopServiceNodeService hadoopServiceNodeService;
	@Autowired
	private HadoopDataNodeDao hadoopDataNodeDao;
	private HadoopTreeObj tree;
	private DataNodeObj datanode;
	private NodeManagerObj nodeManager;
	private List resultList;
	//add by qism
	private String nodeId;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * @Title: listServiceList
	 * @Description: 查询服务节点对应的服务实例的状态列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 下午5:43:45
	 */
	public String listServiceList() {
		if (HadoopConstant.dataNode.equals(tree.getService_type())) {
			if (datanode == null) {
				datanode = new DataNodeObj();
			}
			String cluster_id = hadoopDataNodeDao.queryClusterIdByNodeId(nodeId);
			datanode.setPagination(this.getPaginater().initPagination(request));
			datanode.setCluster_id(cluster_id);
			resultList = hadoopServiceNodeService.listDatanodeList(datanode);
		} else if (HadoopConstant.nodeManager.equals(tree.getService_type())) {
			if (nodeManager == null) {
				nodeManager = new NodeManagerObj();
			}
			String cluster_id = hadoopDataNodeDao.queryClusterIdByNodeId(nodeId);
			nodeManager.setCluster_id(cluster_id);
			nodeManager.setPagination(this.getPaginater().initPagination(request));
			resultList = hadoopServiceNodeService.listNodemanagerList(nodeManager);
		}

		return "service_list";
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public HadoopTreeObj getTree() {
		return tree;
	}

	public void setTree(HadoopTreeObj tree) {
		this.tree = tree;
	}

	public HadoopServiceNodeService getHadoopServiceNodeService() {
		return hadoopServiceNodeService;
	}

	public void setHadoopServiceNodeService(HadoopServiceNodeService hadoopServiceNodeService) {
		this.hadoopServiceNodeService = hadoopServiceNodeService;
	}

	public DataNodeObj getDatanode() {
		return datanode;
	}

	public void setDatanode(DataNodeObj datanode) {
		this.datanode = datanode;
	}

	public NodeManagerObj getNodeManager() {
		return nodeManager;
	}

	public void setNodeManager(NodeManagerObj nodeManager) {
		this.nodeManager = nodeManager;
	}

}

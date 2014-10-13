package action.tree;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.host.HadoopHostInfoService;
import service.monitor.HadoopMonitorService;
import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.util.UnitedConstant;

import domain.host.HadoopHostInfoObj;
import domain.tree.HadoopTreeObj;

/**
 * 
 * <p>
 * Title: TreeAction
 * </p>
 * <p>
 * Description: 树操作相关
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime 2014-1-4 下午8:38:06
 * 
 */
@Controller("hadoopTreeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class HadoopTreeAction extends BaseAction {
	@Autowired
	private HadoopTreeService hadoopTreeService;
	@Autowired
	private HadoopMonitorService hadoopMonitorService;
	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	private HadoopTreeObj obj;

	private HadoopHostInfoObj hostInfoObj = new HadoopHostInfoObj();

	private List resultList;

	private String hostId;

	private String id;
	
	private String labelName;
	
	private String type;
	
	
	/**
	 * 
	 * @Title: listTree
	 * @Description: 进入异步加载树页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:38:37
	 */
	public String listTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asynForTree
	 * @Description: 异步加载树
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:39:23
	 */
	public String asynForTree() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		resultList = hadoopTreeService.queryForTree(obj);
		return "tree";
	}

	/**
	 * 
	 * @Title: addChildNode
	 * @Description: 添加子节点
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:35:46
	 */
	public String addNode() {
		if (HadoopConstant.serviceNode.equals(obj.getNode_type())) {// 主机节点
			resultList = hadoopHostInfoService.queryForHostList(hostInfoObj);
		}
		return "addNode";
	}

	/**
	 * 
	 * @Title: saveNode
	 * @Description: 保存添加的节点
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:36:34
	 */
	public String saveNode() {
		if (HadoopConstant.hostNode.equals(obj.getNode_type())) {//主机节点
			obj.setHostId(hostId);// 主机ID
			obj.setName(labelName);// 节点名称
		}
		hadoopTreeService.insertByObj(obj);
		return null;
	}

	/**
	 * 
	 * @Title: tabs
	 * @Description:进入tabs页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:42:42
	 */
	public String tabs() {
		if (obj.getUuid() != null && !"".equals(obj.getUuid())) {
			hostInfoObj.setId(obj.getUuid());
			hostInfoObj = hadoopHostInfoService.queryByObj(hostInfoObj);
		}
		if(PaasConstant.HADOOP.equals(obj.getNode_type())){
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setNode_type(HadoopConstant.root);
			List<HadoopTreeObj> treeList = hadoopTreeService.queryForListByObj(treeObj);
			if(treeList != null && treeList.size() > 0){
				obj = treeList.get(0);
			}
		}
		return "tabs";
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 查询
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-12 下午7:09:42
	 */
	public String queryTreeNodeByName() {
		if (labelName != null && !"".equals(labelName)) {
			HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
			try {
				labelName = URLDecoder.decode(labelName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			hadoopTreeObj.setName(labelName);
			hadoopTreeObj.setNode_type(type);
			//查询节点列表
			resultList = hadoopTreeService.queryForListByObj(hadoopTreeObj);
		}
		return "querynode";
	}
	
	
	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public String getExpandNodes() {
		try {
			labelName = URLDecoder.decode(labelName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HadoopTreeObj u = new HadoopTreeObj();
		if (id != null && !"".equals(id)) {
			u.setId(id);
		}
		if (labelName != null && !"".equals(labelName)) {
			u.setName(labelName);
		}
		if (type != null && !"".equals(type)) {
			u.setNode_type(type);
		}
		resultList = hadoopTreeService.getExpandNodes(u);
		return "expandNode";

	}
	public HadoopTreeObj getObj() {
		return obj;
	}

	public void setObj(HadoopTreeObj obj) {
		this.obj = obj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public HadoopHostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(HadoopHostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}

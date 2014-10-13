package com.sitech.ssd.ah.paas.action.tree;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.entity.PaasEntityService;
import com.sitech.ssd.ah.paas.service.host.PaasHostInfoService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: PaasTreeAction
 * </p>
 * <p>
 * Description: paas资源树相关操作
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
 * @createtime 2014-3-28 上午9:16:56
 * 
 */
@Controller("paasTreeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class PaasTreeAction extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(PaasTreeAction.class);

	@Autowired
	private PaasTreeService paasTreeService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private PaasHostInfoService paasHostInfoService;
	@Autowired
	private PaasEntityService paasEntityService;
	@Autowired
	private BusiManagerTreeService busiManagerTreeService;

	private PaasTreeObj treeObj;

	private PaasEntityObj entityObj;
	
	private BusiManagerTree bmtObj;

	private GreenPlumHostInfoObj hostInfoObj;
	private String id;
	private String name;// 节点名字
	private String entity_id;// 实体ID
	private String parent_id;// 父节点ID
	private String node_type;// 节点类型
	private String server_type;// 服务类型
	private String coll_time;
	private String timeline;
	private String cycle_time;// 自定义时间
	private String result;
	private List resultList;
	private String jspName; //用于页面回调定位

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public PaasTreeObj getTreeObj() {
		return treeObj;
	}

	public void setTreeObj(PaasTreeObj treeObj) {
		this.treeObj = treeObj;
	}

	public PaasEntityObj getEntityObj() {
		return entityObj;
	}

	public void setEntityObj(PaasEntityObj entityObj) {
		this.entityObj = entityObj;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}

	public GreenPlumHostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(GreenPlumHostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}
	

	public BusiManagerTree getBmtObj() {
		return bmtObj;
	}

	public void setBmtObj(BusiManagerTree bmtObj) {
		this.bmtObj = bmtObj;
	}

	public String getJspName() {
		return jspName;
	}

	public void setJspName(String jspName) {
		this.jspName = jspName;
	}

	/**
	 * 
	 * @Title: tabs
	 * @Description: 跳到tab页
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 上午11:21:48
	 */
	public String tabs() {
		return "tabs";
	}

	/**
	 * 
	 * @Title: listPaasTree
	 * @Description: 跳到Paas资源树页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午5:20:05
	 */
	public String listPaasTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 异步加载资源树
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午5:21:04
	 */
	public String asyncForTree() throws HttpClientException {
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}
		try {
			if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
				resultList = paasTreeService.queryForPaasTree(request);
			} else {// 对于普通登录用户，需要进行权限控制
				int flag = 0;
				for (int i = 0; i < users.length; i++) {
					if (account.equals(users[i])) {
						flag = 1;
					}
				}
				if (flag == 1) {
					resultList = paasTreeService.queryForPaasTree(request);
				} else {
					resultList = paasTreeService
							.queryForAuthTree(request, Integer.parseInt(userId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tree";
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 通过名字查询
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-27 下午5:53:30
	 */
	public String queryTreeNodeByName() {
		if (name != null && !"".equals(name)) {
			PaasTreeObj obj = new PaasTreeObj();
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			obj.setName(name);
			obj.setNode_type(node_type);
			try {
				resultList = paasTreeService.queryTreeNodeByObj(obj);
			} catch (Exception e) {
				logger.error("查询树节点数据异常" + e.getMessage() + e, e);
			}
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
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:07:39
	 */
	public String getExpandNodes() {
		PaasTreeObj obj = new PaasTreeObj();
		if (name != null && !"".equals(name)) {
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			obj.setName(name);
			obj.setNode_type(node_type);
			if (PaasConstant.RESOURCEPOOL.equals(node_type)) {// 资源池
				resultList = paasTreeService.queryNodesForResourcePool(obj);
			} else if (PaasConstant.EXAMPLES.equals(node_type)) {
				resultList = paasTreeService.queryNodesForExamples(obj);
			}
		}
		return "expandNode";
	}

	// ---------------------------Caas相关操作---------------------------
	/**
	 * 
	 * @Title: addCache
	 * @Description: 跳转到添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 11:28:00 AM
	 */
	public String addCache() {
		return "addCache";
	}

	/**
	 * 
	 * @Title: saveCache
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 11:34:40 AM
	 */
	public String saveCache() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.CAAS_TYPE);
		treeObj.setServer_type(PaasConstant.MEMCACHE);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delCache
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:18:42
	 */
	public String delCache() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addCacheBusiness
	 * @Description: 跳转到添加业务页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 2:16:33 PM
	 */
	public String addCacheBusiness() {
		return "addCacheBusiness";
	}

	/**
	 * 
	 * @Title: saveCacheBusiness
	 * @Description: 保存业务
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:19:07
	 */
	public String saveCacheBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.CAAS_BUSI);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delCacheBusiness
	 * @Description: 删除业务
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:19:36
	 */
	public String delCacheBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addCacheExample
	 * @Description: 跳转到添加缓存实例页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 4:51:04 PM
	 */
	public String addCacheExample() {
		return "addCacheExample";
	}

	/**
	 * 
	 * @Title: saveCacheExample
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:23:06
	 */
	public String saveCacheExample() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.CAAS_EXAMPLE);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delCacheExample
	 * @Description: 删除缓存实例
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 7:10:32 PM
	 */
	public String delCacheExample() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addCacheHost
	 * @Description: 跳转到添加缓存实例页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 4:51:04 PM
	 */
	public String addCacheHost() {
		return "addCacheHost";
	}

	/**
	 * 
	 * @Title: saveCacheHost
	 * @Description: 保存缓存
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 7:07:32 PM
	 */
	public String saveCacheHost() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.CAAS_HOST);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delCacheHost
	 * @Description: 删除缓存主机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2014 7:10:32 PM
	 */
	public String delCacheHost() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	// ---------------------------Daas相关操作---------------------------
	/**
	 * 
	 * @Title: addDBType
	 * @Description: 跳转到添加数据库类型页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime May 8, 2014 11:28:00 AM
	 */
	public String addDBType() {
		return "addDBType";
	}

	/**
	 * 
	 * @Title: saveDBType
	 * @Description: 保存数据库类型
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:25:08
	 */
	public String saveDBType() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.DAAS_TYPE);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delDBType
	 * @Description: 删除数据库类型
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:25:16
	 */
	public String delDBType() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addDBBusiness
	 * @Description: 跳转到添加数据库业务页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:25:42
	 */
	public String addDBBusiness() {
		return "addDBBusiness";
	}

	/**
	 * 
	 * @Title: saveDBBusiness
	 * @Description: 保存数据库业务
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:25:50
	 */
	public String saveDBBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.DAAS_BUSI);
		treeObj.setEntity_id(RandomUUID.getUuid());
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delDBBusiness
	 * @Description: 删除数据库业务
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:09
	 */
	public String delDBBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addDB
	 * @Description: 添加数据库
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:32
	 */
	public String addDB() {
		return "addDB";
	}

	/**
	 * 
	 * @Title: saveDB
	 * @Description: 保存数据库
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:43
	 */
	public String saveDB() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		if (server_type != null && !"".equals(server_type)) {
			if (PaasConstant.GP.equals(server_type)) {
				treeObj.setServer_type(PaasConstant.GP);
			} else if (PaasConstant.MYSQL.equals(server_type)) {
				treeObj.setServer_type(PaasConstant.MYSQL);
			}
		}
		treeObj.setNode_type(PaasConstant.DAAS_DB);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delDB
	 * @Description: 删除数据库
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:57
	 */
	public String delDB() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addDBEntity
	 * @Description: 添加数据库实体
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:32
	 */
	public String addDBEntity() {
		return "addDBEntity";
	}

	/**
	 * 
	 * @Title: saveDBEntity
	 * @Description: 保存数据库实体或Gp的服务主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:43
	 */
	public String saveDBEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		String[] entity_ids = treeObj.getEntity_id().split(",");
		String[] names = treeObj.getName().split(",");
		for (int i = 0; i < entity_ids.length; i++) {
			treeObj.setNode_type(PaasConstant.DAAS_DB_ENTITY);
			treeObj.setEntity_id(entity_ids[i]);
			treeObj.setName(names[i]);
			try {
				result = paasTreeService.insertByObj(treeObj);
			} catch (Exception e) {
				result = "失败：" + e.getMessage() + e.getClass().getName();
			}
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addServerHost
	 * @Description: 跳转到添加主机页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:53:26
	 */
	public String addServerHost() {
		if (hostInfoObj == null) {
			hostInfoObj = new GreenPlumHostInfoObj();
		}
		hostInfoObj.setPagination(this.getPaginater().initPagination(request));// 分页
		try {
			hostInfoObj.setId(treeObj.getParent_id());
			hostInfoObj.setTableName("tb_paas_resource_tree");
			resultList = paasHostInfoService.queryPhysicsHostList(hostInfoObj);
		} catch (Exception e) {
			logger.error("查询出错", e);
			e.printStackTrace();
		}
		return "addServerHost";
	}

	/**
	 * 
	 * @Title: saveServerHost
	 * @Description: 保存主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:57:48
	 */
	public String saveServerHost() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = 0;
		String[] h_uuids = treeObj.getEntity_id().split(",");
		String[] h_names = treeObj.getName().split(",");
		for (int i = 0; i < h_uuids.length; i++) {
			treeObj.setEntity_id(h_uuids[i]);
			treeObj.setName(h_names[i]);
			treeObj.setNode_type(PaasConstant.DAAS_DB_ENTITY);
			treeObj.setServer_type(PaasConstant.GP);
			try {
				result = paasTreeService.insertByObj(treeObj);
			} catch (Exception e) {
				result = "失败：" + e.getMessage() + e.getClass().getName();
			}
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delDBEntity
	 * @Description: 删除数据库实体
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:57
	 */
	public String delDBEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: listDBEntity
	 * @Description: 弹出数据库实体页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午9:35:04
	 */
	public String listDBEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		if (entityObj == null) {
			entityObj = new PaasEntityObj();
		}
		PaasTreeObj paasTreeObj = new PaasTreeObj();
		paasTreeObj.setNode_type(PaasConstant.DAAS_DB_ENTITY);
		paasTreeObj.setParent_id(treeObj.getParent_id());
		paasTreeObj.setServer_type(treeObj.getServer_type());
		/* 查询在tb_paas_resource_tree中的entity_id */
		List entityIdList = paasTreeService.queryEntityIdList(paasTreeObj);
		if (entityIdList != null && entityIdList.size() > 0) {
			entityObj.setEntityIdList(entityIdList);
		}
		entityObj.setEntity_type(treeObj.getServer_type());
		/* 展示页面里不显示在tb_paas_resource_tree表中已存在的实体 */
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasEntityService.queryForEntityList(entityObj);
		return "listDBEntity";
	}

	/**
	 * @Title: listGpHost
	 * @Description: 用于显示GP服务节点下的主机
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-25 下午4:32:14
	 */
	public String listGpHost() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		PaasHostInfoObj paasHostInfoObj = new PaasHostInfoObj();
		paasHostInfoObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasHostInfoService.queryForHostList(paasHostInfoObj);
		return "listGpHost";
	}
	
	/**
	 * 
	 * @Title: autoGeneration
	 * @Description: 自动生成业务下两层节点
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-2 下午3:20:08
	 */
	public String autoGeneration(){
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		List nameList = new ArrayList();
		PaasTreeObj paTreeObj = new PaasTreeObj();
		paTreeObj.setNode_type(PaasConstant.DAAS_BUSI);
		paTreeObj.setServer_type(PaasConstant.ORACLE);
		// 查询tb_paas_resource_tree中oracle的业务名称
		List<PaasTreeObj> treeObjList = paasTreeService.queryTreeNodeByObj(paTreeObj);
		for (PaasTreeObj treObj : treeObjList) {
			nameList.add(treObj.getName());
		}
		// oracle下的数据库服务名称对应着业务名称
		List<PaasEntityObj> entityNameList = new ArrayList<PaasEntityObj>();
		PaasEntityObj entityObj = new PaasEntityObj();
		entityObj.setEntity_type(PaasConstant.ORACLE_SERVER);
		// 查询tb_paas_entity中数据库服务的名字
		List<PaasEntityObj> list = paasEntityService.queryDistinctEntityNameByType(entityObj);
		if (list != null && list.size() > 0) {
			// 去掉数据库服务名称中有"$"和"UNKNOWN"的数据
			for (PaasEntityObj paasEntityObj : list) {
				if (!paasEntityObj.getEntity_name().contains("$")
						&& !paasEntityObj.getEntity_name().contains("UNKNOWN")) {
					entityNameList.add(paasEntityObj);
				}
			}
			for (PaasEntityObj paasEntObj : entityNameList) {
				// 判断tb_paas_resource_tree中的业务名称是否已存在
				if (nameList.contains(paasEntObj.getEntity_name())) {
					logger.info("Oracle资源池下已包含名字是" + paasEntObj.getEntity_name() + "的业务");
					result = PaasConstant.SUCCESS;
				} else {
					PaasTreeObj paasTreeObj = new PaasTreeObj();
					paasTreeObj.setName(paasEntObj.getEntity_name());
					paasTreeObj.setEntity_id(RandomUUID.getUuid());
					paasTreeObj.setParent_id(treeObj.getId());
					paasTreeObj.setNode_type(PaasConstant.DAAS_BUSI);
					paasTreeObj.setServer_type(PaasConstant.ORACLE);
					try {
						// 生成业务名称
						result = paasTreeService.insertByObj(paasTreeObj);
					} catch (Exception e) {
						result = "失败：" + e.getMessage() + e.getClass().getName();
					}
					// 生成业务名称下三个节点：Oracle DB,Oracle Instance,Oracle Service
					PaasTreeObj paObj = paasTreeService.queryForPaasTreeObj(paasTreeObj);
					if (paObj != null) {
						PaasTreeObj oracleDB = new PaasTreeObj();
						oracleDB.setName("Oracle DB");
						oracleDB.setParent_id(paObj.getId());
						oracleDB.setNode_type(PaasConstant.DAAS_DB);
						oracleDB.setServer_type(PaasConstant.ORACLE_DB);
						paasTreeService.insertByObj(oracleDB);
						PaasTreeObj oracleInstance = new PaasTreeObj();
						oracleInstance.setName("Oracle Instance");
						oracleInstance.setParent_id(paObj.getId());
						oracleInstance.setNode_type(PaasConstant.DAAS_DB);
						oracleInstance.setServer_type(PaasConstant.ORACLE_INSTANCE);
						paasTreeService.insertByObj(oracleInstance);
						PaasTreeObj oracleService = new PaasTreeObj();
						oracleService.setName("Oracle Service");
						oracleService.setParent_id(paObj.getId());
						oracleService.setNode_type(PaasConstant.DAAS_DB);
						oracleService.setServer_type(PaasConstant.ORACLE_SERVER);
						paasTreeService.insertByObj(oracleService);

						/**
						 * 生成这三个节点对应的数据
						 */
						PaasTreeObj oracleDBObj = paasTreeService.queryForPaasTreeObj(oracleDB);
						PaasEntityObj oracleDBEntity = new PaasEntityObj();
						oracleDBEntity.setEntity_type(PaasConstant.ORACLE_DB);
						// 添加数据库数据
						List<PaasEntityObj> oracleDBList = paasEntityService
								.queryForEntityList(oracleDBEntity);
						for (PaasEntityObj dbObj : oracleDBList) {
							PaasTreeObj dbEntity = new PaasTreeObj();
							dbEntity.setName(dbObj.getEntity_name());
							dbEntity.setEntity_id(dbObj.getEntity_id());
							dbEntity.setParent_id(oracleDBObj.getId());
							dbEntity.setNode_type(PaasConstant.DAAS_DB_ENTITY);
							dbEntity.setServer_type(PaasConstant.ORACLE_DB);
							// 添加数据库数据
							paasTreeService.insertByObj(dbEntity);
						}
						// 添加数据库实例数据
						PaasTreeObj oracleInstanceObj = paasTreeService
								.queryForPaasTreeObj(oracleInstance);
						PaasEntityObj oracleInstanceEntity = new PaasEntityObj();
						oracleInstanceEntity.setEntity_type(PaasConstant.ORACLE_INSTANCE);
						List<PaasEntityObj> oracleInstanceList = paasEntityService
								.queryForEntityList(oracleInstanceEntity);
						for (PaasEntityObj instanceObj : oracleInstanceList) {
							PaasTreeObj instanceEntity = new PaasTreeObj();
							instanceEntity.setName(instanceObj.getEntity_name());
							instanceEntity.setEntity_id(instanceObj.getEntity_id());
							instanceEntity.setParent_id(oracleInstanceObj.getId());
							instanceEntity.setNode_type(PaasConstant.DAAS_DB_ENTITY);
							instanceEntity.setServer_type(PaasConstant.ORACLE_INSTANCE);
							paasTreeService.insertByObj(instanceEntity);
						}
						// 添加数据库服务数据
						PaasTreeObj oracleServiceObj = paasTreeService
								.queryForPaasTreeObj(oracleService);
						PaasEntityObj oracleServiceEntity_Busi = new PaasEntityObj();
						oracleServiceEntity_Busi.setNodeName(paasEntObj.getEntity_name());
						oracleServiceEntity_Busi.setEntity_type(PaasConstant.ORACLE_SERVER);
						List<PaasEntityObj> oracleServiceList_Busi = paasEntityService
								.queryForEntityList(oracleServiceEntity_Busi);
						PaasEntityObj oracleServiceEntity_Sysm = new PaasEntityObj();
						oracleServiceEntity_Sysm.setEntity_name("$");
						oracleServiceEntity_Sysm.setEntity_type(PaasConstant.ORACLE_SERVER);
						List<PaasEntityObj> oracleServiceList_Sysm = paasEntityService
								.queryForEntityList(oracleServiceEntity_Sysm);
						oracleServiceList_Sysm.addAll(oracleServiceList_Busi);
						for (PaasEntityObj serviceObj : oracleServiceList_Sysm) {
							PaasTreeObj serviceEntity = new PaasTreeObj();
							serviceEntity.setName(serviceObj.getEntity_name());
							serviceEntity.setEntity_id(serviceObj.getEntity_id());
							serviceEntity.setParent_id(oracleServiceObj.getId());
							serviceEntity.setNode_type(PaasConstant.DAAS_DB_ENTITY);
							serviceEntity.setServer_type(PaasConstant.ORACLE_SERVER);
							paasTreeService.insertByObj(serviceEntity);
						}
					}
				}
			}
		}
		return "results";
	}
	
	/**
	 * 
	 * @Title: renameBusi
	 * @Description: 重命名
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-3 上午9:51:35
	 */
	public String renameBusi() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = paasTreeService.updateByObj(treeObj);
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	// ---------------------------Maas相关操作---------------------------
	/**
	 * 
	 * @Title: addMiddlewareType
	 * @Description: 跳转到添加中间件页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime May 8, 2014 11:28:00 AM
	 */
	public String addMiddlewareType() {
		return "addMiddlewareType";
	}

	/**
	 * 
	 * @Title: saveMiddlewareType
	 * @Description: 保存中间件
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime May 8, 2014 11:34:40 AM
	 */
	public String saveMiddlewareType() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.MAAS_TYPE);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delMiddlewareType
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:27:56
	 */
	public String delMiddlewareType() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addMiddlewareBusiness
	 * @Description: 跳转添加业务页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:28:10
	 */
	public String addMiddlewareBusiness() {
		return "addMiddlewareBusiness";
	}

	/**
	 * 
	 * @Title: saveMiddlewareBusiness
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime May 8, 2014 2:16:33 PM
	 */
	public String saveMiddlewareBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.MAAS_APP);
		treeObj.setEntity_id(RandomUUID.getUuid());
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delMiddlewareBusiness
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:28:59
	 */
	public String delMiddlewareBusiness() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addMiddlewareExample
	 * @Description: 跳转到添加实例页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime May 8, 2014 4:51:04 PM
	 */
	public String addMiddleware() {
		return "addMiddleware";
	}

	/**
	 * 
	 * @Title: saveMiddleware
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:29:27
	 */
	public String saveMiddleware() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		treeObj.setNode_type(PaasConstant.MAAS_DOMAIN);
		try {
			result = paasTreeService.insertByObj(treeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delMiddleware
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:29:43
	 */
	public String delMiddleware() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addMiddlewareEntity
	 * @Description: 添加实体
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:32
	 */
	public String addMiddlewareEntity() {
		return "addMiddlewareEntity";
	}

	/**
	 * 
	 * @Title: saveMiddlewareEntity
	 * @Description: 保存实体
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:43
	 */
	public String saveMiddlewareEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		String[] entity_ids = treeObj.getEntity_id().split(",");
		String[] names = treeObj.getName().split(",");
		for (int i = 0; i < entity_ids.length; i++) {
			treeObj.setNode_type(PaasConstant.MAAS_ENTITY);
			treeObj.setEntity_id(entity_ids[i]);
			treeObj.setName(names[i]);
			try {
				result = paasTreeService.insertByObj(treeObj);
			} catch (Exception e) {
				result = "失败：" + e.getMessage() + e.getClass().getName();
			}
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delMiddlewareEntity
	 * @Description: 删除实体
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午10:26:57
	 */
	public String delMiddlewareEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		int ret = -1;
		if (treeObj.getId() != null && !"".equals(treeObj.getId())) {
			ret = paasTreeService.deleteByObj(treeObj);
		}
		if (ret == 1) {
			result = PaasConstant.SUCCESS;
		} else {
			result = PaasConstant.FAIL;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: listMiddlewareEntity
	 * @Description: 弹出数据库实体页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午9:35:04
	 */
	public String listMiddlewareEntity() {
		if (treeObj == null) {
			treeObj = new PaasTreeObj();
		}
		if (entityObj == null) {
			entityObj = new PaasEntityObj();
		}
		PaasTreeObj paasTreeObj = new PaasTreeObj();
		paasTreeObj.setNode_type(PaasConstant.DAAS_DB_ENTITY);
		paasTreeObj.setParent_id(treeObj.getParent_id());
		paasTreeObj.setServer_type(treeObj.getServer_type());
		/* 查询在tb_paas_resource_tree中的entity_id */
		List entityIdList = paasTreeService.queryEntityIdList(treeObj);
		if (entityIdList != null && entityIdList.size() > 0) {
			entityObj.setEntityIdList(entityIdList);
		}
		entityObj.setEntity_type(treeObj.getServer_type());
		/* 展示页面里不显示在tb_paas_resource_tree表中已存在的实体 */
		entityObj.setPagination(this.getPaginater().initPagination(request));
		resultList = paasEntityService.queryForEntityList(entityObj);
		return "listMiddlewareEntity";
	}
	
	/**
	 * @Title:getBusniessList
	 * @Description:获取业务列表
	 * @param 
	 * @return
	 * @throws
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-9-1 下午7:54:39
	 */
	public String getBusniessList(){
		if (bmtObj == null) {
			bmtObj = new BusiManagerTree();
		}
		BusiManagerTree bmt = new BusiManagerTree();
		bmt.setName(bmtObj.getName());
		bmt.setType(bmtObj.getType());
		resultList = busiManagerTreeService.queryForList(bmt);
		jspName = this.jspName;
		return "bmtList";
	}
}

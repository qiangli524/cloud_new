package com.sitech.basd.yicloud.service.entitytree;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.dao.templettree.TempletTreeDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.QueryEventInfo;
import com.sitech.basd.yicloud.domain.entitytree.QueryTaskInfo;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

/**
 * 
 * <p>
 * Title: EntityTreeServiceImpl
 * </p>
 * <p>
 * Description: ztree树管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Apr 18, 2012 11:08:57 AM
 * 
 */
public class EntityTreeServiceImpl implements EntityTreeService {
	private EntityTreeDao entityTreeDao;
	private TempletTreeDao templetTreeDao;
	private VMHostDao vmHostDao;

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	public void setTempletTreeDao(TempletTreeDao templetTreeDao) {
		this.templetTreeDao = templetTreeDao;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj) {
		return entityTreeDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryVMForTree(EntityTreeObj obj) {
		return entityTreeDao.queryVMForTree(obj);
	}

	/**
	 * 
	 * @Title: insertTreeNode
	 * @Description: 向树中插入节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:07:09 AM
	 */
	public int insertTreeNode(EntityTreeObj obj) {
		return entityTreeDao.insertTreeNode(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 修改树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int updateTreeNode(EntityTreeObj obj) {
		// 重命名虚拟机
		/*
		 * EntityTreeObj tempObj = new EntityTreeObj();
		 * tempObj.setId(obj.getId()); tempObj =
		 * entityTreeDao.queryTreeNode(tempObj); String vmName =
		 * tempObj.getName(); int result = -1; String url =
		 * "/vmware/domain/rename/[vmName:" + vmName + "].[newName:" +
		 * obj.getName() + "]/"; String renameResult = InvokeUtil.invoke(url);
		 * Map ps = ParamParser.makeup(renameResult); String response = (String)
		 * ps.get("responseCode"); if (response.equals("1")) { result = 1;
		 * entityTreeDao.updateTreeNode(obj); }
		 */
		return entityTreeDao.updateTreeNode(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 删除树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int delTreeNode(EntityTreeObj obj) {
		return entityTreeDao.delTreeNode(obj);
	}

	/**
	 * 
	 * @Title: delSubNode
	 * @Description: 删除某一节点对应的子节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:28:29 AM
	 */
	public int delSubNode(EntityTreeObj obj) {
		return entityTreeDao.delSubNode(obj);
	}

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public EntityTreeObj queryTreeNode(EntityTreeObj obj) {
		return entityTreeDao.queryTreeNode(obj);
	}

	/**
	 * 
	 * @Title: queryHostCount
	 * @Description: 查询主机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryHostCount(EntityConObj obj) {
		return entityTreeDao.queryHostCount(obj);
	}

	/**
	 * 
	 * @Title: queryVmCount
	 * @Description: 查询虚拟机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryVmCount(EntityConObj obj) {
		return entityTreeDao.queryVmCount(obj);
	}

	/**
	 * 
	 * @Title: queryClusterCount
	 * @Description: 查询集群的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryClusterCount(EntityConObj obj) {
		return entityTreeDao.queryClusterCount(obj);
	}

	/**
	 * 
	 * @Title: queryNetOrStoreCount
	 * @Description: 查询网络或存储的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryNetOrStoreCount(EntityConObj obj) {
		return entityTreeDao.queryNetOrStoreCount(obj);
	}

	/**
	 * 
	 * @Title: queryEntityInfo
	 * @Description: 查询主机，虚拟机等实体信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public List<EntityConObj> queryEntityInfo(EntityConObj obj) {

		// return vmManagerDao.queryEntityInfo(obj);
		List<EntityConObj> lst = entityTreeDao.queryEntityInfo(obj);
		/*
		 * for (EntityConObj conObj : lst) { Class c = null; try { c = Class
		 * .forName("com.sitech.basd.yicloud.domain.entitytree.CollObj"); }
		 * catch (ClassNotFoundException e) { e.printStackTrace(); } //
		 * 调用webservice接口 String param =
		 * "/10-10-24:cs_100/kpivalue.json?kpi=CM-00-01-001-35&kpi=CM-00-01-001-32&kpi=PM-00-01-001-09&kpi=PM-00-01-001-10&kpi=CM-00-01-001-36"
		 * ; String json = InvokeMonUtil.invoke(param); if
		 * (!json.equals("error")) { // 得到返回的map LinkedHashMap<String,
		 * ArrayList<String>> map = (LinkedHashMap) JSONUtil .fromJSON(json,
		 * LinkedHashMap.class); // 遍历map,得到其中的value,其中value是数组
		 * Collection<ArrayList<String>> coll = map.values(); for
		 * (Iterator<ArrayList<String>> it = coll.iterator(); it .hasNext();) {
		 * List<CollObj> list = JSONArray.toList(JSONArray
		 * .fromObject(it.next()), c); if (list != null && list.size() == 1) {
		 * CollObj cObj = list.get(0); if
		 * (cObj.getKpiId().equals("CM-00-01-001-35")) {// 置备的空间
		 * conObj.setProvisionStore(cObj.getKpiValue()); } else if
		 * (cObj.getKpiId().equals("CM-00-01-001-32")) {// 已用空间
		 * conObj.setStore(cObj.getKpiValue()); } else if
		 * (cObj.getKpiId().equals("PM-00-01-001-09")) {// 已使用cpu
		 * conObj.setCpu(cObj.getKpiValue()); } else if
		 * (cObj.getKpiId().equals("PM-00-01-001-10")) {// 已消耗内存
		 * conObj.setMem(cObj.getKpiValue()); } else if
		 * (cObj.getKpiId().equals("CM-00-01-001-36")) {// 活动客户机内存
		 * conObj.setClientMem(cObj.getKpiValue()); } } } } }
		 */
		return lst;
	}

	/**
	 * 
	 * @Title: queryEntityInfoByObj
	 * @Description: 查询一条主机，虚拟机等实体信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public EntityConObj queryEntityInfoByObj(EntityConObj obj) {
		return entityTreeDao.queryEntityInfoByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description:获取同一集群下的主机列表
	 * @param
	 * @return List<EntityTreeObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:40:37 AM
	 */
	public List<EntityTreeObj> queryForHostList(EntityTreeObj obj) {
		return entityTreeDao.queryForHostList(obj);
	}

	/**
	 * 
	 * @Title: queryForMigHostList
	 * @Description: 获取迁移主机
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 11:21:56 AM
	 */
	public List queryForMigHostList(EntityTreeObj obj) {
		return entityTreeDao.queryForMigHostList(obj);
	}

	/**
	 * 
	 * @Title: querySolutionList
	 * @Description: 查询所有修复模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public List querySolutionList(SolutionObj obj) {
		return entityTreeDao.querySolutionList(obj);
	}

	/**
	 * 
	 * @Title: insertSolutionByObj
	 * @Description: 向修复模板列表插入数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int insertSolutionByObj(SolutionObj obj) {
		return entityTreeDao.insertSolutionByObj(obj);
	}

	/**
	 * 
	 * @Title: taskInfo
	 * @Description: 调用接口查询任务
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List taskInfo(String type, String name, int pagesize, int pagenum) {
		List<QueryTaskInfo> list = new ArrayList<QueryTaskInfo>();
		String str = null;
		if (type.equals("0")) {// vmware虚拟机
			str = "vmName";
		}
		if (type.equals("1")) {
			str = "hostName";
		}
		try {
			String url = "/vmware/domain/recenttasks/[" + str + ":" + name + "].[pageRows:"
					+ pagesize + "].[pages:" + pagenum + "]/";
			String result = InvokeUtil.invoke(url);
			if (result != null) {
				list = JSONArray.toList(JSONArray.fromObject(result), QueryTaskInfo.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			list = null;
		}

		return list;
	}

	/**
	 * 
	 * @Title: eventInfo
	 * @Description: 调用接口查询事件
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public List eventInfo(String type, String name) {
		List<QueryEventInfo> list = new ArrayList<QueryEventInfo>();
		String str = null;
		if (type.equals("0")) {// vmware虚拟机
			str = "vmName";
		}
		if (type.equals("1")) {// vmware主机
			str = "hostName";
		}
		try {
			String url = "/vmware/domain/recentevents/[" + str + ":" + name + "]/";
			String result = InvokeUtil.invoke(url);
			list = JSONArray.toList(JSONArray.fromObject(result), QueryEventInfo.class);
			if (list != null && list.size() > 0) {
				for (QueryEventInfo info : list) {
					try {
						info.setDescription(URLDecoder.decode(info.getDescription(), "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			list = null;
		}

		return list;
	}

	/**
	 * 
	 * @Title: markAsTem
	 * @Description: 将虚拟机标记为模板
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public String markAsTem(EntityTreeObj obj, String contentParent_id) {
		String name = obj.getEntityId();
		String result = "-1";
		String url = "/vmware/domain/markAsTemplate/[vmName:" + name + "]/";
		String markResult = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(markResult);
		String code = (String) ps.get("responseCode");
		if (code.equals("1")) {// 将虚拟机转化为模板成功后更改节点
			obj.setType(TypeConstant.VMWARE_IMAGE);// vmware 模板类型
			entityTreeDao.updateTreeNode(obj);

			VMHostObj vmHostObj = new VMHostObj();
			vmHostObj.setVH_UUID(name);
			vmHostObj.setVH_TYPE("5");
			vmHostDao.updateVmhostType(vmHostObj);
			result = "1";
			
			VMHostObj queryObj = new VMHostObj();
			queryObj.setVH_UUID(obj.getEntityId());
			queryObj = vmHostDao.queryByObj(queryObj);

			// 将模板写入模板树表中
			if(queryObj!=null){
				TempletTreeObj treeObj = new TempletTreeObj();
				treeObj.setName(obj.getName());
				treeObj.setTemplet_id(queryObj.getID()+"");
				treeObj.setType(2);
				treeObj.setParent_id(Integer.parseInt(contentParent_id));
				int creatMenuRet = templetTreeDao.creatMenu(treeObj);
			}
		}

		return result;
	}

	/**
	 * 
	 * @Title: moveintoCluster
	 * @Description:将主机从数据中心移到集群下
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 10, 2012 11:34:58 AM
	 */
	public String moveintoCluster(String dcName, String clusterName, EntityTreeObj obj,
			String targetId) {
		String hostName = obj.getEntityId();
		String result = null;
		try {
			String url = "/vmware/host/moveintoCluster/[dcName:" + dcName + "].[clusterName:"
					+ clusterName + "].[hostName:" + hostName + "]/";
			String moveResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(moveResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				/** 通知 */
				try {
					NoticeUtil.getInstance()
							.updateCMDBCluster(clusterName, Operation.OPER_REL_MODI);
					NoticeUtil.getInstance().updateCMDBDC(dcName, Operation.OPER_REL_MODI);// cmdb
				} catch (Exception e) {

				}
				try {
					NoticeUtil.getInstance().updateHostRelation(hostName);
				} catch (Exception ex) {

				}
				obj.setParentId(Integer.parseInt(targetId));
				obj.setType(TypeConstant.VMWARE_HOST);
				entityTreeDao.updateTreeNode(obj);
				result = "1";
			} else {
				String mark = (String) ps.get(ResponseCode.RESPONSEREMARK);
				result = mark;
			}
		} catch (Exception ex) {
			result = ex.getMessage();
		}
		return result;
	}

	/**
	 * 
	 * @Title:
	 * @Description: 更新TB_CLOUD_ENTITY_CON表数据
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int updateConObj(EntityConObj obj) {
		return entityTreeDao.updateConObj(obj);
	}

	/**
	 * 
	 * @Title: deleteConObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 26, 2012 9:58:58 AM
	 */
	public int deleteConObj(EntityConObj obj) {
		return entityTreeDao.deleteConObj(obj);
	}

	/**
	 * 
	 * @Title: insertConObj
	 * @Description: 向TB_CLOUD_ENTITY_CON表中插入数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:07:09 AM
	 */
	public int insertConObj(EntityConObj obj) {
		return entityTreeDao.insertConObj(obj);
	}

	public EntityTreeDao getEntityTreeDao() {
		return entityTreeDao;
	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	@Override
	public int updateEntityConInfo(EntityConObj obj) {
		return entityTreeDao.updateEntityConInfo(obj);
	}

	/**
	 * 
	 * @Title: queryClusterList
	 * @Description: 查询集群列表
	 * @param
	 * @return list
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 11:07:09 AM
	 */
	@Override
	public List queryClusterList(EntityTreeObj obj) {
		// TODO Auto-generated method stub
		return entityTreeDao.queryClusterList(obj);
	}

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 查询当前节点对应的主机及集群的uuid,及虚拟机uuid
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public EntityTreeObj queryForConn(EntityTreeObj obj) {
		return entityTreeDao.queryForConn(obj);
	}

	/**
	 * 
	 * @Title: queryForXenHost
	 * @Description: 查询要迁移的主机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 11:45:48 AM
	 */
	public List queryForXenHost(EntityTreeObj obj) {
		return entityTreeDao.queryForXenHost(obj);
	}

	/**
	 * 
	 * @Title: queryEntityData
	 * @Description: 查询主机在哪个数据中心下
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public List<EntityConObj> queryEntityData(EntityConObj obj) {
		return entityTreeDao.queryEntityData(obj);
	}

	/**
	 * 
	 * @Title: delTreeNodeByEntity
	 * @Description: 通过实体ID删除节点
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:28:29 AM
	 */
	public int delTreeNodeByEntity(EntityTreeObj obj) {
		return entityTreeDao.delTreeNodeByEntity(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNodeByUuid
	 * @Description: 通过实体id更新树节点数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 5:45:21 PM
	 */
	public int updateTreeNodeByUuid(EntityTreeObj obj) {
		return entityTreeDao.updateTreeNodeByUuid(obj);
	}

	/**
	 * 
	 * @Title: queryDcClHostVMRelat
	 * @Description: 查询虚拟机关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostRelat(Map<String, Object> map) {
		return entityTreeDao.queryDcClHostRelat(map);
	}

	/**
	 * 
	 * @Title: queryDcClHostRelat
	 * @Description: 查询主机关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostVMRelat(Map<String, Object> map) {
		return entityTreeDao.queryDcClHostVMRelat(map);
	}

	/**
	 * 
	 * @Title: queryDcClRelat
	 * @Description: 查询集群关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClRelat(Map<String, Object> map) {
		return entityTreeDao.queryDcClRelat(map);
	}

	/**
	 * 
	 * @Title: deleteAllData
	 * @Description:删除库中原有数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	public int deleteAllData() {
		return entityTreeDao.deleteAllData();
	}

	/**
	 * 
	 * @Title: updateNodeParentId
	 * @Description: 只更新树的父节点id
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int updateNodeParentId(EntityTreeObj obj) {
		return entityTreeDao.updateNodeParentId(obj);
	}

	/**
	 * 
	 * @Title: queryVMForAuth
	 * @Description: 查询树(供设置虚拟机权限使用)
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public List queryVMForAuth(EntityTreeObj obj) {
		return entityTreeDao.queryVMForAuth(obj);
	}

	/**
	 * 
	 * @Title: searchNodes
	 * @Description: 搜索名称或ip地址匹配的主机或虚拟机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public List searchNodes(Map map) {
		return entityTreeDao.searchNodes(map);
	}

	public List getClusterFailHost(EntityTreeObj obj) {
		return entityTreeDao.getClusterFailHost(obj);
	}
}

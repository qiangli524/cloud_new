package com.sitech.ssd.ah.paas.service.tree;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.tree.HadoopTreeService;
import util.HadoopConstant;
import util.HadoopExampleStatus;

import com.sitech.basd.component.dao.process.ProcessDao;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.ssd.ah.paas.dao.tree.PaasTreeDao;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.util.PaasConstant;

import dao.cluster.HadoopClusterDao;
import dao.host.HadoopHostInfoDao;
import dao.service.HadoopServiceNodeDao;
import dao.tree.HadoopTreeDao;
import domain.tree.HadoopTreeObj;

/**
 * 
 * <p>
 * Title: PaasTreeServiceImpl
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
 * @createtime 2014-3-28 上午9:16:43
 * 
 */
@Service("paasTreeService")
public class PaasTreeServiceImpl implements PaasTreeService {
	
	private static Logger logger = Logger.getLogger(PaasTreeServiceImpl.class); 
	@Autowired
	private PaasTreeDao paasTreeDao;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	@Autowired
	private PropertyUtil bsTreeIconProp;
	@Autowired
	private HadoopTreeDao hadoopTreeDao;
	@Autowired
	private HadoopTreeService hadoopTreeService;
	@Autowired
	private ProcessDao processDao;

	/**
	 * 
	 * @Title: queryForPaasTree
	 * @Description: 查询资源树(包括:CAAS,DAAS,MAAS,大数据)
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午5:34:20
	 */
	@Override
	public List<PaasTreeObj> queryForPaasTree(HttpServletRequest request) {
		List<PaasTreeObj> list = new ArrayList<PaasTreeObj>();
		List<HadoopTreeObj> hadoopList = new ArrayList<HadoopTreeObj>();
		PaasTreeObj tempObj = new PaasTreeObj();
		PaasTreeObj obj = new PaasTreeObj();
		String id = request.getParameter("id");
		// String name = request.getParameter("name");
		// String entity_id = request.getParameter("entity_id");
		// String parent_id = request.getParameter("parent_id");
		String node_type = request.getParameter("node_type");
		String table = request.getParameter("table");
		if(PaasConstant.TABLE_HADOOP.equals(table)){
			String rootId = "";
			HadoopTreeObj ha = new HadoopTreeObj();
			if(PaasConstant.HADOOP.equals(node_type)){
				ha.setNode_type(HadoopConstant.root);
				List<HadoopTreeObj> rootList = hadoopTreeDao.queryForListByObj(ha);
				if(rootList != null && rootList.size() > 0) {
					rootId = rootList.get(0).getId();
				}
			}else{
				rootId = id;
			}
			HadoopTreeObj hatree = new HadoopTreeObj();
			hatree.setParent_id(rootId);
			List<HadoopTreeObj> resultList = hadoopTreeDao.queryForListByObj(hatree);
			resultList = hadoopTreeService.setStatus(resultList);
			for (HadoopTreeObj u : resultList) {
				PaasTreeObj tObj = new PaasTreeObj();
				tObj.setId(u.getId());
				tObj.setName(u.getName());
				tObj.setEntity_id(u.getUuid());
				tObj.setNode_type(u.getNode_type());
				tObj.setServer_type(u.getService_type());
				tObj.setTable(PaasConstant.TABLE_HADOOP);
				// 判断是不是父节点
				HadoopTreeObj tree = new HadoopTreeObj();
				tree.setParent_id(u.getId());
				List<HadoopTreeObj> lst = hadoopTreeDao.queryForListByObj(tree);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}

				// 设置图标 update by lipengpeng
				String type = u.getNode_type();
				if (HadoopConstant.root.equals(type)) {
					tObj.setIcon(unitedTreeIconProp.getString("anhui"));
					tObj.setTitle("根节点");
				} else if (HadoopConstant.hadoop_dc.equals(type)) {
					tObj.setIcon(bsTreeIconProp.getString("busi.sys.center.png"));
					tObj.setTitle("数据中心");
				} else if (HadoopConstant.serviceNode.equals(type)) {
					tObj.setIcon(unitedTreeIconProp.getString("servicenode"));
					tObj.setTitle("服务节点");
				} else if (HadoopConstant.hostNode.equals(type)) {
					// 服务实例节点需要判断服务是否正常
					if (HadoopExampleStatus.NORMAL.equals(u.getStatus())) {
						tObj.setIcon(unitedTreeIconProp.getString("hostnode"));
						tObj.setTitle("运行正常");
					} else if (HadoopExampleStatus.SERIOUS.equals(u.getStatus())) {
						tObj.setIcon(unitedTreeIconProp.getString("vm_noexist"));
						/** 查询节点下不正常的进程,如果存在则拼接不正常信息  add by qism @20170702*/
						List<ProcessObj> processObj = processDao.querySeriousHadoopProcess(u);
						if(processObj != null){
							logger.info("异常进程的个数是：["+processObj.size()+"]个");
							String message="";
							for(ProcessObj pro : processObj){
								switch(pro.getPROCESS_STATE()){
									case 0: {message=message+pro.getPROCESS()+":无运行状态"+"\n";}
										break;
						            case 1: {message=message+pro.getPROCESS()+":异常停止 "+"\n";}
						                break;
						            case 3: {message=message+pro.getPROCESS()+":启动失败 "+"\n";}
						                break;
						            case 4: {message=message+pro.getPROCESS()+":停止失败 "+"\n";}
					                	break;
						            case 6: {message=message+pro.getPROCESS()+":实际个数与配置个数不符 "+"\n";}
				                		break;
						            case 7: {message=message+pro.getPROCESS()+":服务器无法ping通 "+"\n";}
				                		break;
						            case 8: {message=message+pro.getPROCESS()+":服务器登录失败 "+"\n";}
				                		break;
						            default:
						                break;
								}
							}
							if(message != "" && message != null){
								tObj.setTitle(message);
							}else{
								tObj.setTitle("运行正常");
							}
						}else{
							logger.info("无异常进程");
						}
					}
				} else {
					tObj.setIcon(unitedTreeIconProp.getString("cluster"));
					tObj.setTitle("集群");
				}
				list.add(tObj);
			}
		} else {
			if (id == null || "".equals(id)) {
				obj.setNode_type(PaasConstant.ROOT);
			} else {
				obj.setParent_id(id);
			}
			List<PaasTreeObj> resultList = paasTreeDao.queryForPaasTree(obj);
			for (PaasTreeObj memObj : resultList) {
				if (!PaasConstant.CAAS_HOST.equals(memObj.getNode_type())
						&& !PaasConstant.DAAS_DB_ENTITY.equals(memObj
								.getNode_type())
						&& !PaasConstant.MAAS_ENTITY.equals(memObj.getNode_type())) {
					PaasTreeObj tObj = new PaasTreeObj();
					tObj.setId(memObj.getId());
					tObj.setName(memObj.getName());
					tObj.setEntity_id(memObj.getEntity_id());
					tObj.setNode_type(memObj.getNode_type());
					tObj.setServer_type(memObj.getServer_type());
					tObj.setTitle(memObj.getName());
					// 判断是不是父节点
					if (PaasConstant.CAAS_EXAMPLE.equals(memObj.getNode_type())
							|| PaasConstant.DAAS_DB.equals(memObj.getNode_type())
							|| PaasConstant.MAAS_DOMAIN.equals(memObj
									.getNode_type())) {
						tObj.setIsParent(false);
					} else if(PaasConstant.HADOOP.equals(memObj.getNode_type())){
						tObj.setIsParent(true);
						tObj.setTable(PaasConstant.TABLE_HADOOP);
					} else{
						tempObj.setParent_id(memObj.getId());
						List<PaasTreeObj> lst = paasTreeDao
								.queryForPaasTree(tempObj);
						if (lst == null || lst.size() == 0) {
							tObj.setIsParent(false);
						}
						tObj.setTable(PaasConstant.TABLE_PAAS);
					}
					// 设置图标
					String type = memObj.getNode_type();
					if (PaasConstant.ROOT.equals(type)) {
						tObj.setIcon(unitedTreeIconProp.getString("anhui"));// 节点1
						tObj.setNocheck(true);
					} else if (PaasConstant.CAAS.equals(type)
							|| PaasConstant.DAAS.equals(type)
							|| PaasConstant.MAAS.equals(type)
							|| PaasConstant.HADOOP.equals(type)) {
						tObj.setIcon(unitedTreeIconProp.getString("datacenter"));// 节点2
						tObj.setNocheck(true);
					} else if (PaasConstant.CAAS_TYPE.equals(type)
							|| PaasConstant.DAAS_TYPE.equals(type)
							|| PaasConstant.MAAS_TYPE.equals(type)) {
						tObj.setIcon(bsTreeIconProp
								.getString("busi.sys.center.png"));// 类型
						tObj.setNocheck(true);
					} else if (PaasConstant.CAAS_BUSI.equals(type)
							|| PaasConstant.DAAS_BUSI.equals(type)
							|| PaasConstant.MAAS_APP.equals(type)) {
						tObj.setIcon(unitedTreeIconProp.getString("cluster"));// 业务
						tObj.setNocheck(true);
					} else if (PaasConstant.CAAS_EXAMPLE.equals(type)
							|| PaasConstant.DAAS_DB.equals(type)
							|| PaasConstant.MAAS_DOMAIN.equals(type)) {
						tObj.setIcon(bsTreeIconProp.getString("sys.app.png"));// 应用
						tObj.setNocheck(true);
					} else if (PaasConstant.CAAS_HOST.equals(type)
							|| PaasConstant.DAAS_DB_ENTITY.equals(type)
							|| PaasConstant.MAAS_ENTITY.equals(type)) {
						tObj.setIcon(bsTreeIconProp.getString("app.deploy.png"));// 实例
						tObj.setNocheck(true);
					}
					list.add(tObj);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForAuthTree
	 * @Description: 查询资源权限树
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午6:24:11
	 */
	@Override
	public List<PaasTreeObj> queryForAuthTree(HttpServletRequest request,
			int userId) {
		return null;
	}

	/**
	 * 
	 * @Title: queryTreeNodeByObj
	 * @Description: 通过名字查询
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-27 下午5:53:30
	 */
	@Override
	public List<PaasTreeObj> queryTreeNodeByObj(PaasTreeObj obj) {
		return paasTreeDao.queryForPaasTree(obj);
	}

	/**
	 * 
	 * @Title: queryForPaasTreeObj
	 * @Description: 查询单条数据
	 * @param
	 * @return PaasTreeObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-2 下午4:13:09
	 */
	public PaasTreeObj queryForPaasTreeObj(PaasTreeObj obj) {
		PaasTreeObj paasTreeObj = new PaasTreeObj();
		List<PaasTreeObj> list = paasTreeDao.queryForPaasTree(obj);
		if (list != null && list.size() > 0) {
			paasTreeObj = list.get(0);
		}
		return paasTreeObj;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:24
	 */
	@Override
	public String insertByObj(PaasTreeObj treeObj) {
		int ret = 0;
		String result = PaasConstant.FAIL;
		ret = paasTreeDao.insertByObj(treeObj);
		if (ret == 0) {
			result = PaasConstant.SUCCESS;
		}
		return result;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:37
	 */
	@Override
	public int updateByObj(PaasTreeObj obj) {
		return paasTreeDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:47
	 */
	@Override
	public int deleteByObj(PaasTreeObj obj) {
		return paasTreeDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title: queryNodesForResourcePool
	 * @Description: 查询资源池集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:31
	 */
	@Override
	public List<PaasTreeObj> queryNodesForResourcePool(PaasTreeObj obj) {
		return paasTreeDao.queryNodesForResourcePool(obj);
	}

	/**
	 * 
	 * @Title: queryNodesForExamples
	 * @Description: 查询实例集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:35
	 */
	@Override
	public List<PaasTreeObj> queryNodesForExamples(PaasTreeObj obj) {
		return paasTreeDao.queryNodesForExamples(obj);
	}

	/**
	 * 
	 * @Title: queryEntityIdList
	 * @Description: 查询entity_id
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:54:32
	 */
	@Override
	public List<String> queryEntityIdList(PaasTreeObj obj) {
		List<String> entityIdList = new ArrayList<String>();
		List<PaasTreeObj> treeList = paasTreeDao.queryForPaasTree(obj);
		if (treeList != null && treeList.size() > 0) {
			for (PaasTreeObj treeObj : treeList) {
				entityIdList.add(treeObj.getEntity_id());
			}
		}
		return entityIdList;
	}

	/**
	 * 
	 * @Title: acquireListForType
	 * @Description: 通过类型递归查询
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:22:09
	 */
	@Override
	public List<PaasTreeObj> acquireListForType(String node_type,
			String server_type, PaasTreeObj obj, List<PaasTreeObj> resultList)
			throws Exception {
		try {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setParent_id(obj.getId());
			if (obj.getNode_type() != null && !"".equals(obj.getNode_type())) {
				if (PaasConstant.DAAS_BUSI.equals(obj.getNode_type())
						|| PaasConstant.MAAS_APP.equals(obj.getNode_type())) {
					treeObj.setServer_type(server_type);
				}
			}
			List<PaasTreeObj> treeList = paasTreeDao.queryForPaasTree(treeObj);
			for (PaasTreeObj paasTreeObj : treeList) {
				if (node_type.equals(paasTreeObj.getNode_type())) {
					if (server_type != null && !"".equals(server_type)) {
						if (server_type.equals(paasTreeObj.getServer_type())) {
							resultList.add(paasTreeObj);
						}
					} else {
						resultList.add(paasTreeObj);
					}
				} else {
					acquireListForType(node_type, server_type, paasTreeObj,
							resultList);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}
}

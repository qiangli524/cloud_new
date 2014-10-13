package service.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.tree.HadoopTreeService;
import util.HadoopConstant;
import dao.host.HadoopHostInfoDao;
import domain.host.HadoopHostInfoObj;
import domain.tree.HadoopTreeObj;

@Service("hadoopHostInfoService")
public class HadoopHostInfoServiceImpl implements HadoopHostInfoService {

	@Autowired
	private HadoopHostInfoDao hadoopHostInfoDao;
	@Autowired
	private HadoopTreeService hadoopTreeService;

	/**
	 * @Title: queryForHostList
	 * @Description: 查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:31:57
	 */
	@Override
	public List<HadoopHostInfoObj> queryForHostList(HadoopHostInfoObj obj) {

		return hadoopHostInfoDao.queryForHostList(obj);
	}

	/**
	 * @Title: queryForListUnderServiceNode
	 * @Description: 查询服务节点下的主机
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:31:57
	 */
	@Override
	public List<HadoopHostInfoObj> queryForListUnderServiceNode(
			HadoopTreeObj hadoopTreeObj) {
		return hadoopHostInfoDao.queryForListUnderServiceNode(hadoopTreeObj);
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条主机信息
	 * @param
	 * @return HadoopHostInfoObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-8 上午11:32:55
	 */
	@Override
	public HadoopHostInfoObj queryByObj(HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.queryByObj(obj);
	}

	@Override
	public List<HadoopHostInfoObj> queryForHadoopHostLists(HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.queryForHadoopHostLists(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 上午11:20:09
	 */
	@Override
	public int insertByObj(HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: updHostInfoByObj
	 * @Description: 修改主机管理信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午2:35:39
	 */
	@Override
	public int updHostInfoByObj(HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.updHostInfoByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午2:58:40
	 */
	@Override
	public int deleteByObj(HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.deleteByObj(obj);
	}

	/**
	 * 查询主机和集群的关系
	 * 
	 * @Title: queryForHadoopHostAndClusterInfoObj
	 * @param
	 * @return HadoopHostInfoObj
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-15 下午4:31:40
	 */
	@Override
	public HadoopHostInfoObj queryForHadoopHostAndClusterInfoObj(
			HadoopHostInfoObj obj) {
		return hadoopHostInfoDao.queryForHadoopHostAndClusterInfoObj(obj);
	}

	/**
	 * 
	 * @Title: queryHostInfoByNode
	 * @Description: 通过节点查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:57:21
	 */
	@Override
	public List<HadoopHostInfoObj> queryHostInfoByNode(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> hostList = new ArrayList<HadoopHostInfoObj>();
		HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
		hadoopTreeObj.setParent_id(obj.getNode_id());// 通过节点ID 查询该节点下的 子节点ID
		List nodeIdList = new ArrayList();
		if (HadoopConstant.hostNode.equals(obj.getNode_type())) {// 主机节点
			hostList = hadoopHostInfoDao.queryHostInfoByHostNode(obj);
		} else if (HadoopConstant.serviceNode.equals(obj.getNode_type())) {// 服务节点
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(hadoopTreeObj);
			if (treeList != null && treeList.size() > 0) {
				for (HadoopTreeObj treeObj : treeList) {
					nodeIdList.add(treeObj.getId());
				}
			}
			obj.setNodeIdList(nodeIdList);
			hostList = hadoopHostInfoDao.queryHostInfoByOtherNode(obj);
		} else if (HadoopConstant.hadoop_cluster.equals(obj.getNode_type())) {// hadoop_cluster节点
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(hadoopTreeObj);
			for (HadoopTreeObj treeObj : treeList) {
				HadoopTreeObj obj1 = new HadoopTreeObj();
				obj1.setParent_id(treeObj.getId());
				List<HadoopTreeObj> treeList1 = hadoopTreeService
						.queryForListByObj(obj1);
				for (HadoopTreeObj treeObj1 : treeList1) {
					HadoopTreeObj obj2 = new HadoopTreeObj();
					obj2.setParent_id(treeObj1.getId());
					List<HadoopTreeObj> treeList2 = hadoopTreeService
							.queryForListByObj(obj2);
					if (treeList2 != null && treeList2.size() > 0) {
						for (HadoopTreeObj obj3 : treeList2) {
							nodeIdList.add(obj3.getId());
						}
					}
				}
			}
			obj.setNodeIdList(nodeIdList);
			hostList = hadoopHostInfoDao.queryHostInfoByOtherNode(obj);
		} else if (HadoopConstant.hadoop_dc.equals(obj.getNode_type())) { // 数据中心节点
			obj.setCluster_id(obj.getId());
			hostList = hadoopHostInfoDao.queryHostInfoByDCNode(obj);
		} else {// 其他节点
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(hadoopTreeObj);
			for (HadoopTreeObj treeObj : treeList) {
				HadoopTreeObj obj1 = new HadoopTreeObj();
				obj1.setParent_id(treeObj.getId());
				List<HadoopTreeObj> treeList1 = hadoopTreeService
						.queryForListByObj(obj1);
				if (treeList1 != null && treeList1.size() > 0) {
					for (HadoopTreeObj treeObj1 : treeList1) {
						nodeIdList.add(treeObj1.getId());
					}
				}
			}
			obj.setNodeIdList(nodeIdList);
			hostList = hadoopHostInfoDao.queryHostInfoByOtherNode(obj);
		}
		return hostList;
	}

	/**
	 * @Title: queryForExampleList
	 * @Description: 查询节点列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-20 下午7:34:43
	 */
	@Override
	public List<HadoopHostInfoObj> queryForExampleList(
			HadoopHostInfoObj hadoopHostInfoObj) {
		return hadoopHostInfoDao.queryForExampleList(hadoopHostInfoObj);
	}

	/**
	 * @Title: queryForHostListJoinColl
	 * @Description: 联合监控表查询
	 * @param
	 * @return List
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:28:34
	 */
	@Override
	public List queryForHostListJoinColl(HadoopHostInfoObj hostForm) {
		return hadoopHostInfoDao.queryForHostListJoinColl(hostForm);
	}
	
	/**
	 * 
	 * @Title: queryHostList
	 * @Description: 查询主机信息
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-9 下午7:48:00
	 */
	@Override
	public List<HadoopHostInfoObj> queryHostList(HadoopHostInfoObj obj){
		return hadoopHostInfoDao.queryHostList(obj);
	}

	/**
	 * 
	 * @Title: queryAllHostForTree
	 * @Description: 查询树上的所有主机
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-15 下午5:27:34
	 */
	public List<HadoopHostInfoObj> queryAllHostForTree(HadoopTreeObj obj) {
		return hadoopHostInfoDao.queryAllHostForTree(obj);
	}
}

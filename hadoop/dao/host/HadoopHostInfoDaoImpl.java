package dao.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.host.HadoopHostInfoObj;
import domain.tree.HadoopTreeObj;

/**
 * <p>
 * Title: HadoopHostInfoDaoImpl
 * </p>
 * <p>
 * Description: hadoop主机持久层实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午10:33:12
 * 
 */
@Repository("hadoopHostInfoDao")
public class HadoopHostInfoDaoImpl extends BaseDao implements HadoopHostInfoDao {

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
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryForHostListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("HadoopHostInfo.queryForHostList",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryForHostList:"
					+ sqlexception.getMessage() + getClass().getName());
		}

		return lst;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopHostInfoObj> queryForListUnderServiceNode(
			HadoopTreeObj hadoopTreeObj) {
		List<HadoopHostInfoObj> list = new ArrayList<HadoopHostInfoObj>();
		try {
			list = getSqlMap().queryForList(
					"HadoopHostInfo.queryForListUnderServiceNode",
					hadoopTreeObj);
		} catch (Exception e) {
			LogHelper.error("HadoopHostInfo.queryForListUnderServiceNode: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("HadoopHostInfo.insertByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HadoopHostInfo.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
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
		HadoopHostInfoObj infoObj = new HadoopHostInfoObj();
		try {
			infoObj = (HadoopHostInfoObj) getSqlMap().queryForObject(
					"HadoopHostInfo.queryByObj", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopHostInfo.queryByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return infoObj;
	}
	@Override
	public List<HadoopHostInfoObj> queryForHadoopHostLists(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> list = new ArrayList<HadoopHostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryForHadoopHostCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopHostInfo.queryForHadoopHostLists", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopHostInfo.queryForHadoopHostLists: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public HadoopHostInfoObj queryForHadoopHostAndClusterInfoObj(
			HadoopHostInfoObj obj) {
		HadoopHostInfoObj obj1 = new HadoopHostInfoObj();
		try {
			obj1 = (HadoopHostInfoObj) getSqlMap().queryForObject(
					"HadoopHostInfo.queryForHadoopHostAndClusterInfoObj", obj);
		} catch (Exception e) {
			LogHelper
					.error("HadoopHostInfo.queryForHadoopHostAndClusterInfoObj: "
							+ e.getMessage() + e.getClass().getName());
		}
		return obj1;
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午3:58:59
	 */
	@Override
	public int updateByObj(HadoopHostInfoObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("HadoopHostInfo.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HadoopHostInfo.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object ob = getSqlMap().update("HadoopHostInfo.updHostInfoByObj",
					obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HadoopHostInfo.updHostInfoByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("HadoopHostInfo.deleteByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HadoopHostInfo.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryHostInfoByNode
	 * @Description: 通过服务实例节点查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:57:21
	 */
	@Override
	public List<HadoopHostInfoObj> queryHostInfoByHostNode(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryHostInfoByHostNodeCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"HadoopHostInfo.queryHostInfoByHostNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryHostInfoByHostNode:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryHostInfoByOtherNode
	 * @Description: 通过其他节点查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午11:33:49
	 */
	@Override
	public List<HadoopHostInfoObj> queryHostInfoByOtherNode(
			HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryHostInfoByOtherNodeCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"HadoopHostInfo.queryHostInfoByOtherNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryHostInfoByOtherNode:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: queryHostInfoByDCNode
	 * @Description: 查询数据中心节点的主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 下午3:46:58
	 */
	@Override
	public List<HadoopHostInfoObj> queryHostInfoByDCNode(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryHostInfoByDCNodeCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"HadoopHostInfo.queryHostInfoByDCNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryHostInfoByDCNode:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryLogHostInfoList
	 * @Description: jvm 中查询 log输出 error和fatal的次数的主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-18 下午5:28:26
	 */
	@Override
	public List<HadoopHostInfoObj> queryLogHostInfoList(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"HadoopHostInfo.queryLogHostInfoListCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"HadoopHostInfo.queryLogHostInfoList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryLogHostInfoList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopHostInfoObj> queryForExampleList(
			HadoopHostInfoObj hadoopHostInfoObj) {
		List<HadoopHostInfoObj> list = new ArrayList<HadoopHostInfoObj>();
		try {
			if (hadoopHostInfoObj.getPagination() != null) {
				hadoopHostInfoObj.setFIRSTROWNUM(hadoopHostInfoObj
						.getPagination().getFirstRownum());
				hadoopHostInfoObj.setPAGESIZE(hadoopHostInfoObj.getPagination()
						.getPageSize());
				hadoopHostInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryForExampleCount",
								hadoopHostInfoObj)).intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopHostInfo.queryForExampleList", hadoopHostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HadoopHostInfo.queryForExampleList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
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
	@SuppressWarnings("unchecked")
	@Override
	public List queryForHostListJoinColl(HadoopHostInfoObj hostForm) {
		List<HadoopHostInfoObj> list = new ArrayList<HadoopHostInfoObj>();
		try {
			if (hostForm.getPagination() != null) {
				hostForm.setFIRSTROWNUM(hostForm.getPagination()
						.getFirstRownum());
				hostForm.setPAGESIZE(hostForm.getPagination().getPageSize());
				hostForm.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.countForHostListJoinColl",
								hostForm)).intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopHostInfo.queryForHostListJoinColl", hostForm);
		} catch (Exception e) {
			LogHelper.error("HadoopHostInfo.queryForHostListJoinColl: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
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
	public List<HadoopHostInfoObj> queryHostList(HadoopHostInfoObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopHostInfo.queryHostListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("HadoopHostInfo.queryHostList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryHostList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
	@Override
	public List<HadoopHostInfoObj> queryAllHostForTree(HadoopTreeObj obj) {
		List<HadoopHostInfoObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"HadoopHostInfo.queryAllHostForTree", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryAllHostForTree:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}

package dao.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.service.DataNodeObj;
import domain.service.HadoopServiceNodeObj;
import domain.service.NodeManagerObj;

/**
 * <p>
 * Title: HadoopServiceNodeDaoImpl
 * </p>
 * <p>
 * Description: 服务节点持久层实现
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
 * @createtime 2014-1-6 下午3:18:03
 * 
 */
@Repository("hadoopServiceNodeDao")
public class HadoopServiceNodeDaoImpl extends BaseDao implements HadoopServiceNodeDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午3:17:46
	 */
	@Override
	public int insertByObj(HadoopServiceNodeObj hadoopServiceNodeObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().insert("ServiceNode.insertByObj", hadoopServiceNodeObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("ServiceNode.insertByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForDataNodeList
	 * @Description: 查询datanode服务对应的服务实例状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 下午7:58:03
	 */
	public List queryForDataNodeList(DataNodeObj data) {
		List lst = null;
		try {
			if (data.getPagination() != null) {
				data.setFIRSTROWNUM(data.getPagination().getFirstRownum());
				data.setPAGESIZE(data.getPagination().getPageSize());
				data.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ServiceNode.queryForDataNodeListCount", data)).intValue());
			}
			lst = getSqlMap().queryForList("ServiceNode.queryForDataNodeList", data);
		} catch (Exception sqlexception) {
			LogHelper.error("ServiceNode.queryForDataNodeList:" + sqlexception.getMessage()
					+ getClass().getName());
		}

		return lst;
	}

	/**
	 * 
	 * @Title: queryForNodeManagerList
	 * @Description: 查询nodemanager服务对应的服务实例状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 下午7:58:58
	 */
	public List queryForNodeManagerList(NodeManagerObj node) {
		List lst = null;

		try {
			if (node.getPagination() != null) {
				node.setFIRSTROWNUM(node.getPagination().getFirstRownum());
				node.setPAGESIZE(node.getPagination().getPageSize());
				node.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ServiceNode.queryForNodeManagerListCount", node)).intValue());
			}
			lst = getSqlMap().queryForList("ServiceNode.queryForNodeManagerList", node);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopHostInfo.queryForNodeManagerList:" + sqlexception.getMessage()
					+ getClass().getName());
		}

		return lst;

	}
}

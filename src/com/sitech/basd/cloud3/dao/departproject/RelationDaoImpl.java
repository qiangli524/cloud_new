package com.sitech.basd.cloud3.dao.departproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@Repository("relationDao")
public class RelationDaoImpl extends BaseDao implements RelationDao {
	/**
	 * 
	 * @Title: querySelectedVMList
	 * @Description: 查询已选的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List<VMHostObj> querySelectedVMList(VMHostObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"proAndVMrelation.querySelectedVMListCount", obj)).intValue());
				list = getSqlMap().queryForList("proAndVMrelation.querySelectedVMList", obj);
			}
		} catch (Exception sqlException) {
			LogHelper.error("proAndVMrelation.querySelectedVMList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryRemainVMList
	 * @Description: 查询备选虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List queryRemainVMList(VMHostObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"proAndVMrelation.queryRemainVMListCount", obj)).intValue());
				list = getSqlMap().queryForList("proAndVMrelation.queryRemainVMList", obj);
			}
		} catch (Exception sqlException) {
			LogHelper.error("proAndVMrelation.queryRemainVMList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除虚拟机与项目的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-4
	 */
	@Override
	public int deleteRelation(RelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("proAndVMrelation.deleteRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("proAndVMrelation.deleteRelation:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: insertRelation
	 * @Description: 插入到项目和虚拟机关系表中
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public int insertRelation(RelationObj obj) {
		String relationId = RandomUUID.getUuid();
		obj.setRelatonId(relationId);
		int ret = 0;
		try {
			Object o = getSqlMap().insert("proAndVMrelation.insertRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("proAndVMrelation.insertRelation:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryVMByProjectId
	 * @Description: 根据用户项目查询对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List queryVMByProjectId(VMHostObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"proAndVMrelation.queryVMByProjectIdCount", obj)).intValue());
				list = getSqlMap().queryForList("proAndVMrelation.queryVMByProjectId", obj);
			}
		} catch (Exception e) {
			LogHelper.error("proAndVMrelation.queryVMByProjectId:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByPro
	 * @Description: 通过项目查询虚拟机标识和链接标识
	 * @param
	 * @return List<RelationObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午6:55:42
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RelationObj> queryForListByPro(RelationObj relationObj) {
		List<RelationObj> list = new ArrayList<RelationObj>();
		try {
			list = getSqlMap().queryForList("proAndVMrelation.queryForListByPro", relationObj);
		} catch (Exception e) {
			LogHelper.error("proAndVMrelation.queryForListByPro: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
}

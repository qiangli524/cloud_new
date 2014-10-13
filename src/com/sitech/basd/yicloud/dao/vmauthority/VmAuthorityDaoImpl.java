package com.sitech.basd.yicloud.dao.vmauthority;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;

public class VmAuthorityDaoImpl extends BaseDao implements VmAuthorityDao {
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 向TB_CLOUD_ENTITYUSER表插入数据
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int insertByObj(VmAuthorityObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VmAuthority.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("VmAuthority.insertByObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除TB_CLOUD_ENTITYUSER表数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int deleteByObj(VmAuthorityObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VmAuthority.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("VmAuthority.deleteByObj" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询TB_CLOUD_ENTITYUSER列表
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public List queryForList(VmAuthorityObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("VmAuthority.queryForList", obj);
		} catch (Exception e) {
			LogHelper.error("VmAuthority.queryForList:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description:更新TB_CLOUD_ENTITYUSER列表
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int updateByObj(VmAuthorityObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VmAuthority.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("VmAuthority.deleteByObj" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更具用户查询其对应的资源
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public List queryResourceByAccount(VmAuthorityObj obj) {
		List list = null;
		try {
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"VmAuthority.queryResourceByAccountForCount",obj)).intValue());
				list = getSqlMap().queryForList("VmAuthority.queryResourceByAccount", obj);
			}
		} catch (Exception e) {
			LogHelper.error("VmAuthority.queryResourceByAccount:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 根据普通用户查询其对应的所有资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryAllCountResource(VmAuthorityObj obj) {
		RelationObj userObj = null;
		try {
			userObj = (RelationObj) getSqlMap().queryForObject("VmAuthority.queryAllCountResource", obj);
		} catch (Exception e) {
			LogHelper.error("VmAuthority.queryAllCountResource" + e.getMessage() + getClass().getName());
		}
		return userObj;
	}
	/**
	 * 
	 * @Title: queryUsedCountResource
	 * @Description: 根据普通用户查询其对应的已用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	@Override
	public RelationObj queryUsedCountResource(VmAuthorityObj obj) {
		RelationObj userObj = null;
		try {
			userObj = (RelationObj) getSqlMap().queryForObject("VmAuthority.queryUsedCountResource", obj);
		} catch (Exception e) {
			LogHelper.error("VmAuthority.queryUsedCountResource" + e.getMessage() + getClass().getName());
		}
		return userObj;
	}
	/**
	 * 
	 * @Title: queryAllVMListByAccount
	 * @Description:  根据普通用户查询其对应项目的所有虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-07
	 */
	public List queryAllVMListByAccount(RelationObj obj) {
		List list = null;
		try {
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"VmAuthority.queryAllVMListByAccountCount",obj)).intValue());
				list = getSqlMap().queryForList("VmAuthority.queryAllVMListByAccount", obj);
			}
		} catch (Exception e) {
			LogHelper.error("VmAuthority.queryAllVMListByAccount:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}

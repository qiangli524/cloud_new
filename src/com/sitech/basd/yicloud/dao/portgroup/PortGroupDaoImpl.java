package com.sitech.basd.yicloud.dao.portgroup;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;

public class PortGroupDaoImpl extends BaseDao implements PortGroupDao {
	/**
	 * @Title:插入端口组信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(PortGroup obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("portgroup.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("portgroup.insertByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询虚拟交换机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryPGListByPortGroup(PortGroup obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"portgroup.queryPGCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("portgroup.queryPGList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("portgroup.queryPGList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:修改端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByObj(PortGroup obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("portgroup.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("portgroup.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByObj(PortGroup obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("portgroup.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("portgroup.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据端口组ID查询端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public PortGroup queryPortGroupById(PortGroup obj) {
		PortGroup pgObj = null;
		try {
			Object o = getSqlMap().queryForObject(
					"portgroup.queryPortGroupById", obj);
			if (o != null) {
				pgObj = (PortGroup) o;
			}
		} catch (Exception sqlException) {
			LogHelper.error("portgroup.queryPortGroupById:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return pgObj;
	}

	/**
	 * @Title:查询主机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<NicRelationObj> queryHostPortGroup(VirtualSwitch obj) {
		List<NicRelationObj> lst = null;
		try {
			lst = getSqlMap().queryForList("portgroup.queryHostPortGroup", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("portgroup.queryHostPortGroup:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: createNetWork
	 * @Description: 创建vmware网络
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 18, 2013 5:54:15 PM
	 */
	public int createNetWork(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("portgroup.createNetWork", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("portgroup.createNetWork:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteVirtualSwitch
	 * @Description: 删除虚拟交换机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteVirtualSwitch(VirtualSwitch obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("portgroup.deleteVirtualSwitch", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("portgroup.deleteVirtualSwitch:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByVswitchid
	 * @Description: 删除一个交换机下的端口组
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByVswitchid(VirtualSwitch obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("portgroup.deleteByVswitchid", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("portgroup.deleteByVswitchid:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}

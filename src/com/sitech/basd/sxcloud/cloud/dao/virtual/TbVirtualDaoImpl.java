package com.sitech.basd.sxcloud.cloud.dao.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbVirtualDaoImpl extends BaseDao implements TbVirtualDao {
	/**
	 * @Title:查询已有虚拟机列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2VirtualInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbVirtual.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbVirtual.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbVirtual.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbVirtual.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtual.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbVirtual.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtual.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个虚拟机对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TbCloud2VirtualInfoObj queryByObj(TbCloud2VirtualInfoObj obj) {
		List lst = null;
		TbCloud2VirtualInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2VirtualInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新虚拟机信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbVirtual.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtual.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:移至项目更新PROJECT_ID
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByProjectIdObj(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.update("TbVirtual.updateByProjectIdObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtual.updateByProjectIdObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:启动停止虚拟机
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int StartAndStopByObj(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbVirtual.startAndStopByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtual.startAndStopByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询虚拟机状态信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public List queryListIDByObj(TbCloud2VirtualInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbVirtual.queryListIDByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbVirtual.queryListIDByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询虚拟机详细信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryVirtualServerByObj(TbCloud2VirtualInfoObj obj) {
		return null;
	}

	/**
	 * 
	 * @Title: 获取所有虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForAllListByObj(TbCloud2VirtualInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbVirtual.queryForAllListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbVirtual.queryForAllListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title:通过多个Id获取对应虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2VirtualInfoObj> queryVirtualList(String id) {
		List<TbCloud2VirtualInfoObj> list = null;
		try {
			list = getSqlMap().queryForList("TbVirtual.queryVirtualList", id);
		} catch (Exception sqlexception) {
			LogHelper.error("TbVirtual.queryVirtualList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

}

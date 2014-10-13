package com.sitech.basd.sxcloud.cloud.dao.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbCloud2VirtualConfigDaoImpl extends BaseDao implements
		TbCloud2VirtualConfigDao {

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:24 PM
	 */
	public int deleteByObj(TbCloud2VirtualConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbCloud2VirtualConfig.deleteByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2VirtualConfig.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:31 PM
	 */
	public int insertByObj(TbCloud2VirtualConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbCloud2VirtualConfig.insertByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2VirtualConfig.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询出具体虚拟机配置信息
	 * @param
	 * @return TbCloud2VirtualConfigObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:08 PM
	 */
	public TbCloud2VirtualConfigObj queryByObj(TbCloud2VirtualConfigObj obj) {
		List lst = null;
		TbCloud2VirtualConfigObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2VirtualConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 根据虚拟机配置部分信息查询匹配的所有虚拟机配置信息
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:32:51 PM
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2VirtualConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"TbCloud2VirtualConfig.queryByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbCloud2VirtualConfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud2VirtualConfig.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:15 PM
	 */
	public int updateByObj(TbCloud2VirtualConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbCloud2VirtualConfig.updateByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2VirtualConfig.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}

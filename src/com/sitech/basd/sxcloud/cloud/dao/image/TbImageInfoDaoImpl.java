package com.sitech.basd.sxcloud.cloud.dao.image;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbImageInfoDaoImpl extends BaseDao implements TbImageInfoDao {
	/**
	 * @Title:查询已有映像列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForListByObj(TbCloud2ImageInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbImageInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("TbImageInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbImageInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2ImageInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbImageInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbImageInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2ImageInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbImageInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbImageInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个映像对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2ImageInfoObj queryByObj(TbCloud2ImageInfoObj obj) {
		List lst = null;
		TbCloud2ImageInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2ImageInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新映像信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2ImageInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbImageInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbImageInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询已有虚拟镜像列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForTypeList(TbCloud2ImageInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbImageInfo.queryForTypeList", obj);
		}

		catch (Exception sqlexception) {
			LogHelper.error("TbImageInfo.queryForTypeList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: updateName
	 * @Description: 只更新镜像名称
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:51:31 PM
	 */
	public int updateName(TbCloud2ImageInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbImageInfo.updateName", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbImageInfo.updateName:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title:查询虚拟机镜像
	 * @Copyright: Copyright (c) 2012-9-14
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryVMHostByVMObj(VMHostObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbImageInfo.queryVMHostByVMObj", obj);
		}

		catch (Exception sqlexception) {
			LogHelper.error("TbImageInfo.queryVMHostByVMObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}

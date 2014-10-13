package com.sitech.basd.sxcloud.cloud.dao.net;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DomainUtil;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
public class TbNetDaoImpl extends BaseDao implements TbNetDao {
	/**
	 * @Title:查询已有网络列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForListByObj(TbCloud2NetInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbNet.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbNet.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbNet.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2NetInfoObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setNET_ID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbNet.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbNet.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2NetInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbNet.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbNet.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个网络对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TbCloud2NetInfoObj queryByObj(TbCloud2NetInfoObj obj) {
		List lst = null;
		TbCloud2NetInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2NetInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新网络信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2NetInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbNet.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbNet.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:为下拉列表查询NET表
	 * @Copyright: Copyright (c) 20120104
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryByNetObjForList(TbCloud2NetInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbNet.queryByNetObjForList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbNet.queryByNetObjForList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: getNetResource
	 * @Description: 获取网络资源信息
	 * @param
	 * @return Map
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 21, 2013 2:00:58 PM
	 */
	public List getNetResource() {
		List map = new ArrayList();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			DomainUtil.setDomainToMap(paramMap);
			map = (List) getSqlMap().queryForList("TbNet.netResourceByType",paramMap);

		} catch (Exception sqlexception) {
			LogHelper.error("TbNet.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return map;
	}
	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2013-09-12
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public String insertNet(TbCloud2NetInfoObj obj){
		String id = RandomUUID.getUuid();
		obj.setNET_ID(id);
		try {
			Object o = getSqlMap().insert("TbNet.insertByObj", obj);
		} catch (SQLException sqlException) {
			LogHelper.error("TbNet.insertByObj:" + sqlException.getMessage());
		}
		return id;
	}

	/**
	 * @Title: queryForListByDomainId
	 * @Description: 通过网络域id查询vlan信息
	 * @param
	 * @return List<TbCloud2NetInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:52:38
	 */
	@Override
	public List<TbCloud2NetInfoObj> queryForListByDomainId(String domainid) {
		List<TbCloud2NetInfoObj> list = new ArrayList<TbCloud2NetInfoObj>();
		try {
			list = getSqlMap().queryForList("TbNet.queryForListByDomainId",domainid);
		} catch (Exception e) {
			LogHelper.error("TbNet.queryForListByDomainId: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}

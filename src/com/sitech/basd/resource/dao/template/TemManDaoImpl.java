package com.sitech.basd.resource.dao.template;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@Repository("temManDao")
public class TemManDaoImpl extends BaseDao implements TemManDao {
	@Autowired
	private ReourceInDomainUtil reourceInDomainUtil;

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public List queryForList(TemManObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TemMan.queryByObjForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("TemMan.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("TemMan.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return TemManObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public TemManObj queryOneTemManObj(TemManObj obj) {
		TemManObj result = null;
		List<TemManObj> list = queryForList(obj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更新一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int updateByObj(TemManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TemMan.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemMan.updateByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 删除一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int deleteByObj(TemManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TemMan.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemMan.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 插入一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int insertByObj(TemManObj obj) {
		int ret = 0;
		try {
			if (obj.getDomain() == null || "".equals(obj.getDomain())) {
				/*
				 * 用于自动同步数据使用
				 */
				obj.setDomain(reourceInDomainUtil.initResourceDomainBySessionAndConfig(obj
						.getConnectId()));
			}
			// 设置UUID
			obj.setId(RandomUUID.getUuid());
			Object o = getSqlMap().insert("TemMan.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemMan.insertByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByHostCodeAndTemplateCode
	 * @Description: 根据模板及连接Code删除信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:23:58
	 */
	public int deleteByHostCodeAndTemplateCode(TemManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TemMan.deleteByHostCodeAndTemplateCode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemMan.deleteByHostCodeAndTemplateCode:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByHostCodeAndTemplateCode
	 * @Description: 根据模板及连接Code更新信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:24:31
	 */
	public int updateByHostCodeAndTemplateCode(TemManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TemMan.updateByHostCodeAndTemplateCode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemMan.updateByHostCodeAndTemplateCode:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: getAllTemplateEntity
	 * @Description:获取所有唯一实体列表（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-3 上午10:09:59
	 */
	public List<String> getAllTemplateEntity(TemManObj obj) {
		List<String> list = null;
		try {
			list = getSqlMap().queryForList("TemMan.getAllTemplateEntity", obj);
		} catch (Exception sqlException) {
			LogHelper.error("TemMan.getAllTemplateEntity:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<TemManObj> queryTemListById(TemManObj obj) {
		List<TemManObj> list = null;
		try {
			list = getSqlMap().queryForList("TemMan.queryTemListById", obj);
		} catch (Exception sqlException) {
			LogHelper.error("TemMan.queryTemListById:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @see com.sitech.basd.resource.dao.template.TemManDao#queryForCount(com.sitech.basd.resource.domain.template.TemManObj)
	 */
	@Override
	public Integer queryForCount(TemManObj obj) throws SQLException {
		return (Integer) (getSqlMap().queryForObject("TemMan.queryByObjForCount", obj));
	}

	@Override
	public List<String> queryTemSystemList() throws SQLException {
		return getSqlMap().queryForList("TemMan.queryTemSystemList");
	}

	@Override
	public List<TemManObj> queryTemBySystem(String systemName) throws SQLException {
		return getSqlMap().queryForList("TemMan.queryTemBySystem", systemName);
	}

}

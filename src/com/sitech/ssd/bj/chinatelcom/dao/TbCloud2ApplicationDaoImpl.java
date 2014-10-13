package com.sitech.ssd.bj.chinatelcom.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj;

/**
 * <p>Title: TbCloud2ApplicationDaoImpl</p>
 * <p>Description: 数据接口实现类，对应于表tb_cloud2_application</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipp
 * @version 1.0
 * @createtime 2014-3-20 下午4:50:29
 *
 */
@Repository("tbCloud2ApplicationDao")
public class TbCloud2ApplicationDaoImpl extends BaseDao implements
		TbCloud2ApplicationDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<TbCloud2ApplicationObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:45:44
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2ApplicationObj> queryForListByObj(
			TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		List<TbCloud2ApplicationObj> list = new ArrayList<TbCloud2ApplicationObj>();
		try {
			if (tbCloud2ApplicationObj.getPagination() != null) {
				tbCloud2ApplicationObj.setFIRSTROWNUM(tbCloud2ApplicationObj.getPagination().getFirstRownum());
				tbCloud2ApplicationObj.setPAGESIZE(tbCloud2ApplicationObj.getPAGESIZE());
				tbCloud2ApplicationObj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject("TbCloud2Application.queryForCountByObj", tbCloud2ApplicationObj)).intValue()
				);
			}
			list = getSqlMap().queryForList("TbCloud2Application.queryForListByObj", tbCloud2ApplicationObj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2Application.queryForListByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录，如果传入的是id的集合，则批量删除
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:45:57
	 */
	@Override
	public int deleteByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		int ret = 0;
		try {
			getSqlMap().delete("TbCloud2Application.deleteByObj", tbCloud2ApplicationObj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2Application.deleteByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:46:27
	 */
	@Override
	public int insertByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		int ret = 0;
		try {
			getSqlMap().insert("TbCloud2Application.insertByObj", tbCloud2ApplicationObj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2Application.insertByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:46:41
	 */
	@Override
	public int updateByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		int ret = 0;
		try {
			getSqlMap().update("TbCloud2Application.updateByObj", tbCloud2ApplicationObj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2Application.updateByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryForCountByObj
	 * @Description: 查询记录条数
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:49:13
	 */
	@Override
	public int queryForCountByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		int ret = 0;
		try {
			ret = (Integer) getSqlMap().queryForObject("TbCloud2Application.queryForCountByObj", tbCloud2ApplicationObj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2Application.queryForCountByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

}

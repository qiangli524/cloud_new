package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.HostAdapter;

/**
  * @Title: 主机网络适配器DAO实现类
  * @Description:
  * 	查询出的数据比较少，不进行分页
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午05:36:50
 */
@Repository("osHostAdapterDao")
public class OsHostAdapterDaoImpl extends BaseDao implements OsHostAdapterDao {

	@Override
	public int insertHostAdapter(HostAdapter adapter) {
		int ret = 0;
		try {
			getSqlMap().insert("HostAdapter.insertHostAdapter", adapter);
		} catch (SQLException sqlEx) {
			ret = -1;
			LogHelper.error("HostAdapter.insertHostAdapter:" + sqlEx.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteHostAdapter(HostAdapter adapter) {
		int ret = 0;
		try {
			ret = getSqlMap().delete("HostAdapter.deleteHostAdapter", adapter);
		} catch (SQLException sqlEx) {
			ret = -1;
			LogHelper.error("HostAdapter.deleteHostAdapter:" + sqlEx.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateHostAdapter(HostAdapter adapter) {
		int ret = 0;
		try {
			ret = getSqlMap().delete("HostAdapter.updateHostAdapter", adapter);
		} catch (SQLException sqlEx) {
			ret = -1;
			LogHelper.error("HostAdapter.updateHostAdapter:" + sqlEx.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HostAdapter> selectHostAdapterList(HostAdapter adapter) {
		List<HostAdapter> ret = null; 
		try {
			ret = (List<HostAdapter>)getSqlMap().queryForList("HostAdapter.selectHostAdapterList", adapter);
		} catch (SQLException sqlEx) {
			LogHelper.error("HostAdapter.selectHostAdapterList:" + sqlEx.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}

package com.sitech.basd.cloud3.dao.departproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("departInfoDao")
public class DepartInfoDaoImpl extends BaseDao implements DepartInfoDao{

	/**
	 * @Title: queryForList
	 * @Description: 查询部门集合
	 * @param
	 * @return List<DepartInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-18 上午10:03:13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartInfoObj> queryForList(DepartInfoObj departInfoObj) {
		List<DepartInfoObj> list = new ArrayList<DepartInfoObj>();
		try {
			list = sqlMapClient.queryForList("DepartInfo.queryForList", departInfoObj);
		} catch (Exception e) {
			LogHelper.error("DepartInfo.queryForList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}

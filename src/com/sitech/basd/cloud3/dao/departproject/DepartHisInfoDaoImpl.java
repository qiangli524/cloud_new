package com.sitech.basd.cloud3.dao.departproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartHisInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("departHisInfoDao")
public class DepartHisInfoDaoImpl extends BaseDao implements DepartHisInfoDao {

	@Override
	public DepartHisInfoObj queryOneObj(DepartHisInfoObj departHisInfoObj) {
		DepartHisInfoObj departHisInfoObj2 = new DepartHisInfoObj();
		List<DepartHisInfoObj> list = queryForList(departHisInfoObj);
		if (list.size() > 0) {
			departHisInfoObj2 = list.get(0);
		}
		return departHisInfoObj2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DepartHisInfoObj> queryForList(DepartHisInfoObj departHisInfoObj) {
		List<DepartHisInfoObj> list = new ArrayList<DepartHisInfoObj>();
		try {
			list = sqlMapClient.queryForList("DepartHisInfo.queryForList", departHisInfoObj);
		} catch (Exception e) {
			LogHelper.error("DepartHisInfo.queryForList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByMap
	 * @Description: 查询满足条件的集合
	 * @param
	 * @return List<DepartHisInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午9:20:45
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartHisInfoObj> queryForListByMap(Map<String, String> map) {
		List<DepartHisInfoObj> list = new ArrayList<DepartHisInfoObj>();
		try {
			list = sqlMapClient.queryForList("DepartHisInfo.queryForListByMap", map);
		} catch (Exception e) {
			LogHelper.error("DepartHisInfo.queryForListByMap: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}

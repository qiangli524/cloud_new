package com.sitech.basd.cloud3.dao.departproject;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.departproject.DepartHisInfoObj;

public interface DepartHisInfoDao {

	public DepartHisInfoObj queryOneObj(DepartHisInfoObj departHisInfoObj);
	
	public List<DepartHisInfoObj> queryForList(DepartHisInfoObj departHisInfoObj);

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
	public List<DepartHisInfoObj> queryForListByMap(Map<String, String> map);
}

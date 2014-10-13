package com.sitech.basd.cloud3.dao.departproject;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;

public interface DepartInfoDao {

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
	List<DepartInfoObj> queryForList(DepartInfoObj departInfoObj);

}

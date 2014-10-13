package com.sitech.basd.resource.service.template;

import java.util.List;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.utils.exception.HttpClientException;

public interface TemManService {

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
	public List queryForList(TemManObj obj);

	/**
	 * 
	 * @Title:根据模板ID获取同一虚拟化类型下的所有模板列表
	 * @Copyright:Copyright (c) Aug 29, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public List<TemManObj> queryTemListById(TemManObj obj);

	public int updateByObj(TemManObj obj);

	public int deleteByObj(TemManObj obj);

	public int insertByObj(TemManObj obj);

	/**
	 * 
	 * @Title: createTem
	 * @Description: 创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 15, 2013 8:54:57 AM
	 */
	public String createTem(TemManObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: delTem
	 * @Description: 删除模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2013 9:37:06 AM
	 */
	public String delTem(TemManObj obj) throws HttpClientException;

}

package com.sitech.basd.workflow.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface CloudIbatisBaseDao<T, PK extends Serializable> {

	public abstract SqlMapClient getSqlMapClient();

	@Autowired
	public abstract void setSqlMapClient(final SqlMapClient sqlMapClient);

	/**
	 * 
	 * @Title: save
	 * @Description: 新增一条数据
	 * @param
	 * @return Serializable
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-21 上午9:26:05
	 */
	public abstract Serializable save(final String mapKey, final T entity);

	/**
	 * 
	 * @Title: updateOne
	 * @Description: 更新一条记录
	 * @param
	 * @return Integer
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-21 上午9:26:22
	 */
	public abstract Integer updateOne(final String mapKey, final T entity);

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除一条记录
	 * @param
	 * @return Integer
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-21 上午9:26:35
	 */
	public abstract Integer delete(final String mapKey, final T entity);

	/**
	 * 
	 * @Title: get
	 * @Description: 根据Id查询记录
	 * @param
	 * @return T
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-21 上午9:27:43
	 */
	public abstract T get(final String mapKey, final PK id);

	/**
	 * 
	 * @Title: getAll
	 * @Description: 获取结果列表
	 * @param
	 * @return List<T>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-21 上午9:27:55
	 */
	public abstract List<T> getAll(final String mapKey, final T entity);

	/**
	 * 
	 * @Title: getRecordCount
	 * @Description: 查询记录数量
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-10-25 上午10:50:17
	 */
	public int getRecordCount(final String mapKey, final T entity);

}
package com.sitech.basd.scheduler.dao.task;

import java.util.List;

import com.sitech.basd.scheduler.domain.task.ResourceTaskObj;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample;

public interface ResourceTaskDao {

	/**
	 * 条件插入
	 */
	public abstract void insertBySelective(ResourceTaskObj obj) throws Exception;

	/**
	 * 主键删除
	 * @param obj
	 * @throws Exception
	 */
	public abstract void deleteByPrimaryKey(String taskId) throws Exception;

	/**
	 * 条件删除
	 * @param obj
	 * @throws Exception
	 */
	public abstract void deleteByExample(ResourceTaskObjExample obj) throws Exception;

	/**
	 * 条件查询记录个数
	 * @param obj 条件对象
	 * @return int
	 */
	public abstract int countByExample(ResourceTaskObjExample obj);

	/**
	 * 条件查询记录
	 * @param obj 条件对象
	 * @return int
	 */
	public abstract List<ResourceTaskObj> selectByExampleList(ResourceTaskObjExample obj);
	
	/**
	 * 主键查询对象
	 * @param obj 条件对象
	 * @return int
	 */
	public abstract ResourceTaskObj selectByPrimaryKey(String taskId);

	/**
	 *条件查询对象
	 * @param obj
	 * @return
	 */
	public abstract ResourceTaskObj selectByExample(ResourceTaskObjExample obj);

	/**
	 * 条件更新对象
	 * @param obj 条件对象
	 * @return int
	 */
	public abstract int updateByExampleSelective(ResourceTaskObj obj, ResourceTaskObjExample obje);

}
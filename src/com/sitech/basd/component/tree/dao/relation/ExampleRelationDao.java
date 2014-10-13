package com.sitech.basd.component.tree.dao.relation;

import java.util.List;
import java.util.Map;

import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;

public interface ExampleRelationDao {
	/**
	 * 
	 * @Title: inserByObj
	 * @Description: 增加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 30, 2013 9:06:24 AM
	 */
	public int inserByObj(ExampleRelationObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除关系表中的记录
	 * @author duangh
	 * @date May 30, 2013 4:52:11 PM
	 * @param obj
	 * @return
	 */
	public int deleteByObj(ExampleRelationObj obj);

	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询符合关系的列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 3:59:05 PM
	 */
	public List queryListByObj(ExampleRelationObj obj);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询关系
	 * @param
	 * @return ExampleRelationObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 3:59:42 PM
	 */
	public ExampleRelationObj queryByObj(ExampleRelationObj obj);

	/**
	 * 
	 * @Title: filterGrade
	 * @Description: 过滤同一类型的脚本级别
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 22, 2013 10:48:48 AM
	 */
	public List filterGrade(Map map);
}

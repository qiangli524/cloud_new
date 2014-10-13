package com.sitech.basd.component.tree.service.relation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.tree.dao.relation.ExampleRelationDao;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;

@Service("exampleRelationService")
public class ExampleRelationServiceImpl implements ExampleRelationService {
	@Autowired
	private ExampleRelationDao exampleRelationDao;

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
	public int inserByObj(ExampleRelationObj obj) {
		return exampleRelationDao.inserByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除关系表中的一条数据
	 * @author duangh
	 * @date May 30, 2013 4:58:16 PM
	 * @param obj
	 * @return
	 */
	public int deleteByObj(ExampleRelationObj obj) {
		return exampleRelationDao.deleteByObj(obj);
	}

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
	public List queryListByObj(ExampleRelationObj obj) {
		return exampleRelationDao.queryListByObj(obj);
	}

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
	public ExampleRelationObj queryByObj(ExampleRelationObj obj) {
		return exampleRelationDao.queryByObj(obj);
	}

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
	public List filterGrade(Map map) {
		return exampleRelationDao.filterGrade(map);
	}
}

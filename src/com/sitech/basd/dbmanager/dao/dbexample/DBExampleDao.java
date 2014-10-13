package com.sitech.basd.dbmanager.dao.dbexample;

import java.util.List;

import com.sitech.basd.dbmanager.domain.dbexample.DBExampleObj;

public interface DBExampleDao {
	/**
	 * 
	 * @Title: exampleList
	 * @Description: 查询example
	 * @param
	 * @return List<MFrameObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<DBExampleObj> queryExampleList(DBExampleObj obj);

	/**
	 * 
	 * @Title: insertexample
	 * @Description: 增加一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertExample(DBExampleObj obj);

	/**
	 * 
	 * @Title: updateexample
	 * @Description: 更新一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateExample(DBExampleObj obj);

	/**
	 * 
	 * @Title: deleteexample
	 * @Description: 删除一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int deleteExample(DBExampleObj obj);
	
	/**
	 * 与主机表关联查询用户名密码
	 * @param obj
	 * @return
	 */
	public List<DBExampleObj> queryExampleAndUserManagerBy(DBExampleObj obj);
}

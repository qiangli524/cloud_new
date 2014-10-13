package com.sitech.basd.dbmanager.dao.dbtablespace;

import java.util.List;

import com.sitech.basd.dbmanager.domain.dbtablespace.DBTableSpaceObj;

public interface DBTabelSpaceDao {

	/**
	 * 
	 * @Title:TableSpaceUserList
	 * @Description: 查询TableSpace
	 * @param
	 * @return List<DBExampleUserObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<DBTableSpaceObj> queryTableSpaceList(DBTableSpaceObj obj);

	/**
	 * 
	 * @Title: insertTableSpace
	 * @Description: 增加一条TableSpace记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertTableSpace(DBTableSpaceObj obj);

	/**
	 * 
	 * @Title: updateTableSpace
	 * @Description: 更新一条TableSpace记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateTableSpace(DBTableSpaceObj obj);

	/**
	 * 
	 * @Title: deleteTableSpace
	 * @Description: 删除一条TableSpace记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int deleteTableSpace(DBTableSpaceObj obj);

	/**
	 * 
	 * @Title: 更新是否执行标识
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateTableSpaceExecute(DBTableSpaceObj obj);
}

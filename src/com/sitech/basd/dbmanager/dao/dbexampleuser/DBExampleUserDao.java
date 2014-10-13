package com.sitech.basd.dbmanager.dao.dbexampleuser;

import java.util.List;

import com.sitech.basd.dbmanager.domain.dbexampleuser.DBExampleUserObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBPowerObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.PowerUserRelationObj;

public interface DBExampleUserDao {
	/**
	 * 
	 * @Title: ExampleUserList
	 * @Description: 查询example
	 * @param
	 * @return List<DBExampleUserObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<DBExampleUserObj> queryExampleUserList(DBExampleUserObj obj);

	/**
	 * 
	 * @Title: insertExampleUser
	 * @Description: 增加一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertExampleUser(DBExampleUserObj obj);

	/**
	 * 
	 * @Title: updateExampleUser
	 * @Description: 更新一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateExampleUser(DBExampleUserObj obj);

	/**
	 * 
	 * @Title: deleteExampleUser
	 * @Description: 删除一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int deleteExampleUser(DBExampleUserObj obj);

	public List<DBPowerObj> queryAllPower();

	public int insertPowerUserRealation(PowerUserRelationObj obj);

	public List<PowerUserRelationObj> queryAllRelation(
		PowerUserRelationObj purObj);

	public List<DBPowerObj> queryAllPowerSelf(DBPowerObj dbPowerObj);

	public int delUserPower(PowerUserRelationObj purObj);

}

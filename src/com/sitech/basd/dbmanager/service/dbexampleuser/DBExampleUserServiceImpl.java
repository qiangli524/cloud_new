package com.sitech.basd.dbmanager.service.dbexampleuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.dbmanager.dao.dbexampleuser.DBExampleUserDao;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBExampleUserObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBPowerObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.PowerUserRelationObj;

@Service("dBExampleUserService")
public class DBExampleUserServiceImpl implements DBExampleUserService {
	@Autowired
	private DBExampleUserDao dBExampleUserDao;

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
	public List<DBExampleUserObj> queryExampleUserList(DBExampleUserObj obj) {
		return dBExampleUserDao.queryExampleUserList(obj);
	}

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
	public int insertExampleUser(DBExampleUserObj obj) {
		return dBExampleUserDao.insertExampleUser(obj);
	}

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
	public int updateExampleUser(DBExampleUserObj obj) {
		return dBExampleUserDao.updateExampleUser(obj);
	}

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
	public int deleteExampleUser(DBExampleUserObj obj) {
		return dBExampleUserDao.deleteExampleUser(obj);
	}

	@Override
	public List<DBPowerObj> queryAllPower() {
	    return dBExampleUserDao.queryAllPower();
	}

	@Override
	public int insertPowerUserRealation(PowerUserRelationObj obj) {
	    return dBExampleUserDao.insertPowerUserRealation(obj);
	}

	@Override
	public List<PowerUserRelationObj> queryAllRelation(
		PowerUserRelationObj purObj) {
	    // TODO Auto-generated method stub
	    return dBExampleUserDao.queryAllRelation(purObj);
	}

	@Override
	public List<DBPowerObj> queryAllPowerSelf(DBPowerObj dbPowerObj) {
	    // TODO Auto-generated method stub
	    return dBExampleUserDao.queryAllPowerSelf(dbPowerObj);
	}

	@Override
	public int delUserPower(PowerUserRelationObj purObj) {
	    // TODO Auto-generated method stub
	    return dBExampleUserDao.delUserPower(purObj);
	}

}

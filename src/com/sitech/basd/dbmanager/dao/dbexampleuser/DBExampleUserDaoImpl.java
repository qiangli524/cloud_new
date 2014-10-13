package com.sitech.basd.dbmanager.dao.dbexampleuser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.dbmanager.domain.dbexampleuser.DBExampleUserObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBPowerObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.PowerUserRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("dBExampleUserDao")
public class DBExampleUserDaoImpl extends BaseDao implements DBExampleUserDao {

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
		List<DBExampleUserObj> list = new ArrayList<DBExampleUserObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("exampleUser.queryForCount", obj))
								.intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("exampleUser.queryExampleUserList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("exampleUser.queryExampleUserList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("exampleUser.insertExampleUser", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("exampleUser.insertExampleUser:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("exampleUser.updateExampleUser", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("exampleUser.updateExampleUser:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("exampleUser.deleteExampleUser", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("exampleUser.deleteExampleUser:" + e.getMessage());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DBPowerObj> queryAllPower() {
	    List<DBPowerObj> list = new ArrayList<DBPowerObj>();
	    try {
		list = getSqlMap().queryForList("exampleUser.queryAllPower");
	    } catch (Exception e) {
		LogHelper.error("exampleUser.queryAllPower: " + e.getMessage() + e.getClass().getName());
	    }
	    return list;
	}

	@Override
	public int insertPowerUserRealation(PowerUserRelationObj obj) {
	    int ret = 0;
	    try {
		Object o = getSqlMap().insert("exampleUser.insertPowerUserRealation",obj);
		if (o != null) {
		    ret = (Integer) o;
		}
	    } catch (Exception e) {
		ret = -1;
		LogHelper.error("exampleUser.insertPowerUserRealation: " + e.getMessage() + e.getClass().getName());
	    }
	    return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowerUserRelationObj> queryAllRelation(
		PowerUserRelationObj purObj) {
	    List<PowerUserRelationObj> list = new ArrayList<PowerUserRelationObj>();
	    try {
		list = getSqlMap().queryForList("exampleUser.queryAllRelation", purObj);
	    } catch (Exception e) {
		LogHelper.error("exampleUser.queryAllRelation: " + e.getMessage() + e.getClass().getName());
	    }
	    return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DBPowerObj> queryAllPowerSelf(DBPowerObj dbPowerObj) {
	    List<DBPowerObj> list = new ArrayList<DBPowerObj>();
	    try {
		list = getSqlMap().queryForList("exampleUser.queryAllPowerSelf", dbPowerObj);
	    } catch (Exception e) {
		LogHelper.error("exampleUser.queryAllPowerSelf: " + e.getMessage() + e.getClass().getName());
	    }
	    return list;
	}

	@Override
	public int delUserPower(PowerUserRelationObj purObj) {
	    int ret = 0;
	    try {
		Object obj = getSqlMap().delete("exampleUser.delUserPower", purObj);
		if (obj != null) {
		    ret = (Integer) obj;
		}
	    } catch (Exception e) {
		LogHelper.error("exampleUser.delUserPower: " + e.getMessage() + e.getClass().getName());
		ret = -1;
	    }
	    return ret;
	}
}

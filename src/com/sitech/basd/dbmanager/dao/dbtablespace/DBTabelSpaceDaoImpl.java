package com.sitech.basd.dbmanager.dao.dbtablespace;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.dbmanager.domain.dbtablespace.DBTableSpaceObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("dBTabelSpaceDao")
public class DBTabelSpaceDaoImpl extends BaseDao implements DBTabelSpaceDao {
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
	public List<DBTableSpaceObj> queryTableSpaceList(DBTableSpaceObj obj) {
		List<DBTableSpaceObj> list = new ArrayList<DBTableSpaceObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("tableSpace.queryForCount", obj))
								.intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("tableSpace.queryTableSpaceList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("tableSpace.queryTableSpaceList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

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
	public int insertTableSpace(DBTableSpaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("tableSpace.insertTableSpace", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("tableSpace.insertTableSpace:" + e.getMessage());
		}
		return ret;
	}

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
	public int updateTableSpace(DBTableSpaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("tableSpace.updateTableSpace", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("tableSpace.updateTableSpace:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteTableSpace(DBTableSpaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("tableSpace.deleteTableSpace", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("tableSpace.deleteTableSpace:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public int updateTableSpaceExecute(DBTableSpaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("tableSpace.updateTableSpaceExecute", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("tableSpace.updateTableSpaceExecute:" + e.getMessage());
		}
		return ret;
	}
}

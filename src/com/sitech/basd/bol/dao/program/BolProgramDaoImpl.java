package com.sitech.basd.bol.dao.program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("bolProgramDao")
public class BolProgramDaoImpl extends BaseDao implements BolProgramDao {
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序
	 * @param
	 * @return BolProgramVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:25
	 */
	public BolProgramVO queryByObj(BolProgramVO obj) {
		List lst = null;
		BolProgramVO bolProgramObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			bolProgramObj = (BolProgramVO) lst.get(0);
		}
		return bolProgramObj;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序列表
	 * @param
	 * @return List<BolProgramVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:51
	 */
	public List<BolProgramVO> queryForListByObj(BolProgramVO obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BolProgram.queryForListByObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BolProgram.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BolProgram.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:19
	 */
	public synchronized int insertByObj(BolProgramVO obj) {
		int ret = 0;
		try {
			obj.setId(this.queryId());
			Object o = getSqlMap().insert("BolProgram.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgram.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:36
	 */
	public int updateByObj(BolProgramVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BolProgram.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgram.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:50
	 */
	public int deleteByObj(BolProgramVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BolProgram.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgram.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryId
	 * @Description: 查询实体的id
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 9, 2014 4:06:26 PM
	 */
	public int queryId() throws SQLException{
		Integer result = (Integer)getSqlMapClient().queryForObject("BolProgram.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}
	
}

package com.sitech.basd.bol.dao.process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("bolProcessDao")
public class BolProcessDaoImpl extends BaseDao implements BolProcessDao {
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询进程
	 * @param
	 * @return BolProcessVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:55:39
	 */
	public BolProcessVO queryByObj(BolProcessVO obj) {
		List lst = null;
		BolProcessVO bolProcessObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			bolProcessObj = (BolProcessVO) lst.get(0);
		}
		return bolProcessObj;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询进程列表
	 * @param
	 * @return List<BolProcessVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:32
	 */
	public List<BolProcessVO> queryForListByObj(BolProcessVO obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BolProcess.queryForListByObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BolProcess.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BolProcess.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入进程
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:38
	 */
	public int insertByObj(BolProcessVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("BolProcess.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProcess.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新进程信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:46
	 */
	public int updateByObj(BolProcessVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BolProcess.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProcess.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除进程
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:51
	 */
	public int deleteByObj(BolProcessVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BolProcess.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProcess.deleteByObj:"
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
		Integer result = (Integer)getSqlMapClient().queryForObject("BolHost.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}
}

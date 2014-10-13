package com.sitech.basd.bol.dao.program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramLibVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("bolProgramLibDao")
public class BolProgramLibDaoImpl extends BaseDao implements BolProgramLibDao {
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询能力库
	 * @param
	 * @return BolProgramLibVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:59:41
	 */
	public BolProgramLibVO queryByObj(BolProgramLibVO obj) {
		List lst = null;
		BolProgramLibVO bolProgramLibVO = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			bolProgramLibVO = (BolProgramLibVO) lst.get(0);
		}
		return bolProgramLibVO;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询能力库列表
	 * @param
	 * @return List<BolProcessVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:59:57
	 */
	public List<BolProgramLibVO> queryForListByObj(BolProgramLibVO obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BolProgramLib.queryForListByObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BolProgramLib.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BolProgramLib.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:12
	 */
	public synchronized int insertByObj(BolProgramLibVO obj) {
		int ret = 0;
		try {
			obj.setId(this.queryId());
			Object o = getSqlMap().insert("BolProgramLib.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramLib.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:25
	 */
	public int updateByObj(BolProgramLibVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BolProgramLib.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramLib.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:46
	 */
	public int deleteByObj(BolProgramLibVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BolProgramLib.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramLib.deleteByObj:"
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
		Integer result = (Integer)getSqlMapClient().queryForObject("BolProgramLib.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}
}

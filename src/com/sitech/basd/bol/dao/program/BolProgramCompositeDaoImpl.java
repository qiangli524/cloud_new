package com.sitech.basd.bol.dao.program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramCompositeVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("bolProgramCompositeDao")
public class BolProgramCompositeDaoImpl extends BaseDao implements BolProgramCompositeDao {
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序组成
	 * @param
	 * @return BolProgramCompositeVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:34
	 */
	public BolProgramCompositeVO queryByObj(BolProgramCompositeVO obj) {
		List lst = null;
		BolProgramCompositeVO bolProgramCompositeVO = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			bolProgramCompositeVO = (BolProgramCompositeVO) lst.get(0);
		}
		return bolProgramCompositeVO;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序组成列表
	 * @param
	 * @return List<BolProgramCompositeVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:53
	 */
	public List<BolProgramCompositeVO> queryForListByObj(BolProgramCompositeVO obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BolProgramComposite.queryForListByObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BolProgramComposite.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BolProgramComposite.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:20
	 */
	public synchronized int insertByObj(BolProgramCompositeVO obj) {
		int ret = 0;
		try {
			obj.setId(this.queryId());
			Object o = getSqlMap().insert("BolProgramComposite.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramComposite.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:41
	 */
	public int updateByObj(BolProgramCompositeVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BolProgramComposite.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramComposite.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:41:01
	 */
	public int deleteByObj(BolProgramCompositeVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BolProgramComposite.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BolProgramComposite.deleteByObj:"
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
		Integer result = (Integer)getSqlMapClient().queryForObject("BolProgramComposite.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}
	
}

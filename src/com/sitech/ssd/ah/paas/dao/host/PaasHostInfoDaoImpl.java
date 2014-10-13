package com.sitech.ssd.ah.paas.dao.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;

@Repository("paasHostInfoDao")
public class PaasHostInfoDaoImpl extends BaseDao implements PaasHostInfoDao {

	/**
	 * @Title: queryForHostList
	 * @Description: 查询主机列表
	 * @param
	 * @return List<PaasHostInfoObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:31:57
	 */
	@Override
	public List<PaasHostInfoObj> queryForHostList(PaasHostInfoObj obj) {
		List<PaasHostInfoObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("PaasHostInfo.queryForHostListCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("PaasHostInfo.queryForHostList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasHostInfo.queryForHostList:" + sqlexception.getMessage()
					+ getClass().getName());
		}

		return lst;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 上午11:20:09
	 */
	@Override
	public int insertByObj(PaasHostInfoObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("PaasHostInfo.insertByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasHostInfo.insertByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条主机信息
	 * @param
	 * @return PaasHostInfoObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-8 上午11:32:55
	 */
	@Override
	public PaasHostInfoObj queryByObj(PaasHostInfoObj obj) {
		PaasHostInfoObj infoObj = new PaasHostInfoObj();
		try {
			infoObj = (PaasHostInfoObj) getSqlMap().queryForObject("PaasHostInfo.queryByObj", obj);
		} catch (Exception e) {
			LogHelper.error("PaasHostInfo.queryByObj: " + e.getMessage() + e.getClass().getName());
		}
		return infoObj;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午3:58:59
	 */
	@Override
	public int updateByObj(PaasHostInfoObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("PaasHostInfo.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasHostInfo.updateByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午2:58:40
	 */
	@Override
	public int deleteByObj(PaasHostInfoObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("PaasHostInfo.deleteByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("PaasHostInfo.deleteByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryPhysicsHostList
	 * @Description:查询物理主机集合
	 * @param
	 * @return List<GreenPlumHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午8:26:22
	 */
	@Override
	public List<GreenPlumHostInfoObj> queryPhysicsHostList(GreenPlumHostInfoObj obj) {
		List<GreenPlumHostInfoObj> lst = new ArrayList<GreenPlumHostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"PaasHostInfo.queryPhysicsHostListCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("PaasHostInfo.queryPhysicsHostList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasHostInfo.queryPhysicsHostList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

}

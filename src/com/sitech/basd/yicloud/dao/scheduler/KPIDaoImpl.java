package com.sitech.basd.yicloud.dao.scheduler;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;

public class KPIDaoImpl extends BaseDao implements KPIDao {
	/**
	 * 
	 * @Title: listKPI
	 * @Description: 显示所有KPI
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 4:18:38 PM
	 */
	public List listKPI(KPIWeightObj obj){
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"KPI.queryForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("KPI.queryForList", obj);
		} catch (Exception e) {
			LogHelper.error("KPI.queryForList:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: addKPI
	 * @Description: 新增kpi
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:21:10 PM
	 */
	public int addKPI(KPIWeightObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("KPI.insertKPI",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("KPI.insertKPI:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateKPI
	 * @Description: 修改kpi
	 * @param
	 * @return int
	 * @throws
	 * @author hehui 
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:21:50 PM
	 */
	public int updateKPI(KPIWeightObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("KPI.updateKPI",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("KPI.updateKPI:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteKPI
	 * @Description: 删除kpi
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:22:40 PM
	 */
	public int deleteKPI(KPIWeightObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("KPI.delKPI",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("KPI.delKPI:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryForObj
	 * @Description: 查询KPI
	 * @param
	 * @return KPIWeightObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 18, 2012 9:54:46 AM
	 */
	public KPIWeightObj queryForObj(KPIWeightObj obj){
		KPIWeightObj reobj = null;
		try {
			reobj = (KPIWeightObj)getSqlMap().queryForObject("KPI.queryForObj",obj);
		} catch (Exception e) {
			reobj = new KPIWeightObj();
			LogHelper.error("KPI.queryForObj:" + e.getMessage()
					+ getClass().getName());
		}
		return reobj;
	}
}

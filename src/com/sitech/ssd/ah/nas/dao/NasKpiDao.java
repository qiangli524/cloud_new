package com.sitech.ssd.ah.nas.dao;

import java.util.List;

import com.sitech.ssd.ah.nas.domain.NasKpiObj;


public interface NasKpiDao {

	/**
	 * @Title: queryNasKpiByObj
	 * @Description: 查询naskpi
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月4日14:48:06
	 */
	public List<NasKpiObj> queryNasKpiByObj(NasKpiObj obj);
	
	
	/**
	 * @Title: insertKpiByObj
	 * @Description: 插入kpi指标
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void insertKpiByObj(NasKpiObj obj);
	
	
	
	/**
	 * @Title: updateKpiByObj
	 * @Description: 更新kpi数据
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void updateKpiByObj(NasKpiObj obj);
	
}

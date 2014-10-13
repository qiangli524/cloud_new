package com.sitech.basd.yicloud.service.scheduler;

import java.util.List;

import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;

public interface KPIService {
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
	public List listKPI(KPIWeightObj obj);
	
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
	public int addKPI(KPIWeightObj obj);
	
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
	public int updateKPI(KPIWeightObj obj);
	
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
	public int deleteKPI(KPIWeightObj obj);
	
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
	public KPIWeightObj queryForObj(KPIWeightObj obj);
}

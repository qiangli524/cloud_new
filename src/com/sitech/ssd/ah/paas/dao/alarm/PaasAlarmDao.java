package com.sitech.ssd.ah.paas.dao.alarm;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.alarm.PaasAlarmObj;

/**
 * 
 * <p>
 * Title: PaasAlarmDao
 * </p>
 * <p>
 * Description: Paas告警
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-7-24 上午10:34:02
 * 
 */
public interface PaasAlarmDao {
	/**
	 * 
	 * @Title: queryForAlarmList
	 * @Description: 告警列表
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-17 下午9:35:24
	 */
	public List<PaasAlarmObj> queryForAlarmList(PaasAlarmObj obj);

}

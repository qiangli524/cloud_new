package com.sitech.basd.resource.dao.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.SwitchPerformanceObj;

public interface SwitchPerformanceDao {

	/**
	 * 
	 * @Title: SwitchPerformanceList
	 * @Description: 查询接口性能列表
	 * @param
	 * @return List<SwitchPerfromanceObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 20, 2013
	 */
	public List<SwitchPerformanceObj> queryForList(SwitchPerformanceObj obj);
}

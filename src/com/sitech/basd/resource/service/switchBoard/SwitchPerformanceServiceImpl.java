package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.switchBoard.SwitchPerformanceDao;
import com.sitech.basd.resource.domain.switchBoard.SwitchPerformanceObj;

@Service("switchPerformanceService")
public class SwitchPerformanceServiceImpl implements SwitchPerformanceService {

	@Autowired
	private SwitchPerformanceDao performanceDao;

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
	public List<SwitchPerformanceObj> queryForList(SwitchPerformanceObj obj) {
		return performanceDao.queryForList(obj);
	}
}

package com.sitech.basd.resource.dao.switchBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.switchBoard.SwitchPerformanceObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("switchPerformanceDao")
public class SwitchPerformanceDaoImpl extends BaseDao implements SwitchPerformanceDao {

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
	@SuppressWarnings("unchecked")
	public List<SwitchPerformanceObj> queryForList(SwitchPerformanceObj obj) {
		List<SwitchPerformanceObj> list = new ArrayList<SwitchPerformanceObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("switchPerformance.queryForCount",
								obj)).intValue()); // 分页查询的基本信息 }
			}
			list = getSqlMap().queryForList("switchPerformance.queryPerformanceList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("switchPerformance.queryPerformanceList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}

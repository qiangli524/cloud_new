package com.sitech.ssd.ah.paas.dao.tab;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;

@Repository("greenPlumTabDao")
public class GreenPlumTabDaoImpl extends BaseDao implements GreenPlumTabDao {

	@Override
	public List<GreenPlumHostInfoObj> queryGreenPlumHostList(GreenPlumHostInfoObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"GreenPlumTab.queryGreenPlumHostListCount", obj)).intValue());
				list = getSqlMap().queryForList("GreenPlumTab.queryGreenPlumHostList", obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("GreenPlumTab.queryGreenPlumHostList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}

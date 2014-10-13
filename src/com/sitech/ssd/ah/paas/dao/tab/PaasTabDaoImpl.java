package com.sitech.ssd.ah.paas.dao.tab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tab.MapChartSet;

@Repository("paasTabDao")
public class PaasTabDaoImpl extends BaseDao implements PaasTabDao {
	@Override
	public List<PaasEntityObj> queryNodeList(PaasEntityObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("PaasTab.queryNodeListCount", obj))
								.intValue());
				list = getSqlMap().queryForList("PaasTab.queryNodeList", obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("PaasTab.queryNodeList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<PaasEntityObj> queryInstanceNodeList(PaasEntityObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("PaasTab.queryInstanceNodeListCount",
								obj)).intValue());
			}
			list = getSqlMap().queryForList("PaasTab.queryInstanceNodeList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasTab.queryInstanceNodeList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<PaasEntityObj> queryInstanceListForBusiNode(PaasEntityObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("PaasTab.queryInstanceListForBusiNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasTab.queryInstanceListForBusiNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public double queryBusHealthyLimitForInstanceId(PaasEntityObj obj) {
		double limit = 0;
		try {
			limit = (Double) getSqlMap().queryForObject(
					"PaasTab.queryBusHealthyLimitForInstanceId", obj);
		} catch (SQLException e) {
			logger.error("PaasTab.queryBusHealthyLimitForInstanceId:" + e.getMessage()
					+ getClass().getName());
		}
		return limit;
	}

	@Override
	public List<PaasEntityObj> queryBusiListByEntityId(PaasEntityObj obj) {
		List<PaasEntityObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasTab.queryBusiListByEntityId", obj);
		} catch (SQLException e) {
			logger.error("PaasTab.queryBusiListByEntityId:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public List<PaasEntityObj> queryParentEntityByEntityId(PaasEntityObj obj) {
		List<PaasEntityObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasTab.queryParentEntityByEntityId", obj);
		} catch (SQLException e) {
			logger.error("PaasTab.queryParentEntityByEntityId:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public List<Data> queryChartDataByDate(MapChartSet chartSet) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("PaasTab.queryChartDataByDate", chartSet);
		} catch (Exception e) {
			LogHelper.error("PaasTab.queryChartDataByDate: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}

	@Override
	public List<Data> queryChartDataByMonth(MapChartSet chartSet) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("PaasTab.queryChartDataByMonth", chartSet);
		} catch (Exception e) {
			LogHelper.error("PaasTab.queryChartDataByMonth: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}

	@Override
	public List<Data> queryChartDataByDateBetween(MapChartSet chartSet) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("PaasTab.queryChartDataByDateBetween", chartSet);
		} catch (Exception e) {
			LogHelper.error("PaasTab.queryChartDataByDateBetween: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}
}

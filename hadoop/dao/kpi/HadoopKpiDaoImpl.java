package dao.kpi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.kpi.HadoopKpiObj;

@Service("hadoopKpiDao")
public class HadoopKpiDaoImpl extends BaseDao  implements HadoopKpiDao {

	@Override
	public List<HadoopKpiObj> queryHadoopKpiLists(HadoopKpiObj obj) {
		List<HadoopKpiObj> list = new ArrayList<HadoopKpiObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HadoopKpi.queryHadoopKpiCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("HadoopKpi.queryHadoopKpiLists", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopKpi.queryHadoopKpiLists:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public void insertHadoopKpi(HadoopKpiObj obj) {
		try {
			getSqlMap().insert("HadoopKpi.insertHadoopKpi", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopKpi.insertHadoopKpi: " + e.getMessage()
					+ e.getClass().getName());
		}
	}
	
	public void deleteByObj(HadoopKpiObj obj) {
		try {
			getSqlMap().delete("HadoopKpi.deleteByObj", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopKpi.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
	}

	@Override
	public void updateHadoopKpi(HadoopKpiObj obj) {
		try {
			getSqlMap().insert("HadoopKpi.updateHadoopKpi", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopKpi.updateHadoopKpi: " + e.getMessage()
					+ e.getClass().getName());
		}
		
	}
	
	@Override
	public HadoopKpiObj queryByObj(HadoopKpiObj obj) {
		HadoopKpiObj kpiObj = new HadoopKpiObj();
		try {
			kpiObj = (HadoopKpiObj) getSqlMap().queryForObject("HadoopKpi.queryByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopKpi.queryByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return kpiObj;
	}
}

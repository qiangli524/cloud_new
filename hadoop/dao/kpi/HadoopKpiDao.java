package dao.kpi;

import java.util.List;

import domain.kpi.HadoopKpiObj;

public interface HadoopKpiDao {
	
	public List<HadoopKpiObj> queryHadoopKpiLists(HadoopKpiObj obj);
	
	public void insertHadoopKpi(HadoopKpiObj obj);
	
	public void updateHadoopKpi(HadoopKpiObj obj);

	public void deleteByObj(HadoopKpiObj obj);
	
	public HadoopKpiObj queryByObj(HadoopKpiObj obj);
}

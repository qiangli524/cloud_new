package dao.clusterKpi;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;

import domain.clusterKpi.ClusterKpiObj;

public interface ClusterKpiDao {

	
	public List<Data>  queryForList(ClusterKpiObj clusterKpiObj) throws SQLException;
    

}


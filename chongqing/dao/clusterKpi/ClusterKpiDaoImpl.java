package dao.clusterKpi;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

import dao.clusterKpi.ClusterKpiDao;
import domain.clusterKpi.ClusterKpiObj;




@Service("ClusterKpiDao")
public class ClusterKpiDaoImpl extends BaseDao implements ClusterKpiDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Data>  queryForList(ClusterKpiObj clusterKpiObj) throws SQLException {
		List<Data> list = null;
		try {
			 list = getSqlMap().queryForList("ClusterKpi.queryForList", clusterKpiObj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("DirectoryType.queryForList:" + e.getMessage()
					+ e.getClass().getName());
			throw e;
		}
		return list;
	}


}

package dao.metries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.metries.HadoopMetriesObj;

@Repository("hadoopMetriesDao")
public class HadoopMetriesDaoImpl extends BaseDao implements HadoopMetriesDao{

	/**
	 * @Title: queryForListByCluster
	 * @Description: 通过集群id查询集合
	 * @param
	 * @return List<HadoopMetriesObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午11:06:56
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMetriesObj> queryForListByCluster(HadoopMetriesObj hadoopMetriesObj) {
		List<HadoopMetriesObj> list = new ArrayList<HadoopMetriesObj>();
		try {
			list = getSqlMap().queryForList("HadoopMetries.queryForListByCluster", hadoopMetriesObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMetries.queryForListByCluster: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}

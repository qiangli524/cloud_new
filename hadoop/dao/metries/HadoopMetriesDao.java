package dao.metries;

import java.util.List;

import domain.metries.HadoopMetriesObj;

public interface HadoopMetriesDao {

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
	public List<HadoopMetriesObj> queryForListByCluster(HadoopMetriesObj hadoopMetriesObj);
}

package service.metries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.metries.HadoopMetriesDao;
import domain.metries.HadoopMetriesObj;

@Service("hadoopMetriesService")
public class HadoopMetriesServiceImpl implements HadoopMetriesService{

	@Autowired
	private HadoopMetriesDao hadoopMetriesDao;
	
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
	@Override
	public List<HadoopMetriesObj> queryForListByCluster(HadoopMetriesObj hadoopMetriesObj) {
		return hadoopMetriesDao.queryForListByCluster(hadoopMetriesObj);
	}

}

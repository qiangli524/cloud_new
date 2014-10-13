package service.cluster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.cluster.HadoopClusterDao;
import domain.cluster.HadoopClusterObj;

@Service("hadoopClusterServer")
public class HadoopClusterServerImpl implements HadoopClusterServer {
	
	@Autowired
	private HadoopClusterDao hadoopClusterDao;

	@Override
	public List<HadoopClusterObj> queryClusterList(HadoopClusterObj obj) {
		return hadoopClusterDao.queryClusterList(obj);
	}

}

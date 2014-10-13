package service.cluster;

import java.util.List;

import domain.cluster.HadoopClusterObj;

public interface HadoopClusterServer {
	public List<HadoopClusterObj> queryClusterList(HadoopClusterObj obj);
}
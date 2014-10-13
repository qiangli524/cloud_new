package com.sitech.ssd.gx.service.huaweiCluster;

import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.huawei.Cluster;

public interface HuaweiClusterService {
	/**
	 * 
	 * @Title: queryClusterInfoFromRest
	 * @Description: 从接口查询集群详细信息
	 * @param
	 * @return Cluster
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException 
	 * @createtime 2013-12-6 下午5:42:15
	 */
	public Cluster queryClusterInfoFromRest(String siteId,String clusterId) throws HttpClientException;
}

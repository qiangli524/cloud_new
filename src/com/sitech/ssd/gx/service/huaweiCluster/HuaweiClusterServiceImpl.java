package com.sitech.ssd.gx.service.huaweiCluster;

import com.sitech.ssd.gx.constant.HuaweiRestURI;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.huawei.Cluster;

public class HuaweiClusterServiceImpl implements HuaweiClusterService {
	
	@Override
	public Cluster queryClusterInfoFromRest(String siteId, String clusterId) throws HttpClientException {
		String hostURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR  + siteId + HuaweiRestURI.CLUSTER_URI + HuaweiRestURI.SEPARATOR + clusterId;
		Cluster cluster = HttpClientCustomUtil.get(hostURI, new JacksonUtil.TypeReference<Cluster>() {
		});
		return cluster;
	}
}

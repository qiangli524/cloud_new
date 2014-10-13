package com.sitech.ssd.bpm.service;

import java.util.List;

import com.sitech.ssd.bpm.domain.CloudWorkorder;


/** 
* @ClassName: CloudWorkOrderService 
* @Description: TODO(北京电信工单管理) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-5-28 下午2:02:29 
* @version 1.0 
*/
public interface CloudWorkOrderService {
	
	public List<CloudWorkorder> list(CloudWorkorder t);

    public int add(CloudWorkorder t);

    public int delete(CloudWorkorder t);

    public int update(CloudWorkorder t);

	public CloudWorkorder query(CloudWorkorder t);
	
}
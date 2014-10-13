package com.sitech.ssd.bpm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.bpm.dao.CloudWorkorderDao;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
@Service("cloudWorkOrderService")
public class CloudWorkOrderServiceImpl implements CloudWorkOrderService {

	@Autowired
    private CloudWorkorderDao cloudWorkorderDao;

    /**
    * 创建实体
    */
    public int add(CloudWorkorder t) {
    	return cloudWorkorderDao.insertByObj(t);
    }


    /**
    * 删除实体
    */
    public int delete(CloudWorkorder t) {
    	return cloudWorkorderDao.deleteByObj(t);
    }


    /**
    * 更新实体
    */
    public int update(CloudWorkorder t) {
    	return cloudWorkorderDao.updateByObj(t);
    }

	/** (非 Javadoc) 
	* <p>Title: list</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see com.sitech.ssd.bpm.service.CloudWorkOrderService#list(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public List<CloudWorkorder> list(CloudWorkorder t) {
		return cloudWorkorderDao.queryForList(t);
	}


	/** (非 Javadoc) 
	* <p>Title: query</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see com.sitech.ssd.bpm.service.CloudWorkOrderService#query(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public CloudWorkorder query(CloudWorkorder t) {
		
		return cloudWorkorderDao.queryForObj(t);
	}


}
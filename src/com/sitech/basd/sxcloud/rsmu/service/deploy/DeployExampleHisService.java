package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleHisObj;


public interface DeployExampleHisService {

	/**
     * @Title:根据部署实例管理信息查询匹配的所有部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployExampleHisObj obj); 
	 /**
     * @Title:查询出具体部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployExampleHisObj queryByObj(TbBusiDeployExampleHisObj obj);
	 /**
     * @Title:删除部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployExampleHisObj obj);
	 /**
     * @Title:插入部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployExampleHisObj obj);

	
	
}

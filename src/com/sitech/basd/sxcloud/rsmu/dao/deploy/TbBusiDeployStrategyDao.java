package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployStrategyObj;

public interface TbBusiDeployStrategyDao {

	/**
     * @Title:根据部署策略信息查询匹配的所有部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployStrategyObj obj);
	/**
     * @Title:查询所以的部署策略信息(只查ID和策略名)
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryNameListByObj(TbBusiDeployStrategyObj obj);
	 /**
    * @Title:查询出具体部署策略信息
    * @Copyright: Copyright (c) 201006
    * @Company: si-tech
    * @author yangwenchao
    * @version 1.0
   */
	public TbBusiDeployStrategyObj queryByObj(TbBusiDeployStrategyObj obj);
	 /**
     * @Title:更新部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployStrategyObj obj);
	 /**
     * @Title:删除部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployStrategyObj obj);
	 /**
     * @Title:插入部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployStrategyObj obj);
}

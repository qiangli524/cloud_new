package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployGeneralObj;

public interface TbBusiDeployGeneralDao {

	/**
     * @Title:根据"部署个性化配置"部分信息查询匹配的所有部署个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployGeneralObj obj);
	 /**
     * @Title:查询出具体个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployGeneralObj queryByObj(TbBusiDeployGeneralObj obj);
	 /**
     * @Title:更新个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployGeneralObj obj);
	 /**
     * @Title:删除个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployGeneralObj obj);
	 /**
     * @Title:插入个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployGeneralObj obj);

}

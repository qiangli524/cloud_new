package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;

public interface VideoExampleService {
	/**
     * @Title:根据部署录像信息查询匹配的所有部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiVideoExampleObj obj);
	 /**
     * @Title:查询出具体部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public TbBusiVideoExampleObj queryByObj(TbBusiVideoExampleObj obj);
	 /**
     * @Title:更新部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int updateByObj(TbBusiVideoExampleObj obj);
	 /**
     * @Title:删除部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int deleteByObj(TbBusiVideoExampleObj obj);
	 /**
     * @Title:插入部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int insertByObj(TbBusiVideoExampleObj obj);
	
	/**
     * @Title:录像过程记录列表(只查ID)
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwca
     * @version 1.0
    */
	public List queryIDListByObj(TbBusiDeployVideorecordObj obj);
	/**
     * @Title:根据录像id查找所有的录像命令
     * @Copyright: Copyright (c) 201008
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForCommandListByVideoid(TbBusiDeployVideoCommandsetObj obj);
	
}

package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;

public interface DeployVideoCommandsetService {
	/**
     * @Title:根据部署录像命令信息查询匹配的所有部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployVideoCommandsetObj obj);
	 /**
     * @Title:查询出具体部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public TbBusiDeployVideoCommandsetObj queryByObj(TbBusiDeployVideoCommandsetObj obj);
	 /**
     * @Title:更新部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployVideoCommandsetObj obj);
	 /**
     * @Title:删除部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployVideoCommandsetObj obj);
	 /**
     * @Title:插入部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployVideoCommandsetObj obj);
	
}

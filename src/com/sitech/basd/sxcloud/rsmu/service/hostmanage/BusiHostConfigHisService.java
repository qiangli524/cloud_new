package com.sitech.basd.sxcloud.rsmu.service.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;

public interface BusiHostConfigHisService {

	/**
     * @Title:根据主机配置部分信息查询匹配的所有主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:查询出具体主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostConfigHisObj queryByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:更新主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:删除主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:插入主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostConfigHisObj obj);


}

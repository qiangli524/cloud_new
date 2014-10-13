package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;

public interface TbBusiHostConfigHisDao {

	 /**
     * @Title:根据主机配置部分信息查询匹配的所有主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:查询出具体主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostConfigHisObj queryByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:更新主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:删除主机配历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostConfigHisObj obj);
	 /**
     * @Title:插入主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostConfigHisObj obj);

}

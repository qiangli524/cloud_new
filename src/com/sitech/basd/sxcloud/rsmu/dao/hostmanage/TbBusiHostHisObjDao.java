package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostHisObj;

public interface TbBusiHostHisObjDao {

	/**
     * @Title:根据主机部分信息查询匹配的所有主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostHisObj obj);
	 /**
     * @Title:查询出具体主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostHisObj queryByObj(TbBusiHostHisObj obj);
	 /**
     * @Title:更新主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostHisObj obj);
	 /**
     * @Title:插入主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostHisObj obj);

}

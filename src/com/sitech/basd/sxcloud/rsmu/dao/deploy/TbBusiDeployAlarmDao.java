package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployAlarmObj;

public interface TbBusiDeployAlarmDao {
 
	/**
     * @Title:根据异常告警部分信息查询匹配的所有异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployAlarmObj obj);
	 /**
     * @Title:查询出具体异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployAlarmObj queryByObj(TbBusiDeployAlarmObj obj);
	 /**
     * @Title:更新异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployAlarmObj obj);
	 /**
     * @Title:删除异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployAlarmObj obj);
	 /**
     * @Title:插入异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployAlarmObj obj);

}

package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployAlarmObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployAlarmDaoImpl extends BaseDao implements
		TbBusiDeployAlarmDao {

	/**
     * @Title:删除异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
     * @Title:插入异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
     * @Title:查询出具体异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployAlarmObj queryByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * @Title:根据异常告警部分信息查询匹配的所有异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployAlarmObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployAlarm.queryForListByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiDeployAlarm.queryForListByObj", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployAlarm.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	/**
     * @Title:更新异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}

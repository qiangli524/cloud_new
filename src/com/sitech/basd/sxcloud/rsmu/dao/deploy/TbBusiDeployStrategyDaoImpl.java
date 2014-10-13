package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployStrategyObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployStrategyDaoImpl extends BaseDao implements
		TbBusiDeployStrategyDao {

	/**
     * @Title:删除部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployStrategyObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiDeployStrategy.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployStrategy.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployStrategyObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiDeployStrategy.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployStrategy.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:查询出具体部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployStrategyObj queryByObj(TbBusiDeployStrategyObj obj) {
		List lst = null;
		TbBusiDeployStrategyObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiDeployStrategyObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据部署策略信息查询匹配的所有部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployStrategyObj obj) {
		List lst = null;
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployStrategy.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiDeployStrategy.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiDeployStrategy.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}
    /*
     * (non-Javadoc)
     * 只查策略ID和策略名
     */
	public List queryNameListByObj(TbBusiDeployStrategyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbBusiDeployStrategy.queryNameListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiDeployStrategy.queryNameListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}
	/**
     * @Title:更新部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployStrategyObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiDeployStrategy.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployStrategy.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	

}

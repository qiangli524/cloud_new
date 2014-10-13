package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployConfigObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployConfigDaoImpl extends BaseDao implements
		TbBusiDeployConfigDao {

	/**
     * @Title:删除部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployConfigObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiDeployConfig.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployConfig.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployConfigObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiDeployConfig.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployConfig.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	 /**
     * @Title:查询出具体部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployConfigObj queryByObj(TbBusiDeployConfigObj obj) {
		List lst = null;
		TbBusiDeployConfigObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiDeployConfigObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据部署基本信息查询匹配的所有部署信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployConfigObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployConfig.queryForListByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiDeployConfig.queryForListByObj", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployConfig.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	/**
     * @Title:更新部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}

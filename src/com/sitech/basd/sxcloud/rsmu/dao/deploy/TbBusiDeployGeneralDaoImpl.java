package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployGeneralObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployGeneralDaoImpl extends BaseDao implements
         TbBusiDeployGeneralDao {

	/**
     * @Title:删除个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployGeneralObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiDeployGeneral.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployGeneral.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployGeneralObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiDeployGeneral.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployGeneral.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:查询出具体个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployGeneralObj queryByObj(TbBusiDeployGeneralObj obj) {
		List lst = null;
		TbBusiDeployGeneralObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiDeployGeneralObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据"部署个性化配置"部分信息查询匹配的所有部署个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployGeneralObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployGeneral.queryForListByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiDeployGeneral.queryForListByObj", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployGeneral.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	/**
     * @Title:更新个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployGeneralObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiDeployGeneral.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbSysApp.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

}

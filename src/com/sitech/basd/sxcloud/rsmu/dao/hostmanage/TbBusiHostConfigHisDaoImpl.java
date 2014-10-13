package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiHostConfigHisDaoImpl extends BaseDao implements
		TbBusiHostConfigHisDao {

	/**
     * @Title:删除主机配历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostConfigHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiHostConfigHis.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiHostConfigHis.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostConfigHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiHostConfigHis.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiHostConfigHis.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	 /**
     * @Title:查询出具体主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostConfigHisObj queryByObj(TbBusiHostConfigHisObj obj) {
		List lst = null;
		TbBusiHostConfigHisObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiHostConfigHisObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据主机配置部分信息查询匹配的所有主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostConfigHisObj obj) {
		List lst = null;
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiHostConfigHis.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiHostConfigHis.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiHostConfigHis.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}

	/**
     * @Title:更新主机配置历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiHostConfigHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiHostConfigHis.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiHostConfigHis.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

}

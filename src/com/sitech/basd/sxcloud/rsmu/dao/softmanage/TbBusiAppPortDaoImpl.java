package com.sitech.basd.sxcloud.rsmu.dao.softmanage;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiAppPortObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiAppPortDaoImpl extends BaseDao implements TbBusiAppPortDao {

	public int deleteByObj(TbBusiAppPortObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiAppPort.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiAppPort.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	public int insertByObj(TbBusiAppPortObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiAppPort.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiAppPort.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;

	}

	public TbBusiAppPortObj queryByObj(TbBusiAppPortObj obj) {
		List lst = null;
		TbBusiAppPortObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiAppPortObj)lst.get(0);
		}
		return tempObj ;
	}

	public List queryForListByObj(TbBusiAppPortObj obj) {
		List lst = null;
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiSoftwareInfo.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiAppPort.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiAppPort.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}

	public int updateByObj(TbBusiAppPortObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiAppPort.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiAppPort.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	public int deleteAppidByObj(TbBusiAppPortObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiAppPort.deleteAppidByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiAppPort.deleteAppidByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

}

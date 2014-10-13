package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostHisObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiHostHisObjDaoImpl extends BaseDao implements
		TbBusiHostHisObjDao {

	 /**
     * @Title:删除主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiHostHis.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiHostHis.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiHostHis.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiHostHis.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:查询出具体主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostHisObj queryByObj(TbBusiHostHisObj obj) {
		List lst = null;
		TbBusiHostHisObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiHostHisObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据主机部分信息查询匹配的所有主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostHisObj obj) {
		List lst = null;
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiHostHis.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiHostHis.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiHostHis.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}

}

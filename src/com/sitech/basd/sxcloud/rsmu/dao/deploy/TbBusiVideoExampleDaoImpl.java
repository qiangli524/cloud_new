package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiVideoExampleDaoImpl extends BaseDao implements TbBusiVideoExampleDao {

	/**
     * @Title:删除部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int deleteByObj(TbBusiVideoExampleObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiVideoExample.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiVideoExample.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int insertByObj(TbBusiVideoExampleObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiVideoExample.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiVideoExample.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	 /**
     * @Title:查询出具体部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public TbBusiVideoExampleObj queryByObj(TbBusiVideoExampleObj obj) {
		List lst = null;
		TbBusiVideoExampleObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiVideoExampleObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据部署录像信息查询匹配的所有部署录像
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiVideoExampleObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiVideoExample.queryByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiVideoExample.queryForListByObj", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiVideoExample.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	/**
     * @Title:更新部署录像信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */

	public int updateByObj(TbBusiVideoExampleObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiVideoExample.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiVideoExample.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

}

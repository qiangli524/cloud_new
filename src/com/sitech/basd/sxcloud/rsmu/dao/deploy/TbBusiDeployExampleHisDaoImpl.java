package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleHisObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;




public class TbBusiDeployExampleHisDaoImpl extends BaseDao implements
		TbBusiDeployExampleHisDao {

	/**
     * @Title:删除部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployExampleHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiDeployExampleHis.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (SQLException sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployExampleHis.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployExampleHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiDeployExampleHis.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (SQLException sqlexception) {sqlexception.printStackTrace();
            ret = -1;
            LogHelper.error( "TbBusiDeployExampleHis.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:查询出具体部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployExampleHisObj queryByObj(TbBusiDeployExampleHisObj obj) {
		List lst = null;
		TbBusiDeployExampleHisObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiDeployExampleHisObj)lst.get(0);
		}
		return tempObj ;
	}
   
	/**
     * @Title:根据部署实例管理信息查询匹配的所有部署实例管理信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployExampleHisObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployExampleHis.queryByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiDeployExampleHis.queryForListByObj", obj);
        }catch (SQLException sqlexception) {
        	sqlexception.printStackTrace();
			LogHelper.error("TbBusiDeployExampleHis.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	
}

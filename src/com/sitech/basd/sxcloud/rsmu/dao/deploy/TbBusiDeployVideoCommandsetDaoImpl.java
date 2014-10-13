package com.sitech.basd.sxcloud.rsmu.dao.deploy;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployVideoCommandsetDaoImpl extends BaseDao implements TbBusiDeployVideoCommandsetDao {

	/**
     * @Title:删除部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployVideoCommandsetObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiDeployVideoCommandset.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployVideoCommandset.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployVideoCommandsetObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiDeployVideoCommandset.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployVideoCommandset.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	 /**
     * @Title:查询出具体部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public TbBusiDeployVideoCommandsetObj queryByObj(TbBusiDeployVideoCommandsetObj obj) {
		List lst = null;
		TbBusiDeployVideoCommandsetObj tempObj =null;
		lst = queryForListByObj(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(TbBusiDeployVideoCommandsetObj)lst.get(0);
		}
		return tempObj ;
	}

	/**
     * @Title:根据部署录像命令信息查询匹配的所有部署录像命令
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployVideoCommandsetObj obj) {
		List lst = null;
        try {
        	if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiDeployVideoCommandset.queryByObjForCount", obj)).intValue());
			}
            lst = getSqlMap().queryForList("TbBusiDeployVideoCommandset.queryForListByObj", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployVideoCommandset.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}

	/**
     * @Title:更新部署录像命令信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */

	public int updateByObj(TbBusiDeployVideoCommandsetObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().update("TbBusiDeployVideoCommandset.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiDeployVideoCommandset.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	
	/**
     * @Title:根据录像id查找所有的录像命令
     * @Copyright: Copyright (c) 201008
     * @Company: si-tech
     * @author wangzechao
     * @version 1.0
    */
	public List queryForCommandListByVideoid(TbBusiDeployVideoCommandsetObj obj){
		List lst = null;
        try {
            lst = getSqlMap().queryForList("TbBusiDeployVideoCommandset.queryForCommandListByVideoid", obj);
        }catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployVideoCommandset.queryForCommandListByVideoid:" +sqlexception.getMessage() + getClass().getName()) ;
		}
        return lst;
	}
	public static void main(String[] args) {
		TbBusiDeployVideoCommandsetDaoImpl dao=new TbBusiDeployVideoCommandsetDaoImpl();
		TbBusiDeployVideoCommandsetObj obj=new TbBusiDeployVideoCommandsetObj();
		obj.setCOMMAND("ls");
		obj.setVIDEOID(1009);
		dao.insertByObj(obj);
	}
}

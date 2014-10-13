package com.sitech.basd.sxcloud.rsmu.dao.softmanage;


import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoHisObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiSoftwareInfoHisDaoImpl extends BaseDao implements TbBusiSoftwareInfoHisDao {

	
	/**
     * @Title:删除软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiSoftwareInfoHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().delete("TbBusiSoftwareInfoHis.deleteByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiSoftwareInfoHis.deleteByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:插入软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiSoftwareInfoHisObj obj) {
		int ret = 0;
        try {
            Object o = getSqlMap().insert("TbBusiSoftwareInfoHis.insertByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "TbBusiSoftwareInfoHis.insertByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}

	/**
     * @Title:查询出具体软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiSoftwareInfoHisObj queryByObj(TbBusiSoftwareInfoHisObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * @Title:根据软件历史部分信息查询匹配的所有应用信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiSoftwareInfoHisObj obj) {
		List lst = null;
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("TbBusiSoftwareInfoHis.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiSoftwareInfoHis.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error( "TbBusiSoftwareInfoHis.queryForListByObj:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return lst ;
	}

	/**
     * @Title:更新软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiSoftwareInfoHisObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}
public static void main(String[] args) {
	TbBusiSoftwareInfoHisDaoImpl dao=new TbBusiSoftwareInfoHisDaoImpl();
	TbBusiSoftwareInfoHisObj obj=new TbBusiSoftwareInfoHisObj();
	obj.setSOFTWARE_SIZE("aaa");
	obj.setMANUFACTURERS("aaa");
	obj.setSYSTEM_REQUEST("aaa");
	obj.setPROVIDERS("aaa");
	obj.setINTRODUCE("aaa");
	obj.setREMARK("aaa");
	obj.setNAME("aaa");
	obj.setVERSION("aaa");
	obj.setAPPID(1010);
	obj.setSOFTWAREID(1011);
	obj.setUPDATEUSER("abc");
	obj.setUPDATETYPE("1");
	dao.insertByObj(obj);
}
}

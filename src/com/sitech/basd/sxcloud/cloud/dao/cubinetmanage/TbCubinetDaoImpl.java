package com.sitech.basd.sxcloud.cloud.dao.cubinetmanage;

import java.util.List;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.domain.cubinetmanage.TbCubinetObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbCubinetDaoImpl extends BaseDao implements TbCubinetDao {

	/**
	 * @Title:根据主机部分信息查询匹配的所有机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	static Logger log = Logger.getLogger("infoAppender");

	public List queryForListByObj(TbCubinetObj obj) {
		List lst = null;

		try {
			// log.info( "TbCubinet.queryTbCubinetAllForListByObj:" +
			// getClass().getName()) ;
			lst = getSqlMap().queryForList(
					"TbCubinet.queryTbCubinetAllForListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCubinet.queryTbCubinetAllForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public TbCubinetObj queryByObj(TbCubinetObj obj) {
		List lst = null;
		TbCubinetObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCubinetObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(TbCubinetObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.update("TbCubinet.updateTbCubinetByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			sqlexception.printStackTrace();
			LogHelper.error("TbCubinet.updateTbBusiHostByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(TbCubinetObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.delete("TbCubinet.deleteTbCubinetByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCubinet.deleteTbBusiHostByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(TbCubinetObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap()
					.insert("TbCubinet.insertTbCubinetByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCubinet.insertTbCubinetByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:取出房间下拉列表信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbRoomForCubSelect() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbCubinet.queryTbRoomForCubSelect");
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCubinet.queryTbRoomForCubSelect:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:取出机柜里的主机个数
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int queryTbHostCountForCanDelete(TbCubinetObj obj) {
		int hostCount = 0;
		try {
			hostCount = ((Integer) getSqlMap().queryForObject(
					"TbCubinet.queryTbHostCountForCanDelete", obj)).intValue();
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCubinet.queryTbRoomForCubSelect:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return hostCount;
	}
	
	/**
	 * 
	 * @Title: getSequence
	 * @Description: 求一个自增长的值作为Id
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-15 下午04:58:38
	 */
	public String getSequence(){
		int sequence = 0;
		try{
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj(); 
			globalObj.setKEY("TB_CLOUD2_CUBINET_INFO_SEQUENCE");
			Object obj = getSqlMap().queryForObject("TbGlobalConfig.queryForObjByObj", globalObj);
			TbGlobalConfigObj reObj = null;
			if(obj!=null){
				reObj = (TbGlobalConfigObj)obj;
				sequence = Integer.parseInt(reObj.getVALUE());
			}
			if(obj==null){
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				Object insertObj = getSqlMap().insert("TbGlobalConfig.insertByObj", globalObj);
				return "1";
			}else if(sequence >= 999999){
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
				return "1";
			}else{
				globalObj.setVALUE((sequence+1)+"");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
			}
		}catch(Exception e){
			LogHelper.error("TbGlobalConfig.operate:"
					+ e.getMessage() + getClass().getName());
		}
		return sequence+"";
	}

}

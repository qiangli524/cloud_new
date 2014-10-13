package com.sitech.basd.yicloud.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.deploy.TbCloud2DepFileObj;

public class TbCloud2DepFileDaoImpl extends BaseDao implements TbCloud2DepFileDao {

	/**
	 * @Title:插入部署文件信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2DepFileObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbCloud2DepFile.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGroupfunc.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see com.sitech.basd.yicloud.dao.deploy.TbCloud2DepFileDao#queryBatchID()
	 * 得到批次id
	 * @author yangwca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public int queryBatchID() {
		int ret=0;
		try{
			List lst = getSqlMap().queryForList("TbCloud2DepFile.queryBatchID");
			if(lst!=null && lst.size()>0){
				for(int i=0;i<lst.size();i++){
					ret=(Integer)lst.get(i);
				}
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2DepFile.queryBatchID:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	
	
}

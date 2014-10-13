package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareFileInfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("tbBusiSoftwareFileInfoDao")
public class TbBusiSoftwareFileInfoDaoImpl extends BaseDao implements TbBusiSoftwareFileInfoDao {

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入软件文件表
	 * @param
	 * @return int
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-10 下午3:13:24
	 */
	public int insertByObj(TbBusiSoftwareFileInfoObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbBusiSoftwareFileInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiSoftwareFileInfo.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

}

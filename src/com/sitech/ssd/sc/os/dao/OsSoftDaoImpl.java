package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsSoftModel;

/**
 * 
 * @ClassName: OsSoftDaoImpl
 * @Description: 软件包管理Dao实现
 * @author JamTau
 * @date 2014-9-10 上午10:13:07
 *
 */
@Repository("osSoftDao")
public class OsSoftDaoImpl extends BaseDao implements OsSoftDao {

	@Override
	public int copyOsTemplateSoft(OsSoftModel soft) {
		int ret = 0;
		try {
			getSqlMap().insert("OsSoft.copyOsTemplateSoft",soft);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsSoft.copyOsTemplateSoft:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteOsSoft(OsSoftModel soft) {
		int ret = 0;
		try {
			getSqlMap().insert("OsSoft.deleteOsSoft",soft);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsSoft.deleteOsSoft:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsSoftModel> selectOsSoftList(OsSoftModel soft) {
		List<OsSoftModel> list = null;
		try {
			list = getSqlMap().queryForList("OsSoft.selectOsSoftList", soft);
		} catch (SQLException e) {
			LogHelper.error("OsSoft.selectOsSoftList:" + e.getMessage() + 
					getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsSoftModel> unionOsSoftList(OsSoftModel soft){
		List<OsSoftModel> list = null;
		try {
			list = getSqlMap().queryForList("OsSoft.unionOsSoftList", soft);
		} catch (SQLException e) {
			LogHelper.error("OsSoft.unionOsSoftList:" + e.getMessage() + 
					getClass().getName());
		}
		return list;
	}
	
}

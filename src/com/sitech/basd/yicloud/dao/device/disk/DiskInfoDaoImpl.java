package com.sitech.basd.yicloud.dao.device.disk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;

@Repository("diskInfoDao")
public class DiskInfoDaoImpl extends BaseDao implements DiskInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DiskInfoObj> queryForListByObj(DiskInfoObj paramObj) {
		List<DiskInfoObj> list = new ArrayList<DiskInfoObj>();
		try {
			if (paramObj.getPagination() != null) {
				paramObj.setFIRSTROWNUM(paramObj.getPagination().getFirstRownum());
				paramObj.setPAGESIZE(paramObj.getPagination().getPageSize());
				paramObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"diskInfo.queryForCountByObj", paramObj))
								.intValue());
			}
			list = getSqlMap().queryForList("diskInfo.queryForListByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.queryForListByObj: 查询磁盘列表失败，失败原因： " + e );
		}
		return list;
	}
	
	@Override
	public List<DiskInfoObj> queryForListByGroupIsNull(DiskInfoObj paramObj) {
		List<DiskInfoObj> list = new ArrayList<DiskInfoObj>();
		try {
			if (paramObj.getPagination() != null) {
				paramObj.setFIRSTROWNUM(paramObj.getPagination().getFirstRownum());
				paramObj.setPAGESIZE(paramObj.getPagination().getPageSize());
				paramObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"diskInfo.queryForListByGroupIsNullCount", paramObj))
								.intValue());
			}
			list = getSqlMap().queryForList("diskInfo.queryForListByGroupIsNull", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.queryForListByGroupIsNull: 查询磁盘列表失败，失败原因： " + e );
		}
		return list;
	}

	@Override
	public int insertByObj(DiskInfoObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().insert("diskInfo.insertByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.insertByObj: 插入磁盘组记录" + paramObj.getDisk_name() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

	@Override
	public int updateByObj(DiskInfoObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().update("diskInfo.updateByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.updateByObj: 更新磁盘记录" + paramObj.getDisk_name() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}
	@Override
	public int updateGroupIdByObj(DiskInfoObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().update("diskInfo.updateGroupIdByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.updateGroupIdByObj: 更新磁盘记录" + paramObj.getDisk_name() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

	@Override
	public int deleteByObj(DiskInfoObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().delete("diskInfo.deleteByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("diskInfo.deleteByObj: 删除磁盘" + paramObj.getDisk_name() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

}

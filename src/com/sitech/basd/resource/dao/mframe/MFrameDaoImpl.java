package com.sitech.basd.resource.dao.mframe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.mframe.MFrameObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("mFrameDao")
public class MFrameDaoImpl extends BaseDao implements MFrameDao {

	/**
	 * 
	 * @Title: FrameList
	 * @Description: 查询机框列表
	 * @param
	 * @return List<MFrameObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<MFrameObj> queryFrameList(MFrameObj obj) {
		List<MFrameObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("mFrame.queryForCount", obj))
								.intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("mFrame.queryFrameList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("mFrame.queryFrameList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 增加一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertFrame(MFrameObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("mFrame.insertFrame", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("mFrame.insertFrame:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 更新一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateFrame(MFrameObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("mFrame.updateFrame", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("mFrame.updateFrame:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteConfig
	 * @Description: 删除一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 23, 2013
	 */
	public int deleteFrame(MFrameObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("mFrame.deleteFrame", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("mFrame.deleteFrame:" + e.getMessage());
		}
		return ret;
	}
}

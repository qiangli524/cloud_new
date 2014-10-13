package com.sitech.basd.bol.dao.nodetask;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.bol.NodeTaskTypeConstant;

@SuppressWarnings("all")
@Repository("nodeTaskDao")
public class NodeTaskDaoImpl extends BaseDao implements NodeTaskDao {
	
	
	public BolNodeTaskVO queryByObj(BolNodeTaskVO obj) {
		List lst = null;
		BolNodeTaskVO taskObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			taskObj = (BolNodeTaskVO) lst.get(0);
		}
		return taskObj;
	}

	public List<BolNodeTaskVO> queryForListByObj(BolNodeTaskVO obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"NodeTask.queryForListByObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("NodeTask.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("NodeTask.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int insertByObj(BolNodeTaskVO obj) {
		int ret = 0;
		try {
			if(obj.getTaskType()==NodeTaskTypeConstant.APPLY){
				obj.setTaskTrack("申请");
			}else if(obj.getTaskType()==NodeTaskTypeConstant.RELEASE){
				obj.setTaskTrack("申请");
			}else if(obj.getTaskType()==NodeTaskTypeConstant.UPGRADE){
				obj.setTaskTrack(" 升级");
			}
			Object o = getSqlMap().insert("NodeTask.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("NodeTask.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int updateByObj(BolNodeTaskVO obj) {
		int ret = 0;
		try {
			BolNodeTaskVO queryObj = new BolNodeTaskVO();
			queryObj.setTaskId(obj.getTaskId());
			String track = queryByObj(queryObj).getTaskTrack();
			if(obj.getTaskState() == 4){
				obj.setTaskTrack(track+"-->申请成功");
			}else if(obj.getTaskState() == 5){
				obj.setTaskTrack(track+"-->申请失败");
			}else if(obj.getTaskState() == 21){
				obj.setTaskTrack(track+"-->升级成功");
			}else if(obj.getTaskState() == 22){
				obj.setTaskTrack(track+"-->升级失败");
			}
			Object o = getSqlMap().update("NodeTask.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("NodeTask.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int deleteByObj(BolNodeTaskVO obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("NodeTask.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("NodeTask.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
}

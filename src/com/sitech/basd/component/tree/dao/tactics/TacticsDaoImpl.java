package com.sitech.basd.component.tree.dao.tactics;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.tree.domain.tactics.TacticsObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("tacticsDao")
public class TacticsDaoImpl extends BaseDao implements TacticsDao {

	@Override
	public int insertTacticsObj(TacticsObj tacticsObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().insert("Tactics.insertTacticsObj", tacticsObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Tactics.insertTacticsObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	@Override
	public int deleteTacticsObj(TacticsObj tacticsObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().delete("Tactics.deleteTacticsObj", tacticsObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Tactics.deleteTacticsObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateTacticsObj(TacticsObj tacticsObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().update("Tactics.updateTacticsObj", tacticsObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Tactics.updateTacticsObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TacticsObj> queryForList(TacticsObj tacticsObj) {
		List<TacticsObj> list = null;
		try {
			if (tacticsObj.getPagination() != null) {
				tacticsObj.setFIRSTROWNUM(tacticsObj.getPagination().getFirstRownum());
				tacticsObj.setPAGESIZE(tacticsObj.getPagination().getPageSize());
				tacticsObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Tactics.queryTacticsCount", tacticsObj)).intValue());
			}
			list = getSqlMap().queryForList("Tactics.queryTacticsList", tacticsObj);
		} catch (Exception e) {
			// TODO: handle exception
			LogHelper.error("Tactics.queryTacticsList:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int countNum(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("Tactics.queryTacticsCount", tacticsObj);
			if (obj != null) {
				ret = (Integer)obj;
			}
		} catch (Exception e) {
			LogHelper.error("Tactics.queryTacticsCount: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteTacticsByTaskId(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		int ret = -1;
		try {
			Object obj = getSqlMap().delete("Tactics.deleteTacticsByTaskId", tacticsObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Tactics.deleteTacticsByTaskId: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

}

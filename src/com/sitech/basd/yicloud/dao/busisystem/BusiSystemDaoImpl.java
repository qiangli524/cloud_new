package com.sitech.basd.yicloud.dao.busisystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

@Repository("busiSystemDao")
public class BusiSystemDaoImpl extends BaseDao implements BusiSystemDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 根据传入的对象要求查询符合条件的记录集合
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午5:19:14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiSystemObj> queryForListByObj(BusiSystemObj busiSystemObj) {
		List<BusiSystemObj> list = new ArrayList<BusiSystemObj>();
		try {
			if (busiSystemObj.getPagination() != null) {
				busiSystemObj.setFIRSTROWNUM(busiSystemObj.getPagination().getFirstRownum());
				busiSystemObj.setPAGESIZE(busiSystemObj.getPagination().getPageSize());
				busiSystemObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("busiSystem.CountByObj", busiSystemObj)).intValue()
				);
			}
			list = getSqlMap().queryForList("busiSystem.queryForListByObj", busiSystemObj);
		} catch (Exception e) {
			LogHelper.error("busiSystem.queryForListByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}

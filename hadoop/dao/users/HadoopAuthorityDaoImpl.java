package dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.users.HadoopAuthorityObj;

/**
 * <p>
 * Title: HadoopAuthorityDaoImpl
 * </p>
 * <p>
 * Description: hadoop权限
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-3-5 下午4:27:23
 * 
 */
@Repository("hadoopAuthorityDao")
public class HadoopAuthorityDaoImpl extends BaseDao
		implements
			HadoopAuthorityDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-5 下午4:26:16
	 */
	@Override
	public int insertByObj(HadoopAuthorityObj authorityObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopAuthority.insertByObj", authorityObj);
		} catch (Exception e) {
			LogHelper.error("HadoopAuthority.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopAuthorityObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:20:33
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopAuthorityObj> queryForListByObj(
			HadoopAuthorityObj authorityObj) {
		List<HadoopAuthorityObj> list = new ArrayList<HadoopAuthorityObj>();
		try {
			if (authorityObj.getPagination() != null) {
				authorityObj.setFIRSTROWNUM(authorityObj.getPagination()
						.getFirstRownum());
				authorityObj.setPAGESIZE(authorityObj.getPagination()
						.getPageSize());
				authorityObj.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopAuthority.queryForCount", authorityObj))
								.intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopAuthority.queryForListByObj", authorityObj);
		} catch (Exception e) {
			LogHelper.error("HadoopAuthority.queryForListByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:53:27
	 */
	@Override
	public void updateByObj(HadoopAuthorityObj authorityObj) {
		try {
			getSqlMap().update("HadoopAuthority.updateByObj", authorityObj);
		} catch (Exception e) {
			LogHelper.error("HadoopAuthority.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
	}

}

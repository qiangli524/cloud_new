package com.sitech.ssd.gx.dao.huaweihost;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.gx.domain.huaweihost.HuaweiHostObj;

public class HuaweiHostDaoImpl extends BaseDao implements HuaweiHostDao {

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加主机
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 上午11:30:32
	 */
	public int insertByObj(HuaweiHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("huaweiHost.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("huaweiHost.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 获得主机信息
	 * @param
	 * @return List<HuaweiHostObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:02:04
	 */
	@SuppressWarnings("unchecked")
	public List<HuaweiHostObj> queryForListByObj(HuaweiHostObj obj) {
		List<HuaweiHostObj> lst = new ArrayList<HuaweiHostObj>();
		try {
			lst = (List<HuaweiHostObj>) getSqlMap().queryForList(
					"huaweiHost.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("huaweiHost.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}

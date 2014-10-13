package com.sitech.ssd.gx.dao.huaweivm;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.gx.domain.huaweivm.HuaweiVmObj;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
public class HuaweiVMHostDaoImpl extends BaseDao implements HuaweiVMHostDao {

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询虚拟机信息
	 * @param
	 * @return HuaweiVmObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:26:33
	 */
	public HuaweiVmObj queryByObj(HuaweiVmObj obj) {
		List lst = null;
		HuaweiVmObj vmObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			vmObj = (HuaweiVmObj) lst.get(0);
		}
		return vmObj;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return List<HuaweiVmObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:26:49
	 */
	public List<HuaweiVmObj> queryForListByObj(HuaweiVmObj obj) {
		List<HuaweiVmObj> lst = new ArrayList<HuaweiVmObj>();
		try {
			lst = (List<HuaweiVmObj>)getSqlMap().queryForList("huaweiVM.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("huaweiVM.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加虚拟机信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:24:37
	 */
	public int insertByObj(HuaweiVmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("huaweiVM.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("huaweiVM.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}

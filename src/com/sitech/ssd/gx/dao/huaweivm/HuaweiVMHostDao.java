package com.sitech.ssd.gx.dao.huaweivm;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.gx.domain.huaweivm.HuaweiVmObj;

@SuppressWarnings("all")
public interface HuaweiVMHostDao {
	
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
	public HuaweiVmObj queryByObj(HuaweiVmObj obj);

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
	public List<HuaweiVmObj> queryForListByObj(HuaweiVmObj obj);

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
	public int insertByObj(HuaweiVmObj obj);

}

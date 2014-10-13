package com.sitech.ssd.gx.dao.huaweihost;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.ssd.gx.domain.huaweihost.HuaweiHostObj;

public interface HuaweiHostDao {

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
	public int insertByObj(HuaweiHostObj obj);
	
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
	public List<HuaweiHostObj> queryForListByObj(HuaweiHostObj obj);


}

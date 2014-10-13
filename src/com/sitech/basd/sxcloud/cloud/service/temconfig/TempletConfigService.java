package com.sitech.basd.sxcloud.cloud.service.temconfig;

import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletTypeConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;


public interface TempletConfigService {

	/**
	 * @Title:查询已有服务列表
	 * @Copyright: Copyright (c) 2012-08-27
	 * @Company: si-tech
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletTypeConfigObj obj);
	/**
	 * @Title:查询服务具体信息
	 * @Copyright: Copyright (c) 2012-08-27
	 * @Company: si-tech
	 * @version 1.0
	 */
	
	@SuppressWarnings("unchecked")
	public List queryForListByConObj(TempletConfigObj obj);

	/**
	 * @Title:查询并返回一个服务对象
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */

	public TempletTypeConfigObj queryByObj(TempletTypeConfigObj obj);
	
	/**
	 * @Title:创建服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */

	public int insertByObj(TempletTypeConfigObj obj);
	
	/**
	 * @Title:更新服务信息
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */

	public int updateByObj(TempletTypeConfigObj obj);
	
	/**
	 * @Title:删除已有服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */

	public int deleteByObj(TempletTypeConfigObj obj);
	
	/**
	 * @Title:插入新服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByConObj(TempletConfigObj obj);
	/**
	 * @Title:修改服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByConObj(TempletConfigObj obj);
	/**
	 * @Title:删除服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteConObj(TempletConfigObj obj);
	
	/**
	 * @Title:查询服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public TempletConfigObj queryByConObj(TempletConfigObj obj);
}

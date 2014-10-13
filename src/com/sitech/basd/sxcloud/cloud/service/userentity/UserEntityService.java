/**   
 * @Title: UserEntityService.java
 * @Package com.sitech.basd.sxcloud.cloud.service.userentity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiangdi  
 * @version 1.0
 * @createtime 2014-8-22 下午3:35:58
 */
package com.sitech.basd.sxcloud.cloud.service.userentity;

import java.util.List;

/**
 * <p>Title: UserEntityService</p>
 * <p>Description: 用户关联实体服务类
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author jiangdi
 * @version 1.0
 * @createtime 2014-8-22 下午3:35:58
 *
 */
public interface UserEntityService {

	/**
	 * @Title: getUserAllEntitys
	 * @Description: 获取用户所有实体id
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-22 下午3:36:48
	 */
	public List	getUserAllEntitys(String userid);
}

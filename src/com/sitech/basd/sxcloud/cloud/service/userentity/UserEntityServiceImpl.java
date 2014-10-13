/**   
 * @Title: UserEntityServiceImpl.java
 * @Package com.sitech.basd.sxcloud.cloud.service.userentity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiangdi  
 * @version 1.0
 * @createtime 2014-8-22 下午3:37:57
 */
package com.sitech.basd.sxcloud.cloud.service.userentity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.service.BaseService;

/**
 * <p>Title: UserEntityServiceImpl</p>
 * <p>Description: 用户关联实体服务类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author jiangdi
 * @version 1.0
 * @createtime 2014-8-22 下午3:37:57
 *
 */
@Service("userEntityServiceImpl")
public class UserEntityServiceImpl  extends BaseService implements UserEntityService{

	@Override
	public List getUserAllEntitys(String userid) {
		List result = new ArrayList();
		try {
			result = getSqlMap().queryForList("UserEntity.queryForAllEntity",Integer.valueOf(userid));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

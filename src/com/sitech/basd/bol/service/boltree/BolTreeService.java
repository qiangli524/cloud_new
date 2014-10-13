package com.sitech.basd.bol.service.boltree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.bol.domain.boltree.BolTreeObj;

/**
 * <p>
 * Title: BolTreeService
 * </p>
 * <p>
 * Description: 资源视图树逻辑层接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:45:06
 * 
 */
public interface BolTreeService {

	/**
	 * @Title: queryForRolTreeList
	 * @Description: 查询树对象集合
	 * @param
	 * @return List<RolTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 上午11:19:35
	 */
	List<BolTreeObj> queryForBolTreeList(HttpServletRequest request);

}

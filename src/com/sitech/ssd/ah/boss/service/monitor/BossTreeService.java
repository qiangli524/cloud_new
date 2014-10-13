package com.sitech.ssd.ah.boss.service.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: BossTreeService
 * </p>
 * <p>
 * Description: Boss树服务接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-25 上午11:15:38
 * 
 */
public interface BossTreeService {
	/**
	 * @Title: queryBossTreeList
	 * @Description: 查询树节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 上午11:54:00
	 */
	public List queryBossTreeList(HttpServletRequest request);
}

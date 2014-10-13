package com.sitech.basd.bol.service.nodestatus;

import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;

/**
 * 
 * <p>Title: BolNodeStatusService</p>
 * <p>Description: 节点状态
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime Jun 13, 2014 12:45:33 PM
 *
 */
public interface BolNodeStatusService {
	
	/**
	 * 
	 * @Title: queryNodeStatus
	 * @Description: 获取节点的状态
	 * @param
	 * @return float
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 13, 2014 1:22:52 PM
	 */
	public BolNodeStatusObj queryNodeStatus(BolNodeStatusObj obj);
	
}

package com.sitech.basd.bol.dao.nodestatus;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>Title: BolNodeStatusDao</p>
 * <p>Description: 节点状态
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime Jun 13, 2014 12:45:33 PM
 *
 */
public interface BolNodeStatusDao {
	
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

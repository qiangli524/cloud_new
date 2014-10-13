package com.sitech.basd.bol.service.nodestatus;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.dao.nodestatus.BolNodeStatusDao;
import com.sitech.basd.bol.domain.boltree.BolTreeObj;
import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>Title: BolNodeStatusServiceImpl</p>
 * <p>Description: 节点状态
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime Jun 13, 2014 12:45:57 PM
 *
 */
@Repository("bolNodeStatusService")
public class BolNodeStatusServiceImpl implements BolNodeStatusService {
	@Autowired
	private BolNodeStatusDao bolNodeStatusDao;

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
	public BolNodeStatusObj queryNodeStatus(BolNodeStatusObj obj){
		return bolNodeStatusDao.queryNodeStatus(obj);
	} 
	
	
}

package com.sitech.basd.bol.dao.nodestatus;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.boltree.BolTreeObj;
import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>Title: BolNodeStatusDaoImpl</p>
 * <p>Description: 节点状态
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime Jun 13, 2014 12:45:57 PM
 *
 */
@Repository("bolNodeStatusDao")
public class BolNodeStatusDaoImpl extends BaseDao implements BolNodeStatusDao {
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
		float status = 4.00f;
		BolNodeStatusObj statusObj = new BolNodeStatusObj();
		try {
			statusObj = (BolNodeStatusObj)getSqlMap().queryForObject("BolNodeStatus.queryNodeStatus", obj);
			if(statusObj != null){
				Date date = this.StrToDate(statusObj.getTime());
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				Date now = new Date();
				long nowTime = now.getTime() - 5*60*1000;
				now = new Date(nowTime);
				if(now.after(date)){
					statusObj.setNodeValue(status);
				}
			}
		} catch (Exception e) {
			LogHelper.error("BolNodeStatus.queryNodeStatus: " + e.getMessage() + e.getClass().getName());
			e.printStackTrace();
		}
		return statusObj;
	} 
	
	/**
	 * 
	 * @Title: StrToDate
	 * @Description: 字符串转化为日期
	 * @param
	 * @return Date
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 16, 2014 5:31:04 PM
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}

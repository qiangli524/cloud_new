/**  
 * @Title: SendSmsDao.java
 * @Package com.sitech.ssd.sc.sendSms.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenjl
 * @date 2014-7-29 下午3:20:42
 * @version
 */
package com.sitech.ssd.sc.sendSms.dao;

import com.sitech.ssd.sc.sendSms.domain.SendSmsObj;

/**
 * @ClassName: SendSmsDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-29 下午3:20:42
 * 
 */
public interface SendSmsDao {

	/**
	 * @Title: insertSendSms
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author chenjl
	 * @throws
	 */
	public int insertSendSms(SendSmsObj obj);

	/**
	 * @Title: selectAuditPhone
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return SendSmsObj 返回类型
	 * @author chenjl
	 * @throws
	 */
	public SendSmsObj selectAuditPhone();

}

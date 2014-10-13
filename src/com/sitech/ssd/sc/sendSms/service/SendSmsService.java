/**  
 * @Title: SendSmsService.java
 * @Package com.sitech.ssd.sc.sendSms.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenjl
 * @date 2014-7-29 下午4:39:38
 * @version
 */
package com.sitech.ssd.sc.sendSms.service;

import com.sitech.ssd.sc.sendSms.domain.SendSmsObj;

/**
 * @ClassName: SendSmsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-29 下午4:39:38
 * 
 */
public interface SendSmsService {

	public void insertSendSms(SendSmsObj obj);

	public SendSmsObj selectAuditPhone();

}

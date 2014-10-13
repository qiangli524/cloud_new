/**  
 * @Title: SendSmsServiceImp.java
 * @Package com.sitech.ssd.sc.sendSms.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenjl
 * @date 2014-7-29 下午4:39:52
 * @version
 */
package com.sitech.ssd.sc.sendSms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sitech.ssd.sc.sendSms.dao.SendSmsDao;
import com.sitech.ssd.sc.sendSms.domain.SendSmsObj;

/**
 * @ClassName: SendSmsServiceImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-29 下午4:39:52
 * 
 */
@Service("sendSmsService")
public class SendSmsServiceImp implements SendSmsService {

	@Autowired
	private SendSmsDao sendSmsDao;

	public void insertSendSms(SendSmsObj obj) {
		sendSmsDao.insertSendSms(obj);
	}

	/*
	 * <p>Title: selectAuditPhone</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sitech.ssd.sc.sendSms.service.SendSmsService#selectAuditPhone()
	 */
	@Override
	public SendSmsObj selectAuditPhone() {

		return sendSmsDao.selectAuditPhone();
	}
}

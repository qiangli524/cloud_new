/**  
 * @Title: SendSmsDaoImp.java
 * @Package com.sitech.ssd.sc.sendSms.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenjl
 * @date 2014-7-29 下午3:21:04
 * @version
 */
package com.sitech.ssd.sc.sendSms.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.sendSms.domain.SendSmsObj;

/**
 * @ClassName: SendSmsDaoImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-29 下午3:21:04
 * 
 */
@Repository("sendSmsDao")
public class SendSmsDaoImp extends BaseDao implements SendSmsDao {

	@Override
	public int insertSendSms(SendSmsObj obj) {
		int _ret = 0;
		try {
			getSqlMap().insert("SendSms.insertSendSms", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("SendSms.insertSendSms:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public SendSmsObj selectAuditPhone() {
		SendSmsObj smsObj = null;
		try {
			smsObj = (SendSmsObj) getSqlMap().queryForObject(
					"SendSms.selectAuditPhone", "");
		} catch (SQLException e) {
			LogHelper.error("SendSms.selectAuditPhone:" + e.getMessage()
					+ getClass().getName());
		}
		return smsObj;
	}
}

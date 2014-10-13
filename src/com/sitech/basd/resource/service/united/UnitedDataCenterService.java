package com.sitech.basd.resource.service.united;

import java.sql.SQLException;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.utils.exception.HttpClientException;

public interface UnitedDataCenterService {
	/**
	 * 
	 * @Title: createDataCenter
	 * @Description: 创建数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws HttpClientException
	 * @createtime Jul 22, 2013 4:20:11 PM
	 */
	public String createDataCenter(UnitedTreeObj obj) throws HttpClientException, SQLException;

	/**
	 * 
	 * @Title: delDataCenter
	 * @Description:删除数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws HttpClientException
	 * @createtime Jul 22, 2013 5:45:53 PM
	 */
	public String delDataCenter(UnitedTreeObj obj) throws HttpClientException, SQLException;
}

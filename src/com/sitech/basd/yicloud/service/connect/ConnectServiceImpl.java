package com.sitech.basd.yicloud.service.connect;

import java.util.List;

import com.sitech.basd.yicloud.dao.connect.ConnectDao;
import com.sitech.basd.yicloud.domain.connect.ConnectObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;

public class ConnectServiceImpl implements ConnectService {

	private ConnectDao connectDao;

	/**
	 * @Title:获取连通状态下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:33:10 PM
	 * @version 1.0
	 */
	public List queryForListByConStatus(DictionaryObj obj) {
		return connectDao.queryForListByConStatus(obj);
	}

	/**
	 * @Title:获取A,Z端外围接口下拉框指
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:33:30 PM
	 * @version 1.0
	 */
	public List queryForListByInterface(ConnectObj obj) {
		return connectDao.queryForListByInterface(obj);
	}

	/**
	 * @Title:获取接口连通数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:34:15 PM
	 * @version 1.0
	 */
	public List queryForListByObj(ConnectObj obj) {
		return connectDao.queryForListByObj(obj);
	}
	
	/**
	 * @Title:添加接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:27:15 PM
	 * @version 1.0
	 */
	public int insertByObj(ConnectObj obj){
		return connectDao.insertByObj(obj);
	}
	
	/**
	 * @Title:根据ID查询接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:12:13 PM
	 * @version 1.0
	 */
	public ConnectObj queryByObj(ConnectObj obj){
		return connectDao.queryByObj(obj);
	}
	
	/**修改接口连通信息 
	 * @Title:
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:24:48 PM
	 * @version 1.0
	 */
	public int updateByObj(ConnectObj obj){
		return connectDao.updateByObj(obj);
	}
	
	/**根据ID删除接口连通信息
	 * @Title:
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 19, 2012 11:21:55 AM
	 * @version 1.0
	 */
	public int deleteByObj(ConnectObj obj){
		return connectDao.deleteByObj(obj);
	}

	public void setConnectDao(ConnectDao connectDao) {
		this.connectDao = connectDao;
	}

}

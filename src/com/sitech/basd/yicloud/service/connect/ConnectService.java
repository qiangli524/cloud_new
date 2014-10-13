package com.sitech.basd.yicloud.service.connect;

import java.util.List;

import com.sitech.basd.yicloud.domain.connect.ConnectObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;

public interface ConnectService {
	/**
	 * @Title:获取接口连通数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public List queryForListByObj(ConnectObj obj);

	/**
	 * @Title:获取A，Z端外围接口下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:58 PM
	 * @version 1.0
	 */
	public List queryForListByInterface(ConnectObj obj);

	/**
	 * @Title:获取连通状态下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:30:16 PM
	 * @version 1.0
	 */
	public List queryForListByConStatus(DictionaryObj obj);
	
	/**
	 * @Title:添加接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:26:44 PM
	 * @version 1.0
	 */
	public int insertByObj(ConnectObj obj);
	
    /**根据ID查询接口连通信息
	 * @Title:
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:11:33 PM
	 * @version 1.0
	 */
	public ConnectObj queryByObj(ConnectObj obj);
	
	/**
	 * @Title:修改接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:24:18 PM
	 * @version 1.0
	 */
	public int updateByObj(ConnectObj obj);
	
	/**根据ID删除接口连通信息
	 * @Title:
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 19, 2012 11:21:16 AM
	 * @version 1.0
	 */
	public int deleteByObj(ConnectObj obj);

}

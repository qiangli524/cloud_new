package com.sitech.basd.yicloud.dao.interfaces;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.interfaces.InterfaceObj;

public interface InterfaceDao {

	/**
	 * @Title:查询外围接口数据结果集
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(InterfaceObj obj);

	/**
	 * @Title:查询下拉框设备主机ID的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByDeviceObj(DeviceObj obj);

	/**
	 * @Title:查询下拉框端口状态的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByStatusObj(DictionaryObj obj);

	/**
	 * @Title:查询下拉框外围接口类型的值
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByTypeObj(DictionaryObj obj);


	/**
	 * @Title:添加外围接口
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(InterfaceObj obj);

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public InterfaceObj queryByObj(InterfaceObj obj);

	/**
	 * @Title:修改外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateByObj(InterfaceObj obj);

	/**
	 * @Title:根据ID删除外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(InterfaceObj obj);
	

}

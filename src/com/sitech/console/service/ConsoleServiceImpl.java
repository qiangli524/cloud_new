package com.sitech.console.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.exception.ConsoleException;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;

/**
 * 
 * <p>
 * Title: ConsoleServiceImpl
 * </p>
 * <p>
 * Description: 控制台逻辑实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-7-30 下午2:15:54
 * 
 */
@Service("consoleService")
public class ConsoleServiceImpl implements ConsoleService {
	@Autowired
	private TbGlobalConfigDao tbGlobalConfigDao;

	/**
	 * 
	 * @Title: initWebsockifyParam
	 * @Description: 实例Websockify参数
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-30 下午2:23:54
	 */
	public Map<String, String> initWebsockifyParam() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		TbGlobalConfigObj param = new TbGlobalConfigObj();
		param.setKEY("websockify_param");
		param = tbGlobalConfigDao.queryByObj(param);
		if (param != null) {
			String[] params = param.getVALUE().split(":");
			map.put("websockify_host", params[0]);
			map.put("websockify_port", params[1]);
		} else {
			throw new ConsoleException(
					"全局配置表未配置‘websockify_param’参数，参数格式为：{websockify_host : websockify_port}");
		}
		return map;
	}
}

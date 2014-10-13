package com.sitech.console.service;

import java.util.Map;

/**
 * 
 * <p>
 * Title: ConsoleService
 * </p>
 * <p>
 * Description: 虚拟化控制台逻辑接口
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
 * @createtime 2014-7-30 下午2:15:30
 * 
 */
public interface ConsoleService {
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
	public Map<String, String> initWebsockifyParam() throws Exception;
}

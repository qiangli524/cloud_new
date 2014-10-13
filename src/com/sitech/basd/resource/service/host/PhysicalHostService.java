package com.sitech.basd.resource.service.host;

public interface PhysicalHostService {
	/**
	 * 
	 * @Title: shutDownHost
	 * @Description:远程主机下电
	 * @author duangh
	 * @date 2013 九月 10 14:28:43
	 * @return
	 */
	public String shutDownHost(String id);

	/**
	 * 
	 * @Title: startUpHost
	 * @Description:远程主机上电
	 * @author duangh
	 * @date 2013 九月 10 14:29:04
	 * @return
	 */
	public String startUpHost(String hosts);

	/**
	 * 
	 * @Title: bootFromPXE
	 * @Description:设置主机从PXE启动
	 * @author duangh
	 * @date 2013 九月 10 14:29:31
	 * @return
	 */
	public String bootFromPXE(String hosts);

	public String rebootHost(String hosts);

	/**
	 * 
	 * @Title: installOS
	 * @Description:执行安装操作系统脚本
	 * @author duangh
	 * @date 2013 九月 10 22:46:13
	 * @return
	 */
	public String installOS(String os);
}

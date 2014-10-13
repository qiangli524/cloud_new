package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.util.ssh.SshConnection;

public interface ShellService {
	/**
	 * @Title:获取SSH通道
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public SshConnection getSshConnection(HttpServletRequest request,
			String host, String port, String username, String pwd);

	/**
	 * @Title:关闭SSH通道
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public void closeConnection(HttpServletRequest request);

	/**
	 * @Title:往SSH通道写数据
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public void writeToChannel(HttpServletRequest request,
			HttpServletResponse response, boolean sendNewLine, String shellCmd,
			String videoid, int sequence);

	/**
	 * @Title:录像过程记录列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public List queryForListByObj(TbBusiDeployVideorecordObj obj);

	/**
	* @Title:录像过程记录
	* @Copyright: Copyright (c) 201006
	* @Company: si-tech
	* @author yangwenchao
	* @version 1.0
	*/
	public int insertByObj(TbBusiDeployVideorecordObj obj);

	/**
	 * @Title:获得执行编号
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public int queryForID_FREQ_SEQUENCES();

	/**
	 * @Title:查询序列作为videoid
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	*/
	public int queryForVideoId();
}

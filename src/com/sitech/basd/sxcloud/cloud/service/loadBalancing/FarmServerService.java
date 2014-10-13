package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;

public interface FarmServerService {

	/**
	 * @Title:根据真实服务器部分信息查询匹配的所有真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(FarmServerObj obj);;

	/**
	 * @Title:查询具体的真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public FarmServerObj queryByObj(FarmServerObj obj);;

	/**
	 * @Title:变更真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int updateByObj(FarmServerObj obj);

	/**
	 * @Title:删除真实服务器部分信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int deleteByObj(FarmServerObj obj);

	/**
	 * @Title:插入真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int insertByObj(FarmServerObj obj);
}

package com.sitech.basd.sxcloud.rsmu.dao.cloudschedu;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.RealServerObj;


public interface RealServerDao {

	/**
	 * @Title:根据真实服务器部分信息查询匹配的所有真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(RealServerObj obj);;

	/**
	 * @Title:查询具体的真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public RealServerObj queryByObj(RealServerObj obj);;

	/**
	 * @Title:变更真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int updateByObj(RealServerObj obj);

	/**
	 * @Title:删除真实服务器部分信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int deleteByObj(RealServerObj obj);

	/**
	 * @Title:插入真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int insertByObj(RealServerObj obj);
}

package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;

public interface FarmsService {

	/**
	 * @Title:根据真实服务器部分信息查询匹配的所有农场信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(FarmObj obj);;

	/**
	 * @Title:查询具体的农场信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public FarmObj queryByObj(FarmObj obj);;

	/**
	 * @Title:变更农场信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int updateByObj(FarmObj obj);

	/**
	 * @Title:删除农场信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int deleteByObj(FarmObj obj);

	/**
	 * @Title:插入真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int insertByObj(FarmObj obj);

	/**
	 * @Title:同步radware数据到数据库
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int SynchronousFarms();
}

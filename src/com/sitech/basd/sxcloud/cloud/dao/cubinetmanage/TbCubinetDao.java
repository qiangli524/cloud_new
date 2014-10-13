package com.sitech.basd.sxcloud.cloud.dao.cubinetmanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.cubinetmanage.TbCubinetObj;

public interface TbCubinetDao {

	/**
	 * @Title:根据机柜部分信息查询匹配的所有机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(TbCubinetObj obj);

	/**
	 * @Title:查询出具体机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public TbCubinetObj queryByObj(TbCubinetObj obj);

	/**
	 * @Title:更新机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(TbCubinetObj obj);

	/**
	 * @Title:更新机柜信息(修改vlan后的更新方式，需把EXCUTE_FLAG字段更改为0,以便后台扫描到进行操作)
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangzeca
	 * @version 1.0
	 */
	public int deleteByObj(TbCubinetObj obj);

	/**
	 * @Title:插入机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(TbCubinetObj obj);

	/**
	 * @Title:取出房间下拉列表信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbRoomForCubSelect();

	/**
	 * @Title:取出机柜里的主机个数
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int queryTbHostCountForCanDelete(TbCubinetObj obj);
	
	/**
	 * 
	 * @Title: getSequence
	 * @Description: 求一个自增长的值作为Id
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-15 下午04:58:38
	 */
	public String getSequence();
}

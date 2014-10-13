package com.sitech.basd.sxcloud.cloud.service.defend;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.defend.DefendObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;

public interface DefendService {

	/**
	 * @Title:根据防篡改部分信息查询匹配的所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(DefendObj obj);

	/**
	 * @Title:查询出具体防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public DefendObj queryByObj(DefendObj obj);

	/**
	 * @Title:更新防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(DefendObj obj);

	/**
	 * @Title:删除防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(DefendObj obj);

	/**
	 * @Title:插入防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(DefendObj obj);

	/**
	 * @Title:查询主机名称与主机ID作为页面的下拉列表显示
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByTbBusiHostObj(TbBusiHostObj obj);

	/**
	 * @Title:插入防篡改信息历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertHisByObj(DefendObj obj);

	/**
	 * @Title:查询防篡改历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryHisListByObj(DefendObj obj);

	/**
	 * @Title:查询可以添加防篡改的IP地址
	 * @Copyright: Copyright (c) 20120403
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryDefendIp(DefendObj obj);
}

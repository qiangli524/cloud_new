package com.sitech.basd.sxcloud.cloud.service.defend;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.defend.DefendDao;
import com.sitech.basd.sxcloud.cloud.domain.defend.DefendObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class DefendServiceImpl extends BaseService implements DefendService {

	@SuppressWarnings( { "unchecked", "unused" })
	private static List DefendList = null;

	/**
	 * @Title:删除防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(DefendObj obj) {
		return defendDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(DefendObj obj) {
		return defendDao.insertByObj(obj);
	}

	/**
	 * @Title:查询出具体防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public DefendObj queryByObj(DefendObj obj) {
		return defendDao.queryByObj(obj);
	}

	/**
	 * @Title:根据防篡改部分信息查询匹配的所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(DefendObj obj) {
		return defendDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(DefendObj obj) {
		return defendDao.updateByObj(obj);
	}

	/**
	 * @Title:查询主机名称与主机ID作为页面的下拉列表显示
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByTbBusiHostObj(TbBusiHostObj obj) {

		return defendDao.queryForListByTbBusiHostObj(obj);
	}

	/**
	 * @Title:插入防篡改信息历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertHisByObj(DefendObj obj) {
		return defendDao.insertHisByObj(obj);
	}

	/**
	 * @Title:查询防篡改历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryHisListByObj(DefendObj obj) {
		return defendDao.queryHisListByObj(obj);
	}

	/**
	 * @Title:查询可以添加防篡改的IP地址
	 * @Copyright: Copyright (c) 20120403
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryDefendIp(DefendObj obj) {
		return defendDao.queryDefendIp(obj);
	}

	private DefendDao defendDao;

	public void setDefendDao(DefendDao defendDao) {
		this.defendDao = defendDao;
	}

}

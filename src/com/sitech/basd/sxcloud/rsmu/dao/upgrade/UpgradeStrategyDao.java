package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeStrategyObj;

public interface UpgradeStrategyDao {

	/**
	 * @Title:根据升级策略信息查询数据
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(UpgradeStrategyObj obj);;

	/**
	 * @Title:查询升级策略
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public UpgradeStrategyObj queryByObj(UpgradeStrategyObj obj);;

	/**
	 * @Title:变更升级策略
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int updateByObj(UpgradeStrategyObj obj);

	/**
	 * @Title:删除升级策略
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int deleteByObj(UpgradeStrategyObj obj);

	/**
	 * @Title:插入升级策略
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int insertByObj(UpgradeStrategyObj obj);

}

package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleObj;

public interface UpgradeExampleDao {

	/**
	 * @Title:根据升级信息查询匹配的所有升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(UpgradeExampleObj obj);;

	/**
	 * @Title:查询出当前页升级实例管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryListIDByObj(UpgradeExampleObj obj);

	/**
	 * @Title:查询具体的升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public UpgradeExampleObj queryByObj(UpgradeExampleObj obj);;

	/**
	 * @Title:变更升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int updateByObj(UpgradeExampleObj obj);

	/**
	 * @Title:删除升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int deleteByObj(UpgradeExampleObj obj);

	/**
	 * @Title:插入升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int insertByObj(UpgradeExampleObj obj);

	/**
	 * @Title:升级操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int upgradeByObj(UpgradeExampleObj obj);

	/**
	 * @Title:启动应用操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int startByObj(UpgradeExampleObj obj);

	/**
	 * @Title:停止操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public int stopByObj(UpgradeExampleObj obj);

	/**
	 * @Title:更改升级的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateUPGRADE_PERCENT(UpgradeExampleObj obj);

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateSTART_STOP_PERCENT(UpgradeExampleObj obj);
}

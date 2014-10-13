package com.sitech.basd.resource.dao.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.SwitchObj;

public interface SwitchDao {

	/**
	 * 
	 * @Title: SwitchList
	 * @Description: 查询交换机列表
	 * @param
	 * @return List<Switch>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public List<SwitchObj> querySwitchList(SwitchObj obj);

	/**
	 * 
	 * @Title: insertSwitch
	 * @Description: 插入交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int insertSwitch(SwitchObj obj);

	/**
	 * 
	 * @Title: updateSwitch
	 * @Description: 更新交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int updateFrame(SwitchObj obj);

	/**
	 * 
	 * @Title: deleteSwitch
	 * @Description: 删除交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int deleteFrame(SwitchObj obj);

}

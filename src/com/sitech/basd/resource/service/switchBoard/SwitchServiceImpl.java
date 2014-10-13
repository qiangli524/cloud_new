package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.switchBoard.SwitchDao;
import com.sitech.basd.resource.domain.switchBoard.SwitchObj;

@Service("switchService")
public class SwitchServiceImpl implements SwitchService {

	@Autowired
	private SwitchDao switchDao;

	/**
	 * 
	 * @Title: SwitchList
	 * @Description: 查询机框列表
	 * @param
	 * @return List<Switch>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public List<SwitchObj> querySwitchList(SwitchObj obj) {
		return switchDao.querySwitchList(obj);
	}

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
	public int insertSwitch(SwitchObj obj) {
		return switchDao.insertSwitch(obj);
	}

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
	public int updateSwitch(SwitchObj obj) {
		return switchDao.updateFrame(obj);
	}

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
	public int deleteSwitch(SwitchObj obj) {
		return switchDao.deleteFrame(obj);
	}

}

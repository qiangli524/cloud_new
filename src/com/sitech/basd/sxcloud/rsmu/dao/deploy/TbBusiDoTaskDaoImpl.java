package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.sql.SQLException;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDoTaskObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;



public class TbBusiDoTaskDaoImpl extends BaseDao implements TbBusiDoTaskDao {
	
	/**
	 * @Title:插入捕获应用镜像任务到 待处理任务表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public  void insertCatchImageTask(TbBusiDoTaskObj taskObj) {

		try {
			getSqlMap().insert("TbBusiDoTask.insertCatchImageTask", taskObj);
			taskObj.setEXECUTE_FLAG(1);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiDoTask.insertCatchImageTask:"
					+ sqlexception.getMessage());
		}
	}

	/**
	 * @Title:查询应用被捕获过的task记录
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public  TbBusiDoTaskObj getTaskObj(TbBusiDoTaskObj taskObj) {
		TbBusiDoTaskObj tbBusiDoTaskObj = null;
		try {
			tbBusiDoTaskObj = (TbBusiDoTaskObj)getSqlMap().queryForObject("TbBusiDoTask.getTaskObj", taskObj);
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbBusiDoTask.getTaskObj:"
					+ sqlexception.getMessage());
		}
		return tbBusiDoTaskObj ;
	}

	
}

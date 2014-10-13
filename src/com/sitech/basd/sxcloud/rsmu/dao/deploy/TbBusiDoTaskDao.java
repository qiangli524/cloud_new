package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDoTaskObj;


public interface TbBusiDoTaskDao  {
	
	/**
	 * @Title:插入捕获应用镜像任务到 待处理任务表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public  void insertCatchImageTask(TbBusiDoTaskObj taskObj);


	/**
	 * @Title:查询应用被捕获过的task记录
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public  TbBusiDoTaskObj getTaskObj(TbBusiDoTaskObj taskObj);

	
}

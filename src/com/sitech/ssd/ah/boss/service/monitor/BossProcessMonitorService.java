package com.sitech.ssd.ah.boss.service.monitor;

import java.util.List;

import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;

/**
 * <p>
 * Title: BossProcessMonitorService
 * </p>
 * <p>
 * Description: boss进程监控服务接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-4 下午4:50:33
 * 
 */
public interface BossProcessMonitorService {
	/**
	 * @Title: queryMonitorObj
	 * @Description: 查询监控进程
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 下午5:05:34
	 */
	public List<BossMonitorObj> queryMonitorObjList(BossMonitorObj obj);

	/**
	 * @Title: updateMonitorObj
	 * @Description: 更改进程标识
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-14 下午12:05:39
	 */
	public void updateMonitorObj(BossMonitorObj obj);

	/**
	 * @Title: queryMonitorObj
	 * @Description: 查询单个进程对象
	 * @param
	 * @return BossMonitorObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-14 下午4:04:27
	 */
	public List<BossMonitorObj> queryMonitorObjListByObj(BossMonitorObj obj);

	/**
	 * @Title: queryCommonObjList
	 * @Description: 查询集群或程序池集合
	 * @param
	 * @return List<CommonObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-19 下午8:06:29
	 */
	public List<CommonObj> queryCommonObjList(CommonObj obj);
}

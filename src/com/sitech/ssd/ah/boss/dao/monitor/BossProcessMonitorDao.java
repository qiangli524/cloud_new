package com.sitech.ssd.ah.boss.dao.monitor;

import java.util.List;

import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;

/**
 * <p>
 * Title: BossProcessMonitorDao
 * </p>
 * <p>
 * Description: boss进程监控Dao
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
 * @createtime 2014-8-4 下午4:51:50
 * 
 */
public interface BossProcessMonitorDao {
	/**
	 * @Title: queryMonitorObj
	 * @Description: 查询监控进程列表
	 * @param
	 * @return List<BossMonitorObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-13 下午3:09:30
	 */
	public List<BossMonitorObj> queryMonitorObjList(BossMonitorObj obj);

	/**
	 * @Title: queryMonitorObj
	 * @Description: 查询单个进程对象
	 * @param
	 * @return BossMonitorObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-14 下午2:08:57
	 */
	public BossMonitorObj queryMonitorObj(BossMonitorObj obj);

	/**
	 * @Title: updateMonitorObj
	 * @Description: 更改进程标识
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-14 下午12:07:41
	 */
	public void updateMonitorObj(BossMonitorObj obj);

	/**
	 * @Title: queryCommonObjList
	 * @Description: 查询集群或程序池集合
	 * @param
	 * @return List<CommonObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-19 下午8:04:02
	 */
	public List<CommonObj> queryCommonObjList(CommonObj obj);

	/**
	 * @Title: queryMonitorObjByClu
	 * @Description: 查询某集群下是否有池子
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 下午3:20:25
	 */
	public List<BossMonitorObj> queryMonitorObjByClu(BossMonitorObj obj);

	/**
	 * @Title: queryMonitorObjByPool
	 * @Description: 查询某集群下某池子下是否有主机
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 下午3:21:24
	 */
	public List<BossMonitorObj> queryMonitorObjByPoolAndClu(BossMonitorObj obj);

	/**
	 * @Title: queryMonitorObjListf
	 * @Description: 查询总的进程列表
	 * @param
	 * @return List<BossMonitorObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 下午5:02:42
	 */
	public List<BossMonitorObj> queryMonitorObjListf(BossMonitorObj obj);

}

package com.sitech.ssd.ah.busiMon.dao;

import java.util.List;

import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>Title:BossBusiMonAlarmCfgDao</p>
 * <p>Description:boss业务监控配置数据库持久化接口</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 11, 2014 7:37:44 PM
 */
@SuppressWarnings("all")
public interface BossBusiMonAlarmCfgDao {
	
	/**
	 * @Title:queryForListByObj
	 * @Description:查询boss业务监控报警配置list的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return List
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:26 PM
	 */
	public List queryForListByObj(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:addBossBusiMonAlarmCfg
	 * @Description:增加boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return int
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:29 PM
	 */
	public int addBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:updateBossBusiMonAlarmCfgById
	 * @Description:更新boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:31 PM
	 */
	public void updateBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:queryListById
	 * @Description:通过id查询boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return List<TbBossBusiMonAlarmCfg>
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:33 PM
	 */
	public List<TbBossBusiMonAlarmCfg> queryListById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:deleteBossBusiMonAlarmCfgById
	 * @Description:删除boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:35 PM
	 */
	public void deleteBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:openOrCloseBossBusiMon
	 * @Description:关闭或者打开boss业务监控报警的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:38:38 PM
	 */
	public void openOrCloseBossBusiMon(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:checkAlarmCfgIsExist
	 * @Description:检查boss业务监控报警是否存在的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return List<TbBossBusiMonAlarmCfg>
	 * @throws Exception
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 13, 2014 11:15:18 AM
	 */
	public List<TbBossBusiMonAlarmCfg> checkAlarmCfgIsExist(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg)throws Exception;
	/**
	 * @Title:queryPathByIpList
	 * @Description:通过IP查询路径list的方法
	 * @param hostIp
	 * @param dieKpiId
	 * @return List<MonitorCfgObj>
	 * @throws Exception
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:41:28 PM
	 */
	public List<MonitorCfgObj> queryPathByIpList(String hostIp, String dieKpiId) throws Exception;
	
}

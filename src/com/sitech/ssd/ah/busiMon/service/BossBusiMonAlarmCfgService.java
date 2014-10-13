package com.sitech.ssd.ah.busiMon.service;

import java.util.List;

import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>Title:BossBusiMonAlarmCfgService</p>
 * <p>Description:boss业务监控报警配置服务接口</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 11, 2014 7:44:12 PM
 */
@SuppressWarnings("all")
public interface BossBusiMonAlarmCfgService {
	
	/**
	 * @Title:queryForListByObj
	 * @Description:查询boss业务监控报警配置list的方法
	 * @param bossBusiMonAlarmCfg
	 * @return List
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:14 PM
	 */
	public List queryForListByObj(TbBossBusiMonAlarmCfg bossBusiMonAlarmCfg);
	/**
	 * @Title:addBossBusiMonAlarmCfg
	 * @Description:增加boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return int
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:16 PM
	 */
	public int addBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:updateBossBusiMonAlarmCfgById
	 * @Description:更新boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:19 PM
	 */
	public void updateBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:queryBossBusiMonAlarmCfgById
	 * @Description:通过ID查询boss业务监控配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return List<TbBossBusiMonAlarmCfg>
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:21 PM
	 */
	public List<TbBossBusiMonAlarmCfg> queryBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:deleteBossBusiMonAlarmCfg
	 * @Description:删除boss业务监控报警配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:23 PM
	 */
	public void deleteBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:openOrCloseBossBusiMon
	 * @Description:关闭或者开启boss业务监控配置的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 11, 2014 7:44:25 PM
	 */
	public void openOrCloseBossBusiMon(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg);
	/**
	 * @Title:checkAlarmCfgIsExist
	 * @Description:检查boss业务监控报警配置是否存在的方法
	 * @param tbBossBusiMonAlarmCfg
	 * @return List<TbBossBusiMonAlarmCfg>
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 13, 2014 11:12:14 AM
	 */
	public List<TbBossBusiMonAlarmCfg> checkAlarmCfgIsExist(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg)throws Exception;
	
	/**
	 * @Title:queryPathByIpList
	 * @Description:通过IP查询路径的方法
	 * @param hostIp
	 * @param dieKpiId
	 * @return List<MonitorCfgObj>
	 * @throws Exception
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:39:34 PM
	 */
	public List<MonitorCfgObj> queryPathByIpList(String hostIp, String dieKpiId) throws Exception;
}

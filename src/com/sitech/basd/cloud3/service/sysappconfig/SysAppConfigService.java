package com.sitech.basd.cloud3.service.sysappconfig;

import java.util.List;

import com.sitech.basd.cloud3.domain.sysappconfig.SysAppConfigObj;

/**
 * @author lipp
 * 基准应用环境检测指标配置业务层接口
 */
public interface SysAppConfigService {

	/**
	 * 查询所有符合条件的javabean集合
	 * @param sysAppConfigObj
	 * @return
	 */
	public List<SysAppConfigObj> query4List(SysAppConfigObj sysAppConfigObj);
	
	/**
	 * 查询一个符合条件的javabean，一般传入的实例带有id
	 * @param sysAppConfigObj
	 * @return
	 */
	public SysAppConfigObj queryOne(SysAppConfigObj sysAppConfigObj);
	
	/**
	 * 插入一条新的记录
	 * @param sysAppConfigObj
	 * @return
	 */
	public int insertSysAppConfigObj(SysAppConfigObj sysAppConfigObj);
	
	/**
	 * 删除一条记录
	 * @param sysAppConfigObj
	 * @return
	 */
	public int deleteSysAppConfigObj(SysAppConfigObj sysAppConfigObj);
	
	/**
	 * 更新一条记录
	 * @param sysAppConfigObj
	 * @return
	 */
	public int updateSysAppConfigObj(SysAppConfigObj sysAppConfigObj);
	

}

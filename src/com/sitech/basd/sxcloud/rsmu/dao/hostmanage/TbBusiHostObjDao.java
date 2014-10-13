package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusibusiSwitchPortObj;

public interface TbBusiHostObjDao {

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息（除去已经生成部署实例的）。
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryHostMinusExampleByObjAPPID(TbBusiHostObj obj);

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiHostObj obj);

	/**
	 * @Title:查询出具体主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbBusiHostObj queryByObj(TbBusiHostObj obj);

	/**
	 * @Title:更新主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbBusiHostObj obj);

	/**
	 * @Title:更新主机信息(修改vlan后的更新方式，需把EXCUTE_FLAG字段更改为0,以便后台扫描到进行操作)
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangzeca
	 * @version 1.0
	 */
	public int updateVlanByObj(TbBusiHostObj obj);

	/**
	 * @Title:删除主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostObj obj);

	/**
	 * @Title:插入主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostObj obj);

	/**
	 * @Title:查询交换机端口信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public List queryForList_Switch_port(TbBusibusiSwitchPortObj obj);

	/**
	 * @Title:根据主机IP查找该主机的KBP_CLASS和DEVICE_ID
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryKbpClassAndDeviceId(TbBusiHostObj obj);

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的CPU个数
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryCpu_Kpi(TbBusiHostObj obj);

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的内存大小
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryMemory_Kpi(TbBusiHostObj obj);

	/**
	 * @Title:审批应用部署时查询可用的主机IP
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public List queryAppIp(TbBusiDeployExampleObj obj);

	/**
	 * @Title:根据IP查询Id
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryIDByIP(TbBusiHostObj obj);

	/**
	 * @Title:通过excel导入批量插入数据
	 * @Copyright: Copyright (c) 201305
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByimport(List<TbBusiHostObj> hostList, List<TbBusiHostConfigObj> userList);

	/**
	 * 
	 * @Title: updateHostState
	 * @Description: 更新主机的状态（供应用部署使用）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2013 3:45:43 PM
	 */
	public int updateHostState(TbBusiHostObj obj);

	public int countBusiHost(TbBusiHostObj tbBusiHostObj);

	public List<TbBusiHostObj> queryAllMappingsByObj(TbBusiHostObj tbBusiHostObj);

	public int countAll(TbBusiHostObj tbBusiHostObj);

}

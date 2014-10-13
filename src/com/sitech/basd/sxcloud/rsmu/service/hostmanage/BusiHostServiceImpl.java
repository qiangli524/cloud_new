package com.sitech.basd.sxcloud.rsmu.service.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostObjDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusibusiSwitchPortObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class BusiHostServiceImpl extends BaseService implements BusiHostService {

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryForListByObj(obj);
	}

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息（除去已经生成部署实例的）。
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryHostMinusExampleByObjAPPID(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryHostMinusExampleByObjAPPID(obj);
	}

	/**
	 * @Title:查询出具体主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbBusiHostObj queryByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryByObj(obj);
	}

	/**
	 * @Title:更新主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.updateByObj(obj);
	}

	/**
	 * @Title:更新主机信息(修改vlan后的更新方式，需把EXCUTE_FLAG字段更改为0,以便后台扫描到进行操作)
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int updateVlanByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.updateVlanByObj(obj);
	}

	/**
	 * @Title:删除主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostObj obj) {
		return tbBusiHostObjDao.insertByObj(obj);
	}

	/**
	 * @Title:根据主机IP查找该主机的KBP_CLASS和DEVICE_ID
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryKbpClassAndDeviceId(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryKbpClassAndDeviceId(obj);
	}

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的CPU个数
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryCpu_Kpi(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryCpu_Kpi(obj);
	}

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的内存大小
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryMemory_Kpi(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryMemory_Kpi(obj);
	}

	public List queryForList_Switch_port(TbBusibusiSwitchPortObj obj) {
		return tbBusiHostObjDao.queryForList_Switch_port(obj);
	}

	/**
	 * @Title:审批应用部署时查询可用的主机IP
	 * @Copyright: Copyright (c) 20120316
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppIp(TbBusiDeployExampleObj obj) {
		return tbBusiHostObjDao.queryAppIp(obj);
	}

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryIDByIP(TbBusiHostObj obj) {
		return tbBusiHostObjDao.queryIDByIP(obj);
	}

	/**
	 * @Title:通过excel导入批量插入数据
	 * @Copyright: Copyright (c) 201305
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByimport(List<TbBusiHostObj> hostList, List<TbBusiHostConfigObj> userList) {
		return tbBusiHostObjDao.insertByimport(hostList, userList);
	}

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
	public int updateHostState(TbBusiHostObj obj) {
		return tbBusiHostObjDao.updateHostState(obj);
	}

	TbBusiHostObjDao tbBusiHostObjDao;

	public void setTbBusiHostObjDao(TbBusiHostObjDao tbBusiHostObjDao) {
		this.tbBusiHostObjDao = tbBusiHostObjDao;
	}

	@Override
	public int countBusiHost(TbBusiHostObj tbBusiHostObj) {
		return tbBusiHostObjDao.countBusiHost(tbBusiHostObj);
	}

	@Override
	public List<TbBusiHostObj> queryAllMappingsByObj(TbBusiHostObj tbBusiHostObj) {
		// TODO Auto-generated method stub
		return tbBusiHostObjDao.queryAllMappingsByObj(tbBusiHostObj);
	}

	@Override
	public int countAll(TbBusiHostObj tbBusiHostObj) {
		// TODO Auto-generated method stub
		return tbBusiHostObjDao.countAll(tbBusiHostObj);
	}
}

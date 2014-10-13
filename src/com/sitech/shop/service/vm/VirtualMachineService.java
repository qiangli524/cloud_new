package com.sitech.shop.service.vm;

import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.webservice.domain.TbSysUserinfo;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.exception.HttpClientException;

public interface VirtualMachineService {
	/**
	 * 
	 * @Title: getVMList
	 * @Description: 查询某一用户对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-8 下午8:42:00
	 */
	public List getVMList(VMHostObj obj);

	/**
	 * 
	 * @Title: renameVM
	 * @Description: TODO(重命名)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-30
	 */
	public int renameVM(VMHostObj obj);

	/**
	 * 
	 * @Title: console
	 * @Description:获取控制台票据
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-23 上午11:15:26
	 */
	public String console(VMHostObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: updateVmIpObj
	 * @Description: TODO(修改虚拟机对应的IP地址)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-19
	 */
	public String updateVmIpObj(VmIpObj obj);

	/**
	 * 
	 * @Title: getVMObj
	 * @Description: TODO(查询某一用户一个虚拟机)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return VMHostObj 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public VMHostObj getVMObj(VMHostObj obj);

	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(VMHostObj obj);

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-11 下午3:21:16
	 */
	public String putVMPowerState(UnitedTreeObj obj) throws Exception;

    /**
     *根据登陆账号查找用户信息
     * @param loginName 登陆账号
     * @return 用户信息
     * @throws Exception
     */
    //public TbSysUserinfo findShopUserInfo(String  loginName) throws Exception;
}

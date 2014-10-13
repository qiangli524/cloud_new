package com.sitech.basd.yicloud.dao.vmman;

import java.util.List;

import com.sitech.basd.yicloud.domain.vmman.VMHostObj;

public interface VMHostDao {

	/**
	 * @Title:添加虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public abstract int insertByVMhostObj(VMHostObj obj);

	/**
	 * 
	 * @Title: queryVhostIdSequence
	 * @Description: 查询虚拟机序列号
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:28:54 PM
	 */
	public abstract int queryVhostIdSequence();

	/**
	 * 查询虚拟机信息表中所有的信息，根据VH_Status状态来查询，查询状态为0的虚拟机信息。
	 * 
	 * VH_STATUS是为了标识虚拟机信息是否推送给CMDB接口的，如果推送，状态值变为1.
	
	 * queryVMHostInfo(这里用一句话描述这个方法的作用)    
	
	 * @param   name   
	
	 * @param  @return    设定文件   
	
	 * @return String    DOM对象   
	
	 * @Exception 异常对象   
	
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public abstract List<VMHostObj> queryVMHostInfo(VMHostObj obj);

	/**
	 * 更新虚拟机信息表中VH_SATUS字段，0表示虚拟机信息未推送给CMDB接口，1表示已经推送过。
	
	 * updateVMHostStatusInfo(这里用一句话描述这个方法的作用)    
	
	 * @param   name   
	
	 * @param  @return    设定文件   
	
	 * @return String    DOM对象   
	
	 * @Exception 异常对象   
	
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public abstract int updateVMHostStatusInfo(VMHostObj obj);

	/**
	 * 
	 * @Title: queryHostListByObj
	 * @Description: 查询虚拟机信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 3, 2012 2:17:17 PM
	 */
	public abstract List queryHostListByObj();

}
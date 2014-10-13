package com.sitech.basd.yicloud.service.vmware;

public interface OperateVMHostInfo {

	/**
	 * 
	
	 * postAddVMHostInfoForCMDBInte(这里用一句话描述这个方法的作用)    
	
	 * @param   name   
	
	 * @param  @return    设定文件   
	
	 * @return String    DOM对象   
	
	 * @Exception 异常对象   
	
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public abstract String postAddVMHostInfoForCMDBInte(String vhID,
			String vhNAME, String vhSTAT, String vhDESC, String vhCPU,
			String vhMEM, String vhSTORAGE, String vhIP, String vhTYPE);

}
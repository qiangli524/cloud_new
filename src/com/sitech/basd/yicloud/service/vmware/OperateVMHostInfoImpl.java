package com.sitech.basd.yicloud.service.vmware;

import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.ConfigUtil;
import com.sitech.basd.yicloud.dao.vmman.VMHostDao;
import com.sitech.basd.yicloud.domain.vmman.VMHostInterface;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;
import com.sitech.basd.yicloud.util.HttpRequestUtil;

public class OperateVMHostInfoImpl implements OperateVMHostInfo {

	private VMHostDao vmHostDao;

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.service.vmware.OperateVmHostInfo#getVmHostDao()   
	*/
	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.service.vmware.OperateVmHostInfo#getVmHostDao()   
	*/
	public VMHostDao getVmHostDao() {
		return vmHostDao;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.service.vmware.OperateVmHostInfo#setVmHostDao(com.sitech.basd.yicloud.dao.vmman.VMHostDao)   
	*/
	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.service.vmware.OperateVmHostInfo#setVmHostDao(com.sitech.basd.yicloud.dao.vmman.VMHostDao)   
	*/
	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.service.vmware.OperateVMHostInfo#postAddVMHostInfoForCMDBInte(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)   
	*/
	public String postAddVMHostInfoForCMDBInte(String vhID,String vhNAME,String vhSTAT,String vhDESC,String vhCPU,String vhMEM,String vhSTORAGE,String vhIP,String vhTYPE){
		VMHostInterface objIntes=new VMHostInterface(vhID, vhNAME, vhSTAT, vhDESC, vhCPU, vhMEM, vhSTORAGE, vhIP, vhTYPE,"");
		JSONObject json = JSONObject.fromObject(objIntes);
		String url = ConfigUtil.getPaths("cmdbaddvm");
		String str=HttpRequestUtil.requestPostREJson(url,json.toString());
		if(str!=null&&!str.equals("")){
			/**
			 * 判断返回值
			 */
			JSONObject jsonTemp=JSONObject.fromObject(str);
			String flag=jsonTemp.get("FLAG").toString();
			String vhCid=jsonTemp.get("VH_CID").toString();
			if("S".equals(flag)){
				return vhCid;
			}else{
				LogHelper.error(url+"   "+flag);
			}
		}
		return "";
	}

	/**
	 * 
	  
	* changeObjByVMHost(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private static VMHostInterface defiendObjByVMHost(VMHostObj obj) {
		VMHostInterface objInte = new VMHostInterface();
		objInte.setVh_ID(obj.getVH_ID());
		objInte.setVh_CPU(obj.getVH_CPU());
		objInte.setVh_DESC(obj.getVH_DESC());
		objInte.setVh_IP(obj.getVH_COMPLETED());
		objInte.setVh_MEM(obj.getVH_MEM());
		objInte.setVh_NAME(obj.getVH_NAME());
		objInte.setVh_STAT(obj.getVH_STAT());
		objInte.setVh_STORAGE(obj.getVH_STORAGE());
		objInte.setVh_TYPE(obj.getVH_TYPE());
		return objInte;
	}
}

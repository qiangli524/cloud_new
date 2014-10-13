package com.sitech.basd.yicloud.dao.vmmantest;

import java.util.List;

import org.junit.Test;

import com.sitech.basd.yicloud.dao.vmman.VMHostDaoImpl;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;

public class VMHostDaoTest extends VMHostDaoImpl {
	
	@Test
	public void queryVMHostInfoTestFor1(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_STATUS("1");
		List<VMHostObj> objs=queryVMHostInfo(obj);
	}
	
	@Test
	public void queryVMHostInfoTestFor0(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_STATUS("0");
		List<VMHostObj> objs=queryVMHostInfo(obj);
	}
	
	@Test
	public void queryVMHostInfoTest3(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_STATUS("");
		List<VMHostObj> objs=queryVMHostInfo(obj);
	}
	
	@Test
	public void queryVMHostInfoTest4(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_STATUS(null);
		List<VMHostObj> objs=queryVMHostInfo(obj);
	}
	@Test
	/**
	 * oracle
	 */
	public void updateVMHostStatusInfo(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_ID("1_01_001_0029_01_054");
		int ret=updateVMHostStatusInfo(obj);
	}
	
	@Test
	/**
	 * mysql
	 */
	public void updateVMHostStatusInfo2(){
		VMHostObj obj=new VMHostObj();
		obj.setVH_ID("1");
		int ret=updateVMHostStatusInfo(obj);
	}

}

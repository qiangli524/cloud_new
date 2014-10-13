package com.sitech.basd.resource.web.united;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.resource.service.united.VMwareDataCompareService;
import com.sitech.basd.resource.service.united.XenDataCompareService;
import com.sitech.vo.util.UnitedConstant;

@Component("dataCompareAction")
public class DataCompareAction {
	private String result = "success";
	@Autowired
	private VMwareDataCompareService vmwareDataCompareService;
	@Autowired
	private XenDataCompareService xenDataCompareService;

	/**
	 * 
	 * @Title: compareVmwareData
	 * @Description: 比对vmware数据
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 25, 2013 8:21:26 PM
	 */
	public String compareVmwareData() {
		// try {
		result = UnitedConstant.FAIL;
		try {
			vmwareDataCompareService.vmwareDataCompare();// vmware数据同步
			 xenDataCompareService.xenDataCompare();// xen数据同步
			// vmwareDataCompareService.validateHostData();//
			// 已分配资源和未分配资源的比对（暂时适用于安徽需求）
			result = UnitedConstant.SUCCESS;
//		} catch (DataSynchroException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "dataResult";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}

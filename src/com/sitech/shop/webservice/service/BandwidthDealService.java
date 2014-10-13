package com.sitech.shop.webservice.service;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;

public interface BandwidthDealService {
	/**
	 * 
	 * @Title: applyBandwidthProcess
	 * @Description: 购买带宽处理过程
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-8-18 下午3:26:42
	 */
	public VmInfo applyBandwidthProcess(VmInfo info) throws Exception;

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: expandBandwidthProcess
	 * @Description: 公网带宽变更
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */

	public VmInfo expandBandwidthProcess(VmInfo info) throws Exception;
}

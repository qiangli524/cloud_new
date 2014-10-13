package com.sitech.ssd.gx.service.huaweivm;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.gx.constant.HuaweiRestURI;
import com.sitech.ssd.gx.dao.huaweivm.HuaweiVMHostDao;
import com.sitech.ssd.gx.domain.huaweivm.HuaweiVmObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.VMDetailVO;

public class HuaweiVMHostServiceImpl extends BaseService implements HuaweiVMHostService {
	private HuaweiVMHostDao huaweiVMHostDao;
	
	public void setHuaweiVMHostDao(HuaweiVMHostDao huaweiVMHostDao) {
		this.huaweiVMHostDao = huaweiVMHostDao;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询虚拟机信息
	 * @param
	 * @return HuaweiVmObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:26:33
	 */
	public HuaweiVmObj queryByObj(HuaweiVmObj obj) {
		return huaweiVMHostDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return List<HuaweiVmObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:26:49
	 */
	public List<HuaweiVmObj> queryForListByObj(HuaweiVmObj obj) {
		return huaweiVMHostDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加虚拟机信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:24:37
	 */
	public int insertByObj(HuaweiVmObj obj) {
		return huaweiVMHostDao.insertByObj(obj);
	}

	@Override
	public VMDetailVO quertyVmInfoFromRest(String siteCode, String vmCode) throws HttpClientException {
		String vmURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR + siteCode + HuaweiRestURI.VM_URI + HuaweiRestURI.SEPARATOR + vmCode;
		VMDetailVO vmDetailVO = HttpClientCustomUtil.get(vmURI, new JacksonUtil.TypeReference<VMDetailVO>() {
		});
		return vmDetailVO;
	}
	
	@Override
	public Map<String, String> queryVmInGroupState(String siteCode,
			String scopeUrn) throws HttpClientException {
		String vmURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR + siteCode + HuaweiRestURI.VM_URI + HuaweiRestURI.STATES + "/scope/" + scopeUrn ;
		Map<String,String> states = HttpClientCustomUtil.get(vmURI, new JacksonUtil.TypeReference<Map<String,String>>() {
		});
		return states;
	}
}

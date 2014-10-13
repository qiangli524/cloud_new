package com.sitech.ssd.gx.service.huaweivm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.gx.domain.huaweivm.HuaweiVmObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.huawei.VMDetailVO;

public interface HuaweiVMHostService {

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
	public HuaweiVmObj queryByObj(HuaweiVmObj obj);

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
	public List<HuaweiVmObj> queryForListByObj(HuaweiVmObj obj);

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
	public int insertByObj(HuaweiVmObj obj);
	
	/**
	 * 
	 * @Title: quertyVmInfoFromRest
	 * @Description: 从接口查询虚拟机详细信息
	 * @param
	 * @return VMDetailVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException 
	 * @createtime 2013-12-7 下午2:19:17
	 */
	public VMDetailVO quertyVmInfoFromRest(String siteCode,String vmCode) throws HttpClientException;
	
	/**
	 * 
	 * @Title: queryVmInGroupState
	 * @Description: 查询一组虚拟机的状态
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException 
	 * @createtime 2013-12-7 下午7:26:32
	 */
	public Map<String,String> queryVmInGroupState(String siteCode,String scopeUrn) throws HttpClientException;
}

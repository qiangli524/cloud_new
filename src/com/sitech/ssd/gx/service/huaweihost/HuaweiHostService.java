package com.sitech.ssd.gx.service.huaweihost;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.ssd.gx.domain.huaweihost.HuaweiHostObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.huawei.HostVO;

public interface HuaweiHostService {

	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加主机
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 上午11:30:32
	 */
	public int insertByObj(HuaweiHostObj obj);

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 获得主机信息
	 * @param
	 * @return List<HuaweiHostObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午3:02:04
	 */
	public List<HuaweiHostObj> queryForListByObj(HuaweiHostObj obj);
	
	/**
	 * 
	 * @Title: queryHostInfoFromRest
	 * @Description: 从接口查询主机的详细信息
	 * @param
	 * @return HostVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException 
	 * @createtime 2013-12-7 下午12:39:47
	 */
	public HostVO queryHostInfoFromRest(String siteCode,String hostCode) throws HttpClientException;
	
	/**
	 * 
	 * @Title: queryCluSonNodeState
	 * @Description: 查询一组主机的状态
	 * @param
	 * @return Map<String,Boolean>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException 
	 * @createtime 2013-12-7 下午3:53:16
	 */
	public Map<String,Boolean> queryHostInGroupState(String siteCode,String scopeUrn) throws HttpClientException;
}

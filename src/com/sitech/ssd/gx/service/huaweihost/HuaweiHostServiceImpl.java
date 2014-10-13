package com.sitech.ssd.gx.service.huaweihost;

import java.util.List;
import java.util.Map;

import com.sitech.ssd.gx.constant.HuaweiRestURI;
import com.sitech.ssd.gx.dao.huaweihost.HuaweiHostDao;
import com.sitech.ssd.gx.domain.huaweihost.HuaweiHostObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.huawei.Cluster;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.Hosts;

public class HuaweiHostServiceImpl implements HuaweiHostService {

	private HuaweiHostDao huaweiHostDao;

	public void setHuaweiHostDao(HuaweiHostDao huaweiHostDao) {
		this.huaweiHostDao = huaweiHostDao;
	}



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
	public int insertByObj(HuaweiHostObj obj) {
		return huaweiHostDao.insertByObj(obj);
	}


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
	public List<HuaweiHostObj> queryForListByObj(HuaweiHostObj obj) {
		return huaweiHostDao.queryForListByObj(obj);
	}

	@Override
	public HostVO queryHostInfoFromRest(String siteCode, String hostCode) throws HttpClientException {
		String hostURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR  + siteCode + HuaweiRestURI.HOST_URI + HuaweiRestURI.SEPARATOR + hostCode;
		HostVO hostVo = HttpClientCustomUtil.get(hostURI, new JacksonUtil.TypeReference<HostVO>() {
		});
		return hostVo;
	}


	@Override
	public Map<String, Boolean> queryHostInGroupState(String siteCode,
			String scopeUrn) throws HttpClientException {
		String hostURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR  + siteCode + HuaweiRestURI.HOST_URI + HuaweiRestURI.STATES + "/scope/" + scopeUrn ;
		Map<String,Boolean> states = HttpClientCustomUtil.get(hostURI, new JacksonUtil.TypeReference<Map<String,Boolean>>() {
		});
		return states;
	}
}

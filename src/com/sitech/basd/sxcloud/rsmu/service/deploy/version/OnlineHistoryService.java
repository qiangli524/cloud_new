package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;

public interface OnlineHistoryService {

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(上线历史入库)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-16 下午8:25:16
	 */
	public String insertByObj(TbCloud3OnlineHistoryVO obj);

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: TODO(查询上线历史list)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 下午2:46:51
	 */
	public List queryForListByObj(TbCloud3OnlineHistoryVO obj);

}

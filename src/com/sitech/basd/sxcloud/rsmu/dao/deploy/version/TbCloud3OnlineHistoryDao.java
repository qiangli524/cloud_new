package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;

/**
 * 
 * <p>
 * Title: TbCloud3OnlineHistoryDao
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-4-16 下午8:22:27
 * 
 */
public interface TbCloud3OnlineHistoryDao {

	/**
	 * 
	 * @Title: insertByVO
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:45
	 */
	public String insertByVO(TbCloud3OnlineHistoryVO obj);

	/**
	 * 
	 * @Title: queryVOByObj
	 * @Description: 查询数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public List<TbCloud3OnlineHistoryVO> queryListByObj(
			TbCloud3OnlineHistoryVO obj);

}

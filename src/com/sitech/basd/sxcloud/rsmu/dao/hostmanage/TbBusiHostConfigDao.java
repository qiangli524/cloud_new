package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public interface TbBusiHostConfigDao {

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiHostConfigObj obj);

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiHostConfigObj queryByObj(TbBusiHostConfigObj obj);

	/**
	 * @Title:更新主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbBusiHostConfigObj obj);

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostConfigObj obj);

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostConfigObj obj);

	/**
	 * 
	 * @Title: queryForListByHostId
	 * @Description: TODO(通过主机编号查找用户信息)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-12 下午7:31:05
	 */
	public List queryForListByHostId(TbBusiHostConfigObj obj);

	/**
	 * 
	 * @Title: updateHostConfigByUsername
	 * @Description: 更新用户关联基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-13 下午1:06:44
	 */
	public int updateHostConfigByUsername(TbBusiHostConfigObj obj);
	
	/**
	 * 
	 * @Title: updateHostConfigByAppId
	 * @Description: 根据应用的id修改主机的配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2013 10:41:46 AM
	 */
	public int updateHostConfigByAppId(TbBusiHostConfigObj obj);
	
	/**
	 * 
	 * @Title: queryForHostConfigAndDepListByObj
	 * @Description: 查询主机下实例的信息
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 9, 2013 10:31:25 AM
	 */
	public List queryForHostConfigAndDepListByObj(TbBusiHostConfigObj obj);
}

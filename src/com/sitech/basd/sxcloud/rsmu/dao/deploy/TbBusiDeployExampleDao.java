package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;

public interface TbBusiDeployExampleDao {

	/**
	 * @Title:根据部署实例管理信息查询匹配的所有部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiDeployExampleObj obj);

	/**
	 * 查找所有和task有关联的部署实例
	 * 
	 * @param obj
	 * @return
	 */
	public List<TbBusiDeployExampleObj> queryByObjJoinTaskList(TbBusiDeployExampleObj obj);

	/**
	 * @Title:查询出具体部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiDeployExampleObj queryByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:更新部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:删除部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:插入部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:查询出当前页面的部署实例管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryListIDByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:启动或停止服务状态
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int StartAndStopByObj(TbBusiDeployExampleObj obj);

	/**
	 * @Title:更改部署状态
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deployRequest(TbBusiDeployExampleObj obj);

	/**
	 * @Title:更改部署或卸载的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateDEPLOY_PERCENT(TbBusiDeployExampleObj obj);

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateSTART_STOP_PERCENT(TbBusiDeployExampleObj obj);

	/**
	 * 
	 * @Title: queryForBizListByObj
	 * @Description: 业务视图展示部署实例
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 7, 2013 3:01:24 PM
	 */
	public List queryForBizListByObj(TbBusiDeployExampleObj obj);

	/**
	 * 
	 * @Title: updateExampleIsRestart
	 * @Description: 更新部署实例-是否重新启动
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:18:44
	 */
	public void updateExampleIsRestart(TbBusiDeployExampleObj obj);

	/**
	 * 
	 * @Title: updateExampleIsBackup
	 * @Description: 更新部署实例--是否全量备份
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:20:45
	 */
	public void updateExampleIsBackup(TbBusiDeployExampleObj obj);

	/**
	 * 
	 * @Title: queryOnlineExampleList
	 * @Description: 查询部署实例列表
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:21:08
	 */
	public List<TbBusiDeployExampleObj> queryOnlineExampleList(TbBusiDeployExampleObj obj);

	/**
	 * 根据业务系统查询部署实例的个数
	 * 
	 * @param bizsysidstr
	 * @return
	 */
	public int countExample(TbBusiDeployExampleObj tbBusiDeployExampleObj);
	
	/**
	 * 
	 * @Title: queryAppSonNum
	 * @Description: 查询基准应用下部署实例的个数
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-27 下午7:32:54
	 */
	public int queryAppSonNum(TbBusiDeployExampleObj tbBusiDeployExampleObj);
}

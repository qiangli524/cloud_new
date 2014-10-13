package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.config.DeployOperation;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployExampleDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.util.JSONUtil;

public class DeployExampleServiceImpl extends BaseService implements DeployExampleService {
	// 消息队列传输消息，部署实例ID
	private static final String EXAMPLE_KEY = "appExampleId";
	// 消息队列传输消息，部署实例操作
	private static final String EXAMPLE_OPERATION = "appExampleOperation";
	private TbBusiDeployExampleDao tbBusiDeployExampleDao;

	public int deleteByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.deleteByObj(obj);
	}

	public int insertByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.insertByObj(obj);
	}

	public TbBusiDeployExampleObj queryByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.queryByObj(obj);
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.queryForListByObj(obj);
	}

	public int updateByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.updateByObj(obj);
	}

	@SuppressWarnings("unchecked")
	public List queryListIDByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleDao.queryListIDByObj(obj);
	}

	public int StartAndStopByObj(TbBusiDeployExampleObj obj) {
		int result = tbBusiDeployExampleDao.StartAndStopByObj(obj);
		// 若更新状态成功，则进行消息队列发送
		if (result != -1) {
			String start_stop_flag = obj.getSTART_STOP_FLAG();
			Map<String, Object> msgMap = new HashMap<String, Object>();
			// 启动应用操作
			if ("2".equals(start_stop_flag)) {
				msgMap.put(EXAMPLE_KEY, obj.getID());
				msgMap.put(EXAMPLE_OPERATION, DeployOperation.START);
				String message = JSONUtil.toJSON(msgMap);
				// 向消息队列发送启动应用消息
			} else if ("0".equals(start_stop_flag)) {// 停止应用操作
				msgMap.put(EXAMPLE_KEY, obj.getID());
				msgMap.put(EXAMPLE_OPERATION, DeployOperation.STOP);
				String message = JSONUtil.toJSON(msgMap);
				// 向消息队列发送停止应用消息
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: deployRequest
	 * @Description: 点击部署按钮触发更新部署状态
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-29 下午2:36:33
	 */
	public int deployRequest(TbBusiDeployExampleObj obj) {
		obj.setDEPLOY_PERCENT("等待部署");
		int result = tbBusiDeployExampleDao.deployRequest(obj);
		if (result != -1) {
			// 发布部署消息到消息队列
		}
		return result;
	}

	/**
	 * @Title:更改部署或卸载的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateDEPLOY_PERCENT(TbBusiDeployExampleObj obj) {
		tbBusiDeployExampleDao.updateDEPLOY_PERCENT(obj);
	}

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateSTART_STOP_PERCENT(TbBusiDeployExampleObj obj) {
		tbBusiDeployExampleDao.updateSTART_STOP_PERCENT(obj);
	}

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
	public List queryForBizListByObj(TbBusiDeployExampleObj obj) {
		return tbBusiDeployExampleDao.queryForBizListByObj(obj);
	}

	public void setTbBusiDeployExampleDao(TbBusiDeployExampleDao tbBusiDeployExampleDao) {
		this.tbBusiDeployExampleDao = tbBusiDeployExampleDao;
	}

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
	public void updateExampleIsRestart(TbBusiDeployExampleObj obj) {
		tbBusiDeployExampleDao.updateExampleIsRestart(obj);
	}

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
	public void updateExampleIsBackup(TbBusiDeployExampleObj obj) {
		tbBusiDeployExampleDao.updateExampleIsBackup(obj);
	}

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
	public List<TbBusiDeployExampleObj> queryOnlineExampleList(TbBusiDeployExampleObj obj) {
		return tbBusiDeployExampleDao.queryOnlineExampleList(obj);
	}
	
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
	public int queryAppSonNum(TbBusiDeployExampleObj tbBusiDeployExampleObj){
		return tbBusiDeployExampleDao.queryAppSonNum(tbBusiDeployExampleObj);
	}

	@Override
	public List<TbBusiDeployExampleObj> queryByObjJoinTaskList(TbBusiDeployExampleObj obj) {
		return tbBusiDeployExampleDao.queryByObjJoinTaskList(obj);
	}

	@Override
	public int countExample(TbBusiDeployExampleObj tbBusiDeployExampleObj) {
		return tbBusiDeployExampleDao.countExample(tbBusiDeployExampleObj);
	}
	

}

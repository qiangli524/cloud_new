package com.sitech.ssd.ah.boss.action.procedure;

import java.util.List;

import jxl.common.Logger;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;
import com.sitech.ssd.ah.boss.service.monitor.BossProcessMonitorService;
import com.sitech.ssd.ah.boss.service.procedure.BossProcedureService;
import com.sitech.utils.randomid.RandomUUID;

/**
 * <p>
 * Title: BossProcedureAction
 * </p>
 * <p>
 * Description: 用于boss应用进程注册
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-1 下午4:12:03
 * 
 */
@SuppressWarnings("unchecked")
@Controller("bossProcedureAction")
@Scope("prototype")
public class BossProcedureAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BossProcedureAction.class);
	@Autowired
	private BossProcedureService bossProcedureService;
	@Autowired
	private UserService userService;
	@Autowired
	BossProcessMonitorService bossProcessMonitorService;
	private BusiHostService busiHostService;
	private ProcedureObj obj;
	private List<ProcedureObj> bossProcedureObjList;
	private String oper;
	private String result;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BusiHostService getBusiHostService() {
		return busiHostService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public ProcedureObj getObj() {
		return obj;
	}

	public void setObj(ProcedureObj obj) {
		this.obj = obj;
	}

	public List<ProcedureObj> getBossProcedureObjList() {
		return bossProcedureObjList;
	}

	public void setBossProcedureObjList(List<ProcedureObj> bossProcedureObjList) {
		this.bossProcedureObjList = bossProcedureObjList;
	}

	/**
	 * @Title: queryProcedureList
	 * @Description: 查询程序列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 上午10:15:26
	 */
	public String queryProcedureList() {
		if (obj == null) {
			obj = new ProcedureObj();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<TbBusiHostObj> hostList = busiHostService.queryForListByObj(new TbBusiHostObj());
		obj.setHostList(hostList);
		CommonObj comObj = new CommonObj();
		comObj.setType("2");
		List<CommonObj> clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
		comObj.setType("3");
		List<CommonObj> poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
		obj.setClusterList(clusterList);
		obj.setPoolList(poolList);
		bossProcedureObjList = bossProcedureService.queryBossProcedure(obj);
		return "list";
	}

	/**
	 * @Title: addProcedure
	 * @Description: 跳转到添加页面 或修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 下午12:00:47
	 */
	public String addProcedure() {
		// 查询出供安装程序的主机集合
		TbBusiHostObj hostObj = new TbBusiHostObj();
		List<TbBusiHostObj> hostList = busiHostService.queryForListByObj(hostObj);
		CommonObj comObj = new CommonObj();
		comObj.setType("2");
		List<CommonObj> clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
		comObj.setType("3");
		List<CommonObj> poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
		logger.info("开始");
		if ("add".equals(oper)) {
			/** 添加 */
			obj = new ProcedureObj();
			obj.setHostList(hostList);
			obj.setClusterList(clusterList);
			obj.setPoolList(poolList);
		} else {
			/** 修改 */
			logger.info("查询该进程的信息");
			obj = bossProcedureService.queryBossProcedureByUid(obj);
			logger.info("查询结束");
			obj.setHostList(hostList);// 获取主机列表
			obj.setClusterList(clusterList);
			obj.setPoolList(poolList);
			UserObj userObj = new UserObj();
			if (!"".equals(obj.getUser_uid()) && obj.getUser_uid() != null) {
				userObj.setId(obj.getUser_uid());
				userObj = userService.queryUserObjById(userObj);
				userName = userObj.getUsername();
			}
		}
		return "add";
	}

	/**
	 * @Title: unloadProcedure
	 * @Description: 卸载程序
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午11:01:36
	 */
	public String unloadProcedure() {
		result = bossProcedureService.unloadProcedureObj(obj);
		return "results";
	}

	/**
	 * @Title: saveProcedure
	 * @Description: 保存程序
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 下午3:48:51
	 */
	public String saveProcedure() {
		logger.info("进程UID是:" + obj.getUid());
		TbBusiHostObj hostObj = new TbBusiHostObj();
		logger.info("主机ID是:" + obj.getHost_id());
		hostObj.setID(obj.getHost_id());
		TbBusiHostObj hobj = busiHostService.queryByObj(hostObj);
		obj.setHost_ip(hobj.getIP());
		logger.info("主机IP是：" + obj.getHost_ip());
		logger.info("oper是：" + oper);
		if ("add".equals(oper)) {
			logger.info("执行添加");
			String uid = RandomUUID.getUuid();
			obj.setUid(uid);
			result = bossProcedureService.saveProcedure(obj);
		} else {
			logger.info("执行修改");
			result = bossProcedureService.updateProcedureObj(obj);
		}
		return "results";
	}

	/**
	 * @Title: checkIsExist
	 * @Description: 检查集群和进程名称是否联合唯一
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 下午3:49:39
	 */
	public String checkIsExist() {
		if (obj == null) {
			result = "验证出错！";
			return "results";
		} else {
			result = bossProcedureService.checkIsExist(obj);
			return "results";
		}
	}

	/**
	 * @Title: getUserListByHostId
	 * @Description: 得到进程集合，返回页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-3 下午2:47:37
	 */
	public String getProcessListByCluAndPool() {
		if (obj == null) {
			obj = new ProcedureObj();
		}
		String cluster_id = request.getParameter("cluster_id");
		String app_pool = request.getParameter("pool_name");
		obj.setCluster_id(cluster_id);
		obj.setApp_pool(app_pool);
		List<ProcedureObj> lst = bossProcedureService.queryProcedureListByCluAndPool(obj);
		JSONArray ja = JSONArray.fromObject(lst);
		try {
			JSONUtil.printJSON(ja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

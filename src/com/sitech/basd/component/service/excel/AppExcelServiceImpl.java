package com.sitech.basd.component.service.excel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.excel.AppExcelDao;
import com.sitech.basd.component.domain.excel.BaseAppVO;
import com.sitech.basd.component.domain.excel.DeployAppVO;
import com.sitech.basd.component.domain.excel.StandardAppVO;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.service.script.ScriptsService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.util.session.UserSession;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.randomid.RandomUUID;

/**
 * '
 * <p>
 * Title: AppExcelService
 * </p>
 * <p>
 * Description: 智能部署Excel逻辑处理层实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-3 下午2:53:37
 * 
 */
@Service("appExcelService")
public class AppExcelServiceImpl implements AppExcelService {
	@Autowired
	private AppExcelDao appExcelDao;
	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private ExampleRelationService exampleRelationService;
	@Autowired
	private UserService userService;
	@Autowired
	private ScriptsService scriptsService;

	/**
	 * 
	 * @Title: initAppExcelData
	 * @Description: 实例应用Excel数据
	 * @param appId
	 *            - 基准应用ID
	 * @return List<BaseAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:33:18
	 */
	public List<BaseAppVO> initAppExcelData(Integer appId) {
		List<BaseAppVO> list = new ArrayList<BaseAppVO>();
		StandardAppVO standardAppVO = new StandardAppVO();
		standardAppVO.setAppId(appId);
		StandardAppVO standardAppResult = appExcelDao.queryStandardAppVO(standardAppVO);
		list.add(standardAppResult);
		DeployAppVO deployAppVO = new DeployAppVO();
		deployAppVO.setAppId(appId);
		List<DeployAppVO> deployAppVOList = appExcelDao.queryDeployAppVOList(deployAppVO);
		list.addAll(deployAppVOList);
		return list;
	}

	/**
	 * 
	 * @Title: initAppProcessExcelData
	 * @Description: 实例应用进程Excel数据
	 * @param appId
	 *            基准应用ID
	 * @return List<ProcessObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-4 下午4:26:19
	 */
	public List<ProcessObj> initAppProcessExcelData(Integer appId) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		ProcessObj obj = new ProcessObj();
		TbBusiSysTree tree = new TbBusiSysTree();
		tree.setBusiId(appId + "");
		List<TbBusiSysTree> treeList = tbBusiSysTreeService.queryForTree(tree);
		if (treeList != null && treeList.size() > 0) {
			tree = treeList.get(0);
		}
		obj.setEXAMPLE_ID(tree.getId());
		list = processService.queryAppProcessList(obj);
		/*
		 * 处理进程关联脚本信息
		 */
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProcessObj processObj = list.get(i);
				String processId = processObj.getID();
				// 启动脚本ID,停止脚本ID
				String scriptId = appExcelDao.queryScriptIdByProcess(processId);
				processObj.setID(processId + "," + scriptId);
				list.set(i, processObj);
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryExampleNameList
	 * @Description: 查询部署实例名称列表
	 * @param
	 * @return List<DeployAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:48:31
	 */
	public List<String> queryExampleNameList(Integer appId) {
		return appExcelDao.queryExampleNameList(appId + "");
	}

	/**
	 * 
	 * @Title: saveAppProcessData
	 * @Description: 保存应用进程信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-6 下午7:07:38
	 */
	public void saveAppProcessData(List<ProcessObj> processList) {
		if (processList != null && processList.size() > 0) {
			for (ProcessObj processObj : processList) {
				if (processObj.getID() == null && processObj.getPROCESS() != null) {// 新增操作
					saveProcess(processObj);
					// 启动脚本
					saveScriptData(processObj, 1);
					// 停止脚本
					saveScriptData(processObj, 2);
				} else if (processObj.getID() != null) {// 更新操作
					String[] ids = processObj.getID().split(",");
					if (ids.length == 3) {
						processObj.setID(ids[0]);
						updateProcess(processObj);
						// 启动脚本
						updateScriptData(processObj, ids[1], 1);
						// 停止脚本
						updateScriptData(processObj, ids[2], 2);
					} else {// 数据错误
						System.out.println("Excel进程更新数据错误~，id字段长度不符！");
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: saveScriptData
	 * @Description: 保存脚本数据
	 * @param scriptType
	 *            脚本类型 1 ： 启动，2 : 停止
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-8 上午10:58:51
	 */
	private void saveScriptData(ProcessObj processObj, int scriptType) {
		// 获取当前系统登录Session
		HttpSession session = UserSession.getHttpSession();
		String account = session.getAttribute(Constant.ACCOUNT) + "";
		// 获取部署实例id tb_busi_deploy_example
		int exampleIdNumber = Integer.parseInt(dealExampleIdByExampleName(processObj
				.getExample_name()));
		// 查询实例关联user_manager Id
		DeployAppVO result = queryDeployExampleData(exampleIdNumber);
		String user_manager_id = result.getUserId();
		ScriptObj obj = new ScriptObj();
		String example_id = "";
		ExampleRelationObj relation = new ExampleRelationObj();
		relation.setType("4");
		obj.setUpload_person(account);
		obj.setUpdate_person(account);
		String config_uuid = RandomUUID.getUuid();
		// 关系表中插入数据
		example_id = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
				exampleIdNumber);
		relation.setExample_id(example_id);
		relation.setEntity_id(config_uuid);
		exampleRelationService.inserByObj(relation);
		obj.setUser_id(user_manager_id);
		obj.setId(config_uuid);
		// 时间间隔暂时写死为1
		obj.setInterval(1 + "");
		// 时间单位暂时写死为1-minute
		obj.setUnit(1 + "");
		if (1 == scriptType) {// 启动脚本
			String desc = processObj.getExample_name() + "启动脚本";
			obj.setName(desc);
			obj.setDescription(desc);
			obj.setPath(processObj.getSTART_SCRIPT());
		} else if (2 == scriptType) {// 启动脚本
			String desc = processObj.getExample_name() + "停止脚本";
			obj.setName(desc);
			obj.setDescription(desc);
			obj.setPath(processObj.getSTOP_SCRIPT());
		}

		obj.setDescription(processObj.getExample_name() + "");
		obj.setType(scriptType + "");
		// 脚本基本暂时写死为1
		obj.setGrade(1);
		// 脚本执行参数暂时写null
		obj.setParams("");
		scriptsService.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: updateScriptData
	 * @Description: 更新脚本数据
	 * @param scriptType
	 *            脚本类型 1 ： 启动，2 : 停止
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-8 上午10:58:51
	 */
	private void updateScriptData(ProcessObj processObj, String scriptId, int scriptType) {
		// 获取当前系统登录Session
		HttpSession session = UserSession.getHttpSession();
		String account = session.getAttribute(Constant.ACCOUNT) + "";
		ScriptObj obj = new ScriptObj();
		obj.setId(scriptId);
		obj.setUpdate_person(account);
		// 时间间隔暂时写死为1
		obj.setInterval(1 + "");
		// 时间单位暂时写死为1-minute
		obj.setUnit(1 + "");
		if (1 == scriptType) {// 启动脚本
			String desc = processObj.getExample_name() + "启动脚本";
			obj.setName(desc);
			obj.setDescription(desc);
			obj.setPath(processObj.getSTART_SCRIPT());
		} else if (2 == scriptType) {// 启动脚本
			String desc = processObj.getExample_name() + "停止脚本";
			obj.setName(desc);
			obj.setDescription(desc);
			obj.setPath(processObj.getSTOP_SCRIPT());
		}

		obj.setDescription(processObj.getExample_name() + "");
		obj.setType(scriptType + "");
		// 脚本基本暂时写死为1
		obj.setGrade(1);
		// 脚本执行参数暂时写null
		obj.setParams("");
		scriptsService.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: saveProcess
	 * @Description: 保存进程信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-7 下午2:31:48
	 */
	private void saveProcess(ProcessObj processObj) {
		ExampleRelationObj relation = new ExampleRelationObj();
		// 对于部署实例及基准应用的序列id（example_id）进行转化，因为二者可能会出现冲突
		relation.setType("3");
		// 获取部署实例id tb_busi_deploy_example
		int exampleIdNumber = Integer.parseInt(dealExampleIdByExampleName(processObj
				.getExample_name()));
		// 查询实例关联user_manager Id
		DeployAppVO result = queryDeployExampleData(exampleIdNumber);
		String user_manager_id = result.getUserId();
		String example_id = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
				exampleIdNumber);// 3代表部署实例
		relation.setExample_id(example_id);
		String processUuid = RandomUUID.getUuid();
		processObj.setID(processUuid);
		processObj.setISRUNNING(3);
		processObj.setTAST_TYPE(2);
		processObj.setOPERATION(2);
		processObj.setTYPE(1);// 部署管理类型
		processObj.setPROCESS_COUNT_ACTUAL(-1);
		processObj.setPROCESS_STATE(0);
		relation.setEntity_id(processUuid);
		exampleRelationService.inserByObj(relation);
		processObj.setUSER_ID(user_manager_id);
		processObj.setPARENT_ID("0");
		UserObj userObj = new UserObj();
		userObj.setId(user_manager_id);
		userObj = userService.queryUserObjById(userObj);
		processObj.setIP(userObj.getIp());
		processObj.setUSERNAME(userObj.getUsername());
		processService.insertByObj(processObj);
	}

	/**
	 * 
	 * @Title: queryDeployExampleData
	 * @Description: 查询部署实例信息
	 * @param
	 * @return DeployAppVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-8 上午10:39:37
	 */
	private DeployAppVO queryDeployExampleData(int exampleIdNumber) {
		DeployAppVO deployAppVO = new DeployAppVO();
		deployAppVO.setDeployExampleId(exampleIdNumber);
		DeployAppVO result = appExcelDao.queryDeployAppInfoByExample(deployAppVO);
		return result;
	}

	/**
	 * 
	 * @Title: updateProcess
	 * @Description: 更新进程信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-7 下午4:29:22
	 */
	private void updateProcess(ProcessObj processObj) {
		processService.updateProcessByObj(processObj);
	}

	/**
	 * 
	 * @Title: dealExampleIdByExampleName
	 * @Description: 处理部署实例名称为实例ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-7 下午2:14:32
	 */
	private String dealExampleIdByExampleName(String exampleName) {
		return exampleName.substring(exampleName.lastIndexOf("@") + 1);
	}

}

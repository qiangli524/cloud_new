package com.sitech.basd.bol.web.nodetask;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.cluster.BolClusterVO;
import com.sitech.basd.bol.domain.host.BolHostVO;
import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.bol.service.nodetask.NodeTaskService;
import com.sitech.basd.bol.service.program.BolProgramService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.bol.NodeTaskStateConstant;
import com.sitech.utils.bol.NodeTaskTypeConstant;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("serial")
@Controller("nodeTaskAction")
@Scope("prototype")
public class NodeTaskAction extends BaseAction {
	
	@Autowired
	private NodeTaskService nodeTaskService;
	@Autowired
	private BolNodeService bolNodeService;
	@Autowired
	private BolClusterService bolClusterService;
	@Autowired
	private BolHostService bolHostService;
	@Autowired
	private BolProgramService bolProgramService;
	
	private List<BolNodeTaskVO> resultList;
	private List<BolProgramVO> programList;
	private int queryState;
	private int queryType;

	public List<BolNodeTaskVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolNodeTaskVO> resultList) {
		this.resultList = resultList;
	}
	
	public int getQueryState() {
		return queryState;
	}

	public void setQueryState(int queryState) {
		this.queryState = queryState;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public List<BolProgramVO> getProgramList() {
		return programList;
	}

	public void setProgramList(List<BolProgramVO> programList) {
		this.programList = programList;
	}

	public String listNodeTask(){
		BolNodeTaskVO nodeTaskVO = new BolNodeTaskVO();
		if(queryState!=0){
			nodeTaskVO.setTaskState(queryState);
		}
		if(queryType!=0){
			nodeTaskVO.setTaskType(queryType);
		}
		nodeTaskVO.setPagination(this.getPaginater().initPagination(request));
		resultList = nodeTaskService.queryForListByObj(nodeTaskVO);
		return "list";
	}
	
	/**
	 * 
	 * @Title: applyRequest
	 * @Description: 跳转到申请资源页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午12:15:13
	 */
	public String applyRequest(){
		return "apply";
	}
	
	/**
	 * 
	 * @Title: applyResource
	 * @Description: 申请资源
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午12:15:29
	 */
	public String applyResource(){
		JSONObject jo = new JSONObject();
		jo.put("HOST_NAME", request.getParameter("HOST_NAME")==null?"":request.getParameter("HOST_NAME"));
		jo.put("NODE_TYPE", request.getParameter("NODE_TYPE")==null?"":request.getParameter("NODE_TYPE"));
		jo.put("NODE_IP", request.getParameter("NODE_IP")==null?"":request.getParameter("NODE_IP"));
		jo.put("NODE_CAPABILITY", request.getParameter("NODE_CAPABILITY")==null?0:Integer.parseInt(request.getParameter("NODE_CAPABILITY")));
		jo.put("USER", request.getParameter("USER")==null?"":request.getParameter("USER"));
		jo.put("PASSWORD", request.getParameter("PASSWORD")==null?"":request.getParameter("PASSWORD"));
		BolNodeTaskVO vo = new BolNodeTaskVO();
		vo.setTaskResult(jo.toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2").replace("\"", "\'"));
		vo.setTaskType(NodeTaskTypeConstant.APPLY);
		vo.setTaskState(NodeTaskStateConstant.APPLY);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		vo.setTaskId("B_"+ format.format(new Date()));
		nodeTaskService.insertByObj(vo);
		return "save";
	}
	
	/**
	 * 
	 * @Title: addInformationRequest
	 * @Description: 跳转到补录信息页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午1:03:35
	 */
	public String addInformationRequest(){
		String taskId = request.getParameter("taskId");
		String taskType = request.getParameter("taskType");
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskType",taskType);
		return "addInformation";
	}
	
//	bolnodetask_addInformation.do?state="+state +"&reason="+reason+"&taskId="+taskId;
	/**
	 * 
	 * @Title: addInformation
	 * @Description: 补录信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午5:00:54
	 */
	public String addInformation(){
		int state = Integer.parseInt(request.getParameter("state")==null?"0":request.getParameter("state"));
		String reason = request.getParameter("reason");
		String taskId = request.getParameter("taskId");
		
		if(state == 4){
			BolNodeTaskVO queryObj = new BolNodeTaskVO();
			queryObj.setTaskId(taskId);
			queryObj = nodeTaskService.queryByObj(queryObj);
			if(queryObj.getTaskType() ==1){
				try {
					//申请资源成功
					BolClusterVO bolClusterVO = new BolClusterVO();
					List<BolClusterVO> bolCluVoList = bolClusterService
							.queryForListByObj(bolClusterVO);
					BolHostVO bolHostVO = new BolHostVO();
					JSONObject jo = JSONObject.fromObject(queryObj
							.getTaskResult());
					String ip = jo.get("NODE_IP").toString();
					bolHostVO.setIpaddress(ip);
					List<BolHostVO> bolHostVOList = bolHostService
							.queryForListByObj(bolHostVO);
					BolNodeVO bolNodeVO = new BolNodeVO();
					if (bolCluVoList != null && bolCluVoList.size()>0 
							&& bolHostVOList != null && bolHostVOList.size()>0) {
						bolNodeVO.setClusterId(bolCluVoList.get(0).getId());
						bolNodeVO.setHostId(bolHostVOList.get(0).getId());
						bolNodeVO.setName(ip);
						bolNodeVO.setDescrip(ip);
						bolNodeVO.setStatus(1);
						bolNodeService.insertByBolNodeVO(bolNodeVO);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if(queryObj.getTaskType() ==2){
				//释放资源成功
				JSONObject jo = JSONObject.fromObject(queryObj.getTaskDesc());
				String ip = jo.get("NODE_IP").toString();
				BolNodeVO bolNodeVO = new BolNodeVO();
				bolNodeVO.setDescrip(ip);
				try {
					bolNodeService.deleteByBolNodeVO(bolNodeVO);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		BolNodeTaskVO vo = new BolNodeTaskVO();
		vo.setTaskId(taskId);
		vo.setTaskState(state);
		vo.setTaskAddMess(reason);
		nodeTaskService.updateByObj(vo);
		return "save";
	}
	
	/**
	 * 
	 * @Title: upgradeRequest
	 * @Description: 跳转到升级页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午3:05:37
	 */
	public String upgradeRequest(){
		BolProgramVO queryProgramVO = new BolProgramVO();
		programList = bolProgramService.queryForListByObj(queryProgramVO);
		return "upgradeRequest";
	}
	
	/**
	 * 
	 * @Title: upgradeResource
	 * @Description: 升级资源
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午3:28:53
	 */
	public String upgradeResource(){
		JSONObject jo = new JSONObject();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String id = "B_"+ format.format(new Date());
		jo.put("TASK_ID", id);
		jo.put("TASK_TYPE", NodeTaskTypeConstant.UPGRADE);
		jo.put("TASK_STATE", NodeTaskStateConstant.UPGRADE);
		jo.put("NODE_IP", request.getParameter("NODE_IP")==null?"":request.getParameter("NODE_IP"));
		jo.put("HOST_NAME", request.getParameter("HOST_NAME")==null?"":request.getParameter("HOST_NAME"));
		jo.put("UPDATE_VERSION", request.getParameter("UPDATE_VERSION")==null?"":request.getParameter("UPDATE_VERSION"));
		int UPDATE_MODE = request.getParameter("UPDATE_MODE")==null?0:Integer.parseInt(request.getParameter("UPDATE_MODE"));
		jo.put("UPDATE_MODE", UPDATE_MODE);
		if(UPDATE_MODE == 8){
			String programName = request.getParameter("programName")==null?"":request.getParameter("programName");
			jo.put("HLA_TYPE", programName);
		}
		BolNodeTaskVO vo = new BolNodeTaskVO();
		vo.setTaskDesc(jo.toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2").replace("\"", "\'"));
		vo.setTaskType(NodeTaskTypeConstant.UPGRADE);
		vo.setTaskState(NodeTaskStateConstant.UPGRADE);
		vo.setTaskId(id);
		nodeTaskService.insertByObj(vo);
		return "save";
	}
	
	/**
	 * 
	 * @Title: releaseRequest
	 * @Description: 跳转到释放资源页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午5:41:21
	 */
	public String releaseRequest(){
		return "releaseRequest";
	}
	
	/**
	 * 
	 * @Title: releaseResource
	 * @Description: 释放资源
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-27 下午5:47:21
	 */
	public String releaseResource(){
		JSONObject jo = new JSONObject();
		jo.put("NODE_IP", request.getParameter("NODE_IP")==null?"":request.getParameter("NODE_IP"));
		jo.put("NODE_CODE", request.getParameter("NUMBER")==null?"":request.getParameter("NUMBER"));
		BolNodeTaskVO vo = new BolNodeTaskVO();
		vo.setTaskDesc(jo.toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2").replace("\"", "\'"));
		vo.setTaskType(NodeTaskTypeConstant.RELEASE);
		vo.setTaskState(NodeTaskStateConstant.APPLY);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		vo.setTaskId("B_"+ format.format(new Date()));
		nodeTaskService.insertByObj(vo);
		return "save";
	}
	
	/**
	 * 
	 * @Title: selectParam
	 * @Description: 跳转到选择应用程序界面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 17, 2014 12:42:23 PM
	 */
	public String selectParam(){
		BolProgramVO queryProgramVO = new BolProgramVO();
		programList = bolProgramService.queryForListByObj(queryProgramVO);
		return "selectParam";
	}
}

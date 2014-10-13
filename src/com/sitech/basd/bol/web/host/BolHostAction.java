package com.sitech.basd.bol.web.host;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.host.BolHostVO;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.nodetask.NodeTaskService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.utils.bol.NodeTaskStateConstant;
import com.sitech.utils.bol.NodeTaskTypeConstant;

/**
 * 
 * <p>
 * Title: BolHostAction
 * </p>
 * <p>
 * Description: BOL_Host界面展示Action
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
 * @createtime 2014-2-25 上午9:39:29
 * 
 */
@Controller("bolHostAction")
@Scope("prototype")
public class BolHostAction extends BaseAction {
	@Autowired
	private BolHostService bolHostService;
	@Autowired
	private NodeTaskService nodeTaskService;
	
	private List<BolHostVO> resultList;
	private BolHostVO bolHostVO;
	private BolHostVO theForm;
	private int hostId;
	private String hostName;
	private String nodeIp;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getNodeIp() {
		return nodeIp;
	}

	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	public BolHostVO getTheForm() {
		return theForm;
	}

	public void setTheForm(BolHostVO theForm) {
		this.theForm = theForm;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public List<BolHostVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolHostVO> resultList) {
		this.resultList = resultList;
	}

	public BolHostVO getBolHostVO() {
		return bolHostVO;
	}

	public void setBolHostVO(BolHostVO bolHostVO) {
		this.bolHostVO = bolHostVO;
	}

	/**
	 * 
	 * @Title: listBolHost
	 * @Description: 获取主机列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午10:30:23
	 */
	public String listBolHost() {
		if (theForm == null) {
			theForm = new BolHostVO();
		}
		theForm.setPagination(this.getPaginater().initPagination(request));
		try {
			resultList = bolHostService.queryForListByObj(theForm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 修改主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午9:43:08
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new BolHostVO();
		}
		BolHostVO obj = new BolHostVO();
		if (hostId > 0) {
			obj.setId(hostId);
			try {
				theForm = bolHostService.queryForListByObj(obj).get(0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			theForm = null;
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:09:40
	 */
	public String save() {
		if (theForm == null) {
			theForm = new BolHostVO();
		}
		BolHostVO obj = new BolHostVO();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int hostId = 0;
		try {
			hostId = bolHostService.insertByBolHostVO(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//插入任务表
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String taskId = "B_"+ format.format(new Date());
		JSONObject jo = new JSONObject();
		jo.put("TASK_ID", taskId);
		jo.put("TASK_TYPE", 4);
		jo.put("HOST_NAME", obj.getName());
		jo.put("NODE_IP", obj.getIpaddress());
		jo.put("NODE_TYPE", 1);
		jo.put("NODE_CAPABILITY", obj.getCapability());
		jo.put("hostId", hostId);
		
		BolNodeTaskVO vo = new BolNodeTaskVO();
		vo.setTaskDesc(jo.toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2").replace("\"", "\'"));
		vo.setTaskType(4);
		vo.setTaskState(NodeTaskStateConstant.APPLY);
		vo.setTaskId(taskId);
		vo.setTaskTrack("申请");
		nodeTaskService.insertByObj(vo);
		
		return "insert";
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:09:40
	 */
	public String update() {
		if (theForm == null) {
			theForm = new BolHostVO();
		}
		BolHostVO obj = new BolHostVO();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			bolHostService.updateByBolHostVO(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "update";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午11:00:49
	 */
	public String del() {
		if (hostId > 0) {
			BolHostVO obj = new BolHostVO();
			obj.setId(hostId);
			try {
				bolHostService.deleteByBolHostVO(obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "del";
	}
	
	/**
	 * 
	 * @Title: activeHost
	 * @Description: 修改主机的激活状态
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 17, 2014 5:58:50 PM
	 */
	public String activeHost(){
		String hostCode = request.getParameter("hostCode");
		int result = -1;
		JSONObject reJo = new JSONObject();
		if (hostCode!=null && !hostCode.equals("")) {
			if(hostId > 0){
				String activeState = request.getParameter("activeState");
				BolHostVO hostVO = new BolHostVO();
				hostVO.setId(hostId);
				hostVO.setIsActive(2);
				try {
					bolHostService.updateByBolHostVO(hostVO);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//插入任务表
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String taskId = "B_"+ format.format(new Date());
				int taskType = 0;
				if(activeState.endsWith("1")){
					taskType = 6;
				}else{
					taskType = 7;
				}
				JSONObject jo = new JSONObject();
				jo.put("TASK_ID", taskId);
				jo.put("TASK_TYPE", taskType);
				jo.put("HOST_NAME", hostCode);
				jo.put("NODE_IP", request.getParameter("nodeIp")==null?"":request.getParameter("nodeIp"));
				jo.put("HOST_ID", hostId);
				BolNodeTaskVO vo = new BolNodeTaskVO();
				vo.setTaskDesc(jo.toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2").replace("\"", "\'"));
				vo.setTaskType(taskType);
				vo.setTaskState(NodeTaskStateConstant.APPLY);
				vo.setTaskId(taskId);
				vo.setTaskTrack("申请");
				nodeTaskService.insertByObj(vo);
				result = 1;
			}
		}else{
			//未找到主机hostCode，主机不能匹配
			result = -2;
		}
		reJo.put("result", result);
		try {
			JSONUtil.printJSON(reJo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: add
	 * @Description: 调转到添加主机页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2014 5:09:41 PM
	 */
	public String add(){
		return "add";
	}
}

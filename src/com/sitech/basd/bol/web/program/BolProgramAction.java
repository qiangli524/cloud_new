package com.sitech.basd.bol.web.program;

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
import com.sitech.basd.bol.domain.program.BolProgramLibVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.bol.service.nodetask.NodeTaskService;
import com.sitech.basd.bol.service.program.BolProgramLibService;
import com.sitech.basd.bol.service.program.BolProgramService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.bol.NodeTaskStateConstant;
import com.sitech.utils.bol.NodeTaskTypeConstant;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("serial")
@Controller("bolProgramAction")
@Scope("prototype")
public class BolProgramAction extends BaseAction {
	
	@Autowired
	private BolProgramService bolProgramService;
	
	private List<BolProgramVO> resultList;
	private BolProgramVO programVO;
	private int queryStatus = -1;
	private String queryName;

	public List<BolProgramVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolProgramVO> resultList) {
		this.resultList = resultList;
	}

	public BolProgramVO getProgramVO() {
		return programVO;
	}

	public void setProgramVO(BolProgramVO programVO) {
		this.programVO = programVO;
	}

	public int getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(int queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	/**
	 * 
	 * @Title: listBolProgram
	 * @Description: 查询应用程序列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午12:22:00
	 */
	public String listBolProgram(){
		BolProgramVO queryProgramVO = new BolProgramVO();
		if(queryStatus!=-1){
			queryProgramVO.setStatus(queryStatus);
		}
		if(queryName!=null && !"".equals(queryName)){
			queryProgramVO.setName(queryName);
		}
		queryProgramVO.setPagination(this.getPaginater().initPagination(request));
		resultList = bolProgramService.queryForListByObj(queryProgramVO);
		return "list";
	}
	
	/**
	 * 
	 * @Title: addRequest
	 * @Description: 跳转到添加程序的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午1:19:12
	 */
	public String addRequest(){
		String oper = request.getParameter("oper");
		request.setAttribute("oper", oper);
		return "add";
	}
	
	/**
	 * 
	 * @Title: updateRequest
	 * @Description: 跳转到更新应用程序的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 上午11:27:25
	 */
	public String updateRequest(){
		String oper = request.getParameter("oper");
		int programId = Integer.parseInt(request.getParameter("programId"));
		request.setAttribute("oper", oper);
		request.setAttribute("programId", programId);
		BolProgramVO queryProgramVO = new BolProgramVO();
		queryProgramVO.setId(programId);
		programVO = bolProgramService.queryByObj(queryProgramVO);
		return "update";
	}
	
	/**
	 * 
	 * @Title: saveBolProgram
	 * @Description: 保存应用程序
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午12:34:10
	 */
	public String saveBolProgram(){
		BolProgramVO proVO = new BolProgramVO();
		proVO.setName(request.getParameter("name")==null?"":request.getParameter("name"));
		proVO.setDescrip(request.getParameter("descrip")==null?"":request.getParameter("descrip"));
		proVO.setStatus(Integer.parseInt(request.getParameter("status")==null?"1":request.getParameter("status")));
		String oper = request.getParameter("oper");
		if(oper.equals("add")){
			bolProgramService.insertByObj(proVO);
		}else{
			proVO.setId(Integer.parseInt(request.getParameter("id")==null?"0":request.getParameter("id")));
			bolProgramService.updateByObj(proVO);
		}
		return "save";	
	}
	
	/**
	 * 
	 * @Title: delBolProgram
	 * @Description: 删除应用程序
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午12:39:02
	 */
	public String delBolProgram(){
		int programId = Integer.parseInt(request.getParameter("programId"));
		BolProgramVO bolProgramVO = new BolProgramVO();
		bolProgramVO.setId(programId);
		bolProgramService.deleteByObj(bolProgramVO);
		return "save";
	}
	
}

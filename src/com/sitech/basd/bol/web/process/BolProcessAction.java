package com.sitech.basd.bol.web.process;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.bol.service.process.BolProcessService;
import com.sitech.basd.bol.service.program.BolProgramService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("serial")
@Controller("bolProcessAction")
@Scope("prototype")
public class BolProcessAction extends BaseAction {
	
	@Autowired
	private BolProcessService bolProcessService;
	@Autowired
	private BolNodeService bolNodeService;
	@Autowired
	private BolProgramService bolProgramService;
	
	private List<BolProcessVO> resultList;
	private List<BolNodeVO> nodeList;
	private List<BolProgramVO> programList;
	private BolProcessVO processVO;
	private int queryStatus = -1;
	private String queryName;

	public List<BolProcessVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolProcessVO> resultList) {
		this.resultList = resultList;
	}

	public List<BolNodeVO> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<BolNodeVO> nodeList) {
		this.nodeList = nodeList;
	}

	public List<BolProgramVO> getProgramList() {
		return programList;
	}

	public void setProgramList(List<BolProgramVO> programList) {
		this.programList = programList;
	}

	public BolProcessVO getProcessVO() {
		return processVO;
	}

	public void setProcessVO(BolProcessVO processVO) {
		this.processVO = processVO;
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
	 * @Title: listBolProcess
	 * @Description: 查询进程列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午6:58:12
	 */
	public String listBolProcess(){
		BolProcessVO queryVo = new BolProcessVO();
		if(queryStatus!=-1){
			queryVo.setStatus(queryStatus);
		}
		if(queryName!=null && !"".equals(queryName)){
			queryVo.setName(queryName);
		}
		queryVo.setPagination(this.getPaginater().initPagination(request));
		resultList = bolProcessService.queryForListByObj(queryVo);
		return "list";
	}
	
	/**
	 * 
	 * @Title: addRequest
	 * @Description: 跳转到添加进程的页面
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
		try {
			nodeList = bolNodeService.queryForListByObj(new BolNodeVO());
			programList = bolProgramService.queryForListByObj(new BolProgramVO());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 
	 * @Title: updateRequest
	 * @Description: 跳转到更新进程的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 上午11:27:25
	 */
	public String updateRequest(){
		String oper = request.getParameter("oper");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("oper", oper);
		request.setAttribute("id", id);
		BolProcessVO queryVo = new BolProcessVO();
		queryVo.setId(id);
		processVO = bolProcessService.queryByObj(queryVo);
		try {
			nodeList = bolNodeService.queryForListByObj(new BolNodeVO());
			programList = bolProgramService.queryForListByObj(new BolProgramVO());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "update";
	}
	
	/**
	 * 
	 * @Title: saveBolProcess
	 * @Description: 保存进程
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午7:33:36
	 */
	public String saveBolProcess(){
		if(processVO==null){
			processVO = new BolProcessVO();
		}
		String oper = request.getParameter("oper");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		processVO.setLastupdate(format.format(new Date()));
		if(oper.equals("add")){
			bolProcessService.insertByObj(processVO);
		}else{
			bolProcessService.updateByObj(processVO);
		}
		return "save";
	}
	
	/**
	 * 
	 * @Title: delBolProcess
	 * @Description: 删除进程
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午7:36:20
	 */
	public String delBolProcess(){
		if(processVO == null){
			processVO = new BolProcessVO();
		}
		bolProcessService.deleteByObj(processVO);
		return "save";
	}
	
}

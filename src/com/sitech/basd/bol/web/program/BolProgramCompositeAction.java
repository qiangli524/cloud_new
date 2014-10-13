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
import com.sitech.basd.bol.domain.program.BolProgramCompositeVO;
import com.sitech.basd.bol.domain.program.BolProgramLibVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.bol.service.nodetask.NodeTaskService;
import com.sitech.basd.bol.service.program.BolProgramCompositeService;
import com.sitech.basd.bol.service.program.BolProgramLibService;
import com.sitech.basd.bol.service.program.BolProgramService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.bol.NodeTaskStateConstant;
import com.sitech.utils.bol.NodeTaskTypeConstant;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("serial")
@Controller("bolProgramCompositeAction")
@Scope("prototype")
public class BolProgramCompositeAction extends BaseAction {
	
	@Autowired
	private BolProgramCompositeService bolProgramCompositeService;
	@Autowired
	private BolProgramLibService bolProgramLibService;
	@Autowired
	private BolProgramService bolProgramService;
	
	private List<BolProgramCompositeVO> resultList;
	private List<BolProgramVO> programList;
	private List<BolProgramLibVO> libList;
	private BolProgramCompositeVO compositeVO;
	private int queryStatus = -1;
	private String queryName;

	public List<BolProgramCompositeVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolProgramCompositeVO> resultList) {
		this.resultList = resultList;
	}

	public List<BolProgramVO> getProgramList() {
		return programList;
	}

	public void setProgramList(List<BolProgramVO> programList) {
		this.programList = programList;
	}

	public List<BolProgramLibVO> getLibList() {
		return libList;
	}

	public void setLibList(List<BolProgramLibVO> libList) {
		this.libList = libList;
	}

	public BolProgramCompositeVO getCompositeVO() {
		return compositeVO;
	}

	public void setCompositeVO(BolProgramCompositeVO compositeVO) {
		this.compositeVO = compositeVO;
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
	 * @Title: listBolProgramComposite
	 * @Description: 查询应用程序组成列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午4:08:06
	 */
	public String listBolProgramComposite(){
		BolProgramCompositeVO queryCompositeVo = new BolProgramCompositeVO();
		if(queryStatus!=-1){
			queryCompositeVo.setStatus(queryStatus);
		}
		queryCompositeVo.setPagination(this.getPaginater().initPagination(request));
		resultList = bolProgramCompositeService.queryForListByObj(queryCompositeVo);
		return "list";
	}
	
	/**
	 * 
	 * @Title: addRequest
	 * @Description: 跳转到添加程序组成页面
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
		libList = bolProgramLibService.queryForListByObj(new BolProgramLibVO());
		programList = bolProgramService.queryForListByObj(new BolProgramVO());
		return "add";
	}
	
	/**
	 * 
	 * @Title: updateRequest
	 * @Description: 跳转到更新应用程序组成的页面
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
		BolProgramCompositeVO queryVo = new BolProgramCompositeVO();
		queryVo.setId(id);
		compositeVO = bolProgramCompositeService.queryByObj(queryVo);
		libList = bolProgramLibService.queryForListByObj(new BolProgramLibVO());
		programList = bolProgramService.queryForListByObj(new BolProgramVO());
		return "update";
	}
	
	/**
	 * 
	 * @Title: saveBolProgramComposite
	 * @Description: 保存应用程序组成信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午4:43:35
	 */
	public String saveBolProgramComposite(){
		if(compositeVO==null){
			compositeVO = new BolProgramCompositeVO();
		}
		compositeVO.setProgramId(request.getParameter("programId")==null?"":request.getParameter("programId"));
		compositeVO.setLibId(request.getParameter("libId")==null?"":request.getParameter("libId"));
		compositeVO.setStatus(Integer.parseInt(request.getParameter("status")==null?"1":request.getParameter("status")));
		String oper = request.getParameter("oper");
		if(oper.equals("add")){
			bolProgramCompositeService.insertByObj(compositeVO);
		}else{
			compositeVO.setId(Integer.parseInt(request.getParameter("id")==null?"0":request.getParameter("id")));
			bolProgramCompositeService.updateByObj(compositeVO);
		}
		return "save";
	}
	
	/**
	 * 
	 * @Title: delBolProgramComposite
	 * @Description: 删除应用程序组成信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午4:46:09
	 */
	public String delBolProgramComposite(){
		int id = Integer.parseInt(request.getParameter("id"));
		BolProgramCompositeVO programCompositeVO = new BolProgramCompositeVO();
		programCompositeVO.setId(id);
		bolProgramCompositeService.deleteByObj(programCompositeVO);
		return "save";
	}
	
}

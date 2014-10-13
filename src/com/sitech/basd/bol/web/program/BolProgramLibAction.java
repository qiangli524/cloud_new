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
import com.sitech.basd.bol.service.cluster.BolClusterService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeService;
import com.sitech.basd.bol.service.nodetask.NodeTaskService;
import com.sitech.basd.bol.service.program.BolProgramLibService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.bol.NodeTaskStateConstant;
import com.sitech.utils.bol.NodeTaskTypeConstant;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("serial")
@Controller("bolProgramLibAction")
@Scope("prototype")
public class BolProgramLibAction extends BaseAction {
	
	@Autowired
	private BolProgramLibService bolProgramLibService;
	
	private List<BolProgramLibVO> resultList;
	private BolProgramLibVO libVO;
	private int queryStatus = -1;
	private String queryName;

	public List<BolProgramLibVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolProgramLibVO> resultList) {
		this.resultList = resultList;
	}

	public BolProgramLibVO getLibVO() {
		return libVO;
	}

	public void setLibVO(BolProgramLibVO libVO) {
		this.libVO = libVO;
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
	 * @Title: listBolProgramLib
	 * @Description: 查询能力库列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午1:12:19
	 */
	public String listBolProgramLib(){
		BolProgramLibVO programLibVO = new BolProgramLibVO();
		if(queryStatus!=-1){
			programLibVO.setStatus(queryStatus);
		}
		if(queryName!=null && !"".equals(queryName)){
			programLibVO.setName(queryName);
		}
		programLibVO.setPagination(this.getPaginater().initPagination(request));
		resultList = bolProgramLibService.queryForListByObj(programLibVO);
		return "list";
	}
	
	/**
	 * 
	 * @Title: addRequest
	 * @Description: 跳转到添加能力库的页面
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
	 * @Description: 跳转到更新能力库的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 上午11:27:25
	 */
	public String updateRequest(){
		String oper = request.getParameter("oper");
		int libId = Integer.parseInt(request.getParameter("libId"));
		request.setAttribute("oper", oper);
		request.setAttribute("libId", libId);
		BolProgramLibVO queryLibVO = new BolProgramLibVO();
		queryLibVO.setId(libId);
		libVO = bolProgramLibService.queryByObj(queryLibVO);
		return "update";
	}
	
	/**
	 * 
	 * @Title: saveBolProgramLib
	 * @Description: 保存知识库
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午12:01:35
	 */
	public String saveBolProgramLib(){
		if(libVO==null){
			libVO = new BolProgramLibVO();
		}
		libVO.setName(request.getParameter("name")==null?"":request.getParameter("name"));
		libVO.setDescrip(request.getParameter("descrip")==null?"":request.getParameter("descrip"));
		libVO.setStatus(Integer.parseInt(request.getParameter("status")==null?"1":request.getParameter("status")));
		String oper = request.getParameter("oper");
		if(oper.equals("add")){
			bolProgramLibService.insertByObj(libVO);
		}else{
			libVO.setId(Integer.parseInt(request.getParameter("id")==null?"0":request.getParameter("id")));
			bolProgramLibService.updateByObj(libVO);
		}
		return "save";
	}
	
	/**
	 * 
	 * @Title: delBolProgramLib
	 * @Description: 删除能力库
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-10 下午12:19:44
	 */
	public String delBolProgramLib(){
		int libId = Integer.parseInt(request.getParameter("libId"));
		BolProgramLibVO libVO = new BolProgramLibVO();
		libVO.setId(libId);
		bolProgramLibService.deleteByObj(libVO);
		return "save";
	}
	
}

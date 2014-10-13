package com.sitech.ssd.bj.chinatelcom.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj;
import com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("tbCloud2ApplicationAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class TbCloud2ApplicationAction extends BaseAction {

	@Autowired
	private TbCloud2ApplicationService tbCloud2ApplicationService;
	
	private TbCloud2ApplicationObj applicationObj;
	
	private List<TbCloud2ApplicationObj> resultList;//结果集
	
	private String oper;//操作 
	
	public TbCloud2ApplicationObj getApplicationObj() {
		return applicationObj;
	}

	public void setApplicationObj(TbCloud2ApplicationObj applicationObj) {
		this.applicationObj = applicationObj;
	}
	
	public List<TbCloud2ApplicationObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<TbCloud2ApplicationObj> resultList) {
		this.resultList = resultList;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	/**
	 * @Title: list
	 * @Description: 展示列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:29:30
	 */
	public String list(){
		initObj();
		if (applicationObj.getStatus() != null && applicationObj.getStatus().intValue() == -1) {
			applicationObj.setStatus(null);
		}
//		if (applicationObj.getResultcode() != null && applicationObj.getResultcode().equals("-1")) {
//			applicationObj.setResultcode(null);
//		}
		applicationObj.setPagination(this.getPaginater().initPagination(request));
		resultList = tbCloud2ApplicationService.queryForListByObj(applicationObj);
		return "list";
	}
	
	/**
	 * @Title: add
	 * @Description: 进入添加页
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:38:51
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * @Title: update
	 * @Description: 进入修改页
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:39:41
	 */
	public String update(){
		initObj();
		applicationObj = tbCloud2ApplicationService.queryForObjByObj(applicationObj);
		return "update";
	}
	
	/**
	 * @Title: delete
	 * @Description: 删除记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:45:43
	 */
	public void delete(){
		initObj();
		String idstr = applicationObj.getId();
		if (idstr != null) {
			String[] idarr = idstr.split(",");
			TbCloud2ApplicationObj tbCloud2ApplicationObj = new TbCloud2ApplicationObj();
			if (idarr.length > 1) {
				List<String> idList = new ArrayList<String>();
				for (String id : idarr) {
					idList.add(id);
				}
				tbCloud2ApplicationObj.setIdList(idList);
			} else {
				tbCloud2ApplicationObj.setId(idarr[0]);
			}
			int ret = tbCloud2ApplicationService.deleteByObj(tbCloud2ApplicationObj);
			PrintWriterOut.printWirter(response, ret);
		}
	}
	
	/**
	 * @Title: save
	 * @Description: 保存修改
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午6:05:13
	 */
	public void save(){
		initObj();
		int ret = 0;
		if ("add".equals(oper)) {//添加操作
			applicationObj.setId(RandomUUID.getUuid());
			applicationObj.setStatus(1);
			ret = tbCloud2ApplicationService.insertByObj(applicationObj);
		} else if ("edit".equals(oper)) {//修改操作
			ret = tbCloud2ApplicationService.updateByObj(applicationObj);
		}
		PrintWriterOut.printWirter(response, ret);
	}
	
	/**
	 * @Title: initObj
	 * @Description: 初始化
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:49:20
	 */
	private void initObj(){
		if (applicationObj == null) {
			applicationObj = new TbCloud2ApplicationObj();
		}
	}
}

package com.sitech.basd.deployfile.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.deployfile.domain.BaseAppFileTreeVO;
import com.sitech.basd.deployfile.service.BaseAppFileTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.servlet.PrintWriterOut;

import deploy.AppFileTreeObj;

/**
 * 
 * <p>
 * Title: TbSwiftBackupFileTreeAction
 * </p>
 * <p>
 * Description: Swift备份文件树
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
 * @createtime 2014-7-17 上午10:54:00
 * 
 */
@Controller("baseAppFileTreeAction")
@Scope("prototype")
public class BaseAppFileTreeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(BaseAppFileTreeAction.class
			.getName());
	@Autowired
	private BaseAppFileTreeService baseAppFileTreeService;

	private String file_url;// 所有文件清单
	private String baseappid;// 基准应用Id
	private BaseAppFileTreeVO obj;
	private List<BaseAppFileTreeVO> resultList;

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getBaseappid() {
		return baseappid;
	}

	public void setBaseappid(String baseappid) {
		this.baseappid = baseappid;
	}

	public BaseAppFileTreeVO getObj() {
		return obj;
	}

	public void setObj(BaseAppFileTreeVO obj) {
		this.obj = obj;
	}

	public List<BaseAppFileTreeVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BaseAppFileTreeVO> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 
	 * @Title: baseAppFileTree
	 * @Description: 初始化页面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:43:11
	 */
	public String baseAppFileTree() {
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: T异步加载树
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 下午4:15:34
	 */
	public String asyncForTree() {
		List<BaseAppFileTreeVO> list;
		try {
			list = queryTreeNode(request);
			List<BaseAppFileTreeVO> resultList = baseAppFileTreeService.initTreelist(list);
			PrintWriterOut.printWirter(response, JacksonUtil.toJson(resultList));
		} catch (SQLException e) {
			logger.error("异步加载Openstack Swift 备份文件树异常 ！" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点
	 * @param
	 * @return List<BaseAppFileTreeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 下午4:15:25
	 */
	public List<BaseAppFileTreeVO> queryTreeNode(HttpServletRequest request) throws SQLException {
		List<BaseAppFileTreeVO> list = new ArrayList<BaseAppFileTreeVO>();
		BaseAppFileTreeVO obj = new BaseAppFileTreeVO();
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			obj.setParent_id("root");
		} else {
			obj.setParent_id(id);
		}
		obj.setBaseappId(baseappid);
		list = baseAppFileTreeService.queryForTree(obj);
		return list;
	}

	/**
	 * 
	 * @Title: CreateFileTreeByAppid
	 * @Description: 为基准应用生成文件树
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-11 下午4:44:37
	 */
	public String CreateFileTreeByAppid() {

		String appid = request.getParameter("appid");
		AppFileTreeObj AppFileTreeObj = new AppFileTreeObj();
		AppFileTreeObj.setAppid(appid);
		String result = baseAppFileTreeService.CreateFileTreeByAppid(AppFileTreeObj);
		ActionContext.getContext().getValueStack().push(result);
		return "CreateFileTreeByAppid";
	}

}

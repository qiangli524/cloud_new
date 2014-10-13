package com.sitech.basd.resource.web.mframe;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.mframe.MFrameObj;
import com.sitech.basd.resource.service.mframe.MFrameService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: MFrameAction
 * </p>
 * <p>
 * Description: frame
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author siweichao
 * @version 1.0
 * @createtime Jul 11, 2013 2:27:14 PM
 * 
 */
@Controller("mFrameAction")
@Scope("prototype")
public class MFrameAction extends BaseAction {

	@Autowired
	private MFrameService mFrameService;
	private List<MFrameObj> frameList; // 所有机框集合
	private MFrameObj theForm; // 封装表单提交的数据

	public MFrameObj getTheForm() {
		return theForm;
	}

	public void setTheForm(MFrameObj theForm) {
		this.theForm = theForm;
	}

	public List<MFrameObj> getFrameList() {
		return frameList;
	}

	public void setFrameList(List<MFrameObj> frameList) {
		this.frameList = frameList;
	}

	/**
	 * 
	 * @Title: HostTop
	 * @Description: 查询所有机框
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new MFrameObj();
		}
		MFrameObj obj = new MFrameObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getFrame_name()) && theForm.getFrame_name() != null) {
			obj.setFrame_name(theForm.getFrame_name());
		}
		if (!"".equals(theForm.getFrame_status()) && theForm.getFrame_status() != null) {
			obj.setFrame_status(theForm.getFrame_status());
		}
		frameList = mFrameService.queryFrameList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: HostTop
	 * @Description: 修改机框信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new MFrameObj();
		}
		MFrameObj obj = new MFrameObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		obj.setId(id);
		// 根据id查找机框
		List<MFrameObj> list = mFrameService.queryFrameList(obj);
		if (list != null && list.size() > 0) {
			theForm = list.get(0);
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: HostTop
	 * @Description: 更新机框
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String save() {
		if (theForm == null) {
			theForm = new MFrameObj();
		}
		MFrameObj obj = new MFrameObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = theForm.getId();
//		TbSysUserinfoObj user = (TbSysUserinfoObj) request.getSession().getAttribute(
//				Constant.USER_SESSION_KEY); // 获取当前登录用户
		String account=session.get("account").toString();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		if (id != null && !"".equals(id)) {
			// update
			obj.setUpdate_user(account);
			obj.setUpdate_time(date.toString());
			mFrameService.updateFrame(obj);
		} else {
			// insert
			obj.setInsert_user(account);
			String uuid = RandomUUID.getUuid();
			obj.setId(uuid);
			obj.setInsert_time(date.toString());
			mFrameService.insertFrame(obj);
		}
		return "save";
	}

	/**
	 * 
	 * @Title: HostTop
	 * @Description: 删除机框记录
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 15, 2013 2:49:55 PM
	 */
	public String delete() {
		if (theForm == null) {
			theForm = new MFrameObj();
		}
		MFrameObj obj = new MFrameObj();
		obj.setId(theForm.getId());
		mFrameService.deleteFrame(obj);
		return "delete";
	}
}

/*
 * Created on 2006-4-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sitech.basd.sxcloud.cloud.web.feedback.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackObj;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackTypeObj;
import com.sitech.basd.sxcloud.cloud.service.feedback.PublishFeedBackService;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackForm;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackTypeForm;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

/**
 * @author niezl
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PublishFeedBackAction extends CRUDBaseAction {

	private PublishFeedBackService publishFeedBackService;
	private PublishFeedBackTypeForm theForm;
	private PublishFeedBackForm pfbForm;

	public PublishFeedBackForm getPfbForm() {
		return pfbForm;
	}

	public void setPfbForm(PublishFeedBackForm pfbForm) {
		this.pfbForm = pfbForm;
	}

	public PublishFeedBackTypeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(PublishFeedBackTypeForm theForm) {
		this.theForm = theForm;
	}

	public PublishFeedBackService getPublishFeedBackService() {
		return publishFeedBackService;
	}

	public void setPublishFeedBackService(
			PublishFeedBackService publishFeedBackService) {
		this.publishFeedBackService = publishFeedBackService;
	}

	/**
	 * 显示列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String loadListFeedBackType(

	) throws BaseException {
		TbCloud2FeedBackTypeObj obj = new TbCloud2FeedBackTypeObj();
		if (theForm == null) {
			theForm = new PublishFeedBackTypeForm();
		}
		obj.setTYPE_NAME(theForm.getTYPE_NAME());
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List mlst = publishFeedBackService.findListFeedBackType(obj);
		theForm.setResultList(mlst);
		return LIST;
	}

	/**
	 * 编辑模块列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String editFeedBackType(

	) throws BaseException {
		if (theForm == null) {
			theForm = new PublishFeedBackTypeForm();
		}
		String[] tIDs = theForm.getTYPE_IDS();
		if (theForm.getISMP_JSP_COMMAND() != null
				&& theForm.getISMP_JSP_COMMAND().equals("add")) {
			theForm.setTYPE_ID(null);
			return ADD;

		}

		else if (tIDs != null && tIDs[0] != null) {

			PublishFeedBackTypeForm obj = publishFeedBackService
					.findObjByPK(tIDs[0]);
			if (obj != null) {
				theForm.setTYPE_ID(obj.getTYPE_ID());
				theForm.setTYPE_NAME(obj.getTYPE_NAME());
				theForm.setENABLE(String.valueOf(obj.getENABLE()));
				theForm.setTYPE_DESC(obj.getTYPE_DESC());
				theForm.setTYPE_CODE(obj.getTYPE_ID());
			}
		}
		return MODIFY;

	}

	/**
	 * 保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String saveFeedBackType(

	) throws BaseException {
		if (theForm == null) {
			theForm = new PublishFeedBackTypeForm();
		}
		String typeCode = theForm.getTYPE_CODE();// 原始的TYPE_ID
		String typeID = theForm.getTYPE_ID();// 改变的TYPE_ID

		String TYPE_ID = publishFeedBackService.getTypeIdSequence();

		PublishFeedBackTypeForm obj = publishFeedBackService
				.findObjByPK(typeID);
		obj = (obj == null) ? new PublishFeedBackTypeForm() : obj;

		obj.setENABLE(theForm.getENABLE());
		obj.setTYPE_NAME(theForm.getTYPE_NAME());
		obj.setTYPE_DESC(theForm.getTYPE_DESC());
		obj.setTYPE_ID(TYPE_ID);
		obj.setTYPE_CODE(typeCode);

		PublishFeedBackTypeForm cMod = publishFeedBackService.findObjByPK(obj
				.getTYPE_ID());
		if (null == cMod || typeCode.equals(cMod.getTYPE_ID())) {
			if (!typeCode.equals("")) {
				publishFeedBackService.update(obj, Struts2Utils.getRequest());
			} else {
				publishFeedBackService.add(obj, Struts2Utils.getRequest());
			}

		} else {
			return "error";
		}
		return "save";
	}

	/**
	 * 删除模块
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String delFeedBackType(

	) throws BaseException {
		if (theForm == null) {
			theForm = new PublishFeedBackTypeForm();
		}
		String[] tIDs = theForm.getTYPE_IDS();
		try {
			for (int i = 0; i < tIDs.length; i++) {
				publishFeedBackService.deleteByPKs(tIDs[i], Struts2Utils
						.getRequest());
			}
		} catch (Exception e) {
			throw new BaseException();
		}
		return "del";
	}

	/**
	 * 新建信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String addFeedBackInfo(

	) throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		List type = publishFeedBackService.findListTypeName();// 查找反馈类型
		List groups = publishFeedBackService.getAllGroupList();
		pfbForm.setTYPE_LIST(type);
		pfbForm.setGROUP_LIST(groups);
		return SUCCESS;
	}

	public String addFeedBackInfo2(

	) throws BaseException {
		/**
		 * PublishFeedBackForm theForm =(PublishFeedBackForm)form;
		 * TB_PUBLISH_FeedBackTypeDao tB_PUBLISH_FeedBackTYPEDao = new
		 * TB_PUBLISH_FeedBackTypeDao(); String login_id=""; //登录人ID
		 * TblSYS_LOGINMSG user = (TblSYS_LOGINMSG)
		 * request.getSession().getAttribute(TblSYS_LOGINMSG.SESSION_KEY);
		 * login_id=user.getLOGIN_ID(); //取得登录用户ID;
		 * 
		 * List type = tB_PUBLISH_FeedBackTYPEDao.findListTypeName();//查找反馈类型
		 * 
		 * //提交时间为系统当前时间 Date date = new Date(); DateFormat mFormat = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 取得系统时间 //
		 * BusiSupportAppealDao tB_BUSISUPPORT_APPEALDao = new
		 * BusiSupportAppealDao(); // TblSYS_LOGINMSG theBean = new
		 * TblSYS_LOGINMSG(); // theBean =
		 * tB_BUSISUPPORT_APPEALDao.finddeptname(login_id);//提取提交人所在的部门 // List
		 * treeList = new ArrayList(); // MasSearchDeptTree msdt = new
		 * MasSearchDeptTree(); //// treeList =
		 * msdt.getMasSearchDeptTree(user.getLOGIN_ID(), //// theBean == null ? "" :
		 * theBean.getDEPT_ID(), //// theBean == null ? "" :
		 * theBean.getGROUP_CLASS()); // treeList =
		 * msdt.getMasDeptTreeForCopy(user.getLOGIN_ID(), // theBean == null ? "" :
		 * theBean.getDEPT_ID(), // theBean == null ? "" :
		 * theBean.getGROUP_CLASS()); request.setAttribute("treeList", null);
		 * 
		 * request.setAttribute("type",type);
		 * request.setAttribute("login_id",login_id);
		 * request.setAttribute("subtime",mFormat.format(date)); //提交时间
		 */
		return SUCCESS;
	}

	/**
	 * 保存信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String saveFeedBackInfo(

	) throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}

		String ID = publishFeedBackService.getIdSequence();// 自动生成ID
		String TITLE = pfbForm.getTITLE();
		String TYPE_ID = pfbForm.getTYPE_ID();
		String DF_INFO = pfbForm.getDF_INFO();
		String SENTTO_EMPLOYE = pfbForm.getSENTTO_EMPLOYE();
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) Struts2Utils
//				.getSession().getAttribute(Constant.USER_SESSION_KEY);
		String LOGIN_ID =session.get("account").toString();;
		// String IF_AFFIRM = theForm.getIF_AFFIRM();
		String enable = pfbForm.getENABLE();

		PublishFeedBackForm cMod = publishFeedBackService.findObjByID(ID);
		if (null == cMod) {
			if (request.getParameter("type") != null
					&& !request.getParameter("type").equals("")) {
				publishFeedBackService.addFeedBackInfo(ID, "", TITLE, TYPE_ID,
						DF_INFO, SENTTO_EMPLOYE, LOGIN_ID, enable, "0", "1");
			} else {
				publishFeedBackService.addFeedBackInfo(ID, "", TITLE, TYPE_ID,
						DF_INFO, SENTTO_EMPLOYE, LOGIN_ID, enable, "0", "0");
			}
		} else {
			return "error";
		}
		request.setAttribute("type", request.getParameter("type"));
		return "save_info";

	}

	/**
	 * 显示当前信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String loadListFeedBackInfo(

	) throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		List mlst = null;
		String login_id = ""; // 登录人ID
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		login_id = session.get("account").toString(); // 取得登录用户ID;
		TbCloud2FeedBackObj obj = new TbCloud2FeedBackObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setSENTTO_EMPLOYE(login_id);
		if (pfbForm.getTITLE() != null && !"".equals(pfbForm.getTITLE())) {
			obj.setTITLE(pfbForm.getTITLE().trim());
		}
		mlst = publishFeedBackService.findListFeedBackInfo(obj); //
		pfbForm.setResultList(mlst);
		request.setAttribute("type", request.getAttribute("type"));
		return "wait_list";
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String editFeedBackInfo(

	) throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		publishFeedBackService.updateFeedBackInfo(pfbForm.getID(), pfbForm
				.getHF_INFO());
		pfbForm = new PublishFeedBackForm();
		return "update";
	}

	/**
	 * 显示历史信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String loadListHisFeedBackInfo(

	) throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		List mlst = null;
		String login_id = ""; // 登录人ID
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		login_id = session.get("account").toString(); // 取得登录用户ID;
		TbCloud2FeedBackObj obj = new TbCloud2FeedBackObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setSENTTO_EMPLOYE(login_id);
		if (pfbForm.getTITLE() != null && !"".equals(pfbForm.getTITLE())) {
			obj.setTITLE(pfbForm.getTITLE().trim());
		}
		obj.setIF_AFFIRM("1");
		mlst = publishFeedBackService.findListAffiriFeedBackInfo(obj); //
		pfbForm.setResultList(mlst);
		return "his_list";
	}

	public String examineInfo() throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		PublishFeedBackForm obj = publishFeedBackService.findObjByID(ID);
		pfbForm.setSUBMIT_TIME(obj.SUBMIT_TIME);
		pfbForm.setTITLE(obj.TITLE);
		pfbForm.setTYPE_ID(obj.TYPE_ID);
		pfbForm.setDF_INFO(obj.DF_INFO);
		pfbForm.setSENTTO_EMPLOYE(obj.SENTTO_EMPLOYE);
		pfbForm.setLOGIN_ID(obj.LOGIN_ID);
		pfbForm.setID(ID);
		return "examine_info";
	}

	public String searchInfo() throws BaseException {
		/**
		 * PublishFeedBackForm theForm =(PublishFeedBackForm)form; String
		 * beginTime = request.getParameter("selectTimeFrom"); String endTime =
		 * request.getParameter("selectTimeTo"); List mlst = null;
		 * TB_PUBLISH_FeedBackTypeDao tB_PUBLISH_FeedBackTYPEDao = new
		 * TB_PUBLISH_FeedBackTypeDao();
		 * tB_PUBLISH_FeedBackTYPEDao.initPagination(this.getPaginater()
		 * .initPagination(request)); mlst =
		 * tB_PUBLISH_FeedBackTYPEDao.searchHisFeedBackInfo(beginTime,endTime); //
		 */
		return SUCCESS;
	}

	public String examineHisInfo() throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		PublishFeedBackForm obj = publishFeedBackService.findObjByID(ID);
		pfbForm.setSUBMIT_TIME(obj.SUBMIT_TIME);
		pfbForm.setTITLE(obj.TITLE);
		pfbForm.setTYPE_ID(obj.TYPE_ID);
		pfbForm.setDF_INFO(obj.DF_INFO);
		pfbForm.setSENTTO_EMPLOYE(obj.SENTTO_EMPLOYE);
		pfbForm.setLOGIN_ID(obj.LOGIN_ID);
		pfbForm.setHF_INFO(obj.HF_INFO);
		pfbForm.setHF_TIME(obj.HF_TIME);
		pfbForm.setID(ID);
		return "examine_his";
	}

	/**
	 * 显示我反馈的信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String loadListMyFeedBackInfo(

	) throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		List mlst = null;
		String login_id = ""; // 登录人ID
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		login_id = session.get("account").toString(); // 取得登录用户ID;
		TbCloud2FeedBackObj obj = new TbCloud2FeedBackObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setLOGIN_ID(login_id);
		obj.setTITLE(pfbForm.getTITLE());
		obj.setIF_AFFIRM(pfbForm.getIF_AFFIRM());
		mlst = publishFeedBackService.findListMyFeedBackInfo(obj); //
		pfbForm.setResultList(mlst);
		request.setAttribute("type", request.getAttribute("type"));
		return "my_feedback";
	}

	public int getTypeLevel(String cModuleID) {

		int pos = cModuleID.indexOf("00");
		if (pos == -1)
			return 5;
		else
			return (pos + 1) / 2;
	}

	public String editMyFeedBackInfo(

	) throws BaseException {
		/**
		 * PublishFeedBackForm theForm =(PublishFeedBackForm)form; String[] tIDs =
		 * theForm.getIDS();
		 * 
		 * String login_id=""; //登录人ID TblSYS_LOGINMSG user = (TblSYS_LOGINMSG)
		 * request.getSession().getAttribute(TblSYS_LOGINMSG.SESSION_KEY);
		 * login_id=user.getLOGIN_ID(); //取得登录用户ID; String type="";
		 * if(tIDs != null && tIDs[0] !=
		 * null){ TB_PUBLISH_FeedBackTypeDao tB_PUBLISH_FeedBackTYPEDao = new
		 * TB_PUBLISH_FeedBackTypeDao(); PublishFeedBackForm obj =
		 * tB_PUBLISH_FeedBackTYPEDao.findObjByID(tIDs[0]); if(obj != null){
		 * BusiSupportAppealDao
		 * tB_BUSISUPPORT_APPEALDao = new BusiSupportAppealDao();
		 * TblSYS_LOGINMSG theBean = new TblSYS_LOGINMSG(); theBean =
		 * tB_BUSISUPPORT_APPEALDao.finddeptname(login_id);//提取提交人所在的部门 List
		 * treeList = new ArrayList(); MasSearchDeptTree msdt = new
		 * MasSearchDeptTree(); treeList =
		 * msdt.getMasSearchDeptTree(user.getLOGIN_ID(), theBean == null ? "" :
		 * theBean.getDEPT_ID(), theBean == null ? "" :
		 * theBean.getGROUP_CLASS()); request.setAttribute("treeList",
		 * treeList); theForm.setID(obj.getID());
		 * theForm.setTITLE(obj.getTITLE());
		 * theForm.setSUBMIT_TIME(obj.getSUBMIT_TIME());
		 * theForm.setDF_INFO(obj.getDF_INFO()); String stEmploy =
		 * obj.getSENTTO_EMPLOYE(); request.setAttribute("stEmploy", stEmploy);
		 * theForm.setSENTTO_EMPLOYE(stEmploy);
		 * theForm.setLOGIN_ID(obj.getLOGIN_ID());
		 * theForm.setIF_AFFIRM(obj.getIF_AFFIRM());
		 * theForm.setENABLE(obj.getENABLE());
		 * theForm.setTYPE_ID(obj.getTYPE_ID()); theForm.setTYPE(obj.getTYPE());
		 * type=obj.getTYPE(); } } 
		 * if(type!=null&&type.equals("1")) request.setAttribute("type",
		 * "firstpage");
		 */
		return SUCCESS;
	}

	public String saveMyFeedBackInfo(

	) throws BaseException {
		/**
		 * PublishFeedBackForm theForm = (PublishFeedBackForm)form;
		 * TB_PUBLISH_FeedBackTypeDao tB_PUBLISH_FeedBackTYPEDao = new
		 * TB_PUBLISH_FeedBackTypeDao();
		 * 
		 * String ID = theForm.getID(); String TYPE_ID = theForm.getTYPE_ID();
		 * String SUBMIT_TIME = theForm.getSUBMIT_TIME();
		 * if(SUBMIT_TIME.indexOf(".")>0){
		 * SUBMIT_TIME=SUBMIT_TIME.substring(0,SUBMIT_TIME.length()-2); } String
		 * TITLE = theForm.getTITLE(); String DF_INFO = theForm.getDF_INFO();
		 * String SENTTO_EMPLOYE = theForm.getSENTTO_EMPLOYE(); String LOGIN_ID =
		 * theForm.getLOGIN_ID(); //String IF_AFFIRM = theForm.getIF_AFFIRM();
		 * String enable = theForm.getENABLE();
		 * 
		 * tB_PUBLISH_FeedBackTYPEDao.updateMyFeedBackInfo(ID,SUBMIT_TIME,TITLE,TYPE_ID,DF_INFO,SENTTO_EMPLOYE,enable);
		 * 
		 * return loadListMyFeedBackInfo( mapping, theForm, request, response);
		 */
		return null;
	}

	public String loadListAffiriFeedBackInfo(

	) throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		List mlst = null;
		String login_id = ""; // 登录人ID
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		login_id = session.get("account").toString(); // 取得登录用户ID;
		TbCloud2FeedBackObj obj = new TbCloud2FeedBackObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setSENTTO_EMPLOYE(login_id);
		obj.setTITLE(pfbForm.getTITLE());
		mlst = publishFeedBackService.findListAffiriFeedBackInfo(obj); //
		pfbForm.setResultList(mlst);
		return SUCCESS;
	}

	public String examineMyHisInfo(

	) throws BaseException {
		/**
		 * PublishFeedBackForm theForm =(PublishFeedBackForm)form; String id =
		 * theForm.getID(); TB_PUBLISH_FeedBackTypeDao
		 * tB_PUBLISH_FeedBackTYPEDao = new TB_PUBLISH_FeedBackTypeDao();
		 * PublishFeedBackForm obj =
		 * tB_PUBLISH_FeedBackTYPEDao.findHisObjByTITLE(id);
		 * request.setAttribute("ID",obj.ID);
		 * request.setAttribute("SUBMIT_TIME",obj.SUBMIT_TIME);
		 * request.setAttribute("TITLE",obj.TITLE);
		 * request.setAttribute("TYPE_ID",obj.TYPE_ID);
		 * request.setAttribute("DF_INFO",obj.DF_INFO);
		 * request.setAttribute("SENTTO_EMPLOYE",obj.SENTTO_EMPLOYE);
		 * request.setAttribute("LOGIN_ID",obj.LOGIN_ID);
		 * request.setAttribute("IF_AFFIRM",obj.IF_AFFIRM);
		 * request.setAttribute("ENABLE", obj.ENABLE); //
		 */
		return SUCCESS;
	}

	// 确认时添加确认信息
	public String confirmFeedBackInfo(

	) throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		PublishFeedBackForm obj = publishFeedBackService.findObjByID(ID);
		pfbForm.setSUBMIT_TIME(obj.SUBMIT_TIME);
		pfbForm.setTITLE(obj.TITLE);
		pfbForm.setTYPE_ID(obj.TYPE_ID);
		pfbForm.setDF_INFO(obj.DF_INFO);
		pfbForm.setSENTTO_EMPLOYE(obj.SENTTO_EMPLOYE);
		pfbForm.setLOGIN_ID(obj.LOGIN_ID);
		pfbForm.setHF_INFO(obj.HF_INFO);
		pfbForm.setID(ID);
		return "confirm";

	}

	/**
	 * 获得某厂商上传文件目录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String getUserListByGroupId(

	) throws BaseException {
		if (pfbForm == null) {
			pfbForm = new PublishFeedBackForm();
		}
		String group_id = Struts2Utils.getRequest().getParameter("GROUP_ID");
		List userList = publishFeedBackService.getUserListByGroupId(group_id);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (userList.size() > 0) {
			for (int l = 0; l < userList.size(); l++) {
				if (l != 0) {
					sb.append(",");
				}
				Map map = (Map) userList.get(l);
				sb.append("'");
				sb.append(map.get("SENTTO_EMPLOYE"));
				sb.append("':'");
				sb.append(map.get("NAME"));
				sb.append("'");
			}
		}
		sb.append("}");
		Struts2Utils.getResponse().setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.sitech.basd.yicloud.web.datacenter.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.web.datacenter.form.DataCenterForm;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

public class DataCenterAction extends CRUDBaseAction {
	private DataCenterForm theForm;
	private EntityTreeService entityTreeService;

	public EntityTreeService getEntityTreeService() {
		return entityTreeService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public DataCenterForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DataCenterForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title: addDataCenter
	 * @Description: 增加数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 */
	public String addDataCenter() {
		return "addDataCenter";
	}

	/**
	 * @Title: saveDataCenter
	 * @Description: 保存数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 */

	public String saveDataCenter() throws Exception {
		EntityTreeObj obj = new EntityTreeObj();
		String name = Struts2Utils.getRequest().getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		obj.setName(name);
		obj.setType("8");
		obj.setParentId(1);
		int ret;
		String url = "/vmware/dc/create/[dcName:" + name + "]/";
		String dcResult = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(dcResult);
		String result = (String) ps.get(ResponseCode.RESPONSE_CODE);
		if (result.equals(ResponseCode.SUCCESS)) {// 调用接口创建数据中心后插入数据
			String code = (String) ps.get(ResponseCode.CODE);// code，唯一标识
			obj.setEntityId(code);
			/** 通知 */
			try {
				NoticeUtil.getInstance().addCMDBDC(code, Operation.OPER_ADD);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().addDatacenter(code);
			} catch (Exception ex) {

			}
			ret = entityTreeService.insertTreeNode(obj);
		}
		String json = "{\"result\":" + result + "}";
		Struts2Utils.getResponse().getWriter().print(json);
		return null;
	}

	/**
	 * @Title: deleteDataCenter
	 * @Description:删除数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws Exception
	 */
	public String deleteDataCenter() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 节点Id
		// String name = request.getParameter("name");//节点名称，即数据中心名称
		String entityId = request.getParameter("entityId");// 实体Id，即code
		String url = "/vmware/dc/delete/[dcName:" + entityId + "]/";
		String deleteResult = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(deleteResult);
		String result = (String) ps.get(ResponseCode.RESPONSE_CODE);
		if (result.equals(ResponseCode.SUCCESS)) {// 调用接口删除数据成功后删除数据
			/** 通知 */
			try {
				NoticeUtil.getInstance().delCMDBDC(entityId, Operation.OPER_DEL);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().delDatacenter(entityId);
			} catch (Exception ex) {

			}
			EntityTreeObj obj = new EntityTreeObj();
			obj.setId(Integer.parseInt(id));
			entityTreeService.delTreeNode(obj);
		}
		String json = "{\"result\":" + result + "}";
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}
}

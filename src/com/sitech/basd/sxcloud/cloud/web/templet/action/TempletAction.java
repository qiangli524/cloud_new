package com.sitech.basd.sxcloud.cloud.web.templet.action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletTypeConstant;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletTypeObj;
import com.sitech.basd.sxcloud.cloud.service.templet.TempletService;
import com.sitech.basd.sxcloud.cloud.web.templet.form.TempletForm;
import com.sitech.basd.sxcloud.cloud.web.templet.form.TempletTypeForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;
import com.sitech.basd.yicloud.web.vmman.form.VMManagerForm;

public class TempletAction extends CRUDBaseAction {
	private TempletForm theForm;
	private VMManagerForm theTempletForm;
	private TempletTypeForm theTypeForm;
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TempletForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TempletForm theForm) {
		this.theForm = theForm;
	}

	public TempletTypeForm getTheTypeForm() {
		return theTypeForm;
	}

	public void setTheTypeForm(TempletTypeForm theTypeForm) {
		this.theTypeForm = theTypeForm;
	}

	public VMManagerForm getTheTempletForm() {
		return theTempletForm;
	}

	public void setTheTempletForm(VMManagerForm theTempletForm) {
		this.theTempletForm = theTempletForm;
	}

	public TempletService getTempletService() {
		return templetService;
	}

	/**
	 * @Title:查询出所有模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		if (theForm.getTEM_ID() != null && !"".equals(theForm.getTEM_ID())) {
			obj.setTEM_ID(theForm.getTEM_ID().trim());
		}
		if (theForm.getTEM_NAME() != null && !"".equals(theForm.getTEM_NAME())) {
			obj.setTEM_NAME(theForm.getTEM_NAME().trim());
		}
		if (theForm.getTYPE() != "0" && !"0".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}
		List typeList = templetService.queryTypeListByObj(obj);
		theForm.setTypeList(typeList);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetList = templetService.queryForListByObj(obj);
		theForm.setResultList(templetList);
		return SUCCESS;
	}

	/**
	 * @Title:增加模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String addTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		List typeList = templetService.queryTypeListByObj(obj);
		theForm.setTypeList(typeList);
		return ADD;
	}

	/**
	 * @Title:修改模板信息请求
	 * @Copyright: Copyright (c) 20120112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String modTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		String tem_id = request.getParameter("TEM_ID");
		TempletObj obj = new TempletObj();
		obj.setTEM_ID(tem_id);
		// TempletObj tempObj = templetService.queryByObj(obj);
		// String tem_id = request.getParameter("TEM_ID");
		// obj.setTEM_ID(tem_id);
		theForm = templetInfo(theForm, obj);

		return MODIFY;
	}

	/**
	 * @Title:查看模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TempletForm templetInfo(TempletForm theForm, TempletObj obj) {

		TempletObj tempObj = templetService.queryByObj(obj);
		theForm.setTEM_ID(obj.getTEM_ID());
		theForm.setTEM_NAME(tempObj.getTEM_NAME());
		theForm.setTYPE(tempObj.getTYPE());
		theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		theForm.setTEM_DESC(tempObj.getTEM_DESC());
		HashMap map = templetService.queryType(tempObj);
		HashMap kvmap = templetService.getKvMap(tempObj);
		theForm = getForm(
				"com.sitech.basd.sxcloud.cloud.web.templet.form.TempletForm",
				theForm, map, kvmap);

		return theForm;
	}

	/**
	 * @Title:动态设置Form中的显示
	 * @Copyright: Copyright (c) 20120314
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public TempletForm getForm(String classname, TempletForm theForm,
			HashMap map, HashMap kvmap) {
		Class c = null;
		try {
			c = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> set = map.keySet();

		for (String key : set) {
			Object d1 = map.get(key);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = null;
			try {
				f = c.getDeclaredField(key);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}// 返回一个 Field 对象，该对象反映此 Class

			// 对象所表示的类或接口的指定已声明字段。name 参数是一个
			// String，它指定所需字段的简称
			String str = key.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + key.substring(1);// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = null;
			try {
				me = c.getMethod(methodName, f.getType());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}

			// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型
			f.setAccessible(true);
			try {
				me.invoke(theForm, d1);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}// 利用类的一个方法来操纵obj（的一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		Set<String> kvset = kvmap.keySet();

		for (String kvkey : kvset) {
			Object d2 = kvmap.get(kvkey);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = null;
			try {
				f = c.getDeclaredField(kvkey);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}// 返回一个 Field 对象，该对象反映此 Class
			// 对象所表示的类或接口的指定已声明字段。name 参数是一个
			// String，它指定所需字段的简称
			String str = kvkey.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + kvkey.substring(1) + "VALUE";// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = null;
			try {
				me = c.getMethod(methodName, f.getType());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型

			f.setAccessible(true);
			try {
				me.invoke(theForm, d2);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}// 利用类的一个方法来操纵obj（一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		return theForm;

	}

	/**
	 * @Title:查看模板信息
	 * @Copyright: Copyright (c) 20120213
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String lookTempletInfo() throws BaseException {
		// TempletForm theForm = (TempletForm)form;
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		String tem_id = Struts2Utils.getRequest().getParameter("TEM_ID");
		obj.setTEM_ID(tem_id);
		theForm = templetInfo(theForm, obj);
		theForm.setTEM_ID(tem_id);
		return LIST;
	}

	/**
	 * @Title:删除模板信息请求
	 * @Copyright: Copyright (c) 20120112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String delTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		String tem_id = request.getParameter("TEM_ID");
		theForm.setTEM_ID(tem_id);
		TempletObj obj = new TempletObj();
		// TempletObj tempOper = new TempletObj();
		obj.setTEM_ID(theForm.getTEM_ID());
		// tempOper.setTEM_ID(theForm.getTEM_ID());
		TempletObj tempObj = templetService.queryByObj(obj);
		obj.setTEM_NAME(tempObj.getTEM_NAME());
		obj.setTYPE(tempObj.getTYPE());
		obj.setRELEASE_FLAG(tempObj.getRELEASE_FLAG());
		obj.setTEM_DESC(tempObj.getTEM_DESC());
		int result = 0;
		try {
			BeanUtils.copyProperties(tempObj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = templetService.deleteByObj(tempObj);
		if (ret > 0) {
			result = 1;
		}
		// TbSysOperationLogObj operObj =
		// this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		// operObj.setOPERTYPE(2);
		// operObj.setMESSAGE("删除功能信息");
		// operObj.setREMARK("");
		// operObj.setRESULT(result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);//写操作日志

		obj.setOPERTYPE("2");
		obj.setMESSAGE("删除模板");
		obj.setRESULT(Integer.toString(result));
		int oper = templetService.insertHisByObj(obj);// 将操作模板写入操作模板的日志
		return REDIRECT;
	}

	/**
	 * @Title:保存模板信息请求
	 * @Copyright: Copyright (c) 20120112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		// TempletObj obj = new TempletObj();
		TempletObj obj = saveObj(theForm);
		// theForm.setFlag(theForm.getFlag());
		obj.setTEM_ID(theForm.getTEM_ID());
		obj.setTEM_NAME(theForm.getTEM_NAME());
		obj.setTEM_DESC(theForm.getTEM_DESC());
		obj.setTYPE(theForm.getTYPE());
		/**
		 * try { BeanUtils.copyProperties(obj, theForm); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (InvocationTargetException e) { e.printStackTrace(); }
		 */
		// TbSysOperationLogObj operObj =
		// this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		// operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		// if(theForm.getFlag()==0){
		obj.setRELEASE_FLAG("0");
		ret = templetService.insertByObj(obj);
		// operObj.setOPERTYPE(1);
		// operObj.setMESSAGE("新增功能信息");
		obj.setOPERTYPE("0");
		obj.setMESSAGE("添加模板");
		/*
		 * }else{ ret = templetService.updateByObj(obj); operObj.setOPERTYPE(3);
		 * operObj.setMESSAGE("修改功能信息"); obj.setOPERTYPE("1");
		 * obj.setMESSAGE("修改模板"); }
		 */
		if (ret > 0) {
			result = 1;
		}
		// operObj.setRESULT(result);
		obj.setRESULT(Integer.toString(result));
		@SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		int oper = templetService.insertHisByObj(obj);// 将操作模板写入操作模板的日志
		return REDIRECT;
	}

	/**
	 * @Title:保存用户在页面输入的相应的信息
	 * @Copyright: Copyright (c) 20120210
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TempletObj saveObj(TempletForm theForm) {
		TempletObj obj = new TempletObj();
		// obj.setTYPE(theForm.getTYPE());
		int n = 1;
		String KV = "KV";
		for (int i = 0; i < theForm.getCONFIG_NAME().length; i++) {
			KV = KV + n;
			n++;
			KV = theForm.getCONFIG_NAME()[i] + ":"
					+ theForm.getCONFIG_VALUE()[i];
			switch (n - 1) {
			case 1:
				obj.setKV1(KV);
				break;
			case 2:
				obj.setKV2(KV);
				break;
			case 3:
				obj.setKV3(KV);
				break;
			case 4:
				obj.setKV4(KV);
				break;
			case 5:
				obj.setKV5(KV);
				break;
			case 6:
				obj.setKV6(KV);
				break;
			case 7:
				obj.setKV7(KV);
				break;
			case 8:
				obj.setKV8(KV);
				break;
			case 9:
				obj.setKV9(KV);
				break;
			case 10:
				obj.setKV10(KV);
				break;
			case 11:
				obj.setKV11(KV);
				break;
			case 12:
				obj.setKV12(KV);
				break;
			case 13:
				obj.setKV13(KV);
				break;
			case 14:
				obj.setKV14(KV);
				break;
			case 15:
				obj.setKV15(KV);
				break;
			case 16:
				obj.setKV16(KV);
				break;
			case 17:
				obj.setKV17(KV);
				break;
			case 18:
				obj.setKV18(KV);
				break;
			case 19:
				obj.setKV19(KV);
				break;
			case 20:
				obj.setKV20(KV);
				break;
			}
		}
		obj.setKV_NUM(n - 1);
		return obj;
	}

	/**
	 * @Title:保存修改的模板信息请求
	 * @Copyright: Copyright (c) 20120112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveModTempletInfo() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		obj.setTEM_ID(theForm.getTEM_ID());
		// TempletObj obj = saveObj(theForm);
		// theForm.setFlag(theForm.getFlag());
		// theForm.setTEM_ID(theForm.getTEM_ID());
		/*
		 * try { BeanUtils.copyProperties(obj, theForm); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (InvocationTargetException e) { e.printStackTrace(); }
		 */
		// TbSysOperationLogObj operObj =
		// this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		// operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		/*
		 * if(theForm.getFlag()==0){ ret = templetService.updateByObj(obj);
		 * operObj.setOPERTYPE(1); operObj.setMESSAGE("新增功能信息"); }else{ ret =
		 * templetService.updateByObj(obj); operObj.setOPERTYPE(3);
		 * operObj.setMESSAGE("修改功能信息"); }
		 */
		obj = saveModInfo(obj, theForm);
		obj.setTEM_NAME(theForm.getTEM_NAME());
		obj.setTEM_DESC(theForm.getTEM_DESC());
		ret = templetService.updateByObj(obj);
		// operObj.setOPERTYPE(3);
		// operObj.setMESSAGE("修改功能信息");
		obj.setOPERTYPE("1");
		obj.setMESSAGE("修改模板");
		if (ret > 0) {
			result = 1;
		}
		// operObj.setRESULT(result);
		obj.setRESULT(Integer.toString(result));
		@SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		int oper = templetService.insertHisByObj(obj);// 将操作模板写入操作模板的日志
		return REDIRECT;
	}

	/**
	 * @Title:修改模板信息时保存的模板中的信息
	 * @Copyright: Copyright (c) 20120315
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TempletObj saveModInfo(TempletObj obj, TempletForm theForm)
			throws BaseException {
		HashMap map = templetService.queryType(obj); // 查询模板的类型
		try {
			getObj(
					"com.sitech.basd.sxcloud.cloud.web.templet.form.TempletForm",
					"com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj",
					obj, theForm, map);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public TempletObj getObj(String classname1, String classname2,
			TempletObj obj, TempletForm theForm, HashMap map)
			throws java.lang.ClassNotFoundException,
			java.lang.InstantiationException, java.lang.IllegalAccessException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException {

		Class formClass = Class.forName(classname1);// 传入Form
		Class beanClass = Class.forName(classname2); // 传入TempletObj

		Set<String> set = map.keySet();
		int n = 1;
		Field[] fields = formClass.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) { // 得到Form的所有getter方法

			fields[i].setAccessible(true);
			String fieldname = fields[i].getName();
			String up = fieldname.charAt(0) + "";
			up = up.toUpperCase(); // 将第一个字母转化成大写
			String mName = "get" + up + fieldname.substring(1);
			String str = up + fieldname.substring(1);
			Method me1 = formClass.getMethod(mName, new Class[] {});
			for (String key : set) {
				if (str.equals(key + "VALUE")) {
					Object o = me1.invoke(theForm, new Object[] {});
					if (o != null) {
						o.toString();
						Field kvField = formClass.getDeclaredField(key);
						String kvMethod = "set" + "KV" + n;
						Method setkv = beanClass.getMethod(kvMethod, kvField
								.getType()); // 得到kv的方法
						setkv.invoke(obj, key + ":" + o); // 将字符串拼接后设置到kv中
						n++;
					}
				}
			}

		}
		return obj;
	}

	/**
	 * @Title:查询发布模板信息
	 * @Copyright: Copyright (c) 20120213
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @param release
	 */
	public String listReleaseTemplet() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		String tem_id = request.getParameter("TEM_ID");
		TempletObj obj = new TempletObj();
		obj.setTEM_ID(tem_id);
		if (theForm.getTEM_ID() != null && !"".equals(theForm.getTEM_ID())) {
			obj.setTEM_ID(theForm.getTEM_ID().trim());
		}
		if (theForm.getTEM_NAME() != null && !"".equals(theForm.getTEM_NAME())) {
			obj.setTEM_NAME(theForm.getTEM_NAME().trim());
		}
		if (theForm.getTYPE() != "0" && !"0".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}
		List typeList = templetService.queryTypeListByObj(obj);
		theForm.setTypeList(typeList);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetList = templetService.queryForListByObj(obj);
		theForm.setResultList(templetList);
		return "listRelease";
	}

	/**
	 * @Title：发布模板信息
	 * @Copyright: Copyright (c) 20120213
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String releaseTemplet() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		String tem_id = request.getParameter("TEM_ID");
		TempletObj obj = new TempletObj();
		theForm.setTEM_ID(tem_id);
		obj.setTEM_ID(theForm.getTEM_ID());
		theForm = templetInfo(theForm, obj);// 调用方法显示模板的详细信息

		return "release";
	}

	/**
	 * @Title：保存发布模板信息
	 * @Copyright: Copyright (c) 20120213
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveReleaseTemplet() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		obj.setRELEASE_FLAG(theForm.getRELEASE_FLAG());
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		int ret = 0;
		int result = 0;

		ret = templetService.releaseByObj(obj);
		if (theForm.getRELEASE_FLAG().equals("1")) {
			obj.setOPERTYPE("3");
			obj.setMESSAGE("发布模板");
		}
		if (theForm.getRELEASE_FLAG().equals("0")) {
			obj.setOPERTYPE("5");
			obj.setMESSAGE("取消发布模板");
		}
		if (theForm.getRELEASE_FLAG().equals("2")) {
			obj.setOPERTYPE("4");
			obj.setMESSAGE("废弃模板");
		}

		if (ret > 0) {
			result = 1;
		}
		obj.setRESULT(Integer.toString(result));
		int oper = templetService.insertHisByObj(obj);// 将操作模板写入操作模板的日志
		return "saveRelease";
	}

	/**
	 * @Title:查询可用模板信息
	 * @Copyright: Copyright (c) 20120214
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listAvailableTemplet() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		List typeList = templetService.queryTypeListByObj(obj);
		theForm.setTypeList(typeList);
		if (theForm.getTEM_ID() != null && !"".equals(theForm.getTEM_ID())) {
			obj.setTEM_ID(theForm.getTEM_ID().trim());
		}
		if (theForm.getTEM_NAME() != null && !"".equals(theForm.getTEM_NAME())) {
			obj.setTEM_NAME(theForm.getTEM_NAME().trim());
		}
		if (theForm.getTYPE() != "0" && !"0".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}
		obj.setRELEASE_FLAG("1");
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetList = templetService.queryForListByObj(obj);
		theForm.setResultList(templetList);
		return "available";
	}

	/**
	 * @Title:查询操作模板的历史信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listTempletHis() throws BaseException {
		if (theForm == null) {
			theForm = new TempletForm();
		}
		TempletObj obj = new TempletObj();
		if (theForm.getTEM_ID() != null && !"".equals(theForm.getTEM_ID())) {
			obj.setTEM_ID(theForm.getTEM_ID().trim());
		}
		if (theForm.getTEM_NAME() != null && !"".equals(theForm.getTEM_NAME())) {
			obj.setTEM_NAME(theForm.getTEM_NAME().trim());
		}
		if (theForm.getTYPE() != "0" && !"0".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}
		List typeList = templetService.queryTypeListByObj(obj);
		theForm.setTypeList(typeList);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetList = templetService.queryHisForListByObj(obj);
		theForm.setResultList(templetList);
		return "his";
	}

	/**
	 * 
	 * @Title: 模板编号唯一性判断
	 * @Copyright: Copyright (c) 2012-3-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String uniqueJudgement() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String TEM_ID = request.getParameter("TEM_ID");

		TempletObj obj = new TempletObj();

		obj.setTEM_ID(TEM_ID);
		this.paginater.initPagination(request);
		List list = templetService.queryForListByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (null != list && !list.isEmpty()) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.debug("TempletAction.uniqueJudgement():" + e.getMessage()
					+ getClass().getName());
		}
		return null;
	}

	/**
	 * 
	 * @Title: 查看镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String listTempletType() {
		if (theTypeForm == null) {
			theTypeForm = new TempletTypeForm();
		}
		TempletTypeObj obj = new TempletTypeObj();
		obj.setNAME(theTypeForm.getNAME());
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List lst = templetService.queryTempletTypeList(obj);
		theTypeForm.setResultList(lst);
		return "listTempletType";
	}

	private TempletService templetService;

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}

	/**
	 * 
	 * @Title: 增加镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String addTempletType() {
		return "addTempletType";
	}

	/**
	 * 
	 * @Title: 修改镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String modTempletType() {
		if (theTypeForm == null) {
			theTypeForm = new TempletTypeForm();
		}
		TempletTypeObj obj = new TempletTypeObj();
		obj.setID(theTypeForm.getID());
		List lst = templetService.queryTempletTypeList(obj);
		TempletTypeObj reObj = (TempletTypeObj) lst.get(0);
		theTypeForm.setNAME(reObj.getNAME());
		theTypeForm.setTYPE_DESC(reObj.getTYPE_DESC());
		return "modTempletType";
	}

	/**
	 * 
	 * @Title: 删除镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String delTempletType() {
		if (theTypeForm == null) {
			theTypeForm = new TempletTypeForm();
		}
		TempletTypeObj obj = new TempletTypeObj();
		obj.setID(theTypeForm.getID());
		int ret = templetService.deleteByTempletTypeObj(obj);
		return "delTempletType";
	}

	/**
	 * 
	 * @Title: 保存镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String saveTempletType() {
		if (theTypeForm == null) {
			theTypeForm = new TempletTypeForm();
		}
		TempletTypeObj obj = new TempletTypeObj();
		obj.setNAME(theTypeForm.getNAME());
		obj.setTYPE_DESC(theTypeForm.getTYPE_DESC());
		if (theTypeForm.getID() != null && !"".equals(theTypeForm.getID())) {
			obj.setID(theTypeForm.getID());
		}
		int ret = 0;
		if (theTypeForm.getFlag() == 0) {
			ret = templetService.insertByTempletTypeObj(obj);
		} else {
			ret = templetService.updateByTempletTypeObj(obj);
		}
		return "saveTempletType";
	}

	/**
	 * 
	 * @Title: 显示某个类型对应的所有镜像
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */

	public String listAllTempletOfType() {
		if (theTempletForm == null) {
			theTempletForm = new VMManagerForm();
		}
		VMHostObj obj = new VMHostObj();
		obj.setVH_TYPE(TempletTypeConstant.VMWAREIMAGE);
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		obj.setTEMPLET_TYPE(id);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List lst = templetService.queryForListByImageType(obj);
		theTempletForm.setResultList(lst);
		String name = request.getParameter("NAME");
		theTempletForm.setNAME(name);
		return "listAllTempletOfType";
	}

	/**
	 * 
	 * @Title: 显示所有镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String listAllTemplet() {
		if (theTempletForm == null) {
			theTempletForm = new VMManagerForm();
		}
		id = Struts2Utils.getRequest().getParameter("ID");
		VMHostObj obj = new VMHostObj();
		obj.setVH_TYPE(TempletTypeConstant.VMWAREIMAGE);
		obj.setTEMPLET_TYPE(id);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List lst = templetService.queryForListByNOEqualType(obj);
		theTempletForm.setResultList(lst);
		return "listAllTemplet";
	}

	/**
	 * 
	 * @Title: 绑定镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String bindingImage() {
		if (theTempletForm == null) {
			theTempletForm = new VMManagerForm();
		}
		id = Struts2Utils.getRequest().getParameter("ID");
		VMHostObj obj = new VMHostObj();
		obj.setID(theTempletForm.getID());
		obj.setTEMPLET_TYPE(id);
		int ret = templetService.bindingImage(obj);
		return "bindingImage";
	}

	/**
	 * 
	 * @Title: 取消绑定镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String cancleBindingImage() {
		if (theTempletForm == null) {
			theTempletForm = new VMManagerForm();
		}
		id = Struts2Utils.getRequest().getParameter("ID");
		name = Struts2Utils.getRequest().getParameter("NAME");
		VMHostObj obj = new VMHostObj();
		obj.setID(theTempletForm.getID());
		obj.setTEMPLET_TYPE(TempletTypeConstant.NONE); // NONE代表不属于任何类型
		int ret = templetService.bindingImage(obj);
		return "cancleBindingImage";
	}
}

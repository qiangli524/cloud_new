package com.sitech.basd.yicloud.web.middleware.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.DownLoadUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;
import com.sitech.basd.yicloud.domain.middleware.MiddlewareObj;
import com.sitech.basd.yicloud.service.middleware.MiddlewareService;
import com.sitech.basd.yicloud.web.middleware.form.MiddlewareForm;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
public class MiddlewareAction extends CRUDBaseAction {
	private MiddlewareForm theForm;
	private String savePath;

	private String middleid;

	public String getMiddleid() {
		return middleid;
	}

	public void setMiddleid(String middleid) {
		this.middleid = middleid;
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	public String getSavePath() throws Exception {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public MiddlewareForm getTheForm() {
		return theForm;
	}

	public void setTheForm(MiddlewareForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:上传中间件
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String addMiddleware() throws BaseException {

		return "addMiddleware";
	}

	/**
	 * @Title:查询出所有的中间件信息
	 * @Copyright: Copyright (c) 20120514
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listMiddlewareInfo() throws BaseException {

		if (theForm == null) {
			theForm = new MiddlewareForm();
		}
		theForm.setID(0);
		MiddlewareObj obj = new MiddlewareObj();
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = middlewareService.queryForListByObj(obj);
		for (int i = 0; i < resultList.size(); i++) {
			MiddlewareObj tempObj = (MiddlewareObj) resultList.get(i);
			String parth = tempObj.getPARTH();
			String name = tempObj.getNAME();
			name = name + parth.substring(parth.lastIndexOf("."));
			tempObj.setNAME(name);
			// try {
			// tempObj.setPARTH(java.net.URLEncoder.encode(String
			// .valueOf(parth), "UTF-8"));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// try {
			// tempObj.setNAME(java.net.URLEncoder.encode(
			// String.valueOf(name), "UTF-8"));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			resultList.set(i, tempObj);
		}
		theForm.setResultList(resultList);
		return "listMiddlewareInfo";

	}

	/**
	 * @Title:保存中间件信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveMiddleware() throws BaseException {
		if (theForm == null) {
			theForm = new MiddlewareForm();
		}
		String b = (String) Struts2Utils.getRequest().getParameter("b");
		MiddlewareObj obj = new MiddlewareObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret = 0;
		int result = 0;
		if (theForm.getID() == 0) {
			if (b == null || "".equals(b)) {
				FileOutputStream fos = null;
				try {
					File file = new File(getSavePath());
					if (!file.isDirectory()) {
						file.mkdir();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					fos = new FileOutputStream(getSavePath() + File.separator
							+ theForm.getFileFileName());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(theForm.getFile());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				byte[] buffer = new byte[1024];
				int len = 0;
				try {
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			if (Boolean.valueOf(b)) {
				String name = null;
				// try {
				// name = new String(Struts2Utils.getRequest().getParameter(
				// "name").getBytes("ISO8859-1"), "UTF-8");
				// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				try {
					name = URLDecoder.decode(Struts2Utils.getRequest().getParameter("name"),
							"utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				try {
					obj.setPARTH(getSavePath() + File.separator + name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ret = middlewareService.insertByObj(obj);
				if (ret > 0) {
					TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils
							.getRequest());
					operObj.setREMARK("");
					operObj.setOPERTYPE(1);
					operObj.setMESSAGE("新增软件" + obj.getNAME());
					result = 1;
					operObj.setRESULT(result);
					// int retOper = operationService.insertByObj(operObj);//
					// 写操作日志
				}
				// str = "success";
				// }
				// theForm.setSTR(str);
				// if (str != null && str.equals("success")) {
				return "saveMiddleware";
			}
		} else {
			ret = middlewareService.updateByObj(obj);
			return "saveMiddleware";
		}
		return null;
		// return mapping.findForward("default");
	}

	/**
	 * @Title:修改中间件信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String modMiddleware() throws BaseException {
		if (theForm == null) {
			theForm = new MiddlewareForm();
		}
		MiddlewareObj obj = new MiddlewareObj();
		obj.setID(theForm.getID());
		MiddlewareObj tempObj = middlewareService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modMiddleware";
	}

	/**
	 * @Title:删除中间件信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String delMiddleware() throws BaseException {
		if (theForm == null) {
			theForm = new MiddlewareForm();
		}
		MiddlewareObj obj = new MiddlewareObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MiddlewareObj tempObj = middlewareService.queryByObj(obj);
		// 删除文件
		File f = null;
		String fileUrl = tempObj.getPARTH();
		f = new File(fileUrl);
		if (f.exists()) {
			f.delete();
		}
		// 删除数据库中的数据
		int ret = middlewareService.deleteByObj(obj);
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除中间件");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		// int retOper = operationService.insertByObj(operObj);// 写操作日志

		return "delMiddleware";
	}

	private MiddlewareService middlewareService;

	public void setMiddlewareService(MiddlewareService middlewareService) {
		this.middlewareService = middlewareService;
	}

	/**
	 * @Title: downLoadFile
	 * @Description: 下载中间件
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-27 上午12:07:33
	 */
	public void downLoadFile() {
		MiddlewareObj middlewareObj = new MiddlewareObj();
		middlewareObj.setID(Integer.parseInt(middleid));
		middlewareObj = middlewareService.queryByObj(middlewareObj);
		if (middlewareObj != null) {
			String filePath = middlewareObj.getPARTH();
			String fileName = middlewareObj.getNAME();
			fileName = fileName + filePath.substring(filePath.lastIndexOf("."));
			DownLoadUtil.downLoadFile(filePath, fileName, response);
		} else {
			try {
				// PrintWriter pw = response.getWriter();
				// pw.write("没有相关记录");
				// pw.flush();
				// pw.close();
				PrintWriterOut.printWirter(response, "没有相关记录");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

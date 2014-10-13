package com.sitech.basd.yicloud.web.opersystem.action;

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

import com.sitech.basd.sxcloud.rsmu.config.SuffixConstant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.DownLoadUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;
import com.sitech.basd.yicloud.domain.opersystem.OperSystemObj;
import com.sitech.basd.yicloud.service.opersystem.OperSystemService;
import com.sitech.basd.yicloud.web.opersystem.form.OperSystemForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class OperSystemAction extends CRUDBaseAction {
	private OperSystemForm theForm;
	private String savePath;

	private String opersysid;

	public String getOpersysid() {
		return opersysid;
	}

	public void setOpersysid(String opersysid) {
		this.opersysid = opersysid;
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	public String getSavePath() throws Exception {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public OperSystemForm getTheForm() {
		return theForm;
	}

	public void setTheForm(OperSystemForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:上传操作系统
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String addOperSystem() throws BaseException {

		return "addOperSystem";
	}

	/**
	 * @Title:查询出所有的操作信息
	 * @Copyright: Copyright (c) 20120514
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listOperSystemInfo() throws BaseException {
		if (theForm == null) {
			theForm = new OperSystemForm();
		}
		theForm.setID(0);
		OperSystemObj obj = new OperSystemObj();
		if (theForm.getQueryName() != null && !"".equals(theForm.getQueryName())) {
			obj.setNAME(theForm.getQueryName().trim());
		}
		if (theForm.getQUERYTYPE() != null && !"".equals(theForm.getQUERYTYPE())) {
			obj.setTYPE(theForm.getQUERYTYPE());
		}
		if (theForm.getQUERYOPERTYPE() != null && !"".equals(theForm.getQUERYOPERTYPE())) {
			obj.setOPERTYPE(theForm.getQUERYOPERTYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = operSystemService.queryForListByObj(obj);
		for (int i = 0; i < resultList.size(); i++) {
			OperSystemObj tempObj = (OperSystemObj) resultList.get(i);
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
			//
			// tempObj.setNAME(java.net.URLEncoder.encode(
			// String.valueOf(name), "UTF-8"));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			resultList.set(i, tempObj);
		}
		theForm.setResultList(resultList);
		return "listOperSystemInfo";

	}

	/**
	 * @Title:保存操作系统信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveOperSystem() throws BaseException {
		if (theForm == null) {
			theForm = new OperSystemForm();
		}
		try {
			String systemFileName = theForm.getFileFileName();
			int index = systemFileName.lastIndexOf(".");
			String suffix = systemFileName.substring(index + 1);
			int count = 0;
			String[] systemsuffix = SuffixConstant.SOFTWARE.split(",");
			for (String string : systemsuffix) {
				if (string.equals(suffix)) {
					count++;
					break;
				}
			}
			if (count < 1) {
				return null;
			}
		} catch (Exception e) {

		}

		String b = (String) Struts2Utils.getRequest().getParameter("b");
		OperSystemObj obj = new OperSystemObj();
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
				try {
					// name = new String(Struts2Utils.getRequest().getParameter(
					// "name").getBytes("ISO8859-1"), "UTF-8");
					name = URLDecoder.decode(Struts2Utils.getParameter("name"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					obj.setPARTH(getSavePath() + File.separator + name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ret = operSystemService.insertByObj(obj);
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
				return "saveOperSystem";
			}
		} else {
			ret = operSystemService.updateByObj(obj);
			return "saveOperSystem";
		}
		return null;
		// return mapping.findForward("default");
	}

	/**
	 * @Title:修改操作信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String modOperSystem() throws BaseException {
		if (theForm == null) {
			theForm = new OperSystemForm();
		}
		OperSystemObj obj = new OperSystemObj();
		obj.setID(theForm.getID());
		OperSystemObj tempObj = operSystemService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "modOperSystem";
	}

	/**
	 * @Title:删除操作信息
	 * @Copyright: Copyright (c) 201205
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String delOperSystem() throws BaseException {
		if (theForm == null) {
			theForm = new OperSystemForm();
		}
		OperSystemObj obj = new OperSystemObj();
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
		OperSystemObj tempObj = operSystemService.queryByObj(obj);
		// 删除文件
		File f = null;
		String fileUrl = tempObj.getPARTH();
		f = new File(fileUrl);
		if (f.exists()) {
			f.delete();
		}

		int ret = operSystemService.deleteByObj(obj);

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除操作系统文件" + obj.getID());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		// int retOper = operationService.insertByObj(operObj);// 写操作日志

		return "delOperSystem";
	}

	private OperSystemService operSystemService;

	public void setOperSystemService(OperSystemService operSystemService) {
		this.operSystemService = operSystemService;
	}

	/**
	 * @Title: downLoadFile
	 * @Description: 文件下载
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-27 上午12:16:24
	 */
	public void downLoadFile() {
		OperSystemObj operSystemObj = new OperSystemObj();
		operSystemObj.setID(Integer.parseInt(opersysid));
		operSystemObj = operSystemService.queryByObj(operSystemObj);
		if (operSystemObj != null) {
			String filePath = operSystemObj.getPARTH();
			String fileName = operSystemObj.getNAME();
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

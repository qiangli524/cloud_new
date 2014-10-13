package com.sitech.basd.sxcloud.cloud.web.fileManager.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileListInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileTypeInfoObj;
import com.sitech.basd.sxcloud.cloud.service.fileManager.FileManagerService;
import com.sitech.basd.sxcloud.cloud.util.CommonMethod;
import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.DownLoadUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 帮助管理
 * 
 * @author dsfcm
 * 
 */
public class FileManagerAction extends BaseAction {
	private static CommonMethod commonMethod;
	static {
		// License.setLicenseDetails("mimsDemon-si-tech", "326-6841-5475-7500");
		commonMethod = new CommonMethod();
	}
	private FileManagerForm theForm;

	public FileManagerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(FileManagerForm theForm) {
		this.theForm = theForm;
	}

	public String FileManagerList(

	) throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2FileTypeInfoObj obj = new TbCloud2FileTypeInfoObj();
		obj.setTYPE_NAME(theForm.getTYPE_NAME());
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = fileManagerService.fileManagerList(obj);
		theForm.setResultList(list);

		return "FileManagerList";

	}

	/**
	 * 展示文件类型新增
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showFileManagerAdd(

	) throws BaseException {

		return "showFileManagerAdd";
	}

	/**
	 * 保存文件类型
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String fileManagerAdd() throws BaseException {

		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletRequest request = Struts2Utils.getRequest();
			// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request
			// .getSession().getAttribute(Constant.USER_SESSION_KEY);
			TbCloud2FileTypeInfoObj obj = new TbCloud2FileTypeInfoObj();
			theForm.setUSER_ID(session.get("account").toString());
			fileManagerService.fileManagerAdd(theForm);
			Struts2Utils.getResponse().sendRedirect("file_FileManagerList.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 展示文件类型更新画面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showFileManagerUpdate() throws BaseException {
		// 取得信息
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		// theForm.setTYPE_ID(theForm.getTEMP_TYPE_ID()[0]);
		List<FileManagerForm> list = fileManagerService.getFileManager(theForm);
		if (list != null && list.size() > 0) {
			/*
			 * try { BeanUtils .copyProperties(theForm, list.get(0)); } catch
			 * (Exception e) { e.printStackTrace(); }
			 */
			theForm.setENABLE(list.get(0).getENABLE());
			theForm.setTYPE_NAME(list.get(0).getTYPE_NAME());

		}
		return "showFileManagerAdd";
	}

	/**
	 * 更新文件类型
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String fileManagerUpdate(

	) throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request
			// .getSession().getAttribute(Constant.USER_SESSION_KEY);
			theForm.setUSER_ID(session.get("account").toString());
			fileManagerService.fileManagerUpdate(theForm);
			Struts2Utils.getResponse().sendRedirect("file_FileManagerList.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断是否可以删除文件类型
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @throws IOException
	 */
	public String isFileTypeDel() throws Exception {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// 接受TYPE_ID参数
		String para_type_id = request.getParameter("TYPE_ID");
		String[] type_id = para_type_id.split(",");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();

		for (int i = 0; i < type_id.length; i++) {
			theForm.setTYPE_ID(type_id[i]);
			String count = fileManagerService.fileManagerCount(theForm);
			if (!"0".equals(count)) {
				List list = fileManagerService.getFileManager(theForm);
				// out.print(((FileManagerForm) list.get(0)).getTYPE_NAME() +
				// "正在使用，不能删除！");
				PrintWriterOut.printWirter(response, ((FileManagerForm) list.get(0)).getTYPE_NAME()
						+ "正在使用，不能删除！");
				break;
			}
		}

		// out.print("");
		PrintWriterOut.printWirter(response, "");
		return null;
	}

	/**
	 * 删除文件类型
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String fileManagerDel() throws BaseException {
		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletResponse response = Struts2Utils.getResponse();
			fileManagerService.fileManagerDelete(theForm);
			response.sendRedirect("file_FileManagerList.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 展示目录列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @author zhangwj
	 * @time 20110211
	 */
	public String showDirectoryList(

	) throws BaseException {
		TbCloud2FileListInfoObj obj = new TbCloud2FileListInfoObj();
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		obj.setLIST_NAME(theForm.getLIST_NAME());
		obj.setSI_ID(theForm.getSI_ID());
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = fileManagerService.dirManagerList(obj);
		List siList = fileManagerService.getAllSiList();
		theForm.setSI_LIST(siList);
		theForm.setResultList(list);
		return "showDirectoryList";
	}

	/**
	 * 展示目录新增页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String dirManagerAdd() throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		List siList = fileManagerService.getAllSiList(); // 获得厂商列表
		theForm.setSI_LIST(siList); // 设置厂商列表
		return "dirManagerAdd";

	}

	/**
	 * 修改目录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String dirManagerUpdate(

	) throws BaseException {
		// 取得信息
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		theForm.setLIST_ID(theForm.getTEMP_TYPE_ID()[0]);
		List list = fileManagerService.getDirectoryInfo(theForm);
		if (!list.isEmpty()) {
			try {
				BeanUtils.copyProperties(theForm, (FileManagerForm) list.get(0));// 对象拷贝
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		theForm.setCOMMAND("update");// 更新操作
		List siList = fileManagerService.getAllSiList(); // 获得厂商列表
		theForm.setSI_LIST(siList); // 设置厂商列表
		return "dirManagerUpdate";
	}

	/**
	 * 保存新增、修改的目录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @author zhangwj
	 * @time 20110211
	 */
	public String saveDirectory() throws BaseException {
		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();
			String command = theForm.getCOMMAND();
			String root = fileManagerService.getUploadRootStr(); // 获得服务器上传文件主目录
			theForm.setROOT(root); // 设置服务器上传文件主目录
			// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request
			// .getSession().getAttribute(Constant.USER_SESSION_KEY);
			// 登录用户ID
			theForm.setUSER_ID(session.get("account").toString());
			if (null != command && "update".equals(command)) {
				commonMethod.sftpDirOperation(theForm, "alter"); // SFTP 修改目录
				fileManagerService.directoryUpdate(theForm);// 修改目录
			} else {
				commonMethod.sftpDirOperation(theForm, "add"); // SFTP 新建目录
				fileManagerService.directoryAdd(theForm);// 新增目录
			}
			response.sendRedirect("file_showDirectoryList.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除目录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @author zhangwj
	 * @time 20110211
	 */
	public String dirManagerDel() throws BaseException {
		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletResponse response = Struts2Utils.getResponse();
			String root = fileManagerService.getUploadRootStr();
			theForm.setROOT(root);

			// SFTP 删除目录
			commonMethod.sftpDirOperation(theForm, "delete");
			// 删除数据库数据

			fileManagerService.directoryDelete(theForm);
			response.sendRedirect("file_showDirectoryList.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断目录是否被使用
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 * @author zhangwj
	 * @time 20110211
	 */
	public String whetherDirIsUsed(

	) throws BaseException {
		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();
			String para_list_id = request.getParameter("LIST_ID");
			String[] listId = para_list_id.split(",");
			response.setContentType("text/html; charset=gb2312");
			// PrintWriter out = response.getWriter();

			for (int i = 0; i < listId.length; i++) {
				theForm.setLIST_ID(listId[i]);
				String count = fileManagerService.findDirCount(theForm);
				if (!"0".equals(count)) {
					String listName = fileManagerService.getListName(listId[i]);
					// out.print("目录 " + listName + " 已经使用，不能修改删除！");
					PrintWriterOut.printWirter(response, "目录 " + listName + " 已经使用，不能修改删除！");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 * 帮助文件上传列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showHelpFileList(

	) throws BaseException {
		TbCloud2FileInfoObj obj = new TbCloud2FileInfoObj();
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		obj.setFILE_NAME(theForm.getFILE_NAME());
		obj.setTYPE_ID(theForm.getTYPE_ID());
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = fileManagerService.showHelpFileList(obj);
		theForm.setResultList(list);
		List fileTypeList = fileManagerService.getFileTypeList(); // 获得类型列表
		theForm.setFILETYPELIST(fileTypeList); // 设置类型列表
		return "showHelpFileList";
	}

	/**
	 * 帮助文件添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String helpFileAdd() throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		List fileTypeList = null;
		fileTypeList = fileManagerService.getFileTypeList(); // 获得文件类型列表
		// directoryList = dao.getDirectoryList(); //获得目录列表
		theForm.setFILETYPELIST(fileTypeList);
		// theForm.setDIRECTORYLIST(directoryList);
		List siList = fileManagerService.getAllSiList(); // 获得厂商列表
		theForm.setSI_LIST(siList); // 设置厂商列表
		return "helpFileAdd";

	}

	/**
	 * 帮助文件更新
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String helpFileUpdate() throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setFILE_ID(theForm.getTEMP_TYPE_ID()[0]);
		List list = fileManagerService.getHelpFileInfo(theForm);
		if (!list.isEmpty()) {
			HashMap map = (HashMap) list.get(0);
			theForm.setVERSION(String.valueOf(map.get("VERSION")).trim());
			theForm.setLIST_ID(String.valueOf(map.get("LIST_ID")).trim());
			theForm.setTYPE_ID(String.valueOf(map.get("TYPE_ID")));
			theForm.setFILE_DESC(String.valueOf(map.get("FILE_DESC")));
			theForm.setOLD_FILE_DIR(String.valueOf(map.get("OLD_FILE_DIR")));
			theForm.setSI_ID(String.valueOf(map.get("SI_ID")).trim());
			// 保持值
			request.setAttribute("LIST_ID", theForm.getLIST_ID());
		}
		List fileTypeList = null;
		List directoryList = null;
		fileTypeList = fileManagerService.getFileTypeList(); // 获得文件类型列表
		directoryList = fileManagerService.getDirectoryList(); // 获得目录列表
		theForm.setFILETYPELIST(fileTypeList);
		theForm.setDIRECTORYLIST(directoryList);
		List siList = fileManagerService.getAllSiList(); // 获得厂商列表
		theForm.setSI_LIST(siList); // 设置厂商列表
		theForm.setCOMMAND("update");// 更新操作
		return "helpFileAdd";

	}

	/**
	 * 保存新增、修改的帮助文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String helpFileSave() throws BaseException {
		try {
			if (theForm == null) {
				theForm = new FileManagerForm();
			}
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();

			String root = fileManagerService.getUploadRootStr(); // 获得服务器上传文件主目录
			theForm.setROOT(root); // 设置服务器上传文件主目录

			// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request
			// .getSession().getAttribute(Constant.USER_SESSION_KEY);

			// 登录用户ID
			theForm.setUSER_ID(session.get("account").toString());
			String command = theForm.getCOMMAND();
			String flag = theForm.getFLAG();
			File file = theForm.getFile(); // 获得上传文件
			// String sequence = fileManagerService.helpFileSequence();
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			String fileName = sdf.format(new Date())
			// + sequence
					+ theForm.getFileFileName().substring(
							theForm.getFileFileName().lastIndexOf("."));// 获得上传文件名
			String listId = theForm.getLIST_ID();
			String[] idName = listId.split("_");
			// 目录相关
			theForm.setLIST_ID(idName[0]);
			theForm.setLIST_NAME(idName[1]);

			String fileDir = fileManagerService.getUploadRootStr() + theForm.getLIST_NAME()
					+ System.getProperty("file.separator") + fileName;// 获得上传文件后路径

			theForm.setFILE_NAME(theForm.getFileFileName()); // 设置上传文件的名称
			// 16:26:39123_aaaa.txt
			theForm.setFILE_DIR(fileDir);
			if (null != command && "update".equals(command)) { // 更新上传文件
				// 修改上传文件
				if ("YES".equals(flag)) { // 覆盖当前版本，无历史版本生成
					// 更新当前版本
					theForm.setVERSION(theForm.getVERSION() + 1);
					fileManagerService.updateHelpFile(theForm);
					// SFTP更新服务器上传文件
					commonMethod.sftpFileOperation(theForm, "update");
				} else if ("NO".equals(flag)) { // 生成历史版本，把更新前的版本移入历史版本
					// 把当前记录从TB_FILE 表 转移到 TB_HIS_FILE表
					fileManagerService.addHelpFileHis(theForm);
					// 把最新上传的版本更新到TB_FILE表中
					fileManagerService.updateHelpFile(theForm);
					commonMethod.sftpFileOperation(theForm, "upload");
				}
			} else { // 新增上传文件
				// 新增上传文件
				fileManagerService.helpFileAdd(theForm);// 添加上传文件
				commonMethod.sftpFileOperation(theForm, "upload"); // SFTP
				// 上传到服务器
			}
			response.sendRedirect("file_showHelpFileList.do");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 删除上传文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String helpFileDelete() throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		String root = fileManagerService.getUploadRootStr(); // 获得服务器上传文件主目录
		theForm.setROOT(root); // 设置服务器上传文件主目录
		// SFTP 删除上传文件
		commonMethod.sftpFileOperation(theForm, "delete");
		fileManagerService.deleteHelpFile(theForm);
		return showHelpFileList();
	}

	/**
	 * 查看历史版本文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showHelpFileHisList(

	) throws BaseException {
		TbCloud2FileInfoObj obj = new TbCloud2FileInfoObj();
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		obj.setFILE_ID(theForm.getFILE_ID());
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = fileManagerService.showHelpFileHisList(obj);
		theForm.setResultList(list);
		return SUCCESS;
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
	public String getHelpFileDir() throws BaseException {
		if (theForm == null) {
			theForm = new FileManagerForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String si_id = request.getParameter("SI_ID");
		List siList = fileManagerService.getHelpFileDirList("getHelpFileDirList", si_id);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (siList.size() > 0) {
			for (int l = 0; l < siList.size(); l++) {
				if (l != 0) {
					sb.append(",");
				}
				Map map = (Map) siList.get(l);
				sb.append("'");
				sb.append(map.get("LIST_ID"));
				sb.append("':'");
				sb.append(map.get("LIST_NAME"));
				sb.append("'");
			}
		}
		sb.append("}");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
		// out.println(sb.toString());
		PrintWriterOut.printWirter(response, sb.toString());
		return null;
	}

	/**
	 * 进入文件下载页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showDownloadHelpFile(

	) throws BaseException {

		/*
		 * if (theForm == null) { theForm = new FileManagerForm(); }
		 * FileManagerDao dao = new FileManagerDao();
		 * dao.initPagination(this.paginater.initPagination(Struts2Utils
		 * .getRequest())); List resultList = null; resultList =
		 * dao.showHelpFileList(theForm);
		 * 
		 * theForm.setResultList(resultList); List siList = dao.getAllSiList();
		 * //获得厂商列表 theForm.setSI_LIST(siList); //设置厂商列表 List fileTypeList =
		 * dao.getFileTypeList(); //获得类型列表
		 * theForm.setFILETYPELIST(fileTypeList); //设置类型列表
		 */
		return SUCCESS;
	}

	private FileManagerService fileManagerService;

	public void setFileManagerService(FileManagerService fileManagerService) {
		this.fileManagerService = fileManagerService;
	}

	/*---------------------文件下载 lipp------------------------*/
	private String fileid;

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	/**
	 * @Title: downLoadFile
	 * @Description: 下载文件
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午11:45:58
	 */
	public void downLoadFile() {
		TbCloud2FileInfoObj tbCloud2FileInfoObj = new TbCloud2FileInfoObj();
		tbCloud2FileInfoObj.setFILE_ID(fileid);
		List<TbCloud2FileInfoObj> list = fileManagerService.queryFileInfoByObj(tbCloud2FileInfoObj);
		if (list.size() > 0) {
			tbCloud2FileInfoObj = list.get(0);
			String filePath = tbCloud2FileInfoObj.getFILE_DIR();
			String fileName = tbCloud2FileInfoObj.getFILE_NAME();
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

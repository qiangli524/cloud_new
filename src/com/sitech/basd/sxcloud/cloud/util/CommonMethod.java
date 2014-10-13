package com.sitech.basd.sxcloud.cloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
 * 存放共同的方法
 * 
 * @author zhangwj
 * @time 20101019
 * 
 */
public class CommonMethod extends BaseDao {

	private static Log log = LogFactory.getLog(CommonMethod.class);

	/**
	 * 目录管理
	 * @param theForm
	 * @param order
	 */
	public void sftpDirOperation(FileManagerForm theForm, String order) {
		File f = null;
		try {
			//使用SFTP进行文件上传下载操作

			String root = theForm.getROOT();
			//判断根目录是否存在，如果不存在就建立
			f = new File(root);
			if (!f.isDirectory()) {
				f.mkdir();
			}
			//ftp.chdir(root);
			if ("add".equals(order)) { //创建目录
				log.info(root + theForm.getLIST_NAME());
				f = new File(root + theForm.getLIST_NAME());
				f.mkdir();

			} else if ("delete".equals(order)) { //删除目录
				String[] temp_list_id = theForm.getTEMP_TYPE_ID();
				String listId = "";
				String listName = "";
				for (int i = 0; i < temp_list_id.length; i++) {
					listId = temp_list_id[i];
					listName = String.valueOf(getSqlMap().queryForObject(
							"FileManager.getListName", listId));
					f = new File(root + listName);
					if (f.isDirectory()) {
						f.delete();
					}
				}

			} else if ("alter".equals(order)) {//更新操作,采用先删后添加的方式
				try {
					String oldListName = theForm.getOLD_LIST_NAME();
					String listName = theForm.getLIST_NAME();
					if (!listName.equals(oldListName)) {
						//删除目录
						f = new File(root + oldListName);
						if (f.isDirectory()) {
							f.delete();
						}

						//新创建目录
						f = new File(root + listName);
						f.mkdir();

					}
				} catch (Exception e) {
					log.error("getProerties failed:" + e);
				}
			}

			log.info("File operation succeed!");
		} catch (Exception ex) {
			log.info("File operation failed!");
		}

	}

	/**
	 * 帮助管理的文件上传
	 * @param theForm
	 * @param order
	 */
	public void sftpFileOperation(FileManagerForm theForm, String order) {
		InputStream is = null;
		OutputStream os = null;
		try {
			File f = null;
			//使用SFTP进行文件上传下载操作
			String root = theForm.getROOT();
			String separator = System.getProperty("file.separator");

			if ("upload".equals(order)) {//上传文件
				File file = theForm.getFile();
				String filename = theForm.getFileFileName();
				if (!"".equals(filename)) {

					//String fileName= theForm.getFILE_NAME();
					String temp = theForm.getFILE_DIR();

					//	is = file.getInputStream();
					is = new FileInputStream(theForm.getFile());
					os = new FileOutputStream(temp);

					int bytes = 0;
					byte[] buffer = new byte[1024];
					while ((bytes = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytes);
					}
				}
			} else if ("delete".equals(order)) {//删除文件
				String[] fileIds = theForm.getTEMP_TYPE_ID();

				for (int i = 0; i < fileIds.length; i++) {
					List list = getSqlMap().queryForList(
							"FileManager.getFileInfo", fileIds[i]); //获得某FILE_ID的文件列表
					if (null == list || list.size() == 0)
						continue;
					for (int j = 0; j < list.size(); j++) {
						String fileUrl = String.valueOf(
								((HashMap) list.get(j)).get("FILE_DIR")).trim();
						f = new File(fileUrl);
						if (f.exists()) {
							f.delete();
						}
					}
				}

			} else if ("update".equals(order)) {//更新操作,采用先删后曾的方式
				File file = theForm.getFile();
				String newfileName = theForm.getFileFileName();
				if (null != newfileName && !"".equals(newfileName)) {//判断是否上传文件
					if (null != theForm.getOLD_FILE_DIR()
							&& !"".equals(theForm.getOLD_FILE_DIR())) {
						log.info("==" + theForm.getOLD_FILE_DIR());
						f = new File(theForm.getOLD_FILE_DIR());
						if (f.exists()) {
							f.delete();
						}

					}
					//	String fileName= theForm.getFILE_NAME();
					String temp = theForm.getFILE_DIR();
					log.info(temp);
					//	is = file.getInputStream();
					is = new FileInputStream(theForm.getFile());
					os = new FileOutputStream(temp);

					int bytes = 0;
					byte[] buffer = new byte[1024];
					while ((bytes = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytes);
					}
				}
			}

			log.info("File operation succeed!");
		} catch (Exception ex) {
			log.error("File operation failed=" + ex.getMessage());
		} finally {
			try {
				os.close();
				is.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}

	}

	/**
	 * 配置常量文件读取
	 * @author zhangwj
	 * @time 20101111
	 */
	public static String getProperties(String propertiesFileName,
			String properties) {
		try {
			java.util.ResourceBundle info = java.util.ResourceBundle
					.getBundle(propertiesFileName);
			return info.getString(properties);
		} catch (Exception e) {
			log.error("getProerties failed:" + e);
		}
		return null;
	}

}

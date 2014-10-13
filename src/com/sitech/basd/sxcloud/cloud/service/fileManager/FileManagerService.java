package com.sitech.basd.sxcloud.cloud.service.fileManager;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileListInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileTypeInfoObj;
import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;

public interface FileManagerService {
	/**
	 * 文件类型列表
	 * 
	 * @param theForm
	 * @return
	 */
	public List fileManagerList(TbCloud2FileTypeInfoObj obj);

	/**
	 * 取得文件具体信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getFileManager(FileManagerForm theForm);

	/**
	 * 保存新增的文件类型
	 * 
	 * @param theForm
	 */
	public void fileManagerAdd(FileManagerForm theForm);

	/**
	 * 更新文件类型
	 * 
	 * @param theForm
	 */
	public void fileManagerUpdate(FileManagerForm theForm);

	/**
	 * 删除类型配置
	 * 
	 * @param theForm
	 */
	public void fileManagerDelete(FileManagerForm theForm);

	/**
	 * 文件上传目录列表
	 * 
	 * @param theForm
	 * @return
	 */
	List dirManagerList(TbCloud2FileListInfoObj obj);

	/**
	 * 取得目录具体信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getDirectoryInfo(FileManagerForm theForm);

	/**
	 * 保存新增目录
	 * 
	 * @param theForm
	 */
	public void directoryAdd(FileManagerForm theForm);

	/**
	 * 更新修改目录
	 * 
	 * @param theForm
	 */
	public void directoryUpdate(FileManagerForm theForm);

	/**
	 * 删除目录
	 * 
	 * @param theForm
	 */
	public void directoryDelete(FileManagerForm theForm);

	/**
	 * 获得 文件上传根目录
	 * 
	 * @return
	 * @author zhangwj
	 * @time 20110212
	 */
	public String getUploadRootStr();

	/**
	 * 判断是否可以删除文件类型
	 * 
	 * @param theForm
	 * @return
	 */
	public String fileManagerCount(FileManagerForm theForm);

	/**
	 * 获得上传文件列表
	 * 
	 * @param theForm
	 * @return
	 */
	public List showHelpFileList(TbCloud2FileInfoObj obj);

	/**
	 * 获得上传文件历史版本列表
	 * 
	 * @param file_id
	 * @return
	 */
	public List showHelpFileHisList(TbCloud2FileInfoObj obj);

	/**
	 * 获得文件类型列表
	 * 
	 * @return
	 */
	public List getFileTypeList();

	/**
	 * 获得目录列表
	 * 
	 * @return
	 */
	public List getDirectoryList();

	/**
	 * 添加上传文件
	 * 
	 * @param theForm
	 */
	public void helpFileAdd(FileManagerForm theForm);

	/**
	 * 上传文件序列
	 * 
	 * @return
	 */
	public String helpFileSequence();

	/**
	 * 删除上传文件
	 * 
	 * @param theForm
	 */
	public void deleteHelpFile(FileManagerForm theForm);

	/**
	 * 获取的上传文件的信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getHelpFileInfo(FileManagerForm theForm);

	/**
	 * 更新上传文件
	 * 
	 * @param theForm
	 */
	public void updateHelpFile(FileManagerForm theForm);

	/**
	 * 把当前记录从TB_FILE 表 转移到 TB_HIS_FILE表
	 * 
	 * @param theForm
	 */
	public void addHelpFileHis(FileManagerForm theForm);

	/**
	 * 判断目录是否使用
	 * 
	 * @param theForm
	 * @return
	 */
	public String findDirCount(FileManagerForm theForm);

	/**
	 * 根据 目录ID获得目录名称
	 * 
	 * @param string
	 * @return
	 */
	public String getListName(String string);

	/**
	 * 获得所有厂商
	 * 
	 * @return
	 */
	public List getAllSiList();

	/**
	 * 获得某一厂商的目录列表
	 * 
	 * @param string
	 * @param si_id
	 * @return
	 */
	public List getHelpFileDirList(String string, String si_id);

	/**
	 * @Title: queryFileInfoByObj
	 * @Description: 查询文件的集合
	 * @param
	 * @return List<TbCloud2FileInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param tbCloud2FileInfoObj 
	 * @createtime 2013-9-26 下午11:50:50
	 */
	public List<TbCloud2FileInfoObj> queryFileInfoByObj(TbCloud2FileInfoObj tbCloud2FileInfoObj);

}

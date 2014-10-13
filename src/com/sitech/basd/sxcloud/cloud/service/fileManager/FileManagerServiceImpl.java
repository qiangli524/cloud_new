package com.sitech.basd.sxcloud.cloud.service.fileManager;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.fileManager.FileManagerDao;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileListInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileTypeInfoObj;
import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class FileManagerServiceImpl extends BaseService implements
		FileManagerService {

	private FileManagerDao fileManagerDao;

	public FileManagerDao getFileManagerDao() {
		return fileManagerDao;
	}

	public void setFileManagerDao(FileManagerDao fileManagerDao) {
		this.fileManagerDao = fileManagerDao;
	}

	public void addHelpFileHis(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.addHelpFileHis(theForm);
	}

	public void deleteHelpFile(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.deleteHelpFile(theForm);
	}

	public List dirManagerList(TbCloud2FileListInfoObj obj) {
		// TODO Auto-generated method stub
		return fileManagerDao.dirManagerList(obj);
	}

	public void directoryAdd(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.directoryAdd(theForm);
	}

	public void directoryDelete(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.directoryDelete(theForm);
	}

	public void directoryUpdate(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.directoryUpdate(theForm);
	}

	public void fileManagerAdd(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.fileManagerAdd(theForm);
	}

	public String fileManagerCount(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		return fileManagerDao.findDirCount(theForm);
	}

	public void fileManagerDelete(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.fileManagerDelete(theForm);
	}

	public List fileManagerList(TbCloud2FileTypeInfoObj obj) {
		// TODO Auto-generated method stub
		return fileManagerDao.fileManagerList(obj);
	}

	public void fileManagerUpdate(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.fileManagerUpdate(theForm);
	}

	public String findDirCount(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		return fileManagerDao.findDirCount(theForm);
	}

	public List getAllSiList() {
		// TODO Auto-generated method stub
		return fileManagerDao.getAllSiList();
	}

	public List getDirectoryInfo(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		return fileManagerDao.getDirectoryInfo(theForm);
	}

	public List getDirectoryList() {
		// TODO Auto-generated method stub
		return fileManagerDao.getDirectoryList();
	}

	public List getFileManager(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		return fileManagerDao.getFileManager(theForm);
	}

	public List getFileTypeList() {
		// TODO Auto-generated method stub
		return fileManagerDao.getFileTypeList();
	}

	public List getHelpFileDirList(String string, String si_id) {
		// TODO Auto-generated method stub
		return fileManagerDao.getHelpFileDirList(string, si_id);
	}

	public List getHelpFileInfo(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		return fileManagerDao.getHelpFileInfo(theForm);
	}

	public String getListName(String string) {
		// TODO Auto-generated method stub
		return fileManagerDao.getListName(string);
	}

	public String getUploadRootStr() {
		// TODO Auto-generated method stub
		return fileManagerDao.getUploadRootStr();
	}

	public void helpFileAdd(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.helpFileAdd(theForm);
	}

	public String helpFileSequence() {
		// TODO Auto-generated method stub
		return fileManagerDao.helpFileSequence();
	}

	public List showHelpFileHisList(TbCloud2FileInfoObj obj) {
		// TODO Auto-generated method stub
		return fileManagerDao.showHelpFileHisList(obj);
	}

	public List showHelpFileList(TbCloud2FileInfoObj obj) {
		// TODO Auto-generated method stub
		return fileManagerDao.showHelpFileList(obj);
	}

	public void updateHelpFile(FileManagerForm theForm) {
		// TODO Auto-generated method stub
		fileManagerDao.updateHelpFile(theForm);
	}

	/**
	 * @Title: queryFileInfoByObj
	 * @Description: 查询文件的集合
	 * @param
	 * @return List<TbCloud2FileInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午11:50:50
	 */
	@Override
	public List<TbCloud2FileInfoObj> queryFileInfoByObj(TbCloud2FileInfoObj tbCloud2FileInfoObj) {
		return fileManagerDao.queryFileInfoByObj(tbCloud2FileInfoObj);
	}

}

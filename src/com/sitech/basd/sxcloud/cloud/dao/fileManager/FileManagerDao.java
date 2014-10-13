package com.sitech.basd.sxcloud.cloud.dao.fileManager;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileListInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileTypeInfoObj;
import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;

/**
 * �������
 * 
 * @author dsfcm
 * 
 */
public interface FileManagerDao {

	/**
	 * �ļ������б�
	 * 
	 * @param theForm
	 * @return
	 */
	public List fileManagerList(TbCloud2FileTypeInfoObj obj);

	/**
	 * ȡ���ļ�������Ϣ
	 * 
	 * @param theForm
	 * @return
	 */
	public List getFileManager(FileManagerForm theForm);

	/**
	 * ����������ļ�����
	 * 
	 * @param theForm
	 */
	public void fileManagerAdd(FileManagerForm theForm);

	/**
	 * �����ļ�����
	 * 
	 * @param theForm
	 */
	public void fileManagerUpdate(FileManagerForm theForm);

	/**
	 * ɾ����������
	 * 
	 * @param theForm
	 */
	public void fileManagerDelete(FileManagerForm theForm);

	/**
	 * �ļ��ϴ�Ŀ¼�б�
	 * 
	 * @param theForm
	 * @return
	 */
	List dirManagerList(TbCloud2FileListInfoObj obj);

	/**
	 * ȡ��Ŀ¼������Ϣ
	 * 
	 * @param theForm
	 * @return
	 */
	public List getDirectoryInfo(FileManagerForm theForm);

	/**
	 * ��������Ŀ¼
	 * 
	 * @param theForm
	 */
	public void directoryAdd(FileManagerForm theForm);

	/**
	 * �����޸�Ŀ¼
	 * 
	 * @param theForm
	 */
	public void directoryUpdate(FileManagerForm theForm);

	/**
	 * ɾ��Ŀ¼
	 * 
	 * @param theForm
	 */
	public void directoryDelete(FileManagerForm theForm);

	/**
	 * ��� �ļ��ϴ���Ŀ¼
	 * 
	 * @return
	 * @author zhangwj
	 * @time 20110212
	 */
	public String getUploadRootStr();

	/**
	 * �ж��Ƿ����ɾ���ļ�����
	 * 
	 * @param theForm
	 * @return
	 */
	public String fileManagerCount(FileManagerForm theForm);

	/**
	 * ����ϴ��ļ��б�
	 * 
	 * @param theForm
	 * @return
	 */
	public List showHelpFileList(TbCloud2FileInfoObj obj);

	/**
	 * ����ϴ��ļ���ʷ�汾�б�
	 * 
	 * @param file_id
	 * @return
	 */
	public List showHelpFileHisList(TbCloud2FileInfoObj obj);

	/**
	 * ����ļ������б�
	 * 
	 * @return
	 */
	public List getFileTypeList();

	/**
	 * ���Ŀ¼�б�
	 * 
	 * @return
	 */
	public List getDirectoryList();

	/**
	 * ����ϴ��ļ�
	 * 
	 * @param theForm
	 */
	public void helpFileAdd(FileManagerForm theForm);

	/**
	 * �ϴ��ļ�����
	 * 
	 * @return
	 */
	public String helpFileSequence();

	/**
	 * ɾ���ϴ��ļ�
	 * 
	 * @param theForm
	 */
	public void deleteHelpFile(FileManagerForm theForm);

	/**
	 * ��ȡ���ϴ��ļ�����Ϣ
	 * 
	 * @param theForm
	 * @return
	 */
	public List getHelpFileInfo(FileManagerForm theForm);

	/**
	 * �����ϴ��ļ�
	 * 
	 * @param theForm
	 */
	public void updateHelpFile(FileManagerForm theForm);

	/**
	 * �ѵ�ǰ��¼��TB_FILE �� ת�Ƶ� TB_HIS_FILE��
	 * 
	 * @param theForm
	 */
	public void addHelpFileHis(FileManagerForm theForm);

	/**
	 * �ж�Ŀ¼�Ƿ�ʹ��
	 * 
	 * @param theForm
	 * @return
	 */
	public String findDirCount(FileManagerForm theForm);

	/**
	 * ��� Ŀ¼ID���Ŀ¼���
	 * 
	 * @param string
	 * @return
	 */
	public String getListName(String string);

	/**
	 * ������г���
	 * 
	 * @return
	 */
	public List getAllSiList();

	/**
	 * ���ĳһ���̵�Ŀ¼�б�
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
	 * @createtime 2013-9-26 下午11:50:50
	 */
	public List<TbCloud2FileInfoObj> queryFileInfoByObj(TbCloud2FileInfoObj tbCloud2FileInfoObj);

}

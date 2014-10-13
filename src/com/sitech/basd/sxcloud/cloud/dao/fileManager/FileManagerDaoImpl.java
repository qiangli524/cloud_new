package com.sitech.basd.sxcloud.cloud.dao.fileManager;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileListInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.fileManager.TbCloud2FileTypeInfoObj;
import com.sitech.basd.sxcloud.cloud.web.fileManager.form.FileManagerForm;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 帮助管理
 * 
 * @author dsfcm
 * 
 */
public class FileManagerDaoImpl extends BaseDao implements FileManagerDao {

	/**
	 * 文件类型列表
	 * 
	 * @param theForm
	 * @return
	 */
	public List fileManagerList(TbCloud2FileTypeInfoObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileManager.FileManagerListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FileManager.FileManagerList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileManager.FileManagerList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 取得文件具体信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getFileManager(FileManagerForm theForm) {

		try {
			return getSqlMap().queryForList("FileManager.getFileManager",
					theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 保存新增的文件类型
	 * 
	 * @param theForm
	 */
	public void fileManagerAdd(FileManagerForm theForm) {

		try {
			getSqlMap().insert("FileManager.fileManagerAdd", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 更新文件类型
	 * 
	 * @param theForm
	 */
	public void fileManagerUpdate(FileManagerForm theForm) {

		try {
			getSqlMap().update("FileManager.fileManagerUpdate", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 删除类型配置
	 * 
	 * @param theForm
	 */
	public void fileManagerDelete(FileManagerForm theForm) {

		try {
			//getSqlMap().startTransaction();
			for (int i = 0; i < theForm.getTEMP_TYPE_ID().length; i++) {
				theForm.setTYPE_ID(theForm.getTEMP_TYPE_ID()[i]);
				getSqlMap().delete("FileManager.fileManagerDelete", theForm);
			}
			//getSqlMap().commitTransaction();
		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		} finally {
			try {
				//getSqlMap().endTransaction();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 文件上传目录列表
	 * 
	 * @param theForm
	 * @return
	 */
	public List dirManagerList(TbCloud2FileListInfoObj obj) {

		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileManager.directoryManagerListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FileManager.directoryManagerList",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileManager.directoryManagerList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 取得目录具体信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getDirectoryInfo(FileManagerForm theForm) {

		try {
			return getSqlMap().queryForList("FileManager.getDirectoryInfo",
					theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 保存新增目录
	 * 
	 * @param theForm
	 */
	public void directoryAdd(FileManagerForm theForm) {

		try {
			getSqlMap().insert("FileManager.directoryAdd", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 更新修改目录
	 * 
	 * @param theForm
	 */
	public void directoryUpdate(FileManagerForm theForm) {

		try {
			getSqlMap().update("FileManager.directoryUpdate", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 删除目录
	 * 
	 * @param theForm
	 */
	public void directoryDelete(FileManagerForm theForm) {

		try {
			String[] temp = theForm.getTEMP_TYPE_ID();
			if (temp == null)
				return;
		//	getSqlMap().startTransaction();
		//	getSqlMap().startBatch();
			for (int i = 0; i < temp.length; i++) {
				getSqlMap().delete("FileManager.directoryDelete", temp[i]);
			}
		//	getSqlMap().executeBatch();
		//	getSqlMap().commitTransaction();
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		} finally {
			try {
		//		getSqlMap().endTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得 文件上传根目录
	 * 
	 * @return
	 * @author zhangwj
	 * @time 20110212
	 */
	public String getUploadRootStr() {

		try {

			return (String) getSqlMap().queryForObject(
					"FileManager.getUploadRootStr");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 判断是否可以删除文件类型
	 * 
	 * @param theForm
	 * @return
	 */
	public String fileManagerCount(FileManagerForm theForm) {

		try {
			String isdel = String.valueOf(getSqlMap().queryForObject(
					"FileManager.fileManagerCount", theForm));

			return isdel;

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 获得上传文件列表
	 * 
	 * @param theForm
	 * @return
	 */
	public List showHelpFileList(TbCloud2FileInfoObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileManager.helpFileListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FileManager.helpFileList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileManager.helpFileList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 获得上传文件历史版本列表
	 * 
	 * @param file_id
	 * @return
	 */
	public List showHelpFileHisList(TbCloud2FileInfoObj obj) {

		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileManager.helpFileHisListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FileManager.helpFileHisList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileManager.helpFileHisList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 获得文件类型列表
	 * 
	 * @return
	 */
	public List getFileTypeList() {
		try {
			return getSqlMap().queryForList("FileManager.getFileTypeList");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 获得目录列表
	 * 
	 * @return
	 */
	public List getDirectoryList() {
		try {
			return getSqlMap().queryForList("FileManager.getDirectoryList");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 添加上传文件
	 * 
	 * @param theForm
	 */
	public void helpFileAdd(FileManagerForm theForm) {
		try {
			getSqlMap().insert("FileManager.helpFileAdd", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}

	}

	/**
	 * 上传文件序列
	 * 
	 * @return
	 */
	public String helpFileSequence() {
		try {
			return (String) getSqlMap().queryForObject(
					"FileManager.helpFileSequence");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 删除上传文件
	 * 
	 * @param theForm
	 */
	public void deleteHelpFile(FileManagerForm theForm) {
		try {
			String[] temp = theForm.getTEMP_TYPE_ID();
			if (null == temp || temp.length == 0)
				return;
		//	getSqlMap().startTransaction(); // 打开事务
		//	getSqlMap().startBatch(); // 打开批处理
			for (int i = 0; i < temp.length; i++) {
				getSqlMap().delete("FileManager.deleteHelpFile", temp[i]);
				getSqlMap().delete("FileManager.deleteHelpHisFile", temp[i]);
			}
	//		getSqlMap().executeBatch(); // 执行批处理
	//		getSqlMap().commitTransaction(); // 提交事务
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		} finally {// 进行回滚操作
			try {
	//			getSqlMap().endTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 获取的上传文件的信息
	 * 
	 * @param theForm
	 * @return
	 */
	public List getHelpFileInfo(FileManagerForm theForm) {

		try {
			return getSqlMap().queryForList("FileManager.getHelpFileInfo",
					theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}
	}

	/**
	 * 更新上传文件
	 * 
	 * @param theForm
	 */
	public void updateHelpFile(FileManagerForm theForm) {
		try {
			getSqlMap().update("FileManager.updateHelpFile", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}

	}

	/**
	 * 把当前记录从TB_FILE 表 转移到 TB_HIS_FILE表
	 * 
	 * @param theForm
	 */
	public void addHelpFileHis(FileManagerForm theForm) {
		try {
			getSqlMap().insert("FileManager.addHelpFileHis", theForm);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding  Cause: " + sqlexception);
		}

	}

	/**
	 * 判断目录是否使用
	 * 
	 * @param theForm
	 * @return
	 */
	public String findDirCount(FileManagerForm theForm) {

		try {

			return String.valueOf(getSqlMap().queryForObject(
					"FileManager.findDirCount", theForm));

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 根据 目录ID获得目录名称
	 * 
	 * @param string
	 * @return
	 */
	public String getListName(String string) {
		try {

			return String.valueOf(getSqlMap().queryForObject(
					"FileManager.getListName", string));

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 获得所有厂商
	 * 
	 * @return
	 */
	public List getAllSiList() {

		try {
			return getSqlMap().queryForList("FileManager.getAllSiList");

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 获得某一厂商的目录列表
	 * 
	 * @param string
	 * @param si_id
	 * @return
	 */
	public List getHelpFileDirList(String string, String si_id) {

		try {
			return getSqlMap().queryForList("FileManager.getHelpFileDirList",
					si_id);

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
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
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2FileInfoObj> queryFileInfoByObj(TbCloud2FileInfoObj tbCloud2FileInfoObj) {
		List<TbCloud2FileInfoObj> list = new ArrayList<TbCloud2FileInfoObj>();
		try {
			list = getSqlMap().queryForList("FileManager.queryFileInfoByObj", tbCloud2FileInfoObj);
		} catch (Exception e) {
			LogHelper.error("FileManager.queryFileInfoByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list; 
	}

}

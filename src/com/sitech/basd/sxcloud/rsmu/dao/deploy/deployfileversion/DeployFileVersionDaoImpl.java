package com.sitech.basd.sxcloud.rsmu.dao.deploy.deployfileversion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class DeployFileVersionDaoImpl extends BaseDao implements
		DeployFileVersionDao {

	@Override
	public int deleteFileVersionObj(DeployFileVersionObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("FileVersion.deleteFileVersionObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FileVersion.deleteFileVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertFileVersionObj(DeployFileVersionObj obj) {
		int ret = 0;
		try {
			// add by chenjlc 20131224 start
			// 捕获软件包中的文件信息保存到数据库中
			// 开启事物
			getSqlMap().startTransaction();
			// 检查软件捕获的清单信息，"/"为部署，不用保存清单文件信息
			String filesInfo = obj.getFileInfoList();
			if (filesInfo != null && !"".equals(filesInfo)) {
				String files = obj.getFileInfoList();
				String[] fileList = files.split("\n");
				if (files != null && fileList.length > 0) {
					String file_size = "";
					String file_date = "";
					String file_name = "";
					String str = "";
					String[] ff = null;
					for (int i = 0; i < fileList.length; i++) {
						str = fileList[i];
						ff = str.split(",");
						file_name = ff[3];
						file_size = ff[0];
						file_date = ff[1] + ff[2];
						file_date = file_date.replace("-", "").replace(":", "");
						obj.setFILEPATH(file_name);
						obj.setFILESIZE(file_size);
						obj.setFILEUPDATETIME(file_date);
						getSqlMap().insert(
								"FileVersion.insertSoftWareFileInfo", obj);
					}
				}
			}
			// add by chenjlc 20131224 end
			Object o = getSqlMap().insert("FileVersion.insertFileVersionObj",
					obj);
			getSqlMap().commitTransaction();
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FileVersion.insertFileVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public List queryFileVersionList(DeployFileVersionObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileVersion.queryFileVersionCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FileVersion.queryFileVersionList",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileVersion.queryFileVersionList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public DeployFileVersionObj queryFileVersionOne(DeployFileVersionObj obj) {
		DeployFileVersionObj pObj = null;
		try {
			pObj = (DeployFileVersionObj) getSqlMap().queryForObject(
					"FileVersion.queryFileVersionOne", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileVersion.queryFileVersionOne:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return pObj;
	}

	@Override
	public int updateFileVersionObj(DeployFileVersionObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("FileVersion.updateFileVersionObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FileVersion.updateFileVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public double getMaxVersionNoByAppid(DeployFileVersionObj obj) {
		double versionNo = 1.0;
		try {
			Object resobj = getSqlMap().queryForObject(
					"FileVersion.getMaxVersionNoByAppid", obj);
			if (resobj != null) {
				versionNo = (Double) resobj;
			}
		} catch (Exception sqlexception) {
			versionNo = 1.0;
			LogHelper.error("FileVersion.getMaxVersionNoByAppid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return versionNo;
	}

	@Override
	public int insertFileVersionHis(DeployFileVersionObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("FileVersion.insertFileVersionHis",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
		}
		return ret;
	}

	@Override
	public List queryFileVersionHisList(DeployFileVersionObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FileVersion.queryFileVersionHisCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"FileVersion.queryFileVersionHisList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileVersion.queryFileVersionHisList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public String querySoftCatchFiles(DeployFileVersionObj obj) {
		String files = "";
		try {
			files = (String) getSqlMap().queryForObject(
					"FileVersion.querySoftCatchFiles", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileVersion.querySoftCatchFiles:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return files;
	}
}

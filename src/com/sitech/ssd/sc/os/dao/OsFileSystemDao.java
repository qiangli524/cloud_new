package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsFileSystemModel;

/**
  * @Title:OS文件系统
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午06:09:21
 */
public interface OsFileSystemDao {

	public int insertOsFileSystem(OsFileSystemModel model);
	
	public int copyOsTemplateFileSystem(OsFileSystemModel model);
	
	public int deleteOsFileSystem(OsFileSystemModel model);
	
	public int updateOsFileSystem(OsFileSystemModel model);
	
	public OsFileSystemModel selectOsFileSystem(OsFileSystemModel model);
	
	public List<OsFileSystemModel> selectOsFileSystemList(OsFileSystemModel model);
	
	public List<OsFileSystemModel> selectHomeDirList(OsFileSystemModel model);
	
	public List<OsFileSystemModel> unionOsFileSystemList(OsFileSystemModel model);
}

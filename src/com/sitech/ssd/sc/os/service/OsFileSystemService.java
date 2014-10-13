package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsFileSystemModel;

/**
 * @ClassName OsFileSystemService
 * @Desc 
 * @Author JamTau
 * @date May 23, 2014 3:52:16 PM
 */
public interface OsFileSystemService {

	public int addOsFileSystem(OsFileSystemModel model);	
	
	public int copyOsTemplateFileSystem(OsFileSystemModel model);
	
	public int batchAddOsFileSystem(List<OsFileSystemModel> list);
	
	public int modifyOsFileSystem(OsFileSystemModel model);
	
	/**
	 * @Title: deleteOsFileSystem
	 * @Description: deleteOsFileSystem
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsFileSystem(OsFileSystemModel model);
	
	/**
	 * @Title: deleteOsFileSystemList
	 * @Description: deleteOsFileSystemList
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsFileSystemList(OsFileSystemModel model);
	
	/**
	 * @Title: queryOsFileSystem 
	 * @Description: 
	 * 	只实现通过os_host_id,filesys_name查询文件系统
	 * @Author: JamTau
	 * @Date 2014-7-15 上午09:18:20
	 * @param model
	 * @return  OsFileSystemModel
	 */
	public OsFileSystemModel queryOsFileSystem(OsFileSystemModel model);
	
	public List<OsFileSystemModel> queryOsFileSystemList(OsFileSystemModel model);
	
	public List<OsFileSystemModel> queryHomeDirList(OsFileSystemModel model);
	
	public List<OsFileSystemModel> unionOsFileSystemList(OsFileSystemModel model);
}

package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;

/**
 * 
 * @ClassName: OsTemplateFileSystem
 * @Description: OS安装模版 -- 文件系统DAO
 * @author JamTau
 * @date 2014-8-20 下午5:17:18
 *
 */
public interface OsTemplateFileSystemDao {
	
	/**
	 * 
	 * @Title: insertOsTemplateFileSystem
	 * @Description: insert OsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateFileSystem
	 * @Description: copyOsTemplateFileSystem
	 * @param  OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateFileSystem
	 * @Description: delete OsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateFileSystem
	 * @Description: update OsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateFileSystem
	 * @Description: select OsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return OsTemplateFileSystem 返回类型
	 * @throws
	 */
	public OsTemplateFileSystem selectOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateFileSystemList
	 * @Description: select OsTemplateFileSystem List
	 * @param OsTemplateFileSystem 输入参数
	 * @return List<OsTemplateFileSystem> 返回类型
	 * @throws
	 */
	public List<OsTemplateFileSystem> selectOsTemplateFileSystemList(OsTemplateFileSystem obj);

}

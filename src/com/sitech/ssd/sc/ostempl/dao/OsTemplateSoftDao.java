package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;

/**
 * 
 * @ClassName: OsTemplateSoftDao
 * @Description: 安装模版 软件包DAO
 * @author JamTau
 * @date 2014-9-8 下午3:24:26
 *
 */
public interface OsTemplateSoftDao {

	/**
	 * 
	 * @Title: insertOsTemplateSoft
	 * @Description: insertOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateSoft
	 * @Description: copyOsTemplateSoft
	 * @param  OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateSoft
	 * @Description: deleteOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateSoft
	 * @Description: updateOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateSoft
	 * @Description: selectOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return OsTemplateSoft 返回类型
	 * @throws
	 */
	public OsTemplateSoft selectOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateSoftList
	 * @Description: selectOsTemplateSoftList
	 * @param OsTemplateSoft 输入参数
	 * @return List<OsTemplateSoft> 返回类型
	 * @throws
	 */
	public List<OsTemplateSoft> selectOsTemplateSoftList(OsTemplateSoft obj);
}

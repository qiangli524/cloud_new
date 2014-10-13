package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

/**
 * 
 * @ClassName: OsTemplateDao
 * @Description:  OS安装模版 DAO类
 * @author JamTau
 * @date 2014-8-20 下午4:56:26
 *
 */
public interface OsTemplateDao {

	/**
	 * @Title: insertOsTemplate
	 * @Description: insert OsTemplate
	 * @param OsTemplate
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplate(OsTemplate obj);
	
	/**
	 * @Title: deleteOsTemplate
	 * @Description: delete OsTemplate
	 * @param OsTemplate 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: updateOsTemplate
	 * @Description: update OsTemplate
	 * @param OsTemplate 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: selectOsTemplate
	 * @Description: select OsTemplate
	 * @param OsTemplate 输入参数
	 * @return OsTemplate 返回类型
	 * @throws
	 */
	public OsTemplate selectOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateList
	 * @Description: select OsTemplateList
	 * @param 输入参数
	 * @return List<OsTemplate> 返回类型
	 * @throws
	 */
	public List<OsTemplate> selectOsTemplateList(OsTemplate obj);
}

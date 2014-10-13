package com.sitech.ssd.sc.ostempl.service;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

/**
 * 
 * @ClassName: OsTemplateService
 * @Description: OS安装模版Service
 * @author JamTau
 * @date 2014-8-20 下午5:43:14
 *
 */
public interface OsTemplateService {
	
	/**
	 * 
	 * @Title: saveOsTemplate
	 * @Description: save OsTemplate
	 * @param OsTemplate 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplate
	 * @Description: deleteOsTemplate
	 * @param OsTemplate 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplate
	 * @Description: modifyOsTemplate
	 * @param OsTemplate 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: queryOsTemplate
	 * @Description: queryOsTemplate
	 * @param OsTemplate 输入参数
	 * @return OsTemplate 返回类型
	 * @throws
	 */
	public OsTemplate queryOsTemplate(OsTemplate obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateList
	 * @Description: queryOsTemplateList
	 * @param OsTemplate 输入参数
	 * @return List<OsTemplate> 返回类型
	 * @throws
	 */
	public List<OsTemplate> queryOsTemplateList(OsTemplate obj);

}

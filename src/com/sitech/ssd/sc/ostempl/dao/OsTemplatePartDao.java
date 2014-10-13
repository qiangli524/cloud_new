package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;

/**
 * 
 * @ClassName: OsTemplatePartDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-16 下午9:17:52
 *
 */
public interface OsTemplatePartDao {
	/**
	 * 
	 * @Title: insertOsTemplatePart
	 * @Description: insertOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: copyOsTemplatePart
	 * @Description: copyOsTemplatePart
	 * @param  OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplatePart
	 * @Description: deleteOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: updateOsTemplatePart
	 * @Description: updateOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: selectOsTemplatePart
	 * @Description: selectOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return OsTemplatePart 返回类型
	 * @throws
	 */
	public OsTemplatePart selectOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: selectOsTemplatePartList
	 * @Description: selectOsTemplatePartList
	 * @param OsTemplatePart 输入参数
	 * @return List<OsTemplatePart> 返回类型
	 * @throws
	 */
	public List<OsTemplatePart> selectOsTemplatePartList(OsTemplatePart obj);
}

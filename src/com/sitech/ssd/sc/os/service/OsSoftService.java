package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsSoftModel;

/**
 * 
 * @ClassName: OsSoftService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-10 上午10:27:44
 *
 */
public interface OsSoftService {
	
	/**
	 * @Title: copyOsTemplateSoft
	 * @Description: 
	 *   将模版表【tb_os_template_soft】中的配置信息拷贝到安装实例表【tb_os_soft】
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateSoft(OsSoftModel soft);
	
	/**
	 * 
	 * @Title: deleteAllOsSoft
	 * @Description: 
	 * 		删除主机下，所有软件包
	 *     【该方法中必须验证参数，避免删除全表数据】
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteAllOsSoft(OsSoftModel soft);
	
	/**
	 * 
	 * @Title: deleteOsTemplateSoft
	 * @Description: 
	 * 		删除主机下，来自模版的软件包
	 *     【该方法中必须验证参数，避免删除全表数据】
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateSoft(OsSoftModel soft);
	
	/**
	 * 
	 * @Title: queryOsSoftList
	 * @Description: 
	 * 		查询安装实例表【tb_os_soft】的数据
	 * @param  输入参数
	 * @return List<OsSoftModel> 返回类型
	 * @throws
	 */	
	public List<OsSoftModel> queryOsSoftList(OsSoftModel soft);
	
	/**
	 * @Title: unionOsSoftList
	 * @Description: 
	 *   	查询【tb_os_soft】【tb_os_template_soft】的软件包配置数据
	 * @param  输入参数
	 * @return List<OsSoftModel> 返回类型
	 * @throws
	 */
	public List<OsSoftModel> unionOsSoftList(OsSoftModel soft);
}

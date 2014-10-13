package com.sitech.ssd.sc.os.dao;

import java.util.List;
import com.sitech.ssd.sc.os.domain.OsSoftModel;

/**
 * 
 * @ClassName: OsSoftDao
 * @Description: 软件包管理Dao
 * @author JamTau
 * @date 2014-9-10 上午10:03:29
 *
 */
public interface OsSoftDao {
	
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
	 * @Title: deleteOsSoft
	 * @Description: 
	 * 		删除软件包
	 *      【调用该方法前必须验证参数，避免删除全表数据】
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsSoft(OsSoftModel soft);
	
	/**
	 * 
	 * @Title: selectOsSoftList
	 * @Description: 
	 * 		查询安装实例表【tb_os_soft】的数据
	 * @param  输入参数
	 * @return List<OsSoftModel> 返回类型
	 * @throws
	 */
	public List<OsSoftModel> selectOsSoftList(OsSoftModel soft);
	
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

package com.sitech.basd.sxcloud.cloud.dao.templet;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletTypeObj;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;


public interface TempletDao {
	/**
	 * @Title:查询已有模板列表
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletObj obj);
	
	/**
	 * @Title:查询需要的模板
	 * @Copyright: Copyright (c) 2013-03-05
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByType(TempletObj obj);
	
	/**
	 * @Title:查询模板参数
	 * @Copyright: Copyright (c) 2013-03-05
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListValue(TempletObj obj);


	/**
	 * @Title:查询资源类别
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceType();

	/**
	 * @Title:查询所选资源类别对应模板讯息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceTypeTemplet(String TYPE);

	/**
	 * @Title:查询已发布的应用部署模板
	 * @Copyright: Copyright (c) 2012-03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppForListByObj(TempletObj obj);

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public TempletObj queryByObj(TempletObj obj);

	/**
	 * @Title:更新模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int updateByObj(TempletObj obj);

	/**
	 * @Title:删除已有模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int deleteByObj(TempletObj obj);

	/**
	 * @Title:创建模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int insertByObj(TempletObj obj);

	/**
	 * @Title:发布模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int releaseByObj(TempletObj obj);

	/**
	 * @Title:查询已有模板配置
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryConByObjBase(TempletObj obj);
	
	/**
	 * @Title:修改模板的KV
	 * @Copyright: Copyright (c) 2013-03-07
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	
	public int updateStrategy(TempletObj obj);

	/**
	 * @Title:查询模板类型配置
	 * @Copyright: Copyright (c) 2012-02-20
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryTypeListByObj(TempletObj obj);

	/**
	 * @Title:查询模板操作历史
	 * @Copyright: Copyright (c) 2012-02-22
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryHisForListByObj(TempletObj obj);

	/**
	 * @Title:插入操作模板日志
	 * @Copyright: Copyright (c) 2012-02-22
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int insertHisByObj(TempletObj obj);
	/**
	 * 
	 * @Title: 查看镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryTempletTypeList(TempletTypeObj obj);
	
	/**
	 * 
	 * @Title: 增加镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByTempletTypeObj(TempletTypeObj obj);
	
	/**
	 * 
	 * @Title: 修改镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByTempletTypeObj(TempletTypeObj obj);
	
	/**
	 * 
	 * @Title: 删除镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByTempletTypeObj(TempletTypeObj obj);
	
	/**
	 * 
	 * @Title: 显示某个类型对应的所有镜像
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryForListByImageType(VMHostObj obj);
	
	/**
	 * 
	 * @Title: 根据镜像类型查询镜像列表
	 * @Copyright: Copyright (c) 2012-9-14
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryTempletTypeListByType(TempletTypeObj obj);
	
	/**
	 * 
	 * @Title: 显示除某个类型外的所有镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryForListByNOEqualType(VMHostObj obj);
	
	/**
	 * 
	 * @Title: 绑定镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int bindingImage(VMHostObj obj);
	
}

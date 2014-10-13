package com.sitech.basd.sxcloud.cloud.service.image;

import java.util.List;

import com.sitech.basd.rest.workloads.domain.WorkloadInfo;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.web.image.form.ImageForm;

public interface ImageService {

	/**
	 * @Title:查询已有映像列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public List queryForListByObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:查询并返回一个映像对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbCloud2ImageInfoObj queryByObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:更新映像信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int updateByObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:删除已有映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int deleteByObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:创建映像
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int insertByObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:部署映像
	 * @Copyright: Copyright (c) 2012-1-6
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public WorkloadInfo deployImage(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:保存映像
	 * @Copyright: Copyright (c) 2012-1-6
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public boolean saveImage(ImageForm form);

	/**
	 * 
	 * @Title: deployVirtualStatToRunStat
	 * @Description: 部署镜像成为运行状态虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 18, 2012 4:19:00 PM
	 */
	public int deployVirtualStatToRunStat(TbCloud2VirtualInfoObj obj);

	/**
	 * 
	 * @Title: advanceddeployVirtualStatToRunStat
	 * @Description: 高级部署镜像成为运行状态虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 20, 2012 9:41:58 AM
	 */
	public int advanceddeployVirtualStatToRunStat(TbCloud2VirtualInfoObj obj);

	/**
	 * 
	 * @Title:获取虚拟镜像目标列表
	 * @Copyright: Copyright (c) Mar 14, 2012
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public List<TbCloud2ImageTargetObj> getImageIdTargets(
			TbCloud2ImageInfoObj obj);

	/**
	 * @Title:移至项目更新PROJECT_ID
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByProjectIdObj(TbCloud2ImageInfoObj obj);

	/**
	 * @Title:查询虚拟镜像类型列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForTypeList(TbCloud2ImageInfoObj obj);

	/**
	 * 
	 * @Title: updateName
	 * @Description: 只更新镜像名称
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:51:31 PM
	 */
	public int updateName(TbCloud2ImageInfoObj obj);

	/**
	 * 
	 * @Title:查询虚拟机镜像
	 * @Copyright: Copyright (c) 2012-9-14
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryVMHostByVMObj(VMHostObj obj);
}

package com.sitech.basd.sxcloud.cloud.dao.image;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

public interface TbImageInfoDao {
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
	 * @Title:查询已有虚拟镜像列表
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

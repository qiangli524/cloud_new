package com.sitech.basd.yicloud.dao.vmauthority;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;

public interface VmAuthorityDao {
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 向TB_CLOUD_ENTITYUSER表插入数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int insertByObj(VmAuthorityObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除TB_CLOUD_ENTITYUSER表数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int deleteByObj(VmAuthorityObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询TB_CLOUD_ENTITYUSER列表
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public List queryForList(VmAuthorityObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description:更新TB_CLOUD_ENTITYUSER列表
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public int updateByObj(VmAuthorityObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更具用户查询其对应的资源
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public List queryResourceByAccount(VmAuthorityObj obj);
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 根据普通用户查询其对应的所有资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryAllCountResource(VmAuthorityObj obj);
	/**
	 * 
	 * @Title: queryUsedCountResource
	 * @Description: 根据普通用户查询其对应的已用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryUsedCountResource(VmAuthorityObj obj);
	/**
	 * 
	 * @Title: queryAllVMListByAccount
	 * @Description: 根据普通用户查询其对应项目的所有虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-07
	 */
	public List queryAllVMListByAccount(RelationObj obj);
	
}

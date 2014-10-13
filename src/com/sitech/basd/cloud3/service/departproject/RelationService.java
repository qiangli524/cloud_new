package com.sitech.basd.cloud3.service.departproject;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

public interface RelationService {
	/**
	 * 
	 * @Title: querySelectedVMList
	 * @Description: 查询已选的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List<VMHostObj> querySelectedVMList(VMHostObj obj);

	/**
	 * 
	 * @Title: queryRemainVMList
	 * @Description: 查询备选虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List queryRemainVMList(VMHostObj obj);

	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除虚拟机与项目的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-4
	 */
	public int deleteRelation(RelationObj obj);

	/**
	 * 
	 * @Title: insertRelation
	 * @Description: 插入到项目和虚拟机关系表中
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public int insertRelation(RelationObj obj);

	/**
	 * 
	 * @Title: queryVMByProjectId
	 * @Description: 根据用户项目查询对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public List queryVMByProjectId(VMHostObj obj);

	/**
	 * @Title: queryForListByPro
	 * @Description: 通过项目查询虚拟机标识和链接标识
	 * @param
	 * @return List<RelationObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午6:55:42
	 */
	public List<RelationObj> queryForListByPro(RelationObj relationObj);

}

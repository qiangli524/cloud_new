package com.sitech.basd.cloud3.service.departproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.RelationDao;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

@Service("relationService")
public class RelationServiceImpl implements RelationService {
	@Autowired
	private RelationDao relationDao;

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
	public List queryRemainVMList(VMHostObj obj) {
		return relationDao.queryRemainVMList(obj);
	}

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
	public List<VMHostObj> querySelectedVMList(VMHostObj obj) {
		return relationDao.querySelectedVMList(obj);
	}

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
	public int deleteRelation(RelationObj obj) {
		return relationDao.deleteRelation(obj);
	}

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
	public int insertRelation(RelationObj obj) {
		return relationDao.insertRelation(obj);
	}

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
	public List queryVMByProjectId(VMHostObj obj) {
		return relationDao.queryVMByProjectId(obj);
	}

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
	@Override
	public List<RelationObj> queryForListByPro(RelationObj relationObj) {
		// TODO Auto-generated method stub
		return relationDao.queryForListByPro(relationObj);
	}
}

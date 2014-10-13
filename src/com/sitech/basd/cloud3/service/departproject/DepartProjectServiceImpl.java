package com.sitech.basd.cloud3.service.departproject;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.DepartProjectDao;
import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
@Service("departProjectService")
public class DepartProjectServiceImpl implements DepartProjectService {
	private DepartProjectDao departProjectDao;

	@Override
	public int delDepartProject(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.deleteDepartProjectObj(obj);
	}

	@Override
	public List departProjectList(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.queryDepartProjectList(obj);
	}

	@Override
	public DepartProjectObj queryDepartProjectOne(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.queryDepartProjectOne(obj);
	}

	public DepartProjectDao getDepartProjectDao() {
		return departProjectDao;
	}

	public void setDepartProjectDao(DepartProjectDao departProjectDao) {
		this.departProjectDao = departProjectDao;
	}

	@Override
	public int insertDepartProject(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.insertDepartProjectObj(obj);
	}

	@Override
	public int updateDepartProject(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.updateDepartProjectObj(obj);
	}

	@Override
	public List departProjectHisList(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.queryDepartProjectHisList(obj);
	}

	@Override
	public int insertDepartProjectHis(DepartProjectObj obj) {
		// TODO Auto-generated method stub
		return departProjectDao.insertDepartProjectHisObj(obj);
	}

	/**
	 * 
	 * @Title: queryResourceByDepartment
	 * @Description: 查询该项目的资源
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-8-30 上午11:04:36
	 */
	@Override
	public List queryResourceByDepartment(DepartProjectObj obj) {
		return departProjectDao.queryResourceByDepartment(obj);
	}

	/**
	 * @Title: queryForList
	 * @Description: 查询项目集合
	 * @param
	 * @return List<DepartProjectObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-3 下午9:47:34
	 */
	@Override
	public List<DepartProjectObj> queryForList(DepartProjectObj departProjectObj) {
		
		return departProjectDao.queryForList(departProjectObj);
	}
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询所有资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryAllCountResource(DepartProjectObj obj){
		return departProjectDao.queryAllCountResource(obj);
	}
	/**
	 * 
	 * @Title: queryUsedCountResource
	 * @Description: 查询已用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryUsedCountResource(DepartProjectObj obj){
		return departProjectDao.queryUsedCountResource(obj);
	}
	
	/**
	 * 
	 * @Title: queryUsedResource
	 * @Description: 查询已分配资源
	 * @param
	 * @return RelationObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public List<RelationObj> queryUsedResource(DepartProjectObj obj){
		return departProjectDao.queryUsedResource(obj);
	}

	/**
	 * 
	 * @Title: queryAllResource
	 * @Description: 查询所有资源
	 * @param
	 * @return RelationObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-31
	 */
	public List<RelationObj> queryAllResource(DepartProjectObj obj){
		return departProjectDao.queryAllResource(obj);
	}
}

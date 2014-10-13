package com.sitech.basd.scheduler.dao.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapException;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObj;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("resourceTaskDao")
public class ResourceTaskDaoImpl extends BaseDao implements ResourceTaskDao {

	public void insertBySelective(ResourceTaskObj obj) throws Exception {
		try {
			getSqlMap().insert("tb_resource_scheduler_task.insertSelective", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.insertSelective:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}

	public void deleteByPrimaryKey(String taskId) throws Exception {
		
		try {
			getSqlMap().delete("tb_resource_scheduler_task.deleteByPrimaryKey", taskId);
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.deleteByPrimaryKey:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}

	}

	public void deleteByExample(ResourceTaskObjExample obj) throws Exception {

		try {
			getSqlMap().delete("tb_resource_scheduler_task.deleteByExample", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.deleteByExample:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}

	}

	public int countByExample(ResourceTaskObjExample obj) {
		try {
			Integer count = (Integer) getSqlMap().queryForObject("tb_resource_scheduler_task.countByExample", obj);
			return count.intValue();
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.countByExample:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}

	public List<ResourceTaskObj> selectByExampleList(ResourceTaskObjExample obj) {
		List <ResourceTaskObj>objList = new ArrayList<ResourceTaskObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("tb_resource_scheduler_task.countByExample", obj)).intValue());
			}
			objList = getSqlMap().queryForList("tb_resource_scheduler_task.queryByExampleList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.queryByExampleList:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
		return objList;
	}
	
	public ResourceTaskObj selectByPrimaryKey(String taskId) {
		try {
			ResourceTaskObj obj = (ResourceTaskObj) getSqlMap().queryForObject("tb_resource_scheduler_task.selectByPrimaryKey", taskId);
			return obj;
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.selectByPrimaryKey:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}

	public ResourceTaskObj selectByExample(ResourceTaskObjExample obje) {
		try {
			ResourceTaskObj obj = (ResourceTaskObj) getSqlMap().queryForObject("tb_resource_scheduler_task.selectByExample", obje);
			return obj;
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.selectByExample:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}

	public int updateByExampleSelective(ResourceTaskObj obj, ResourceTaskObjExample obje) {
		try {
			UpdateByExampleParms parms = new UpdateByExampleParms(obj, obje);
			int count = getSqlMap().update("tb_resource_scheduler_task.updateByExampleSelective", parms);
			return count;
		} catch (Exception sqlexception) {
			LogHelper.error("tb_resource_scheduler_task.updateByExampleSelective:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}

	private static class UpdateByExampleParms extends ResourceTaskObjExample {
		private Object record;

		public UpdateByExampleParms(Object record, ResourceTaskObjExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

}

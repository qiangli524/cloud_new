package com.sitech.basd.syslog.dao;

import com.ibatis.sqlmap.client.SqlMapException;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.syslog.domain.TbUserOperationLog;
import com.sitech.basd.syslog.domain.TbUserOperationLogExample;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userOperationLogDao")
public class TbUserOperationLogDAOImpl extends BaseDao implements TbUserOperationLogDAO {

	public TbUserOperationLogDAOImpl() {
		super();
	}

    /**
     *简单列表查询
     * @param tbUserOperationLogExample
     * @return list 日志列表
     */
	public List<TbUserOperationLog> selectByExample(TbUserOperationLogExample obj) {
		List<TbUserOperationLog> objList = new ArrayList<TbUserOperationLog>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("tb_user_operation_log.countByExample", obj)).intValue());
			}
			objList = getSqlMap().queryForList("tb_user_operation_log.selectByExample", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("tb_user_operation_log.queryByExampleList:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
		return objList;
	}

    /**
     * 根据主键查询对象
     * @param id
     * @return
     */
    public TbUserOperationLog selectByPrimaryKey(String id) {
        TbUserOperationLog record = (TbUserOperationLog) getSqlMapClientTemplate().queryForObject("tb_user_operation_log.selectByPrimaryKey",id);
        return record;
    }

    /**
     * 根据对象值进行插入
     * @param record
     */
    public void insertSelective(TbUserOperationLog record) {
        getSqlMapClientTemplate().insert("tb_user_operation_log.insertSelective", record);
    }

    /**
     * 主键删除
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(String id) {
        int rows = getSqlMapClientTemplate().delete("tb_user_operation_log.deleteByPrimaryKey",id);
        return rows;
    }

    /**
     * 条件删除
     * @param example
     * @return
     */
    public int deleteByExample(TbUserOperationLogExample example) {
        int rows = getSqlMapClientTemplate().delete("tb_user_operation_log.deleteByExample", example);
        return rows;
    }

    /**
     * 条件查找记录个数
     * @param example
     * @return
     */
    public int countByExample(TbUserOperationLogExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tb_user_operation_log.countByExample", example);
        return count.intValue();
    }

    /**
     * 根据条件选择性更新
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleSelective(TbUserOperationLog record, TbUserOperationLogExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_user_operation_log.updateByExampleSelective", parms);
        return rows;
    }

    /**
     *
     * 根据主键选择性更新
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(TbUserOperationLog record) {
        int rows = getSqlMapClientTemplate().update("tb_user_operation_log.updateByPrimaryKeySelective", record);
        return rows;
    }


	private static class UpdateByExampleParms extends TbUserOperationLogExample {
		private static final long serialVersionUID = 1L;
		private Object record;

		public UpdateByExampleParms(Object record, TbUserOperationLogExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
package com.sitech.shop.webservice.dao;

import com.ibatis.sqlmap.client.SqlMapException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository("orderRelationInstanceDao")
public class TbOrderRelationInstanceDAOImpl extends BaseDao implements TbOrderRelationInstanceDAO {


    public TbOrderRelationInstanceDAOImpl() {
        super();
    }


    public int countByExample(TbOrderRelationInstanceExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tb_order_relation_instance.countByExample", example);
        return count.intValue();
    }


    public int deleteByExample(TbOrderRelationInstanceExample example) {
        int rows = getSqlMapClientTemplate().delete("tb_order_relation_instance.deleteByExample", example);
        return rows;
    }


    public int deleteByPrimaryKey(String uuid) {
        int rows = getSqlMapClientTemplate().delete("tb_order_relation_instance.deleteByPrimaryKey", uuid);
        return rows;
    }


    public void insertSelective(TbOrderRelationInstance record) {
        getSqlMapClientTemplate().insert("tb_order_relation_instance.insertSelective", record);
    }


    public List<TbOrderRelationInstance> selectByExample(TbOrderRelationInstanceExample example) {
        List<TbOrderRelationInstance> list = getSqlMapClientTemplate().queryForList("tb_order_relation_instance.selectByExample", example);
        return list;
    }


    public TbOrderRelationInstance selectByPrimaryKey(String uuid) {
        TbOrderRelationInstance record = (TbOrderRelationInstance) getSqlMapClientTemplate().queryForObject("tb_order_relation_instance.selectByPrimaryKey", uuid);
        return record;
    }


    public int updateByExampleSelective(TbOrderRelationInstance record, TbOrderRelationInstanceExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_order_relation_instance.updateByExampleSelective", parms);
        return rows;
    }


    public int updateByPrimaryKeySelective(TbOrderRelationInstance record) {
        int rows = getSqlMapClientTemplate().update("tb_order_relation_instance.updateByPrimaryKeySelective", record);
        return rows;
    }
    /**
     *简单列表查询
     * @param tbUserOperationLogExample
     * @return list 日志列表
     */
	public List<TbOrderRelationInstance> selectByExamplePage(TbOrderRelationInstanceExample example) {
		List<TbOrderRelationInstance> objList = new ArrayList<TbOrderRelationInstance>();
		if (example.getPagination() != null) {
			example.setFIRSTROWNUM(example.getPagination().getFirstRownum());
			example.setPAGESIZE(example.getPagination().getPageSize());
			example.getPagination().setTotalCount((Integer) getSqlMapClientTemplate().queryForObject("tb_order_relation_instance.countByExample", example));
		}
		objList =getSqlMapClientTemplate().queryForList("tb_order_relation_instance.selectByExamplePage", example);
		return objList;
	}

    private static class UpdateByExampleParms extends TbOrderRelationInstanceExample {
        private Object record;

        public UpdateByExampleParms(Object record, TbOrderRelationInstanceExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
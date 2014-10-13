package com.sitech.shop.webservice.dao;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.webservice.domain.TbOrderRelation;
import com.sitech.shop.webservice.domain.TbOrderRelationExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRelationDao")
public class TbOrderRelationDAOImpl extends BaseDao implements TbOrderRelationDAO {


    public TbOrderRelationDAOImpl() {
        super();
    }


    public int countByExample(TbOrderRelationExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tb_order_relation.countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TbOrderRelationExample example) {
        int rows = getSqlMapClientTemplate().delete("tb_order_relation.deleteByExample", example);
        return rows;
    }


    public int deleteByPrimaryKey(String uuid) {
        int rows = getSqlMapClientTemplate().delete("tb_order_relation.deleteByPrimaryKey", uuid);
        return rows;
    }


    public void insertSelective(TbOrderRelation record) {
        getSqlMapClientTemplate().insert("tb_order_relation.insertSelective", record);
    }


    public List<TbOrderRelation> selectByExample(TbOrderRelationExample example) {
        List list = getSqlMapClientTemplate().queryForList("tb_order_relation.selectByExample", example);
        return list;
    }


    public TbOrderRelation selectByPrimaryKey(String uuid) {
        TbOrderRelation record = (TbOrderRelation) getSqlMapClientTemplate().queryForObject("tb_order_relation.selectByPrimaryKey", uuid);
        return record;
    }


    public int updateByExampleSelective(TbOrderRelation record, TbOrderRelationExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_order_relation.updateByExampleSelective", parms);
        return rows;
    }


    public int updateByPrimaryKeySelective(TbOrderRelation record) {
        int rows = getSqlMapClientTemplate().update("tb_order_relation.updateByPrimaryKeySelective", record);
        return rows;
    }


    private static class UpdateByExampleParms extends TbOrderRelationExample {
        private Object record;

        public UpdateByExampleParms(Object record, TbOrderRelationExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
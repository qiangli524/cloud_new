package com.sitech.shop.webservice.dao;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.webservice.domain.TbOrderStandard;
import com.sitech.shop.webservice.domain.TbOrderStandardExample;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("orderStandardDao")
public class TbOrderStandardDAOImpl extends BaseDao implements TbOrderStandardDAO {


    public TbOrderStandardDAOImpl() {
        super();
    }


    public int countByExample(TbOrderStandardExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tb_order_standard.countByExample", example);
        return count.intValue();
    }


    public int deleteByExample(TbOrderStandardExample example) {
        int rows = getSqlMapClientTemplate().delete("tb_order_standard.deleteByExample", example);
        return rows;
    }


    public int deleteByPrimaryKey(String uuid) {
        int rows = getSqlMapClientTemplate().delete("tb_order_standard.deleteByPrimaryKey", uuid);
        return rows;
    }


    public void insertSelective(TbOrderStandard record) {
        getSqlMapClientTemplate().insert("tb_order_standard.insertSelective", record);
    }


    public List<TbOrderStandard> selectByExample(TbOrderStandardExample example) {
        List<TbOrderStandard> list = getSqlMapClientTemplate().queryForList("tb_order_standard.selectByExample", example);
        return list;
    }


    public TbOrderStandard selectByPrimaryKey(String uuid) {
        TbOrderStandard record = (TbOrderStandard) getSqlMapClientTemplate().queryForObject("tb_order_standard.selectByPrimaryKey", uuid);
        return record;
    }


    public int updateByExampleSelective(TbOrderStandard record, TbOrderStandardExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("tb_order_standard.updateByExampleSelective", parms);
        return rows;
    }


    public int updateByPrimaryKeySelective(TbOrderStandard record) {
        int rows = getSqlMapClientTemplate().update("tb_order_standard.updateByPrimaryKeySelective", record);
        return rows;
    }


    private static class UpdateByExampleParms extends TbOrderStandardExample {
        private Object record;

        public UpdateByExampleParms(Object record, TbOrderStandardExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}
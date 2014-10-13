package com.sitech.shop.webservice.dao;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.webservice.domain.TbSysUserinfo;
import com.sitech.shop.webservice.domain.TbSysUserinfoExample;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("sysUserinfoShopDao")
public class TbSysUserinfoShopDAOImpl extends BaseDao implements TbSysUserinfoShopDAO {

	public TbSysUserinfoShopDAOImpl() {
		super();
	}

	public int countByExample(TbSysUserinfoExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("tb_sys_userinfo_shop.ibatorgenerated_countByExample", example);
		return count.intValue();
	}

	public int deleteByExample(TbSysUserinfoExample example) {
		int rows = getSqlMapClientTemplate().delete("tb_sys_userinfo_shop.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	public int deleteByPrimaryKey(Integer id) {
		TbSysUserinfo key = new TbSysUserinfo();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete("tb_sys_userinfo_shop.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	public void insert(TbSysUserinfo record) {
		getSqlMapClientTemplate().insert("tb_sys_userinfo_shop.ibatorgenerated_insert", record);
	}

	public void insertSelective(TbSysUserinfo record) {
		getSqlMapClientTemplate().insert("tb_sys_userinfo_shop.ibatorgenerated_insertSelective", record);
	}

	public List<TbSysUserinfo> selectByExample(TbSysUserinfoExample example) {
		List<TbSysUserinfo> list = getSqlMapClientTemplate().queryForList("tb_sys_userinfo_shop.ibatorgenerated_selectByExample", example);
		return list;
	}

	public TbSysUserinfo selectByPrimaryKey(Integer id) {
		TbSysUserinfo key = new TbSysUserinfo();
		key.setId(id);
		TbSysUserinfo record = (TbSysUserinfo) getSqlMapClientTemplate().queryForObject("tb_sys_userinfo_shop.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	public int updateByExampleSelective(TbSysUserinfo record, TbSysUserinfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update("tb_sys_userinfo_shop.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	public int updateByExample(TbSysUserinfo record, TbSysUserinfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update("tb_sys_userinfo_shop.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	public int updateByPrimaryKeySelective(TbSysUserinfo record) {
		int rows = getSqlMapClientTemplate().update("tb_sys_userinfo_shop.ibatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	public int updateByPrimaryKey(TbSysUserinfo record) {
		int rows = getSqlMapClientTemplate().update("tb_sys_userinfo_shop.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	private static class UpdateByExampleParms extends TbSysUserinfoExample {
		private Object record;

		public UpdateByExampleParms(Object record, TbSysUserinfoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}
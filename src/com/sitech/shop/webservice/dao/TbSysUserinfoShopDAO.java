package com.sitech.shop.webservice.dao;


import com.sitech.shop.webservice.domain.TbSysUserinfo;
import com.sitech.shop.webservice.domain.TbSysUserinfoExample;

import java.util.List;

public interface TbSysUserinfoShopDAO {

	int countByExample(TbSysUserinfoExample example);

	int deleteByExample(TbSysUserinfoExample example);

	int deleteByPrimaryKey(Integer id);

	void insert(TbSysUserinfo record);

	void insertSelective(TbSysUserinfo record);

	List<TbSysUserinfo> selectByExample(TbSysUserinfoExample example);

	TbSysUserinfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(TbSysUserinfo record, TbSysUserinfoExample example);

	int updateByExample(TbSysUserinfo record, TbSysUserinfoExample example);

	int updateByPrimaryKeySelective(TbSysUserinfo record);

	int updateByPrimaryKey(TbSysUserinfo record);
}
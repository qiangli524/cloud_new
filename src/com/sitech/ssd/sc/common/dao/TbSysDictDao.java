package com.sitech.ssd.sc.common.dao;

import java.util.List;

import com.sitech.ssd.sc.common.domain.TbSysDictObj;


/**
 * 
 * @ClassName: TbSysDictDao
 * @Description: 数据字典DAO接口类
 * @author JamTau
 * @date 2014-8-27 下午2:55:04
 *
 */
public interface TbSysDictDao {

	public int insertTbSysDict(TbSysDictObj obj);
	
	public int deleteTbSysDict(TbSysDictObj obj);
	
	public int updateTbSysDict(TbSysDictObj obj);
	
	public TbSysDictObj selectTbSysDict(TbSysDictObj obj);
	
	public List<TbSysDictObj> selectTbSysDictList(TbSysDictObj obj);
}

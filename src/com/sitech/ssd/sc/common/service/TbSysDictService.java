package com.sitech.ssd.sc.common.service;

import java.util.List;

import com.sitech.ssd.sc.common.domain.TbSysDictObj;

/**
 * 
 * @ClassName: TbSysDictService
 * @Description: 数据字典Service类
 * @author JamTau
 * @date 2014-8-27 下午3:27:55
 *
 */
public interface TbSysDictService {

	
	public boolean saveTbSysDict(TbSysDictObj obj);
	
	public boolean deleteTbSysDict(TbSysDictObj obj);
	
	public boolean modifyTbSysDict(TbSysDictObj obj);
	
	public TbSysDictObj queryTbSysDict(TbSysDictObj obj);
	
	public List<TbSysDictObj> queryTbSysDictEffectList(TbSysDictObj obj);
	
	public List<TbSysDictObj> queryTbSysDictList(TbSysDictObj obj);
}

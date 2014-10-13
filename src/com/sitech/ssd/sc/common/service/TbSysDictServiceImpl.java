package com.sitech.ssd.sc.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.common.dao.TbSysDictDao;
import com.sitech.ssd.sc.common.domain.TbSysDictObj;

/**
 * 
 * @ClassName: TbSysDictServiceImpl
 * @Description: 绯荤粺瀛楀吀瀹炵幇绫�
 * @author JamTau
 * @date 2014-8-27 涓嬪崍3:32:24
 * 
 */
@Service("tbSysDictService")
public class TbSysDictServiceImpl implements TbSysDictService {

	@Resource
	private TbSysDictDao tbSysDictDao;

	@Override
	public boolean saveTbSysDict(TbSysDictObj obj) {
		int f = tbSysDictDao.insertTbSysDict(obj);
		return f == 0 ? true : false;
	}

	@Override
	public boolean deleteTbSysDict(TbSysDictObj obj) {
		int f = tbSysDictDao.deleteTbSysDict(obj);
		return f == 0 ? true : false;
	}

	@Override
	public boolean modifyTbSysDict(TbSysDictObj obj) {
		int f = tbSysDictDao.updateTbSysDict(obj);
		return f == 0 ? true : false;
	}

	@Override
	public TbSysDictObj queryTbSysDict(TbSysDictObj obj) {
		return tbSysDictDao.selectTbSysDict(obj);
	}

	@Override
	public List<TbSysDictObj> queryTbSysDictEffectList(TbSysDictObj obj) {
		obj.setEffect("1");
		return tbSysDictDao.selectTbSysDictList(obj);
	}

	@Override
	public List<TbSysDictObj> queryTbSysDictList(TbSysDictObj obj) {
		return tbSysDictDao.selectTbSysDictList(obj);
	}

}

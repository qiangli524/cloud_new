package com.sitech.basd.cloud3.service.app;

import java.util.List;

import com.sitech.basd.cloud3.dao.app.TbCloud3AppFileTreeDao;
import com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO;

/**
 * 
 * <p>
 * Title: TbCloud3AppFileTreeServiceImpl
 * </p>
 * <p>
 * Description: 应用部署文件树实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-3-17 上午11:22:10
 * 
 */
public class TbCloud3AppFileTreeServiceImpl implements
		TbCloud3AppFileTreeService {
	private TbCloud3AppFileTreeDao tbCloud3AppFileTreeDao;

	public void setTbCloud3AppFileTreeDao(
			TbCloud3AppFileTreeDao tbCloud3AppFileTreeDao) {
		this.tbCloud3AppFileTreeDao = tbCloud3AppFileTreeDao;
	}

	@Override
	public String insertByVO(TbCloud3AppFileTreeVO obj) {
		return tbCloud3AppFileTreeDao.insertByVO(obj);
	}

	@Override
	public List<TbCloud3AppFileTreeVO> queryVOListByParentID(
			TbCloud3AppFileTreeVO obj) {
		return tbCloud3AppFileTreeDao.queryVOListByParentID(obj);
	}

	@Override
	public TbCloud3AppFileTreeVO queryVOByParentPath(TbCloud3AppFileTreeVO obj) {
		return tbCloud3AppFileTreeDao.queryVOByParentPath(obj);
	}

}

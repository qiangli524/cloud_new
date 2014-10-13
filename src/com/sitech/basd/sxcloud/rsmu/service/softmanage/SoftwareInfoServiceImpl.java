package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbBusiSoftwareInfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
public class SoftwareInfoServiceImpl extends BaseService implements SoftwareInfoService {
	
	/**
     * @Title:删除软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiSoftwareInfoObj obj) {
		return tbBusiSoftwareInfoDao.deleteByObj(obj);
	}

	/**
     * @Title:插入软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiSoftwareInfoObj obj) {
		return tbBusiSoftwareInfoDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiSoftwareInfoObj queryByObj(TbBusiSoftwareInfoObj obj) {
		return tbBusiSoftwareInfoDao.queryByObj(obj);
	}

	/**
     * @Title:根据软件部分信息查询匹配的所有应用信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiSoftwareInfoObj obj) {
		return tbBusiSoftwareInfoDao.queryForListByObj(obj);
	}


	/**
     * @Title:更新软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiSoftwareInfoObj obj) {
		return tbBusiSoftwareInfoDao.updateByObj(obj);
	}

	private TbBusiSoftwareInfoDao tbBusiSoftwareInfoDao;

	public void setTbBusiSoftwareInfoDao(TbBusiSoftwareInfoDao tbBusiSoftwareInfoDao) {
		this.tbBusiSoftwareInfoDao = tbBusiSoftwareInfoDao;
	}

	/**
	 * @Title: queryForListByObjNew
	 * @Description:根据对象查询所有
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午4:37:30
	 */
	@Override
	public List<TbBusiSoftwareInfoObj> queryForListByObjNew(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj) {
		return tbBusiSoftwareInfoDao.queryForListByObjNew(tbBusiSoftwareInfoObj);
	}

	/**
	 * @Title: queryForListByAppidUseIn
	 * @Description: 使用in语句根据应用id集合查询符合条件的软件信息
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午5:18:49
	 */
	@Override
	public List<TbBusiSoftwareInfoObj> queryForListByAppidUseIn(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj) {
		return tbBusiSoftwareInfoDao.queryForListByAppidUseIn(tbBusiSoftwareInfoObj);
	}
	
	
}

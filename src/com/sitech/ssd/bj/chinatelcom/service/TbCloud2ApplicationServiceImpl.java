package com.sitech.ssd.bj.chinatelcom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.bj.chinatelcom.dao.TbCloud2ApplicationDao;
import com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj;

/**
 * <p>Title: TbCloud2ApplicationServiceImpl</p>
 * <p>Description: 服务应用   逻辑层接口实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipp
 * @version 1.0
 * @createtime 2014-3-20 下午5:19:43
 *
 */
@Service("tbCloud2ApplicationService")
public class TbCloud2ApplicationServiceImpl implements
		TbCloud2ApplicationService {
	
	@Autowired
	private TbCloud2ApplicationDao tbCloud2ApplicationDao;

	/** 
	 * <p>Title: queryForListByObj</p>
	 * <p>Description: 查询记录</p>
	 * @param tbCloud2ApplicationObj
	 * @return
	 * @see com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService#queryForListByObj(com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj)
	 */
	@Override
	public List<TbCloud2ApplicationObj> queryForListByObj(
			TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		return tbCloud2ApplicationDao.queryForListByObj(tbCloud2ApplicationObj);
	}

	/** 
	 * <p>Title: deleteByObj</p>
	 * <p>Description:删除记录，如果传入的是id的集合，则批量删除 </p>
	 * @param tbCloud2ApplicationObj
	 * @return
	 * @see com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService#deleteByObj(com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj)
	 */
	@Override
	public int deleteByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		return tbCloud2ApplicationDao.deleteByObj(tbCloud2ApplicationObj);
	}

	/** 
	 * <p>Title: insertByObj</p>
	 * <p>Description:插入记录 </p>
	 * @param tbCloud2ApplicationObj
	 * @return
	 * @see com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService#insertByObj(com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj)
	 */
	@Override
	public int insertByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		return tbCloud2ApplicationDao.insertByObj(tbCloud2ApplicationObj);
	}

	/** 
	 * <p>Title: updateByObj</p>
	 * <p>Description: 更新记录</p>
	 * @param tbCloud2ApplicationObj
	 * @return
	 * @see com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService#updateByObj(com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj)
	 */
	@Override
	public int updateByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		return tbCloud2ApplicationDao.updateByObj(tbCloud2ApplicationObj);
	}

	/** 
	 * <p>Title: queryForCountByObj</p>
	 * <p>Description:查询记录数目 </p>
	 * @param tbCloud2ApplicationObj
	 * @return
	 * @see com.sitech.ssd.bj.chinatelcom.service.TbCloud2ApplicationService#queryForCountByObj(com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj)
	 */
	@Override
	public int queryForCountByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj) {
		return tbCloud2ApplicationDao.queryForCountByObj(tbCloud2ApplicationObj);
	}

	/**
	 * @Title: queryForObjByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return List<TbCloud2ApplicationObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午5:42:03
	 */
	public TbCloud2ApplicationObj queryForObjByObj(
			TbCloud2ApplicationObj applicationObj){
		List<TbCloud2ApplicationObj> list = tbCloud2ApplicationDao.queryForListByObj(applicationObj);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new TbCloud2ApplicationObj();
		}
	}
}

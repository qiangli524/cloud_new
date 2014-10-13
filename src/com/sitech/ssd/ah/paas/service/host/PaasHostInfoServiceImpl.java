package com.sitech.ssd.ah.paas.service.host;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.paas.dao.host.PaasHostInfoDao;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;

@Service("paasHostInfoService")
public class PaasHostInfoServiceImpl implements PaasHostInfoService {

	@Autowired
	private PaasHostInfoDao paasHostInfoDao;

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description: 查询主机列表
	 * @param
	 * @return List<PaasHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:04
	 */
	@Override
	public List<PaasHostInfoObj> queryForHostList(PaasHostInfoObj obj) {
		return paasHostInfoDao.queryForHostList(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:13
	 */
	@Override
	public int insertByObj(PaasHostInfoObj obj) {
		return paasHostInfoDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条主机信息
	 * @param
	 * @return PaasHostInfoObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:21
	 */
	@Override
	public PaasHostInfoObj queryByObj(PaasHostInfoObj obj) {
		return paasHostInfoDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:30
	 */
	@Override
	public int updateByObj(PaasHostInfoObj obj) {
		return paasHostInfoDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:37
	 */
	@Override
	public int deleteByObj(PaasHostInfoObj obj) {
		return paasHostInfoDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title: queryPhysicsHostList
	 * @Description:查询物理主机集合
	 * @param
	 * @return List<GreenPlumHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午8:26:22
	 */
	@Override
	public List<GreenPlumHostInfoObj> queryPhysicsHostList(GreenPlumHostInfoObj obj) {
		return paasHostInfoDao.queryPhysicsHostList(obj);
	}

}

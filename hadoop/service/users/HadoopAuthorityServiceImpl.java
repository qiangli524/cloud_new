package service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.users.HadoopAuthorityDao;
import domain.users.HadoopAuthorityObj;

/**
 * <p>
 * Title: HadoopAuthorityServiceImpl
 * </p>
 * <p>
 * Description: hadoop权限逻辑层
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-3-5 下午4:52:22
 * 
 */
@Service("hadoopAuthorityService")
public class HadoopAuthorityServiceImpl implements HadoopAuthorityService {

	@Autowired
	private HadoopAuthorityDao hadoopAuthorityDao;

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-5 下午4:26:16
	 */
	@Override
	public int insertByObj(HadoopAuthorityObj authorityObj) {
		return hadoopAuthorityDao.insertByObj(authorityObj);
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopAuthorityObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:20:33
	 */
	@Override
	public List<HadoopAuthorityObj> queryForListByObj(
			HadoopAuthorityObj authorityObj) {
		return hadoopAuthorityDao.queryForListByObj(authorityObj);
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:53:27
	 */
	@Override
	public void updateByObj(HadoopAuthorityObj authorityObj) {
		hadoopAuthorityDao.updateByObj(authorityObj);
	}

}

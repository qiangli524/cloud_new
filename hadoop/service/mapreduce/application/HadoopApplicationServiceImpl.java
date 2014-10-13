package service.mapreduce.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.mapreduce.application.HadoopApplicationDao;
import domain.mapreduce.application.HadoopApplicationObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationServiceImpl
 * </p>
 * <p>
 * Description: 应用相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-11 下午4:57:25
 * 
 */
@Service("hadoopApplicationService")
public class HadoopApplicationServiceImpl implements HadoopApplicationService {
	@Autowired
	private HadoopApplicationDao hadoopApplicationDao;

	/**
	 * 
	 * @Title: queryApplicationListByObj
	 * @Description: 查询应用列表
	 * @param
	 * @return List<HadoopApplicationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	@Override
	public List<HadoopApplicationObj> queryApplicationListByObj(
			HadoopApplicationObj obj) {
		return hadoopApplicationDao.queryApplicationListByObj(obj);
	}
}

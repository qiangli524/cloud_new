package service.mapreduce.application;

import java.util.List;

import domain.mapreduce.application.HadoopApplicationObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationService
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
 * @createtime 2014-1-11 下午4:56:59
 * 
 */
public interface HadoopApplicationService {
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
	public List<HadoopApplicationObj> queryApplicationListByObj(
			HadoopApplicationObj obj);
}

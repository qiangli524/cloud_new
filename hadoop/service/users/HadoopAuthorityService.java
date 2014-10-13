package service.users;

import java.util.List;

import domain.users.HadoopAuthorityObj;

/**
 * <p>
 * Title: HadoopAuthorityService
 * </p>
 * <p>
 * Description: hadoop权限
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
 * @createtime 2014-3-5 下午4:45:37
 * 
 */
public interface HadoopAuthorityService {

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
	public int insertByObj(HadoopAuthorityObj authorityObj);

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopAuthorityObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @param authorityObj
	 * @createtime 2014-3-6 下午3:20:33
	 */
	public List<HadoopAuthorityObj> queryForListByObj(
			HadoopAuthorityObj authorityObj);

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
	public void updateByObj(HadoopAuthorityObj authorityObj);
}

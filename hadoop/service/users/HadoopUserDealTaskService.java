package service.users;

import domain.users.HadoopUserDealTaskObj;

/**
 * <p>
 * Title: HadoopUserDealTaskService
 * </p>
 * <p>
 * Description: 用户任务处理
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
 * @createtime 2014-2-24 上午10:52:07
 * 
 */
public interface HadoopUserDealTaskService {

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午10:52:45
	 */
	public int insertByObj(HadoopUserDealTaskObj taskObj);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 下午2:32:58
	 */
	public int deleteByObj(HadoopUserDealTaskObj taskObj1);
}

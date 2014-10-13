package com.sitech.shop.dao.balance;

import java.util.List;

import com.sitech.shop.domain.balance.LoadBalanceObj;

public interface LoadBalanceDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询负载均衡器列表
	 * @param
	 * @return List<LoadBalanceObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-24 下午2:10:40
	 */
	public List<LoadBalanceObj> queryForList(LoadBalanceObj obj);

	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return LoadBalanceObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public LoadBalanceObj queryByObj(LoadBalanceObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更新一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int updateByObj(LoadBalanceObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 删除一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int deleteByObj(LoadBalanceObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 插入一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int insertByObj(LoadBalanceObj obj);

	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(LoadBalanceObj obj);
}

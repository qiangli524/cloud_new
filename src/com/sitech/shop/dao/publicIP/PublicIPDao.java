package com.sitech.shop.dao.publicIP;

import java.util.List;

import com.sitech.shop.domain.ip.PublicIPObj;

/**
 * @Title PublicIPDao
 * @Description 公网ip持久层接口
 * @author lipp
 * @date 2014-4-25 上午10:34:25
 * @version 1.0
 */
public interface PublicIPDao {

	/**
	 * @Title: updateByObj
	 * @Description: 修改公网ip，不可修改ip地址，只是修改ip地址的使用关联关系
	 * @return int
	 * @throws
	 * @Date 2014-4-25 上午10:45:39
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int updateByObj(PublicIPObj ipObj);

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询基本列表
	 * @return List<PublicIPObj>
	 * @throws
	 * @Date 2014-4-25 上午10:35:18
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public List<PublicIPObj> queryForListByObj(PublicIPObj ipObj);

	/**
	 * @Title: queryForListUnionTablesByObj
	 * @Description: 关联表查询列表
	 * @return List<PublicIPObj>
	 * @throws
	 * @Date 2014-4-25 下午2:37:33
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public List<PublicIPObj> queryForListUnionTablesByObj(PublicIPObj ipObj);

	/**
	 * @Title: releaseByObj
	 * @Description: 释放ip
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午1:15:50
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int releaseByObj(PublicIPObj ipObj);

	/**
	 * @Title: updateBatch
	 * @Description: 批量更新
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:05:46
	 * @author lipp
	 * @param list
	 * @return
	 */
	public void updateBatch(List<PublicIPObj> list);

	/**
	 * @Title: releaseBatch
	 * @Description: 批量解除
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:18:45
	 * @author lipp
	 * @param list
	 * @return
	 */
	public void releaseBatch(List<PublicIPObj> list);

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午2:46:18
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public void insertByObj(PublicIPObj ipObj);

	/**
	 * @Title:insertForBatch
	 * @Description: 批量插入
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午2:46:18
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public void insertForBatch(List<PublicIPObj> list) throws Exception;

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午2:49:37
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int deleteByObj(PublicIPObj ipObj);

	/**
	 * 
	 * @Title: cancelBindByObj
	 * @Description: TODO(取消绑定公网IP 更新tb_public_ip intranet_ip = Null)
	 * @param @param ip
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int cancelBindByObj(PublicIPObj ip);

	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(根据地域，用户，查询公网IP数量)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(PublicIPObj obj);

	/**
	 * 
	 * @Title: queryForIntranetIp
	 * @Description: 查询某一虚拟机对应的需要转换为公网的内网IP地址
	 * @param
	 * @return List<PublicIPObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-12 上午11:26:38
	 */
	public List<PublicIPObj> queryForIntranetIp(PublicIPObj obj);

	/**
	 * 
	 * @Title: updateStatusByIp
	 * @Description: TODO(根据公网iP地址修改IP的状态)
	 * @param @param publicIPObj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int updateStatusByIp(PublicIPObj publicIPObj);
	/** 
	*
	* @Title: queryForObj 
	* @Description: TODO(查询一条记录) 
	* @param @param ipObj
	* @param @return    设定文件 
	* @return PublicIPObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-7-18
	*/
	public PublicIPObj queryForObj(PublicIPObj ipObj);

}

package com.sitech.shop.service.publicip;

import java.util.List;

import com.sitech.shop.domain.ip.PublicIPObj;


/**
 * @Title PublicIPService
 * @Description 公网ip业务层接口
 * @author lipp
 * @date 2014-4-25 下午1:19:02
 * @version 1.0
 */
public interface PublicIPService {
	/**
	释放公网IP和虚拟机的绑定关系
	 */
	public Boolean releaseByPublicIp(PublicIPObj ipObj);
	
	/**
	 * @Title: list
	 * @Description: 展现列表
	 * @return List<PublicIPObj>
	 * @throws
	 * @Date 2014-4-25 下午1:58:57
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public List<PublicIPObj> list(PublicIPObj ipObj);

	
	/** 
	*
	* @Title: queryForObj 
	* @Description: 查询一条公网IP对象
	* @param @param ipObj
	* @param @return    设定文件 
	* @return PublicIPObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-8-7
	*/
	public PublicIPObj queryForObj(PublicIPObj ipObj);
	/**
	 * @Title: apply
	 * @Description: 申请公网ip
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午2:24:52
	 * @author lipp
	 * @param ipObj
	 * @throws Exception
	 */
	public int apply(PublicIPObj ipObj);

	/**
	 * @Title: release
	 * @Description: 释放对公网ip的持有
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:13:09
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int release(PublicIPObj ipObj);

	/**
	 * @Title: bind
	 * @Description: 虚拟机绑定到ip
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:30:19
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int bind(PublicIPObj ipObj) throws Exception;

	/**
	 * @Title: unbind
	 * @Description: 解除绑定（解决和虚拟机的绑定）
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:33:39
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int unbind(PublicIPObj ipObj) throws Exception;

	/**
	 * @Title: update
	 * @Description: 修改ip
	 * @return PublicIPObj
	 * @throws
	 * @Date 2014-4-26 上午10:50:19
	 * @author lipp
	 * @param ipObj
	 * @param userId
	 * @return
	 */
	public PublicIPObj update(PublicIPObj ipObj, String userId);

	/**
	 * @Title: save
	 * @Description: 保存修改
	 * @return int
	 * @throws
	 * @Date 2014-4-28 上午10:30:23
	 * @author lipp
	 * @param ipObj
	 * @param oper
	 * @return
	 */
	public void save(PublicIPObj ipObj, String oper);

	/**
	 * @Title: delete
	 * @Description: 删除记录
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午3:01:54
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public int delete(PublicIPObj ipObj);

	/**
	 * 
	 * @Title: bindPublicIP
	 * @Description: 绑定公网IP地址
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:07:36
	 */
	public Boolean bindPublicIP(String path, PublicIPObj ip);

	/**
	 * 
	 * @Title: cancelBindPublicIP
	 * @Description: 取消绑定公网IP 公网IP表：内网IP设为Null
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:09:52
	 */
	public Boolean cancelBindPublicIP(String path, PublicIPObj ip);

	/**
	 * 
	 * @Title: addInnerIPMapping
	 * @Description: 在已绑定公网IP做内网出口映射
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:19:17
	 */
	public Boolean addInnerIPMapping(String path, PublicIPObj ip);

	/**
	 * 
	 * @Title: cancelInnerIPMapping
	 * @Description: 取消在已绑定公网IP做内网出口映射
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:19:21
	 */
	public Boolean cancelInnerIPMapping(String path, PublicIPObj ip);


	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(PublicIPObj obj);

	/**
	 * 
	 * @Title: updateStatusByIp
	 * @Description: TODO(根据公网iP地址修改IP的状态)
	 * @param @param publicIPObj 设定文件
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Boolean updateStatusByIp(PublicIPObj publicIPObj);

	/** 
	*
	* @Title: queryVlanofVM 
	* @Description: 查询用的公网vlan号
	* @param @param ipObj
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-8-29
	*/
	public Integer queryVlanofVM(PublicIPObj ipObj);
}

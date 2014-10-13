/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: ResourceDestroyService.java 
 * @Package com.sitech.shop.webservice.service 
 * @Description:  资源销毁，回收接口
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-12 下午1:25:12 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service.destroy;

/**
 * @ClassName: ResourceDestroyService
 * @Description: 资源销毁，回收接口1.商城接口回收需要调用
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-12 下午1:25:12
 * @version 1.0
 */
public interface ResourceDestroyService<T> {
	/**
	 * 
	 * @Title: checkRelation
	 * @Description: 检测要删除的对象
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @createtime 2014-9-12
	 */
	Boolean checkRelation(T t) throws Exception;

	/**
	 * 
	 * @Title: destroyResourse
	 * @Description: 销毁资源，调用接口、调用脚本等
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @createtime 2014-9-12
	 */
	Boolean destroyResourse(T t) throws Exception;

	/**
	 * 
	 * @Title: rollbackResourse
	 * @Description: 针对接口、脚本调用成功，更新数据异常，调用此方法回退 接口或脚本。
	 * @param @param t
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-15
	 */
	Boolean rollbackResourse(T t) throws Exception;

	/**
	 * 
	 * @Title: recycleResourse
	 * @Description: 回收资源
	 * @param 设定文件
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-12
	 */
	Boolean recycleResourse(T t) throws Exception;

	/**
	 * 
	 * @Title: noticeBilling
	 * @Description: 通知计费接口
	 * @param 设定文件
	 * @return Boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @createtime 2014-9-12
	 */
	Boolean noticeBilling(T t) throws Exception;
}

/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: FirewallRuleDestroyServiceImpl.java 
 * @Package com.sitech.shop.webservice.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-12 下午1:27:14 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service.destroy;

import org.springframework.stereotype.Service;

import com.sitech.shop.domain.firewall.FirewallObj;

/**
 * @ClassName: FirewallRuleDestroyServiceImpl
 * @Description: 防火墙规则回收，销毁。
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-12 下午1:27:14
 * @version 1.0
 */
@Service("firewallRuleDestroyService")
public class FirewallRuleDestroyServiceImpl implements ResourceDestroyService<FirewallObj> {

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: checkRelation
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(FirewallObj t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: destroyResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(FirewallObj t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: recycleResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	public Boolean recycleResourse(FirewallObj t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: noticeBilling
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(FirewallObj t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: rollbackResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(FirewallObj t) {
		// TODO Auto-generated method stub
		return null;
	}

}

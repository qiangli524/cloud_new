/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: BandWidthDestroyServiceImpl.java 
 * @Package com.sitech.shop.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-16 上午9:50:11 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service.destroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sitech.shop.domain.balance.LoadBalanceObj;

/**
 * @ClassName: BandWidthDestroyServiceImpl
 * @Description: 带宽销毁实现类
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-16 上午9:50:11
 * @version 1.0
 */
@Service("loadBalanceDestroyService")
public class LoadBalanceDestroyServiceImpl implements ResourceDestroyService<LoadBalanceObj> {
	Logger logger = LoggerFactory.getLogger(LoadBalanceDestroyServiceImpl.class);

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
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(LoadBalanceObj t) throws Exception {
		// TODO Auto-generated method stub
		return true;
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
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(LoadBalanceObj t) throws Exception {
		// TODO Auto-generated method stub
		return true;
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
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(LoadBalanceObj t) throws Exception {
		// TODO Auto-generated method stub
		return true;
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
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	public Boolean recycleResourse(LoadBalanceObj t) throws Exception {
		// TODO Auto-generated method stub
		return true;
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
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(LoadBalanceObj t) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}

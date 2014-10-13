package com.sitech.ssd.ah.paas.dao.host;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;

/**
 * 
 * <p>
 * Title: PaasHostInfoDao
 * </p>
 * <p>
 * Description: paas主机相关操作
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
 * @createtime 2014-4-2 下午5:04:00
 * 
 */
public interface PaasHostInfoDao {

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description: 查询主机列表
	 * @param
	 * @return List<PaasHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:04
	 */
	public List<PaasHostInfoObj> queryForHostList(PaasHostInfoObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:13
	 */
	public int insertByObj(PaasHostInfoObj obj);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条主机信息
	 * @param
	 * @return PaasHostInfoObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:21
	 */
	public PaasHostInfoObj queryByObj(PaasHostInfoObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:30
	 */
	public int updateByObj(PaasHostInfoObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午5:08:37
	 */
	public int deleteByObj(PaasHostInfoObj obj);

	/**
	 * 
	 * @Title: queryPhysicsHostList
	 * @Description:查询物理主机集合
	 * @param
	 * @return List<GreenPlumHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午8:26:22
	 */
	public List<GreenPlumHostInfoObj> queryPhysicsHostList(GreenPlumHostInfoObj obj);

}

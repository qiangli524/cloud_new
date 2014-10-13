package com.sitech.basd.bol.dao.bolresource;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.bol.domain.bolresource.BolResourceObj;
import com.sitech.basd.bol.domain.bolresource.BolResourcesRegisterObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>Title: RolResourceDao</p>
 * <p>Description: 资源视图持久层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:40:42
 *
 */
public interface BolResourceDao {

	/**
	 * @Title: countForResource
	 * @Description: 统计资源数量
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:05:58
	 */
	public int countForResource(BolResourceObj bolResourceObj);

	/**
	 * @Title: countHostNum
	 * @Description: 统计主机数量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:33:26
	 */
	public Integer countHostNum();

	/**
	 * @Title: queryForSum
	 * @Description: 查询各种汇总
	 * @param
	 * @return Double
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:44:24
	 */
	public Double queryForSum(BolResourceObj bolResourceObj);

	/**
	 * @Title: queryForUsed
	 * @Description: 查询资源使用量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午11:11:37
	 */
	public Double queryForUsed(BolResourceObj bolResourceObj);

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询资源列表
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:54:23
	 */
	public List<BolResourceObj> queryForListByObj(BolResourceObj bolResourceObj);

	/**
	 * @Title: queryResourceForProcess
	 * @Description: 展示某种资源关联的资源
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:02:52
	 */
	public List<BolResourceObj> queryResourceForProcess(BolResourceObj bolResourceObj);
	
	/**
	 * 
	 * @Title: queryResourcesRegister
	 * @Description: 查询资源登记情况，即 每个进程的资源占用情况
	 * @param
	 * @return List<BolResourcesRegisterObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 16, 2014 1:01:32 PM
	 */
	public List<BolResourcesRegisterObj> queryResourcesRegister(BolResourcesRegisterObj obj);

}

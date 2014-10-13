package service.jvm;


import java.util.List;

import com.sitech.basd.fusioncharts.vo.FusionCharts;

import domain.host.HadoopHostInfoObj;
import domain.jvm.HadoopJvmObj;

/**
 * 
 * <p>
 * Title: HadoopJvmDao
 * </p>
 * <p>
 * Description: jvm 相关操作
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
 * @createtime 2014-1-18 下午5:21:02
 * 
 */
public interface HadoopJvmService {
	/**
	 * 
	 * @Title: queryHadoopJvm
	 * @Description: jvm 图标
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午9:54:34
	 */
	public FusionCharts queryHadoopJvm(HadoopJvmObj obj);
	
	/**
	 * 
	 * @Title: queryLogList
	 * @Description: Log中输出ERROR,FATAL的主机集合
	 * @param
	 * @return List<HadoopJvmObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 下午2:36:20
	 */
	public List<HadoopJvmObj> queryLogList(HadoopJvmObj obj) throws Exception;
	
	/**
	 * 
	 * @Title: queryLogHostCount
	 * @Description: Log中输出ERROR,FATAL的主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 上午11:46:51
	 */
	public int queryLogHostCount(HadoopJvmObj obj) throws Exception;
	
	/**
	 * 
	 * @Title: showHostList
	 * @Description: 展示主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 下午4:03:12
	 */
	public List<HadoopHostInfoObj> showHostList(HadoopJvmObj obj) throws Exception;
	
	
	
}

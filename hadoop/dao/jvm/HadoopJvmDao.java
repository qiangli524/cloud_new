package dao.jvm;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Data;

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
public interface HadoopJvmDao {
	/**
	 * 
	 * @Title: queryTimeLable
	 * @Description: 查询横坐标
	 * @param
	 * @return List<Category>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午11:51:30
	 */
	public List<Category> queryTimeLable(HadoopJvmObj obj);
	/**
	 * 
	 * @Title: queryHadoopJvm
	 * @Description: 查询jvm值
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午9:40:16
	 */
	public List<Data> queryHadoopJvm(HadoopJvmObj obj);

	/**
	 * 
	 * @Title: queryLogList
	 * @Description: Log中输出ERROR,FATAL的主机集合
	 * @param
	 * @return List<HadoopJvmObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-18 下午2:36:20
	 */
	public List<HadoopJvmObj> queryLogList(HadoopJvmObj obj);
}

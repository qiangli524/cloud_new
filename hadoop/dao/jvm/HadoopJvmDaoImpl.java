package dao.jvm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

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
@Repository("hadoopJvmDao")
public class HadoopJvmDaoImpl extends BaseDao implements HadoopJvmDao{
	
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
	@Override
	public List<Category> queryTimeLable(HadoopJvmObj obj) {
		List<Category> list = new ArrayList<Category>();
		try {
			list = getSqlMap().queryForList("HadoopJvm.queryTimeLable", obj);
		} catch (SQLException e) {
			LogHelper.error("HadoopJvm.queryTimeLable:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}
	
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
	@Override
	public List<Data> queryHadoopJvm(HadoopJvmObj obj) {
		List<Data> list = null;
		try {
			list = getSqlMap().queryForList("HadoopJvm.queryHadoopJvm",obj);
		} catch (SQLException e) {
			logger.error("HadoopJvm.queryHadoopJvm:"+e.getMessage()+e.getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<HadoopJvmObj> queryLogList(HadoopJvmObj obj) {
		List<HadoopJvmObj> list = null;
		try {
			list = getSqlMap().queryForList("HadoopJvm.queryLogList",obj);
		} catch (SQLException e) {
			logger.error("HadoopJvm.queryLogList:"+e.getMessage()+e.getClass().getName());
		}
		return list;
	}
}

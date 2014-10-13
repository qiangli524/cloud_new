package com.sitech.basd.deployfile.dao;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.deployfile.domain.BaseAppFileTreeVO;
import com.sitech.utils.capture.vo.StandardAppVO;

/**
 * 
 * <p>
 * Title: TbBaseAppFileTreeDao
 * </p>
 * <p>
 * Description: 基准应用文件树
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-8-25 上午10:38:25
 * 
 */
public interface TbBaseAppFileTreeDao {
	/**
	 * 
	 * @Title: insertTree
	 * @Description: 插入
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 上午11:39:10
	 */
	public int insertTree(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: queryTreeObj
	 * @Description:查询树节点信息
	 * @param
	 * @return DeployFileTreeObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-12-17 上午8:40:44
	 */
	public BaseAppFileTreeVO queryTreeObj(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树
	 * @param
	 * @return List<SwiftBackupFileTreeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:02:35
	 */
	public List<BaseAppFileTreeVO> queryForTree(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 下午4:02:06
	 */
	public int updateByObj(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: queryBaseAppInfo
	 * @Description: 查询基准应用信息
	 * @param
	 * @return StandardAppVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-19 下午1:59:54
	 */
	public StandardAppVO queryBaseAppInfo(StandardAppVO obj) throws SQLException;
}

package com.sitech.basd.deployfile.service;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.deployfile.domain.BaseAppFileTreeVO;

import deploy.AppFileTreeObj;

public interface BaseAppFileTreeService {

	/**
	 * 
	 * @Title: initTreelist
	 * @Description: 初始化树节点
	 * @param
	 * @return List<BaseAppFileTreeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:48:51
	 */
	public abstract List<BaseAppFileTreeVO> initTreelist(List<BaseAppFileTreeVO> resultList)
			throws SQLException;

	/**
	 * 
	 * @Title: updateForTree
	 * @Description:更新树节点
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-25 上午11:17:07
	 */
	public abstract int updateForTree(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<BaseAppFileTreeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-7-17 上午10:45:58
	 */
	public abstract List<BaseAppFileTreeVO> queryForTree(BaseAppFileTreeVO obj) throws SQLException;

	/**
	 * 
	 * @Title: initBaseAppFileTree
	 * @Description: 实例基准应用文件树
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-7-17 上午10:18:16
	 */
	public abstract void initBaseAppFileTree(String host, Integer port, String username,
			String password, String path, String baseappid) throws SQLException;

	/**
	 * 
	 * @Title: CreateFileTreeByAppid
	 * @Description: 为基准应用生成文件树
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-11 下午4:46:36
	 */
	public String CreateFileTreeByAppid(AppFileTreeObj obj);

}
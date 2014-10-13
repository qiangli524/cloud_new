package com.sitech.basd.cloud3.service.app;

import java.util.List;

import com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO;

public interface TbCloud3AppFileTreeService {

	/**
	 * 
	 * @Title: insertByVO
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-16 下午1:26:45
	 */
	public abstract String insertByVO(TbCloud3AppFileTreeVO obj);

	/**
	 * 
	 * @Title: queryVOListByParentID
	 * @Description: 查询接口列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 11, 2013 9:24:31 AM
	 */
	public abstract List<TbCloud3AppFileTreeVO> queryVOListByParentID(
			TbCloud3AppFileTreeVO obj);

	/**
	 * 
	 * @Title: queryVOByParentPath
	 * @Description: 查询接口列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 11, 2013 9:24:31 AM
	 */
	public abstract TbCloud3AppFileTreeVO queryVOByParentPath(
			TbCloud3AppFileTreeVO obj);

}
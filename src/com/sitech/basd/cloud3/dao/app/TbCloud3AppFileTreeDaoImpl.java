package com.sitech.basd.cloud3.dao.app;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: TbCloud3AppFileTreeDaoImpl
 * </p>
 * <p>
 * Description: 应用部署目录、文件树数据库操作实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-3-16 下午1:25:10
 * 
 */
public class TbCloud3AppFileTreeDaoImpl extends BaseDao implements TbCloud3AppFileTreeDao {
	/*
	 * <p>Title: insertByVO</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see com.sitech.basd.cloud3.dao.app.TbCloud3AppFileTreeDao#insertByVO(com.
	 *      sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO)
	 */
	public String insertByVO(TbCloud3AppFileTreeVO obj) {
		String ret = "";
		try {
			Object o = getSqlMap().insert("TbCloud3AppFileTree.insertByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppFileTree.insertByVO:" + e.getMessage());
		}
		return ret;
	}

	/*
	 * <p>Title: queryVOListByParentID</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see com.sitech.basd.cloud3.dao.app.TbCloud3AppFileTreeDao#queryVOListByParentID
	 *      (com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO)
	 */
	public List<TbCloud3AppFileTreeVO> queryVOListByParentID(TbCloud3AppFileTreeVO obj) {
		List<TbCloud3AppFileTreeVO> lst = null;
		try {
			lst = (List<TbCloud3AppFileTreeVO>) getSqlMap().queryForList(
					"TbCloud3AppFileTree.queryVOListByParentID", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppFileTree.queryVOListByParentID:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/*
	 * <p>Title: queryVOByParentPath</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see com.sitech.basd.cloud3.dao.app.TbCloud3AppFileTreeDao#queryVOByParentPath
	 *      (com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO)
	 */
	public TbCloud3AppFileTreeVO queryVOByParentPath(TbCloud3AppFileTreeVO obj) {
		TbCloud3AppFileTreeVO vo = null;
		try {
			vo = (TbCloud3AppFileTreeVO) getSqlMap().queryForObject(
					"TbCloud3AppFileTree.queryVOByParentPath", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppFileTree.queryVOByParentPath:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return vo;
	}

}

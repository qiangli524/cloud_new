package com.sitech.basd.deployfile.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.deployfile.domain.BaseAppFileTreeVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.capture.vo.StandardAppVO;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: TbBaseAppFileTreeDaoImpl
 * </p>
 * <p>
 * Description: Swift备份文件树Dao实现类
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
 * @createtime 2014-7-17 上午10:06:06
 * 
 */
@Repository("tbBaseAppFileTreeDao")
public class TbBaseAppFileTreeDaoImpl extends BaseDao implements TbBaseAppFileTreeDao {

	@Override
	public int insertTree(BaseAppFileTreeVO obj) throws SQLException {
		obj.setId(RandomUUID.getUuid());
		Object oj = sqlMapClient.insert("TbBaseAppFileTree.insertForTree", obj);
		return 0;
	}

	@Override
	public BaseAppFileTreeVO queryTreeObj(BaseAppFileTreeVO obj) throws SQLException {
		BaseAppFileTreeVO vo = null;
		vo = (BaseAppFileTreeVO) getSqlMapClient().queryForObject("TbBaseAppFileTree.queryTreeObj",
				obj);
		return vo;
	}

	@Override
	public List<BaseAppFileTreeVO> queryForTree(BaseAppFileTreeVO obj) throws SQLException {
		List<BaseAppFileTreeVO> list = null;
		list = getSqlMapClient().queryForList("TbBaseAppFileTree.queryForTree", obj);
		return list;
	}

	@Override
	public StandardAppVO queryBaseAppInfo(StandardAppVO obj) throws SQLException {
		StandardAppVO vo = null;
		vo = (StandardAppVO) getSqlMapClient().queryForObject("TbBaseAppFileTree.queryBaseAppInfo",
				obj);
		return vo;
	}

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
	@Override
	public int updateByObj(BaseAppFileTreeVO obj) throws SQLException {
		Object oj = sqlMapClient.update("TbBaseAppFileTree.updateByObj", obj);
		return 0;
	}
}

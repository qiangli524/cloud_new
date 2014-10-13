package com.sitech.console.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.console.domain.TbVncPortVO;

/**
 * 
 * <p>
 * Title: TbVncPortDaoImpl
 * </p>
 * <p>
 * Description: tb_vnc_port 数据库操作接口实现类
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
 * @createtime 2014-7-30 下午4:38:41
 * 
 */
@Repository("vncPortDao")
public class VncPortDaoImpl extends BaseDao implements VncPortDao {

	@Override
	public List<TbVncPortVO> queryForListByObj(TbVncPortVO vo) throws SQLException {
		if (vo.getPagination() != null) {
			vo.setFIRSTROWNUM(vo.getPagination().getFirstRownum());
			vo.setPAGESIZE(vo.getPagination().getPageSize());
			vo.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject("VncPort.queryForCount", vo)).intValue());
		}
		return getSqlMapClient().queryForList("VncPort.queryForListByObj", vo);
	}

	@Override
	public void insertByObj(TbVncPortVO vo) throws SQLException {
		vo.setId(UUIDGenerator.getUUID());
		getSqlMapClient().insert("VncPort.insertByObj", vo);
	}

	@Override
	public void updateByObj(TbVncPortVO vo) throws SQLException {
		getSqlMapClient().update("VncPort.updateByObj", vo);
	}

	@Override
	public void deleteByObj(TbVncPortVO vo) throws SQLException {
		getSqlMapClient().delete("VncPort.deleteByObj", vo);
	}

	@Override
	public void releaseVncPost(TbVncPortVO vo) throws SQLException {
		getSqlMapClient().update("VncPort.releaseVncPost", vo);
	}
}

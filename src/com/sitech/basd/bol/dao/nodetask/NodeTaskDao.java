package com.sitech.basd.bol.dao.nodetask;

import java.util.List;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;

@SuppressWarnings("all")
public interface NodeTaskDao {
	public BolNodeTaskVO queryByObj(BolNodeTaskVO obj);
	public List<BolNodeTaskVO> queryForListByObj(BolNodeTaskVO obj);
	public int insertByObj(BolNodeTaskVO obj);
	public int updateByObj(BolNodeTaskVO obj);
	public int deleteByObj(BolNodeTaskVO obj);

}

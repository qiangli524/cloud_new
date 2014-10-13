package com.sitech.basd.bol.service.nodetask;

import java.util.List;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;

public interface NodeTaskService {
	public BolNodeTaskVO queryByObj(BolNodeTaskVO obj);
	public List<BolNodeTaskVO> queryForListByObj(BolNodeTaskVO obj);
	public int insertByObj(BolNodeTaskVO obj);
	public int updateByObj(BolNodeTaskVO obj);
	public int deleteByObj(BolNodeTaskVO obj);
}

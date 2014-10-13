package com.sitech.basd.envmanager.dao.develop;

import java.util.List;

import com.sitech.basd.envmanager.domain.develop.DevelopObj;


public interface DevelopDao {
	
	public List queryDevelopObj(DevelopObj obj);
	
	public DevelopObj queryDevelopOne(DevelopObj obj);
	
	public int insertDevelopObj(DevelopObj obj);
	
	public int updateDevelopObj(DevelopObj obj);
	
	public int deleteDevelopObj(DevelopObj obj);

}

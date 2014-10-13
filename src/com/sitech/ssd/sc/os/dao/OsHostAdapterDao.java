package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.HostAdapter;

/**
  * @Title: 主机网络适配器DAO
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午05:33:26
 */
public interface OsHostAdapterDao {
	
	public int insertHostAdapter(HostAdapter adapter);
	
	public int deleteHostAdapter(HostAdapter adapter);
	
	public int updateHostAdapter(HostAdapter adapter);
	
	public List<HostAdapter> selectHostAdapterList(HostAdapter adapter);
}

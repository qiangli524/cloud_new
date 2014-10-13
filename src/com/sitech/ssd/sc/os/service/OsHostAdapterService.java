package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.HostAdapter;

public interface OsHostAdapterService {
	
	public int modifyHostAdapter(HostAdapter adapter);

	public List<HostAdapter> queryHostAdapterList(HostAdapter adapter);
	
	public HostAdapter queryHostAdapter(HostAdapter adapter);

}

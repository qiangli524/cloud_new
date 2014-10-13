package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sc.os.dao.OsHostAdapterDao;
import com.sitech.ssd.sc.os.domain.HostAdapter;

@Service("osHostAdapterService")
public class OsHostAdapterServiceImpl extends BaseDao implements
		OsHostAdapterService {

	@Autowired
	private OsHostAdapterDao osHostAdapterDao;

	@Override
	public int modifyHostAdapter(HostAdapter adapter) {
		return osHostAdapterDao.updateHostAdapter(adapter);
	}

	@Override
	public List<HostAdapter> queryHostAdapterList(HostAdapter adapter) {
		return osHostAdapterDao.selectHostAdapterList(adapter);
	}
	
	@Override
	public HostAdapter queryHostAdapter(HostAdapter adapter){
		HostAdapter _dap = new HostAdapter();
		if(!"".equals(adapter.getNic_order())&&
				!"".equals(adapter.getOs_host_id())){
			List _temp = osHostAdapterDao.selectHostAdapterList(adapter);
			if(_temp.size() > 0 ){
				_dap = (HostAdapter)_temp.get(0);
			}
		}		
		return _dap;
	}
}

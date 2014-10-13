package com.sitech.ssd.ah.paas.service.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.paas.dao.tab.GreenPlumTabDao;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;

@Service("greenPlumTabService")
public class GreenPlumTabServiceImpl implements GreenPlumTabService {

	@Autowired
	private GreenPlumTabDao greenPlumTabDao;

	@Override
	public List<GreenPlumHostInfoObj> queryGreenPlumHostList(GreenPlumHostInfoObj obj) {
		return greenPlumTabDao.queryGreenPlumHostList(obj);
	}
}

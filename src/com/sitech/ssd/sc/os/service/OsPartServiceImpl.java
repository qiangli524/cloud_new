package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsPartDao;
import com.sitech.ssd.sc.os.domain.OsPartModel;


@Service("osPartService")
public class OsPartServiceImpl implements OsPartService {

	@Autowired
	private OsPartDao osPartDao;
	
	@Override
	public int copyOsTemplatePart(OsPartModel part) {
		return osPartDao.copyOsTemplatePart(part);
	}

	@Override
	public int deleteAllOsPart(OsPartModel part) {
		return osPartDao.deleteOsPart(part);
	}
	
	@Override
	public int deleteOsTemplatePart(OsPartModel part) {
		return osPartDao.deleteOsPart(part);
	}

	@Override
	public List<OsPartModel> queryOsPartList(OsPartModel part) {
		return osPartDao.selectOsPartList(part);
	}

	@Override
	public List<OsPartModel> unionOsPartList(OsPartModel part) {
		return osPartDao.unionOsPartList(part);
	}
}

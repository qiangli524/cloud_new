package com.sitech.ssd.sc.ostempl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.ostempl.dao.OsTemplateDao;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

/**
 * 
 * @ClassName: OsTemplateServiceImpl
 * @Description: OS安装模版Sevice实现类
 * @author JamTau
 * @date 2014-8-20 下午6:04:39
 *
 */
@Service("osTemplateService")
public class OsTemplateServiceImpl implements OsTemplateService {
	
	@Resource
	private OsTemplateDao osTemplateDao;

	@Override
	public int saveOsTemplate(OsTemplate obj) {
		int res = -1;
		res = osTemplateDao.insertOsTemplate(obj);
		return res;
	}

	@Override
	public int deleteOsTemplate(OsTemplate obj) {
		int res = -1;
		res = osTemplateDao.deleteOsTemplate(obj);
		return res;
	}

	@Override
	public int modifyOsTemplate(OsTemplate obj) {
		return osTemplateDao.updateOsTemplate(obj);
	}

	@Override
	public OsTemplate queryOsTemplate(OsTemplate obj) {
		return osTemplateDao.selectOsTemplate(obj);
	}

	@Override
	public List<OsTemplate> queryOsTemplateList(OsTemplate obj) {
		return osTemplateDao.selectOsTemplateList(obj);
	}

}

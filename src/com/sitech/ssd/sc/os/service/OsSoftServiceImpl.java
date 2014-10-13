package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsSoftDao;
import com.sitech.ssd.sc.os.domain.OsSoftModel;

@Service("osSoftService")
public class OsSoftServiceImpl implements OsSoftService {
	
	@Autowired
	private OsSoftDao osSoftDao; 
	
	@Override
	public int copyOsTemplateSoft(OsSoftModel soft) {
		return osSoftDao.copyOsTemplateSoft(soft);
	}

	@Override
	public int deleteAllOsSoft(OsSoftModel soft) {
		if(soft==null || soft.getOs_host_id()==null
				|| "".equals(soft.getOs_host_id())){
			return -1;
		}
		return osSoftDao.deleteOsSoft(soft);
	}

	@Override
	public int deleteOsTemplateSoft(OsSoftModel soft){
		if(soft==null || soft.getOs_host_id()==null || soft.getFlag() ==null
				|| "".equals(soft.getOs_host_id()) || "".equals(soft.getFlag())){
			return -1;
		}
		return osSoftDao.deleteOsSoft(soft);
	}
	
	@Override
	public List<OsSoftModel> queryOsSoftList(OsSoftModel soft) {
		return osSoftDao.selectOsSoftList(soft);
	}
	
	@Override
	public List<OsSoftModel> unionOsSoftList(OsSoftModel soft){
		return osSoftDao.unionOsSoftList(soft);
	}
	

}

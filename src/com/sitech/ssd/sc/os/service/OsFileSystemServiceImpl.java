package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsFileSystemDao;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;

@Service("osFileSystemService")
public class OsFileSystemServiceImpl implements OsFileSystemService {
	
	@Autowired
	private OsFileSystemDao OsFileSystemDao;

	@Override
	public int addOsFileSystem(OsFileSystemModel model) {
		return OsFileSystemDao.insertOsFileSystem(model);
	}

	@Override
	public int copyOsTemplateFileSystem(OsFileSystemModel model) {
		return OsFileSystemDao.copyOsTemplateFileSystem(model);
	}

	@Override
	public int batchAddOsFileSystem(List<OsFileSystemModel> list) {
		return 0;
	}

	@Override
	public int deleteOsFileSystem(OsFileSystemModel model) {
		int _ret = -1;
		if(model.getId() != null && !"".equals(model.getId())){
			OsFileSystemDao.deleteOsFileSystem(model);
			_ret = 1;
		}
		return _ret;
	}

	@Override
	public int deleteOsFileSystemList(OsFileSystemModel model){
		return OsFileSystemDao.deleteOsFileSystem(model);
	}
	
	@Override
	public int modifyOsFileSystem(OsFileSystemModel model) {
		return OsFileSystemDao.updateOsFileSystem(model);
	}

	@Override
	public List<OsFileSystemModel> queryOsFileSystemList(OsFileSystemModel model) {
		return OsFileSystemDao.selectOsFileSystemList(model);
	}

	@Override
	public OsFileSystemModel queryOsFileSystem(OsFileSystemModel model) {
		return OsFileSystemDao.selectOsFileSystem(model);
	}
	
	@Override
	public List<OsFileSystemModel> queryHomeDirList(OsFileSystemModel model){
		return OsFileSystemDao.selectHomeDirList(model);
	}

	@Override
	public List<OsFileSystemModel> unionOsFileSystemList(OsFileSystemModel model) {
		return OsFileSystemDao.unionOsFileSystemList(model);
	}
	
}

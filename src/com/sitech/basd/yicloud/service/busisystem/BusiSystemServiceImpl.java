package com.sitech.basd.yicloud.service.busisystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.busisystem.BusiSystemDao;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

@Service("busiSystemService")
public class BusiSystemServiceImpl implements BusiSystemService{

	@Autowired
	private BusiSystemDao busiSystemDao;
	
	/**
	 * @Title: queryForListByObj
	 * @Description: 根据传进来的实体属性条件查询符合条件的记录集合
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午7:29:40
	 */
	@Override
	public List<BusiSystemObj> queryForListByObj(BusiSystemObj busiSystemObj) {
		return busiSystemDao.queryForListByObj(busiSystemObj);
	}

}

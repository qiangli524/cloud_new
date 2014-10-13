package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbBusiSoftwareFileInfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareFileInfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

@Service("softwareFileInfoService")
public class SoftwareFileInfoServiceImpl extends BaseService implements SoftwareFileInfoService {
	@Autowired
	private TbBusiSoftwareFileInfoDao tbBusiSoftwareFileInfoDao;

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 插入信息到软件文件表
	 * @param
	 * @return int
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-10 下午3:06:32
	 */
	public int insertByObj(TbBusiSoftwareFileInfoObj obj) {
		// TODO Auto-generated method stub
		return tbBusiSoftwareFileInfoDao.insertByObj(obj);

	}
}

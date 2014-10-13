package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbBusiAppPortDao;
import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbSysAppDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiAppPortObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class AppServiceImpl extends BaseService implements AppService {
	private TbSysAppDao tbSysAppDao;

	private TbBusiAppPortDao tbBusiAppPortDao;

	public void setTbSysAppDao(TbSysAppDao tbSysAppDao) {
		this.tbSysAppDao = tbSysAppDao;
	}

	public void setTbBusiAppPortDao(TbBusiAppPortDao tbBusiAppPortDao) {
		this.tbBusiAppPortDao = tbBusiAppPortDao;
	}

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysAppObj obj) {
		return tbSysAppDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysAppObj obj) {
		// String str[]=obj.getPORTALL().split("\\|");
		// String str[] = obj.getPORTALL().replace("||", "|").split("\\|");
		// TbBusiAppPortObj appportobj = new TbBusiAppPortObj();
		int appid = tbSysAppDao.insertByObj(obj);
		// appportobj.setAPPID(appid);
		// for (int i = 0; i < str.length; i++) {
		// appportobj.setPORT(str[i]);
		// tbBusiAppPortDao.insertByObj(appportobj);
		// }
		return appid;
	}

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysAppObj queryByObj(TbSysAppObj obj) {
		return tbSysAppDao.queryByObj(obj);
	}

	/**
	 * @Title:根据应用部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysAppObj obj) {
		return tbSysAppDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysAppObj obj) {
		// obj.getPORTALL().replace("||", "|");

		int ret = tbSysAppDao.updateByObj(obj);
		if (obj.getPORTALL() != null && obj.getPORTALL() != "") {
			String str[] = obj.getPORTALL().replace("||", "|").split("\\|");
			TbBusiAppPortObj appportobj = new TbBusiAppPortObj();
			appportobj.setAPPID(obj.getID());
			if (ret > 0) {
				@SuppressWarnings("unused")
				int portRet = tbBusiAppPortDao.deleteAppidByObj(appportobj);// 删除主机下的所有应用
				for (int i = 0; i < str.length; i++) {
					appportobj.setPORT(str[i]);
					tbBusiAppPortDao.insertByObj(appportobj);
				}
			}
		}
		return ret;
	}

	public int StartAndStopByObj(TbSysAppObj obj) {
		return tbSysAppDao.StartAndStopByObj(obj);
	}

	public int deployRequest(TbSysAppObj obj) {
		return tbSysAppDao.deployRequest(obj);
	}

	public int catchRequestByObj(TbSysAppObj obj) {
		return tbSysAppDao.catchRequestByObj(obj);
	}

	public List queryListIDByObj(TbSysAppObj obj) {
		return tbSysAppDao.queryListIDByObj(obj);
	}

	/**
	 * 
	 * @Title: updateOnlinePath
	 * @Description: 更新上线或回滚文件列表
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-15 下午2:38:47
	 */
	public int updateOnlinePath(TbSysAppObj obj) {
		return tbSysAppDao.updateOnlinePath(obj);
	}

	/**
	 * 
	 * @Title: queryForSheet
	 * @Description:导入主机数据前下载模板查询一些基准应用的基本信息写到excel的sheet页中
	 * @author duangh
	 * @date Jun 15, 2013 6:13:59 PM
	 * @return
	 */
	@Override
	public List<Map<String, String>> queryForSheet() {
		// TODO Auto-generated method stub
		return tbSysAppDao.queryForSheet();
	}

	/**
	 * 
	 * @Title: queryForListByIds
	 * @Description:根据id集合获取所有的基准应用
	 * @author lipengpeng
	 * @date Jun 15, 2013 6:13:59 PM
	 * @return
	 */
	@Override
	public List<TbSysAppObj> queryForListByIds(String appid) {
		// TODO Auto-generated method stub
		return tbSysAppDao.queryForListByIds(appid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService#countApp(java
	 * .lang.String)
	 */
	@Override
	public int countApp(TbSysAppObj tbSysAppObj) {
		return tbSysAppDao.countApp(tbSysAppObj);
	}
	
	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据id的集合查询应用
	 * @param
	 * @return List<TbSysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-18 下午9:08:30
	 */
	@Override
	public List<TbSysAppObj> queryForListUseIn(TbSysAppObj tbSysAppObj) {
		return tbSysAppDao.queryForListUseIn(tbSysAppObj);
	}
}

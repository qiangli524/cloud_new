package com.sitech.basd.yicloud.service.xenstore;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.dao.xenstore.XenStoreDao;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;

public class XenStoreServiceImpl implements XenStoreService {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询数据存储列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:46:15 AM
	 */
	public List queryForListByObj(XenStoreObj obj) {
		return xenStoreDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: insertDatastore
	 * @Description: 向TB_YICLOUD_DATASTORE表插入信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int insertDatastore(XenStoreObj obj) {
		return xenStoreDao.insertDatastore(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新TB_YICLOUD_DATASTORE表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int updateByObj(XenStoreObj obj) {
		return xenStoreDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除TB_YICLOUD_DATASTORE表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int deleteByObj(XenStoreObj obj) {
		return xenStoreDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 查询一条存储记录
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public XenStoreObj queryByObj(XenStoreObj obj) {
		return xenStoreDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: querySRIdSequence
	 * @Description: 查询存储的id
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 14, 2013 4:06:46 PM
	 */
	public int querySRIdSequence() {
		return xenStoreDao.querySRIdSequence();
	}

	/**
	 * 
	 * @Title: updateXenStoreMess
	 * @Description: 更新xen存储的信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 11:48:28 AM
	 */
	public int updateXenStoreMess(XenStoreObj obj) {
		return xenStoreDao.updateXenStoreMess(obj);
	}

	/**
	 * 
	 * @Title: queryForStoreList
	 * @Description: 查询所有存储
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 4:53:41 PM
	 */
	public List queryForStoreList(XenStoreObj obj) {
		return xenStoreDao.queryForStoreList(obj);
	}

	/**
	 * 
	 * @Title: deleteStoreRelation
	 * @Description: 删除存储和主机的关系
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 4:54:39 PM
	 */
	public int deleteStoreRelation(XenStoreObj obj) {
		return xenStoreDao.deleteStoreRelation(obj);
	}

	/**
	 * 
	 * @Title: queryForConnectStoreHostCountByObj
	 * @Description: 查询存储关联的主机数
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 29, 2013 5:56:01 PM
	 */
	public int queryForConnectStoreHostCountByObj(XenStoreObj obj) {
		return xenStoreDao.queryForConnectStoreHostCountByObj(obj);
	}

	/**
	 * 
	 * @Title: queryNoRepeatListByObj
	 * @Description: 查询所有不重复的xen存储
	 * @param
	 * @return List<XenStoreObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-6 下午1:05:08
	 */
	public List<XenStoreObj> queryNoRepeatListByObj(XenStoreObj obj) {
		return xenStoreDao.queryNoRepeatListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryNewVmStore
	 * @Description: 查询新建虚拟机时，可挂载的存储
	 * @param
	 * @return List<XenStoreObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-6 下午1:23:59
	 */
	public List<XenStoreObj> queryNewVmStore(XenStoreObj obj) {
		List<XenStoreObj> lst = xenStoreDao.queryForListByObj(obj);
		List<XenStoreObj> reLst = new ArrayList<XenStoreObj>();
		for (XenStoreObj xenStoreObj : lst) {
			if (xenStoreObj.getType().equals("LVM")) {
				TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
				hostObj.setH_uuid(obj.getDependent_host_uuid());
				hostObj = hostInfoService.queryByObj(hostObj);
				xenStoreObj.setName(hostObj.getEq_name() + "-" + xenStoreObj.getName());
				reLst.add(xenStoreObj);
			} else if (xenStoreObj.getType().equals("NFS")) {
				reLst.add(xenStoreObj);
			}
		}
		return reLst;
	}

	XenStoreDao xenStoreDao;
	private HostInfoService hostInfoService;

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setXenStoreDao(XenStoreDao xenStoreDao) {
		this.xenStoreDao = xenStoreDao;
	}

	/**
	 * @Title: countResource
	 * @Description: 统计xen存储使用情况
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午2:49:01
	 */
	@Override
	public CMSObj countResource(XenStoreObj xenStoreObj) {
		return xenStoreDao.countResource(xenStoreObj);
	}
	
	/**
	 * 
	 * @Title: relevanceDataStore
	 * @Description: 关联存储块
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 下午12:51:43
	 */
	public int relevanceDataStore(XenStoreObj obj){
		return xenStoreDao.relevanceDataStore(obj);
	}
}

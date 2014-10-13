package com.sitech.basd.yicloud.dao.xenstore;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;

public interface XenStoreDao {
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
	public List queryForListByObj(XenStoreObj obj);

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
	public int insertDatastore(XenStoreObj obj);

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
	public int updateByObj(XenStoreObj obj);

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
	public int deleteByObj(XenStoreObj obj);

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
	public XenStoreObj queryByObj(XenStoreObj obj);

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
	public int querySRIdSequence();

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
	public int updateXenStoreMess(XenStoreObj obj);

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
	public List queryForStoreList(XenStoreObj obj);

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
	public int deleteStoreRelation(XenStoreObj obj);

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
	public int queryForConnectStoreHostCountByObj(XenStoreObj obj);

	/**
	 * @Title: queryBatchForBestHost
	 * @Description: 获取xen最佳主机
	 * @param
	 * @return Map<String,List<JSONObject>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午4:10:43
	 */
	public Map<String, List<JSONObject>> queryBatchForBestHost(List<TbCloud2HostInfoObj> hostList,
			WorkOrderObj obj);

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
	public List<XenStoreObj> queryNoRepeatListByObj(XenStoreObj obj);

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
	public CMSObj countResource(XenStoreObj xenStoreObj);
	
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
	public int relevanceDataStore(XenStoreObj obj);
}

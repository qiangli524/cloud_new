package com.sitech.ssd.gx.service.entitytree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sitech.ssd.gx.constant.HuaweiRestURI;
import com.sitech.ssd.gx.dao.entitytree.HuaweiEntityTreeDao;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiEntityTreeObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.huawei.Cluster;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.Hosts;

public class HuaweiEntityTreeServiceImpl implements HuaweiEntityTreeService {
	private HuaweiEntityTreeDao huaweiEntityTreeDao;
	
	public void setHuaweiEntityTreeDao(HuaweiEntityTreeDao huaweiEntityTreeDao) {
		this.huaweiEntityTreeDao = huaweiEntityTreeDao;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return List<HuaweiEntityTreeObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:35:35
	 */
	public List<HuaweiEntityTreeObj> queryForTree(HuaweiEntityTreeObj obj) {
		return huaweiEntityTreeDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: insertTreeNode
	 * @Description: 向树中插入节点
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:48:28
	 */
	public int insertTreeNode(HuaweiEntityTreeObj obj) {
		return huaweiEntityTreeDao.insertTreeNode(obj);
	}

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return HuaweiEntityTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:48:16
	 */
	public HuaweiEntityTreeObj queryTreeNode(HuaweiEntityTreeObj obj) {
		return huaweiEntityTreeDao.queryTreeNode(obj);
	}
	
	/**
	 * 
	 * @Title: deleteAllData
	 * @Description:删除库中原有数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	public int deleteAllData() {
		return huaweiEntityTreeDao.deleteAllData();
	}

}

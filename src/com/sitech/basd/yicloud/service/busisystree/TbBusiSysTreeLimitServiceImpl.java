package com.sitech.basd.yicloud.service.busisystree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.dao.busisystree.TbBusiSysTreeLimitDao;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeLimitServiceImpl
 * </p>
 * <p>
 * Description: TB_BUSI_SYS_TREE-业务系统树-用户权限关联表-Service实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-21 下午2:37:00
 * 
 */
@Service("tbBusiSysTreeLimitService")
public class TbBusiSysTreeLimitServiceImpl implements TbBusiSysTreeLimitService {
	@Autowired
	private TbBusiSysTreeLimitDao tbBusiSysTreeLimitDao;
	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private PropertyUtil bsTreeIconProp;

	/**
	 * 
	 * @Title: reInitSysTreeLimitByUser
	 * @Description: 重新实例用户权限
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午3:32:34
	 */
	public int reInitSysTreeLimitByUser(List<TbBusiSysTreeLimit> list, String username) {
		return tbBusiSysTreeLimitDao.reInitSysTreeLimitByUser(list, username);
	}

	/**
	 * 
	 * @Title: queryOneTbBusiSysTreeLimit
	 * @Description: 查询一条记录
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public TbBusiSysTreeLimit queryOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		return tbBusiSysTreeLimitDao.queryOneTbBusiSysTreeLimit(obj);
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTreeLimit> queryTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		return tbBusiSysTreeLimitDao.queryTbBusiSysTreeLimit(obj);
	}

	/**
	 * 
	 * @Title: ifTreeNodeHasLimitByUser
	 * @Description: 树节点是否存在用户权限
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 下午2:43:33
	 */
	public boolean ifTreeNodeHasLimitByUser(String treeNodeId, String username) {
		boolean result = false;
		TbBusiSysTreeLimit limitObj = new TbBusiSysTreeLimit();
		limitObj.setUsername(username);
		limitObj.setTreeNodeId(treeNodeId);
		TbBusiSysTreeLimit limitResultObj = queryOneTbBusiSysTreeLimit(limitObj);
		if (limitResultObj != null) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @Title: initBusiSysTreelist
	 * @Description: 实例树业务系统树数据List
	 * @param
	 * @return List<BusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:24:41
	 */
	public List<BusiSysTree> initBusiSysTreeLimitlist(String username, List<TbBusiSysTree> resultList) {
		List<BusiSysTree> list = new ArrayList<BusiSysTree>();
		TbBusiSysTree tempObj = new TbBusiSysTree();
		tempObj.setUsername(username);

		resultList = tbBusiSysTreeService.queryForLimitTree(tempObj);
		for (TbBusiSysTree obj : resultList) {
			BusiSysTree tObj = new BusiSysTree();
			tObj.setId(obj.getId());
			tObj.setName(obj.getName());
			tObj.setType(obj.getType());
			tObj.setEntityId(obj.getBusiId());
			tObj.setIsParent(false);
			// 判断是不是父节点
			// tempObj.setParentId(obj.getId());
			tObj.setPId(obj.getParentId());
			// List<TbBusiSysTree> lst =
			// tbBusiSysTreeService.queryForLimitTree(tempObj);
			// if (lst == null || lst.size() == 0) {
			// tObj.setIsParent(false);
			// } else {
			// tObj.setIsParent(true);
			// }
			// 查询是否具有权限
			boolean ifTreeNodeHasLimit = ifTreeNodeHasLimitByUser(obj.getId(), username);
			if (ifTreeNodeHasLimit) {
				tObj.setChecked(ifTreeNodeHasLimit);
			}
			// 设置图标
			if (obj.getType() == 0) {// 业务中心
				tObj.setNocheck(false);
			} else if (obj.getType() == 1) {// 业务中心
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.center.png"));
				tObj.setNocheck(false);
			} else if (obj.getType() == 2) { // 业务系统
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.png"));
				tObj.setNocheck(false);
			} else if (obj.getType() == 3) { // 应用
				tObj.setIcon(bsTreeIconProp.getString("sys.app.png"));
				tObj.setNocheck(false);
			} else if (obj.getType() == 4) { // 应用部署的主机
				tObj.setIcon(bsTreeIconProp.getString("app.deploy.png"));
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * insertTbBusiSysTreeLimit:权限表中插入一条记录
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	@Override
	public void insertTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		tbBusiSysTreeLimitDao.insertTbBusiSysTreeLimit(obj);
	}

	/**
	 * 
	 * deleteOneTbBusiSysTreeLimit: 删除一个节点的权限信息
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	@Override
	public void deleteOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		tbBusiSysTreeLimitDao.deleteOneTbBusiSysTreeLimit(obj);
	}
}

package com.sitech.basd.yicloud.service.busisystree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.dao.busisystree.TbBusiSysTreeDao;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeDao
 * </p>
 * <p>
 * Description: 业务系统树业务处理类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:44:34
 * 
 */
@Service("tbBusiSysTreeService")
public class TbBusiSysTreeServiceImpl implements TbBusiSysTreeService {
	@Autowired
	private TbBusiSysTreeDao tbBusiSysTreeDao;
	@Autowired
	private PropertyUtil bsTreeIconProp;

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
	public List<TbBusiSysTree> queryForTree(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTree> queryForLimitTree(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.queryForLimitTree(obj);
	}

	/**
	 * 
	 * @Title: insertTbBusiSysTree
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:30
	 */
	public String insertTbBusiSysTree(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.insertTbBusiSysTree(obj);
	}

	/**
	 * 
	 * @Title: initBusiSysTreelist
	 * @Description: 实例树业务系统树权限数据List
	 * @param
	 * @return List<BusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:24:41
	 */
	public List<BusiSysTree> initBusiSysTreelist(String username,
			List<TbBusiSysTree> resultList) {
		List<BusiSysTree> list = new ArrayList<BusiSysTree>();
		TbBusiSysTree tempObj = new TbBusiSysTree();
		tempObj.setUsername(username);
		for (TbBusiSysTree obj : resultList) {
			BusiSysTree tObj = new BusiSysTree();
			tObj.setId(obj.getId());
			tObj.setName(obj.getName());
			tObj.setType(obj.getType());
			tObj.setEntityId(obj.getBusiId());
			// 判断是不是父节点
			tempObj.setParentId(obj.getId());
			List<TbBusiSysTree> lst = queryForTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			}
			// 设置图标
			if (obj.getType() == 0) {// 业务中心
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.center.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 1) { // 业务系统
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 2) { // 应用
				tObj.setIcon(bsTreeIconProp.getString("sys.app.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 3) { // 应用部署的主机
				tObj.setIcon(bsTreeIconProp.getString("app.deploy.png"));
				tObj.setNocheck(true);
			}
			tObj.setHostIP(obj.getHostIP());
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteTbBusiSysTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	public int deleteTbBusiSysTreeById(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.deleteTbBusiSysTreeById(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 30, 2013 6:54:45 PM
	 */
	public int updateTbBusiSysTreeByObj(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.updateTbBusiSysTreeByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return TbBusiSysTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	public TbBusiSysTree queryBusiCenterSonNodesNum(TbBusiSysTree obj) {
		return tbBusiSysTreeDao.queryBusiCenterSonNodesNum(obj);
	}

	/**
	 * @Title: countByObj
	 * @Description: 统计业务系统下各子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:41:08
	 */
	@Override
	public int countByObj(TbBusiSysTree tbBusiSysTree) {
		// TODO Auto-generated method stub
		return tbBusiSysTreeDao.countByObj(tbBusiSysTree);
	}

	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	@Override
	public List<TbBusiSysTree> queryForListByParentIdList(
			TbBusiSysTree tbBusiSysTree) {
		return tbBusiSysTreeDao.queryForListByParentIdList(tbBusiSysTree);
	}
	
	/**
	 * 
	 * @Title: validateDelete
	 * @Description: 验证能否被删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-23 下午7:30:51
	 */
	public Boolean validateDelete(String id,String type){
		boolean result = false;
		TbBusiSysTree sysTreeObj = new TbBusiSysTree();
		sysTreeObj.setParentId(id);
		if(type.equals("0")){
			sysTreeObj.setType(1);
		}else if(type.equals("1")){
			sysTreeObj.setType(2);
		}else if(type.equals("2")){
			sysTreeObj.setType(3);
		}
		List lst = tbBusiSysTreeDao.queryForTree(sysTreeObj);
		if(lst==null || lst.size()<1){
			result = true;
		}
		return result;
	}
}

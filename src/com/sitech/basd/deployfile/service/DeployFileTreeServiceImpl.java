package com.sitech.basd.deployfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.deployfile.dao.DeployFileTreeDao;
import com.sitech.basd.deployfile.domain.DeployFileTreeObj;
import com.sitech.basd.deployfile.util.DeployFileConstant;
import com.sitech.basd.util.PropertyUtil;

/**
 * 
 * <p>
 * Title: DeployFileTreeServiceImpl
 * </p>
 * <p>
 * Description: 部署文件树备份和回滚操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-12-16 下午12:11:52
 * 
 */
@Service("deployFileTreeService")
public class DeployFileTreeServiceImpl implements DeployFileTreeService {
	@Autowired
	private DeployFileTreeDao deployFileTreeDao;
	@Autowired
	private PropertyUtil deployFileTreeIconProp;

	/**
	 * 
	 * @Title: initTreelist
	 * @Description: 初始化树节点
	 * @param
	 * @return List<DeployFileTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午2:00:35
	 */
	@Override
	public List<DeployFileTreeObj> initTreelist(List<DeployFileTreeObj> resultList) {
		List<DeployFileTreeObj> list = new ArrayList<DeployFileTreeObj>();
		DeployFileTreeObj tempObj = new DeployFileTreeObj();
		for (DeployFileTreeObj obj : resultList) {
			DeployFileTreeObj tObj = new DeployFileTreeObj();
			tObj.setId(obj.getId());
			tObj.setName(obj.getName());
			tObj.setFile_url(obj.getFile_url());
			tObj.setParent_id(obj.getParent_id());
			tObj.setState(obj.getState());
			tObj.setOrder_id(obj.getOrder_id());
			tempObj.setParent_id(obj.getId());
			tempObj.setOrder_id(obj.getOrder_id());
			// 判断是不是父节点
			List<DeployFileTreeObj> lst = queryForTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			}
			// 设置图标
			if (DeployFileConstant.ROOT.equals(obj.getType())) {// type=0,父节点
				tObj.setIcon(deployFileTreeIconProp.getString("computer"));
			} else if (DeployFileConstant.FOLDER.equals(obj.getType())) {// type=1,文件夹节点
				tObj.setIcon(deployFileTreeIconProp.getString("folder"));
			} else if (DeployFileConstant.PAGE.equals(obj.getType())) {// type=2,文件节点
				if (DeployFileConstant.PAGE_ADD.equals(obj.getState())) {
					tObj.setIcon(deployFileTreeIconProp.getString("page_add"));// 文件增加
				} else if (DeployFileConstant.PAGE_DELETE.equals(obj.getState())) {
					tObj.setIcon(deployFileTreeIconProp.getString("page_delete"));// 文件删除
				} else if (DeployFileConstant.PAGE_EDIT.equals(obj.getState())) {
					tObj.setIcon(deployFileTreeIconProp.getString("page_edit"));// 文件修改
				}
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<DeployFileTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午1:43:17
	 */
	@Override
	public List<DeployFileTreeObj> queryForTree(DeployFileTreeObj obj) {
		return deployFileTreeDao.queryForTree(obj);
	}
}

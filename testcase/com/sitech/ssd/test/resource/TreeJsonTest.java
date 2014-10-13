package com.sitech.ssd.test.resource;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.util.AppContext;

/**
 * 
 * <p>
 * Title: TreeJsonTest
 * </p>
 * <p>
 * Description: 树形Json数据生成
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-7 上午8:55:14
 * 
 */
public class TreeJsonTest {

	// @Test
	public void testJsonTree() throws SQLException {
		UnitedTreeDao unitedTreeDao = AppContext.getBean("unitedTreeDao", UnitedTreeDao.class);
		String parentId = "1";
		UnitedTreeObj result = recursiveTree(parentId, unitedTreeDao);
		System.out.println(JacksonUtil.toJson(result));
	}

	public UnitedTreeObj recursiveTree(String parentId, UnitedTreeDao unitedTreeDao)
			throws SQLException {
		UnitedTreeObj node = new UnitedTreeObj();
		node.setId(parentId);
		UnitedTreeObj nodeResult = unitedTreeDao.queryForUnitedTree(node).get(0);
		UnitedTreeObj childNode = new UnitedTreeObj();
		childNode.setParent_id(parentId);
		List<UnitedTreeObj> childTreeNodes = unitedTreeDao.queryForUnitedTree(childNode);
		// 遍历子节点
		for (UnitedTreeObj treeNode : childTreeNodes) {
			UnitedTreeObj n = recursiveTree(treeNode.getId(), unitedTreeDao); // 递归
			//nodeResult.getNodeList().add(n);
		}
		return nodeResult;
	}

	@Test
	public void testString() {
		String name = "123@huojla@1212";
		System.out.println(name.substring(name.lastIndexOf("@") + 1));
	}
}

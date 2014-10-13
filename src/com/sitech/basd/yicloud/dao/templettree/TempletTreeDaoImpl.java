package com.sitech.basd.yicloud.dao.templettree;

import java.io.IOException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;

public class TempletTreeDaoImpl extends BaseDao implements TempletTreeDao {
	
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询目录，模板生成树
	 * @param
	 * @return TempletTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 */
	@Override
	public List<TempletTreeObj> queryForTree(TempletTreeObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TempletTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("TempletTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: creatMenu
	 * @Description: 创建子菜单
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public int creatMenu(TempletTreeObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TempletTree.insertByobj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TempletTree.insertByobj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	

	/**
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除子菜单
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws  
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public int deleteMenu(TempletTreeObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("TempletTree.deleteByObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TempletTree.deleteByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: updateObj
	 * @Description: 修改数据
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws  
	 * @createtime Oct 10, 2012 11:42:00 AM
	 */
	public int updateObj(TempletTreeObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().update("TempletTree.updateObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TempletTree.updateObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
}

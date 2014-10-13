package com.sitech.basd.yicloud.service.templettree;

import java.util.List;

import com.sitech.basd.yicloud.dao.templettree.TempletTreeDao;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;

public class TempletTreeServiceImpl implements TempletTreeService {
	
	private TempletTreeDao templetTreeDao;
	
	public void setTempletTreeDao(TempletTreeDao templetTreeDao) {
		this.templetTreeDao = templetTreeDao;
	}

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
	public List<TempletTreeObj> queryForTree(TempletTreeObj obj){
		return templetTreeDao.queryForTree(obj);
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
		return templetTreeDao.creatMenu(obj);
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
		return templetTreeDao.deleteMenu(obj);
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
		return templetTreeDao.updateObj(obj);
	}
}

package com.sitech.basd.sxcloud.workflow.dao.templet;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.templet.TempletInfoObj;

/**
 * 
 * @Title: 模板操作类
 * @Copyright: Copyright (c) 2012-3
 * @Company: si-tech
 * @author taoxue
 * @version 1.0
 */
public interface TempletInfoDao {
	/**
	 * @Title:保存修改后的模板信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public int saveTempletInfo(TempletInfoObj obj);

	/**
	 * @Title:删除模板信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public int deleteTempletInfo(String requestNo);

	/**
	 * @Title:查询模板列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public List<TempletInfoObj> queryTempletList(TempletInfoObj obj);

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public TempletInfoObj queryByObj(TempletInfoObj obj);

	/**
	 * @Title:根据资源类别编号查询资源信息
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public TempletInfoObj queryResourceListInfo(String num);

}

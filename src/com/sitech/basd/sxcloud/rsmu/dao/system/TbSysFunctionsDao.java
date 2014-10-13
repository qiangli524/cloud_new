package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;

public interface TbSysFunctionsDao {
	/**
	 * @Title:根据模块ID模糊查询功能列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryTbSysFunctionsObjByFUNCID(FuncRoleObj obj);

	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNaviParam(List getNaviParam);

	/**
	 * @Title:根据功能部分信息查询匹配的所有功能模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysFunctionsObj obj);

	/**
	 * @Title:查询出具体功能管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysFunctionsObj queryByObj(TbSysFunctionsObj obj);

	/**
	 * @Title:更新功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysFunctionsObj obj);

	/**
	 * @Title:删除功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysFunctionsObj obj);

	/**
	 * @Title:插入功能信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysFunctionsObj obj);

	/**
	 * @Title:通过模块ID找出对应的模块路径
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String FunctionModule(TbSysFunctionsObj obj);

	/**
	 * 
	 * @Title: queryThreeFuncNode
	 * @Description: 查询所有三级节点
	 * @param
	 * @return List<TbSysFunctionsObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 上午10:48:25
	 */
	public List<TbSysFunctionsObj> queryThreeFuncNode(String funcid);
}

package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysFunctionsDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysGroupfuncDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGroupfuncObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class FunctionsServiceImpl extends BaseService implements FunctionsService {

	private static List SysFunList = null;

	/**
	 * @Title:删除功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysFunctionsObj obj) {
		// TODO Auto-generated method stub
		return tbSysFunctionsDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入功能信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysFunctionsObj obj) {
		// TODO Auto-generated method stub
		return tbSysFunctionsDao.insertByObj(obj);
	}

	/**
	 * @Title:查询出具体功能管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysFunctionsObj queryByObj(TbSysFunctionsObj obj) {
		// TODO Auto-generated method stub
		return tbSysFunctionsDao.queryByObj(obj);
	}

	/**
	 * @Title:根据功能部分信息查询匹配的所有功能模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysFunctionsObj obj) {
		// TODO Auto-generated method stub
		return tbSysFunctionsDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysFunctionsObj obj) {
		// TODO Auto-generated method stub
		return tbSysFunctionsDao.updateByObj(obj);
	}

	/**
	 * @Title:根据部分用户组功能关联信息查询匹配的所有用户组功能关联信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGroupfuncObj obj) {
		return tbSysGroupfuncDao.queryForListByObj(obj);
	}

	/**
	 * @Title:插入用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGroupfuncObj obj) {
		return tbSysGroupfuncDao.insertByObj(obj);
	}

	/**
	 * @Title:删除用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGroupfuncObj obj) {
		return tbSysGroupfuncDao.deleteByObj(obj);
	}

	/**
	 * @Title:通过模块ID找出对应的模块路径
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String FunctionModule(String str, boolean bl) {
		String FunModName = "";
		String[] module = new String[3];
		String[] count = new String[3];
		TbSysFunctionsObj FuncObj = new TbSysFunctionsObj();
		if (SysFunList == null) {
			SysFunList = tbSysFunctionsDao.queryForListByObj(FuncObj);
		} else {
			if (bl == true) {
				SysFunList = tbSysFunctionsDao.queryForListByObj(FuncObj);
			}
		}
		if (!"".equals(str)) {
			int length = str.length();
			if (length >= 2 && length < 4) {
				module[0] = str.substring(0, 2) + "00000000";
				module[1] = str.substring(0, 2) + "xxxxxxxx";
				module[2] = str.substring(0, 2) + "xxxxxxxx";
			}
			if (length >= 4 && length < 6) {
				module[0] = str.substring(0, 2) + "00000000";
				module[1] = str.substring(0, 4) + "000000";
				module[1] = str.substring(0, 4) + "xxxxxxxx";
			}
			if (length >= 6) {
				module[0] = str.substring(0, 2) + "00000000";
				module[1] = str.substring(0, 4) + "000000";
				module[2] = str.substring(0, 6) + "0000";
			}
			for (int i = 0; i < SysFunList.size(); i++) {
				FuncObj = (TbSysFunctionsObj) SysFunList.get(i);
				if (module.length == 3) {
					for (int j = 0; j < module.length; j++) {
						if (FuncObj.getFUNCID().equals(module[0])) {
							count[0] = FuncObj.getFUNNAME();
							break;
						}
						if (FuncObj.getFUNCID().equals(module[1])) {
							count[1] = FuncObj.getFUNNAME();
							break;
						}
						if (FuncObj.getFUNCID().equals(module[2])) {
							count[2] = FuncObj.getFUNNAME();
							break;
						}
					}

				}
			}
		}
		if (count[1] == null && count[2] == null) {
			FunModName = count[0];
		} else {
			FunModName = count[0] + "->" + count[1] + "->" + count[2];
		}

		return FunModName;
	}

	/**
	 * @Title:根据模块ID模糊查询功能列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryTbSysFunctionsObjByFUNCID(FuncRoleObj obj) {
		return tbSysFunctionsDao.queryTbSysFunctionsObjByFUNCID(obj);
	}

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
	public List<TbSysFunctionsObj> queryThreeFuncNode(String funcid) {
		return tbSysFunctionsDao.queryThreeFuncNode(funcid);
	}

	TbSysFunctionsDao tbSysFunctionsDao;
	TbSysGroupfuncDao tbSysGroupfuncDao;

	public void setTbSysFunctionsDao(TbSysFunctionsDao tbSysFunctionsDao) {
		this.tbSysFunctionsDao = tbSysFunctionsDao;
	}

	public void setTbSysGroupfuncDao(TbSysGroupfuncDao tbSysGroupfuncDao) {
		this.tbSysGroupfuncDao = tbSysGroupfuncDao;
	}

	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据传入的属性集合查询符合条件的集合
	 * @param
	 * @return List<TbSysGroupfuncObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午3:12:20
	 */
	@Override
	public List<TbSysGroupfuncObj> queryForListUseIn(TbSysGroupfuncObj funcObj) {
		return tbSysGroupfuncDao.queryForListUseIn(funcObj);
	}

}

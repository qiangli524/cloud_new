package com.sitech.ssd.ah.boss.dao.procedure;

import java.util.List;

import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;

/**
 * <p>
 * Title: BossProcedureDao
 * </p>
 * <p>
 * Description: boss应用进程DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-1 下午4:22:54
 * 
 */
public interface BossProcedureDao {
	/**
	 * @Title: queryBossProcedure
	 * @Description: 查询程序列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 下午4:23:15
	 */
	public List queryBossProcedure(ProcedureObj obj);

	/**
	 * @Title: addProcedure
	 * @Description: 添加程序
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 下午4:23:56
	 */
	public int insertByObj(ProcedureObj obj);

	/**
	 * @Title: queryForIpAndAppCount
	 * @Description: 检查IP和进程标识是否联合唯一
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 下午3:56:50
	 */
	public int queryForIpAndAppCount(ProcedureObj obj);

	/**
	 * @Title: queryProcedureByUid
	 * @Description: 查询单个 对象
	 * @param
	 * @return ProcedureObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午9:11:51
	 */
	public ProcedureObj queryProcedureByUid(ProcedureObj obj);

	/**
	 * @Title: updateProcedureObj
	 * @Description: 修改程序对象
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午10:40:58
	 */
	public int updateProcedureObj(ProcedureObj obj);

	/**
	 * @Title: unloadProcedureObj
	 * @Description: 卸载程序
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午11:03:48
	 */
	public int unloadProcedureObj(ProcedureObj obj);

	/**
	 * @Title: queryProcedureListByCluAndPool
	 * @Description: 根据集群和程序池，得到旗下进程
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-3 下午2:30:51
	 */
	public List queryProcedureListByCluAndPool(ProcedureObj obj);
}

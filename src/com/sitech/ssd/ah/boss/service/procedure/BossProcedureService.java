package com.sitech.ssd.ah.boss.service.procedure;

import java.util.List;

import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;

/**
 * <p>
 * Title: BossProcedureService
 * </p>
 * <p>
 * Description: boss应用进程添加服务接口
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
 * @createtime 2014-8-1 下午4:17:06
 * 
 */
public interface BossProcedureService {
	/**
	 * @Title: queryBossProcedure
	 * @Description: 用于查询程序列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 下午4:14:50
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
	 * @createtime 2014-8-1 下午4:16:41
	 */
	public String saveProcedure(ProcedureObj obj);

	/**
	 * @Title: checkIsExist
	 * @Description: 检查IP和进程标识是否联合唯一
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-2 下午3:55:08
	 */
	public String checkIsExist(ProcedureObj obj);

	/**
	 * @Title: queryBossProcedureByUid
	 * @Description: 查找程序对象
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午9:08:37
	 */
	public ProcedureObj queryBossProcedureByUid(ProcedureObj obj);

	/**
	 * @Title: updateProcedureObj
	 * @Description: 修改程序对象
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午10:40:07
	 */
	public String updateProcedureObj(ProcedureObj obj);

	/**
	 * @Title: unloadProcedureObj
	 * @param: 卸载程序
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-4 上午11:02:42
	 */
	public String unloadProcedureObj(ProcedureObj obj);

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

package com.sitech.ssd.ah.paas.dao.entity;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;

/**
 * 
 * <p>
 * Title: PaasEntityDao
 * </p>
 * <p>
 * Description: 实体相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-18 上午10:04:39
 * 
 */
public interface PaasEntityDao {
	/**
	 * 
	 * @Title: queryForEntityList
	 * @Description: 查询实体列表
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午10:10:07
	 */
	public List<PaasEntityObj> queryForEntityList(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询对象
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午10:10:27
	 */
	public PaasEntityObj queryByObj(PaasEntityObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午10:10:38
	 */
	public int insertByObj(PaasEntityObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午10:10:47
	 */
	public int updateByObj(PaasEntityObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 上午10:10:55
	 */
	public int deleteByObj(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryForEntityProp
	 * @Description: 查询信息(关联tb_paas_entity_prop)
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 下午4:32:16
	 */
	public List<PaasEntityObj> queryForEntityProp(PaasEntityObj obj);
	
	/**
	 * 
	 * @Title: queryDistinctEntityNameByType
	 * @Description: 查询不重复的实体名字通过类型
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-2 下午3:27:06
	 */
	public List<PaasEntityObj> queryDistinctEntityNameByType(PaasEntityObj obj);

}

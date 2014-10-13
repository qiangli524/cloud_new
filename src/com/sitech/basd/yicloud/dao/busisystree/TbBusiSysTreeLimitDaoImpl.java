package com.sitech.basd.yicloud.dao.busisystree;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeLimitDaoImpl
 * </p>
 * <p>
 * Description: TB_BUSI_SYS_TREE-业务系统树-用户权限关联表-Dao实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午3:15:49
 * 
 */
@Repository("tbBusiSysTreeLimitDao")
public class TbBusiSysTreeLimitDaoImpl extends BaseDao implements TbBusiSysTreeLimitDao {
	/**
	 * 
	 * @Title: reInitSysTreeLimitByUser
	 * @Description: 重新实例用户权限
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午3:32:34
	 */
	public int reInitSysTreeLimitByUser(List<TbBusiSysTreeLimit> list, String username) {
		int result = 0;
		SqlMapClient sqlMapClient = getSqlMap();
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.delete("TbBusiSysTreeLimit.deleteTbBusiSysTreeLimitByUser", username);
			sqlMapClient.startBatch();
			for (TbBusiSysTreeLimit obj : list) {
				obj.setId(UUIDGenerator.getUUID());
				getSqlMap().insert("TbBusiSysTreeLimit.insertTbBusiSysTreeLimit", obj);
			}
			result = sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();
		} catch (SQLException e) {
			result = -1;
			LogHelper.error("TbBusiSysTree.queryForTree:" + e.getMessage() + getClass().getName());
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				// just endTransaction
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTreeLimit> queryTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		List<TbBusiSysTreeLimit> lst = null;
		try {
			lst = (List<TbBusiSysTreeLimit>) getSqlMap()
					.queryForList("TbBusiSysTreeLimit.queryTbBusiSysTreeLimit", obj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTreeLimit.queryTbBusiSysTreeLimit:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryOneTbBusiSysTreeLimit
	 * @Description: 查询一条记录
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public TbBusiSysTreeLimit queryOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		TbBusiSysTreeLimit limit = null;
		try {
			limit = (TbBusiSysTreeLimit) getSqlMap().queryForObject("TbBusiSysTreeLimit.queryTbBusiSysTreeLimit", obj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSysTreeLimit.queryTbBusiSysTreeLimit:" + e.getMessage() + getClass().getName());
		}
		return limit;
	}

	/**
	 * 
	 * insertTbBusiSysTreeLimit:权限表中插入一条记录
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	@Override
	public void insertTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		obj.setId(RandomUUID.getUuid());
		getSqlMapClientTemplate().insert("TbBusiSysTreeLimit.insertTbBusiSysTreeLimit", obj);
	}

	/**
	 * 
	 * deleteOneTbBusiSysTreeLimit: 删除一个节点的权限信息
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	@Override
	public void deleteOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj) {
		getSqlMapClientTemplate().delete("TbBusiSysTreeLimit.deleteOneTbBusiSysTreeLimit", obj);
	}
}

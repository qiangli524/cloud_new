package com.sitech.shop.dao.publicIP;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.utils.randomid.RandomUUID;

/**
 * @Title PublicIPDaoImpl
 * @Description 公网ip持久层实现类
 * @author lipp
 * @date 2014-4-25 上午10:46:59
 * @version 1.0
 */
@Repository("publicIPDao")
public class PublicIPDaoImpl extends BaseDao implements PublicIPDao {

	/**
	 * @Title: updateByObj
	 * @Description: 修改公网ip关联关系
	 * @throws
	 * @Date 2014-4-25 上午10:49:03
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public int updateByObj(PublicIPObj ipObj) {
		int rows = getSqlMapClientTemplate().update("publicip.updateByObj", ipObj);
		return rows;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 关联表查询列表
	 * @throws
	 * @Date 2014-4-25 上午10:49:22
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PublicIPObj> queryForListUnionTablesByObj(PublicIPObj ipObj) {
		List<PublicIPObj> list = new ArrayList<PublicIPObj>();
		if (ipObj.getPagination() != null) {
			ipObj.setFIRSTROWNUM(ipObj.getPagination().getFirstRownum());
			ipObj.setPAGESIZE(ipObj.getPagination().getPageSize());
			ipObj.getPagination().setTotalCount(
					((Integer) (getSqlMapClientTemplate().queryForObject(
							"publicip.queryForCountByObj", ipObj))).intValue());
		}
		list = getSqlMapClientTemplate().queryForList("publicip.queryForListUnionTablesByObj",
				ipObj);

		return list;
	}

	/**
	 * @Title: releaseByObj
	 * @Description: 释放ip
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午1:15:50
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public int releaseByObj(PublicIPObj ipObj) {
		int rows = getSqlMapClientTemplate().update("publicip.releaseByObj", ipObj);
		return rows;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询基本列表
	 * @throws
	 * @Date 2014-4-25 下午2:38:49
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PublicIPObj> queryForListByObj(PublicIPObj ipObj) {
		List<PublicIPObj> list = new ArrayList<PublicIPObj>();
		list = getSqlMapClientTemplate().queryForList("publicip.queryForListByObj", ipObj);
		return list;
	}

	/**
	 * @Title: updateBatch
	 * @Description: 批量更新
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:05:46
	 * @author lipp
	 * @param list
	 * @return
	 */
	@Override
	public void updateBatch(final List<PublicIPObj> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<PublicIPObj>() {

			@Override
			public PublicIPObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
				arg0.startBatch();
				for (PublicIPObj publicIPObj : list) {
					arg0.update("publicip.updateByObj", publicIPObj);
				}
				arg0.executeBatch();
				return null;
			}
		});
	}

	/**
	 * @Title: releaseBatch
	 * @Description: 批量解除
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:18:45
	 * @author lipp
	 * @param list
	 * @return
	 */
	@Override
	public void releaseBatch(final List<PublicIPObj> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<PublicIPObj>() {

			@Override
			public PublicIPObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
				arg0.startBatch();
				for (PublicIPObj publicIPObj : list) {
					arg0.delete("publicip.deleteByObj", publicIPObj);
				}
				arg0.executeBatch();
				return null;
			}
		});
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午2:46:18
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public void insertByObj(PublicIPObj ipObj) {
		ipObj.setId(RandomUUID.getUuid());
		Object obj = getSqlMapClientTemplate().insert("publicip.insertByObj", ipObj);
	}

	/**
	 * @Title:insertForBatch
	 * @Description: 批量插入
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午2:46:18
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	public void insertForBatch(final List<PublicIPObj> list) throws Exception {
		try {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback<PublicIPObj>() {
				@Override
				public PublicIPObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
					arg0.startBatch();
					for (PublicIPObj publicIPObj : list) {
						publicIPObj.setId(RandomUUID.getUuid());
						arg0.insert("publicip.insertByObj", publicIPObj);
					}
					arg0.executeBatch();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("publicip.insertByObj: " + e.getMessage() + e.getClass().getName());
		}
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @throws
	 * @Date 2014-4-29 下午2:50:00
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public int deleteByObj(PublicIPObj ipObj) {
		int rows = getSqlMapClientTemplate().delete("publicip.deleteByObj", ipObj);
		return rows;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: ucancelBindByObj
	 * </p>
	 * <p>
	 * Description:取消绑定公网IP 更新tb_public_ip intranet_ip = Null
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param ip
	 * @return
	 * @see dao.publicIP.PublicIPDao#ucancelBindByObj(domain.ip.PublicIPObj)
	 */
	@Override
	public int cancelBindByObj(PublicIPObj ip) {
		int rows = getSqlMapClientTemplate().update("publicip.ucancelBindByObj", ip);
		return rows;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description: 根据地域，用户，查询公网IP数量
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see dao.publicIP.PublicIPDao#queryForCount(domain.ip.PublicIPObj)
	 */
	@Override
	public Integer queryForCount(PublicIPObj obj) {
		return (Integer) (getSqlMapClientTemplate().queryForObject("publicip.queryForCountByObj",
				obj));

	}

	/**
	 * 
	 * @Title: queryForIntranetIp
	 * @Description: 查询某一虚拟机对应的需要转换为公网的内网IP地址
	 * @param
	 * @return List<PublicIPObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-12 上午11:26:38
	 */
	public List<PublicIPObj> queryForIntranetIp(PublicIPObj obj) {
		List<PublicIPObj> list = new ArrayList<PublicIPObj>();
		list = getSqlMapClientTemplate().queryForList("publicip.queryForIntranetIp", obj);
		return list;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateStatusByIp
	 * </p>
	 * <p>
	 * Description:根据公网iP地址修改IP的状态
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param publicIPObj
	 * @return
	 * @see dao.publicIP.PublicIPDao#updateStatusByIp(domain.ip.PublicIPObj)
	 */
	@Override
	public int updateStatusByIp(PublicIPObj publicIPObj) {
		int rows = getSqlMapClientTemplate().update("publicip.updateStatusByIp", publicIPObj);
		return rows;
	}
	/** (非 Javadoc) 
	* <p>Title: queryForObj</p> 
	* <p>Description: 查询一条记录</p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param ipObj
	* @return 
	* @see dao.publicIP.PublicIPDao#queryForObj(domain.ip.PublicIPObj) 
	*/
	@Override
	public PublicIPObj queryForObj(PublicIPObj ipObj) {
		return (PublicIPObj)getSqlMapClientTemplate().queryForObject("publicip.queryForObj", ipObj);
	}
}

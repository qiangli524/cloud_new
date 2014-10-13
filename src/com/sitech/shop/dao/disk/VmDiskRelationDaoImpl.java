package com.sitech.shop.dao.disk;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.domain.disk.VmDiskRelationObj;

/**
 * <p>
 * Title: VmDiskRelationDaoImpl
 * </p>
 * <p>
 * Description: 虚拟机与虚拟磁盘关系
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-4-18 下午3:27:45
 * 
 */
@Repository("vmDiskRelationDao")
public class VmDiskRelationDaoImpl extends BaseDao implements VmDiskRelationDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-4-18 下午3:26:10
	 */
	@Override
	public void insertByObj(VmDiskRelationObj relationObj) {
		getSqlMapClientTemplate().insert("vmDiskRelation.insertByObj", relationObj);
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 下午3:26:06
	 */
	@Override
	public int deleteByObj(VmDiskRelationObj relationObj) {
		int ret = -1;
		ret = getSqlMapClientTemplate().delete("vmDiskRelation.deleteByObj", relationObj);
		return ret;
	}

	/**
	 * 
	 * @Title: insertForBatch
	 * @Description: 批量插入数据
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:46:32
	 */
	public void insertForBatch(final List<VmDiskRelationObj> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<VmDiskRelationObj>() {

			@Override
			public VmDiskRelationObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
				arg0.startBatch();
				for (VmDiskRelationObj disk : list) {
					arg0.insert("vmDiskRelation.insertByObj", disk);
				}
				arg0.executeBatch();
				return null;
			}
		});
	}
}

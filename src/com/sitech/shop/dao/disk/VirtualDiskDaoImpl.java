package com.sitech.shop.dao.disk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.domain.disk.VirtualDiskObj;

@Repository("virtualDiskDao")
public class VirtualDiskDaoImpl extends BaseDao implements VirtualDiskDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<VirtualDiskObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-17 下午3:41:59
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualDiskObj> queryForListByObj(VirtualDiskObj virtualDiskObj) {
		List<VirtualDiskObj> list = new ArrayList<VirtualDiskObj>();
		if (virtualDiskObj.getPagination() != null) {
			virtualDiskObj.setFIRSTROWNUM(virtualDiskObj.getPagination().getFirstRownum());
			virtualDiskObj.setPAGESIZE(virtualDiskObj.getPagination().getPageSize());
			virtualDiskObj.getPagination().setTotalCount(
					((Integer) getSqlMapClientTemplate().queryForObject(
							"VirtualDisk.queryForCountByObj", virtualDiskObj)).intValue());
		}
		list = getSqlMapClientTemplate().queryForList("VirtualDisk.queryForListByObj",
				virtualDiskObj);
		return list;
	}

	/**
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return List<VirtualDiskObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-17 下午3:41:59
	 */
	public VirtualDiskObj queryByObj(VirtualDiskObj virtualDiskObj) {
		VirtualDiskObj result = null;
		List<VirtualDiskObj> list = queryForListByObj(virtualDiskObj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条快照信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-4-10 下午7:31:34
	 */
	public void insertByObj(VirtualDiskObj obj) {
		getSqlMapClientTemplate().insert("VirtualDisk.insertByObj", obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-4-10 下午7:31:34
	 */
	public void insertForBatch(final List<VirtualDiskObj> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<VirtualDiskObj>() {

			@Override
			public VirtualDiskObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
				arg0.startBatch();
				for (VirtualDiskObj disk : list) {
					arg0.insert("VirtualDisk.insertByObj", disk);
				}
				arg0.executeBatch();
				return null;
			}
		});
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:33:23
	 */
	public int deleteByObj(VirtualDiskObj obj) {
		int ret = 0;
		ret = getSqlMapClientTemplate().delete("VirtualDisk.deleteByObj", obj);
		return ret;
	}

	/**
	 * @Title: queryForListDetail
	 * @Description: 查询详细列表
	 * @param
	 * @return List<VirtualDiskObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 上午9:19:20
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualDiskObj> queryForListDetail(VirtualDiskObj virtualDiskObj) {
		List<VirtualDiskObj> list = new ArrayList<VirtualDiskObj>();
		list = getSqlMapClientTemplate().queryForList("VirtualDisk.queryForListDetail",
				virtualDiskObj);
		return list;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @return void
	 * @throws
	 * @Date 2014-4-24 下午3:44:41
	 * @author lipp
	 * @param diskObj
	 * @throws Exception
	 */
	@Override
	public int updateByObj(VirtualDiskObj diskObj) {
		int ret = -1;
		ret = getSqlMapClientTemplate().update("VirtualDisk.updateByObj", diskObj);
		return ret;
	}

	/**
	 * @Title: updateByPath
	 * @Description: 根据路径更新
	 * @return void
	 * @throws
	 * @Date 2014-4-24 下午7:24:17
	 * @author lipp
	 * @param virtualDiskObj
	 * @throws Exception
	 */
	@Override
	public int updateByPath(VirtualDiskObj virtualDiskObj) {
		int ret = 0;
		ret = getSqlMapClientTemplate().update("VirtualDisk.updateByPath", virtualDiskObj);
		return ret;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see com.sitech.basd.resource.dao.united.disk.VirtualDiskDao#queryForCount(com.sitech.basd.resource.domain.united.disk.VirtualDiskObj)
	 */
	@Override
	public Integer queryForCount(VirtualDiskObj obj) {
		return (Integer) (getSqlMapClientTemplate().queryForObject(
				"VirtualDisk.queryForCountByObj", obj));
	}

}

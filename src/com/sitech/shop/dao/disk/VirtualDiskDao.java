package com.sitech.shop.dao.disk;

import java.util.List;

import com.sitech.shop.domain.disk.VirtualDiskObj;

public interface VirtualDiskDao {

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
	public List<VirtualDiskObj> queryForListByObj(VirtualDiskObj virtualDiskObj);

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
	public VirtualDiskObj queryByObj(VirtualDiskObj virtualDiskObj);

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
	public void insertByObj(VirtualDiskObj obj);

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
	public void insertForBatch(List<VirtualDiskObj> list);

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
	public int deleteByObj(VirtualDiskObj obj);

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
	public List<VirtualDiskObj> queryForListDetail(VirtualDiskObj virtualDiskObj);

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
	public int updateByObj(VirtualDiskObj diskObj);

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
	public int updateByPath(VirtualDiskObj virtualDiskObj);

	/**
	 * @throws SQLException
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(VirtualDiskObj obj);
}
